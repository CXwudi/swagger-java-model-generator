package mikufan.cx.generate.store_info;


import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import mikufan.cx.generate.class_gen.ConcreteClassGenerator;
import mikufan.cx.generate.class_gen.GenericClassGenerator;

import java.util.List;

@Builder
public class ClassInfo {

  @Singular @NonNull
  private final List<FieldInfo> fields;
  @NonNull
  private final String clazz;

  /**
   * this mean the generic class that was declared in model.txt like ex:
   * PartialFindResult[SongInListForApiContract] {
   *
   * in above example, SongInListForApiContract is the generic clazz
   */
  private String genericClazz;

  /**
   * once finished filling the info through builder, you can call this to generate the class definition
   * @return well-written class definition
   */
  public String toClassDefinition(){
    if (genericClazz != null){
      return new GenericClassGenerator().generateClass(fields, clazz, genericClazz);
    } else {
      return new ConcreteClassGenerator().generateClass(fields, clazz, genericClazz);
    }
  }
}
