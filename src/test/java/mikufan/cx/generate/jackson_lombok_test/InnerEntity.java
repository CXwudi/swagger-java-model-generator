package mikufan.cx.generate.jackson_lombok_test;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@ToString
@Getter @Builder(toBuilder = true) @Jacksonized
public class InnerEntity {
  private String name;
  private int id;
  private boolean isActive;
  private boolean enabled;

}
