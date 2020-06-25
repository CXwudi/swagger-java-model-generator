package mikufan.cx.generate.class_gen;

import lombok.SneakyThrows;
import mikufan.cx.generate.appender.CommonAppender;
import mikufan.cx.generate.store_info.FieldInfo;
import org.apache.logging.log4j.util.Strings;
import org.eclipse.collections.api.factory.Lists;

import java.util.List;

public class GenericClassGenerator extends AbstractClassGenerator{
  private static final String GENERIC_DECLARED_TYPE = "T";

  @Override
  public String generateClass(
      List<FieldInfo> fields,
      String clazz,
      String genericClazz //should ignore
  ) {
    generateBeforeClass(beforeClassSb);
    generateClassDeclaration(clazz, classDeclarationSb);
    generateFields(fields, genericClazz, fieldSb);
    generateAllArgConstructor(fields, clazz, genericClazz, constructorSb);
    return finalGeneration(beforeClassSb, classDeclarationSb, fieldSb, constructorSb);

  }

  /**
   * //auto-generated ...
   * package ...
   *
   * import ...
   * ...
   *
   */
  protected String generateBeforeClass(StringBuilder beforeClassSb) {
    CommonAppender.addComments(beforeClassSb);
    CommonAppender.addPackage(beforeClassSb, "dummy_package");
    beforeClassSb.append(Strings.LINE_SEPARATOR);
    CommonAppender.addEclispeCollAndLombokImport(beforeClassSb);
    beforeClassSb.append(Strings.LINE_SEPARATOR);

    return beforeClassSb.toString();
  }

  /**
   * \@Annotation
   * public class Clazz<T> {
   *
   *
   */
  protected String generateClassDeclaration(String clazz, StringBuilder classDeclarationSb) {
    CommonAppender.addBasicLombokAnnotationOnClass(classDeclarationSb);
    classDeclarationSb.append("@Builder\n")
    ;//make sure the constructor is generated
    return classDeclarationSb
        .append("public class ").append(clazz.strip()).append("<").append(GENERIC_DECLARED_TYPE).append(">").append(" {")
        .append(Strings.LINE_SEPARATOR).append(Strings.LINE_SEPARATOR).toString();
  }

  /**
   *   private Type field\;
   *
   *
   */
  @SneakyThrows
  protected String generateFields(List<FieldInfo> fields, String genericClass, StringBuilder fieldSb) {
    for (int i = 0; i < fields.size(); i++) {
      var field = fields.get(i);
      var fixedType = getFixedType(genericClass, field.getType());
      fieldSb.append("  ").append(field.getModifier()).append(" ").append(fixedType).append(" ").append(field.getName())
          .append(';').append(Strings.LINE_SEPARATOR).append(Strings.LINE_SEPARATOR);
    }

    return fieldSb.toString();
  }

  private String getFixedType(String genericClass, String type) {
    return type.contains(genericClass)
        ? type.replace(genericClass, GENERIC_DECLARED_TYPE)
        : type;
  }


  protected String generateAllArgConstructor(List<FieldInfo> fields, String clazz, String genericClass, StringBuilder constructorSb) {
    //@JsonCreator
    constructorSb.append("  @JsonCreator\n");
    //public Clazz
    constructorSb.append("  ").append("public ").append(clazz).append(" ");
    var fieldsStream = Lists.immutable.withAll(fields);

    /*
    (
          @JsonProperty("field") Clazz field,
          ...
          ...)

     */
    var paramsString = fieldsStream.collect(field ->
        new StringBuilder("      ").append("@JsonProperty(\"").append(field.getName()).append("\") ")
        .append(getFixedType(genericClass, field.getType())).append(" ").append(field.getName())
    ).makeString("(\n", ", \n", ")");
    constructorSb.append(paramsString);

    /*
      {
        this.field = field;
        ...
        }

     */
    var initialzerBlock = fieldsStream.collect(field ->
        new StringBuilder("    ").append("this.").append(field.getName()).append(" = ").append(field.getName()).append(";")
    ).makeString("{\n", "\n", "\n  }\n");
    constructorSb.append(initialzerBlock);


    return constructorSb.toString();
  }

  protected String finalGeneration(StringBuilder... stringBuilders) {
    var destSb = stringBuilders[0];
    for (int i = 1; i < stringBuilders.length; i++) {
      destSb.append(stringBuilders[i].toString());
    }
    destSb.append("}").append(Strings.LINE_SEPARATOR);
    return destSb.toString();
  }
}
