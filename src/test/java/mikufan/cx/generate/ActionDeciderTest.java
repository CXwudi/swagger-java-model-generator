package mikufan.cx.generate;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.eclipse.collections.api.factory.Lists;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ActionDeciderTest {

  @Test @SneakyThrows
  void testAll() {
    var modelFile = Path.of("models.txt");
    var modelString = Files.readString(modelFile);
    var eachLine = modelString.split(Strings.LINE_SEPARATOR);

    var lines = Lists.immutable.withAll(Arrays.asList(eachLine));
    var whatNextLists = lines.collect(ActionDecider::whatNext);
    var zip = lines.zip(whatNextLists);
    zip.forEach(pair -> log.info("{}", pair));
  }
}