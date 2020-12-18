package mikufan.cx.generate.jackson_lombok_test;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@ToString @Getter
@Builder @Jacksonized
public class SomeHolder<T> {

  private T result;
}
