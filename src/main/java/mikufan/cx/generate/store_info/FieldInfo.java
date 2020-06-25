package mikufan.cx.generate.store_info;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data @Builder
public class FieldInfo {
  private String type;
  private String name;
  private String modifier;
  @Singular
  private List<String> annotations;

}
