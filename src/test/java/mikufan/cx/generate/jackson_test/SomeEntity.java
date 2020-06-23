package mikufan.cx.generate.jackson_test;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@ToString
public class SomeEntity<T> {

  private T result;

  @JsonCreator
  public SomeEntity(@JsonProperty("result") T result) {
    this.result = result;
  }
  public T getResult() {
    return result;
  }
}
