package mikufan.cx.generate.jackson_lombok_test;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;

@ToString @Getter
@Builder(toBuilder = true) @Jacksonized
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class InnerEntity {
  String name;
  int id;
  boolean isActive;
  boolean enabled;

}
