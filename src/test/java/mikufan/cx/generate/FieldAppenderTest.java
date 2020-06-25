package mikufan.cx.generate;

import lombok.extern.slf4j.Slf4j;
import mikufan.cx.generate.appender.FieldAppender;
import mikufan.cx.generate.store_info.ClassInfo;
import org.junit.jupiter.api.Test;

@Slf4j
class FieldAppenderTest {

  @Test
  void writeFieldAll() {

    var fieldAll =
        "additionalNames (string, optional),\n" +
            "category (string, optional) = ['Unspecified', 'AlbumRelease', 'Anniversary', 'Club', 'Concert', 'Contest', 'Convention', 'Other'],\n" +
            "customName (boolean, optional),\n" +
            "date (string, optional),\n" +
            "deleted (boolean, optional),\n" +
            "description (string, optional),\n" +
            "endDate (string, optional),\n" +
            "hasVenueOrVenueName (boolean, optional),\n" +
            "id (integer, optional),\n" +
            "inheritedCategory (string, optional) = ['Unspecified', 'AlbumRelease', 'Anniversary', 'Club', 'Concert', 'Contest', 'Convention', 'Other'],\n" +
            "name (string, optional),\n" +
            "pictureMime (string, optional),\n" +
            "series (ReleaseEventSeriesContract, optional),\n" +
            "songList (SongListBaseContract, optional),\n" +
            "status (string, optional) = ['Draft', 'Finished', 'Approved', 'Locked'],\n" +
            "urlSlug (string, optional),\n" +
            "venue (VenueContract, optional),\n" +
            "venueName (string, optional),\n" +
            "version (integer, optional)\n"+
        "formatted (string, optional),\n" +
        "hasValue (boolean, optional),\n" +
        "latitude (number, optional),\n" +
        "webLinks (Array[WebLinkContract], optional),\n" +
        "longitude (number, optional)";
    var builder = ClassInfo.builder().clazz("Dummy").genericClazz("Fufu");
    for (String str : fieldAll.split("\n")) {
      FieldAppender.putField(str, builder, Action.NEW_GENERIC_CLASS);
    }
    log.info("sb = \n{}", builder.build().toClassDefinition());

  }
}