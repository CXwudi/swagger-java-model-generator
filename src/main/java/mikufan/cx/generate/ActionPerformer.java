package mikufan.cx.generate;
import mikufan.cx.generate.appender.FieldAppender;
import mikufan.cx.generate.store_info.ClassInfo;

/**
 * stateful careful
 */
public class ActionPerformer {

  private static ClassInfo.ClassInfoBuilder classBuilder;

  /**
   * performance one of the action to the line
   * @param action the action
   * @param line the input line
   * @return the className if action is NEW_GENERIC_CLASS or NEW_CLASS,
   * or the complete class definition if the action is END_CLASS
   *
   */
  public static String performAction(Action action, String line){

    switch (action){
      case NEW_GENERIC_CLASS:
        classBuilder = ClassInfo.builder();
        return putGenericTypeClassHeader(line, classBuilder);

      case NEW_CLASS:
        classBuilder = ClassInfo.builder();
        return putConcreteClassHeader(line, classBuilder);

      case END_CLASS:
        return endRecordAndStartWritingThisClass(line, classBuilder);

      case FIELD:
        FieldAppender.putField(line, classBuilder);
        return "";
      default:
        return "";

    }
  }


  protected static String putGenericTypeClassHeader(String line, ClassInfo.ClassInfoBuilder builder) {

    line = line.trim().replace("] {", "");
    var classes = line.split("\\[");
    var thisClass = classes[0];
    var genericClass = classes[1];
    builder.clazz(thisClass);
    builder.genericClazz(genericClass);
    return thisClass;
  }

  protected static String putConcreteClassHeader(String line, ClassInfo.ClassInfoBuilder builder) {
    var className = line.substring(0, line.indexOf("{")).trim();
    builder.clazz(className);
    return className;
  }

  protected static String endRecordAndStartWritingThisClass(String line, ClassInfo.ClassInfoBuilder builder) {
    return builder.build().toClassDefinition();
  }
}
