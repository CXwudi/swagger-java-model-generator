package mikufan.cx.generate.jackson_test;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString @NoArgsConstructor
public class InnerEntity {
  @JsonProperty
  String name;
  @JsonProperty
  int id;

  public InnerEntity(
      String name,
      int id) {
    this.name = name;
    this.id = id;
  }

}
