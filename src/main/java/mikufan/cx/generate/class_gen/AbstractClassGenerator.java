package mikufan.cx.generate.class_gen;

import lombok.Getter;
import lombok.NoArgsConstructor;
import mikufan.cx.generate.store_info.FieldInfo;

import java.util.List;

@Getter @NoArgsConstructor
public abstract class AbstractClassGenerator {
  /**
   * stores import
   */
  protected StringBuilder beforeClassSb = new StringBuilder();
  /**
   * stores declaration and class annotation
   */
  protected StringBuilder classDeclarationSb = new StringBuilder();
  /**
   * store field declaration
   */
  protected StringBuilder fieldSb = new StringBuilder();
  /**
   * store constructor declaration
   */
  protected StringBuilder constructorSb = new StringBuilder();

  public abstract String generateClass(
      List<FieldInfo> fields,
      String clazz,
      String genericClazz);

}
