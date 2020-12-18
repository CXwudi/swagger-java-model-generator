package mikufan.cx.generate.class_gen;

import lombok.SneakyThrows;
import mikufan.cx.generate.store_info.FieldInfo;
import org.apache.logging.log4j.util.Strings;
import org.eclipse.collections.api.factory.Lists;

import java.util.List;
import java.util.Objects;

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
    //generateAllArgConstructor(fields, clazz, genericClazz, constructorSb);
    return finalGeneration(beforeClassSb, classDeclarationSb, fieldSb, constructorSb);

  }

  /**
   * //auto-generated ... <br/>
   * package ... <br/>
   *
   * import ... <br/>
   * ...
   *
   */
  protected String generateBeforeClass(StringBuilder beforeClassSb) {
    commonAppender.addComments(beforeClassSb);
    commonAppender.addPackage(beforeClassSb, "dummy_package");
    beforeClassSb.append(Strings.LINE_SEPARATOR);
    commonAppender.addImportForGeneric(beforeClassSb);
    beforeClassSb.append(Strings.LINE_SEPARATOR);

    return beforeClassSb.toString();
  }

  /**
   * \@Annotation... <br/>
   * public class Clazz<T> {
   *
   *
   */
  protected String generateClassDeclaration(String clazz, StringBuilder classDeclarationSb) {
    commonAppender.addBasicLombokAnnotationOnGenericClass(classDeclarationSb);
    commonAppender.addConstructorLombokAnnotationOnGenericClass(classDeclarationSb);
    return classDeclarationSb
        .append("public class ").append(clazz.strip()).append("<").append(GENERIC_DECLARED_TYPE).append(">").append(" {")
        .append(Strings.LINE_SEPARATOR).append(Strings.LINE_SEPARATOR).toString();
  }

  /**
   *   \@tags
   *   modifier Type field\;
   *
   */
  @SneakyThrows
  protected String generateFields(List<FieldInfo> fields, String genericClass, StringBuilder fieldSb) {
    for (int i = 0; i < fields.size(); i++) {
      var field = fields.get(i);
      var fixedType = getFixedType(genericClass, field.getType());
      var annotations = Lists.immutable.withAll(field.getAnnotations());
      if (annotations.notEmpty()){
        fieldSb.append("  ").append(annotations.makeString(" ")).append(Strings.LINE_SEPARATOR);
      }
      fieldSb.append("  ").append(Objects.toString(field.getModifier(), "")).append(" ").append(fixedType).append(" ").append(field.getName())
          .append(';').append(Strings.LINE_SEPARATOR);
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
    destSb.append(Strings.LINE_SEPARATOR).append("}").append(Strings.LINE_SEPARATOR);
    return destSb.toString();
  }
}
