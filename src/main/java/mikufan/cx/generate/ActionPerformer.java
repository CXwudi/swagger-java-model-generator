package mikufan.cx.generate;
import mikufan.cx.generate.store_info.ClassInfo;

import static mikufan.cx.generate.Action.NEW_CLASS;
import static mikufan.cx.generate.Action.NEW_GENERIC_CLASS;

/**
 * stateful careful <br/>
 * use it by feeding line by line, and then in the last line which is a '}', the class definition is out
 */
public class ActionPerformer {

  private ClassInfo.ClassInfoBuilder classBuilder;
  private Action whatClazz;

  /**
   * performance one of the action to the line
   * @param action the action
   * @param line the input line
   * @return the className if action is NEW_GENERIC_CLASS or NEW_CLASS,
   * or the complete class definition if the action is END_CLASS
   *
   */
  public String performAction(Action action, String line){

    switch (action){
      case NEW_GENERIC_CLASS:
        classBuilder = ClassInfo.builder();
        whatClazz = NEW_GENERIC_CLASS;
        return putGenericTypeClassHeader(line, classBuilder);

      case NEW_CLASS:
        classBuilder = ClassInfo.builder();
        whatClazz = NEW_CLASS;
        return putConcreteClassHeader(line, classBuilder);

      case END_CLASS:
        return endRecordAndStartWritingThisClass(line, classBuilder);

      case FIELD:
        new FieldReader().readFieldAndStore(line, classBuilder, whatClazz);
        return "";
      default:
        return "";

    }
  }


  protected String putGenericTypeClassHeader(String line, ClassInfo.ClassInfoBuilder builder) {

    line = line.trim().replace("] {", "");
    var classes = line.split("\\[");
    var thisClass = classes[0];
    var genericClass = classes[1];
    builder.clazz(thisClass);
    builder.genericClazz(genericClass);
    return thisClass;
  }

  protected String putConcreteClassHeader(String line, ClassInfo.ClassInfoBuilder builder) {
    var className = line.substring(0, line.indexOf("{")).trim();
    builder.clazz(className);
    return className;
  }

  protected String endRecordAndStartWritingThisClass(String line, ClassInfo.ClassInfoBuilder builder) {
    return builder.build().toClassDefinition();
  }
}
