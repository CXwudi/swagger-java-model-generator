package mikufan.cx.generate.jackson_lombok_test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

@Slf4j
class SomeHolderTest {
  @Test @SneakyThrows
  void testRead(){
    var file = Path.of("src/test/java/mikufan/cx/generate//jackson_lombok_test", "entity.json");
    var entity = new ObjectMapper().readValue(file.toFile(), new TypeReference<SomeHolder<InnerEntity>>() {
    });
    log.info("entity = {}", entity);
  }

}