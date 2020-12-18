package mikufan.cx.generate.jackson_lombok_test;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@ToString @Getter
@Builder(toBuilder = true) @Jacksonized
public class SomeHolder<T> {

  private T result;
  private List<T> contents;
}
