package mikufan.cx.generate;


import static mikufan.cx.generate.Action.*;

public class ActionDecider {


  public static Action whatNext(String line){
    if (line.contains("{") && line.contains("[") && line.contains("]")){
      return NEW_GENERIC_CLASS;
    } else if (line.contains("{")){
      return NEW_CLASS;
    } else if (line.contains("}")){
      return END_CLASS;
    } else {
      return FIELD;
    }
  }
}
