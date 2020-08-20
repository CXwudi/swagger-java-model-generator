// auto-generated by get-my-vocadb-java-model at 2020-08-20T16:50:41.979027800
package dummy_package;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.eclipse.collections.api.list.MutableList;

@Getter(onMethod_ = {@JsonIgnore}) @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SongForApiContract {

  @JsonProperty
  private String additionalNames;

  @JsonProperty
  private MutableList<AlbumContract> albums;

  @JsonProperty
  private MutableList<ArtistForSongContract> artists;

  @JsonProperty
  private String artistString;

  @JsonProperty
  private String createDate;

  @JsonProperty
  private String defaultName;

  @JsonProperty
  private String defaultNameLanguage;

  @JsonProperty
  private boolean deleted;

  @JsonProperty
  private int favoritedTimes;

  @JsonProperty
  private int id;

  @JsonProperty
  private int lengthSeconds;

  @JsonProperty
  private MutableList<LyricsForSongContract> lyrics;

  @JsonProperty
  private EntryThumbForApiContract mainPicture;

  @JsonProperty
  private int mergedTo;

  @JsonProperty
  private String name;

  @JsonProperty
  private MutableList<LocalizedStringContract> names;

  @JsonProperty
  private int originalVersionId;

  @JsonProperty
  private String publishDate;

  @JsonProperty
  private MutableList<PVContract> pvs;

  @JsonProperty
  private String pvServices;

  @JsonProperty
  private int ratingScore;

  @JsonProperty
  private ReleaseEventForApiContract releaseEvent;

  @JsonProperty
  private String songType;

  @JsonProperty
  private String status;

  @JsonProperty
  private MutableList<TagUsageForApiContract> tags;

  @JsonProperty
  private String thumbUrl;

  @JsonProperty
  private int version;

  @JsonProperty
  private MutableList<WebLinkForApiContract> webLinks;

}
