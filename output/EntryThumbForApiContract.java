// auto-generated by get-my-vocadb-java-model at 2020-08-20T16:50:41.990997
package dummy_package;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.eclipse.collections.api.list.MutableList;

@Getter(onMethod_ = {@JsonIgnore}) @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntryThumbForApiContract {

  @JsonProperty
  private String mime;

  @JsonProperty
  private String urlOriginal;

  @JsonProperty
  private String urlSmallThumb;

  @JsonProperty
  private String urlThumb;

  @JsonProperty
  private String urlTinyThumb;

}
