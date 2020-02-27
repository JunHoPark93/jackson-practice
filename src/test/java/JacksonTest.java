import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jaytechblog.jacksonpractice.*;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class JacksonTest {
    @Test
    public void json_any_getter() throws JsonProcessingException {
        Member member = new Member("Jay");
        member.add("favorite", "chicken");
        member.add("hobby", "tennis");

        String result = new ObjectMapper().writeValueAsString(member);
        System.out.println(result);

        assertThat(result, containsString("favorite"));
        assertThat(result, containsString("hobby"));
    }

    @Test
    public void json_getter() throws JsonProcessingException {
        Member2 member = new Member2(1, "Jay");

        String result = new ObjectMapper().writeValueAsString(member);

        System.out.println(result);
        assertThat(result, containsString("Jay"));
        assertThat(result, containsString("1"));
    }

    @Test
    public void json_property_order() throws JsonProcessingException {
        Member3 bean = new Member3(1, "My bean");

        String result = new ObjectMapper().writeValueAsString(bean);

        System.out.println(result);
        assertThat(result, containsString("My bean"));
        assertThat(result, containsString("1"));
    }

    @Test
    public void json_value() throws JsonProcessingException {
        String enumAsString = new ObjectMapper().writeValueAsString(TypeEnumWithValue.TYPE1);
        System.out.println(enumAsString);
        assertThat(enumAsString, is("\"치킨\""));
    }

    @Test
    public void json_root_name() throws JsonProcessingException {
        Member4 user = new Member4(1, "Jay");

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        String result = mapper.writeValueAsString(user);

        System.out.println(result);
        assertThat(result, containsString("Jay"));
        assertThat(result, containsString("member"));
    }

    @Test
    public void json_serialize() throws ParseException, JsonProcessingException {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

        String toParse = "20-12-2014 02:30:00";
        Date date = df.parse(toParse);
        Event event = new Event("party", date);
        String result = new ObjectMapper().writeValueAsString(event);
        assertThat(result, containsString(toParse));
    }

    // >>>>>>>>>>>>>>>>>>>>>> JSON DESERIALIZATION

    @Test
    public void json_creator() throws JsonProcessingException {
        String json = "{\n" +
                "    \"id\":1,\n" +
                "    \"theName\":\"Jay\"\n" +
                "}";
        Member5 member = new ObjectMapper().readerFor(Member5.class).readValue(json);

        assertThat(member.name, is(member.name));
    }

    @Test
    public void json_inject() throws JsonProcessingException {
        String json = "{\"name\":\"Jay\"}";

        InjectableValues injectableValues = new InjectableValues.Std().addValue(int.class, 5);
        Member6 member = new ObjectMapper().reader(injectableValues).forType(Member6.class).readValue(json);

        assertThat(member.id, is(5));
    }

    @Test
    public void json_any_setter() throws IOException {
        String json = "{\"name\":\"Jay\",\"favorite\":\"chicken\",\"hobby\":\"tennis\"}";

        Member7 member = new ObjectMapper().readerFor(Member7.class).readValue(json);

        assertThat(member.properties.get("favorite"), is("chicken"));
    }

    @Test
    public void json_deserializer() throws JsonProcessingException {
        String json = "{\"name\":\"Jay event\",\"eventDate\":\"20-12-2020 01:01:00\"}";

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        Event2 event = new ObjectMapper()
                .readerFor(Event2.class)
                .readValue(json);

        assertThat(df.format(event.eventDate), is("20-12-2020 01:01:00"));
    }

    @Test
    public void json_alias() throws JsonProcessingException {
        String json1 = "{\"name\":\"Jay\",\"hobby\":\"tennis\"}";
        String json2 = "{\"his_name\":\"Jay\",\"hobby\":\"tennis\"}";
        String json3 = "{\"her_name\":\"Jay\",\"hobby\":\"tennis\"}";

        Member8 member1 = new ObjectMapper().readerFor(Member8.class).readValue(json1);
        Member8 member2 = new ObjectMapper().readerFor(Member8.class).readValue(json2);
        Member8 member3 = new ObjectMapper().readerFor(Member8.class).readValue(json3);

        assertThat(member1.name, is("Jay"));
        assertThat(member2.name, is("Jay"));
        assertThat(member3.name, is("Jay"));
    }

    @Test
    public void json_ignore_properties() throws JsonProcessingException {
        Member9 member = new Member9(1, "Jay");
        String result = new ObjectMapper().writeValueAsString(member);

        assertThat(result, containsString("Jay"));
        assertThat(result, not(containsString("1")));
    }

    @Test
    public void json_include() throws JsonProcessingException {
        Member10 member = new Member10(1, null);
        String result = new ObjectMapper().writeValueAsString(member);

        assertThat(result, containsString("1"));
        assertThat(result, not(containsString("name")));
    }

    @Test
    public void json_auto_detect_visibility_any() throws JsonProcessingException {
        Member11 member = new Member11(1, "Jay");

        String result = new ObjectMapper().writeValueAsString(member);
        assertThat(result, containsString("1"));
        assertThat(result, containsString("Jay"));
    }

    @Test
    public void disable_jackson_annotation() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(MapperFeature.USE_ANNOTATIONS);
        // 중략
        // 이 mapper를 사용하면, 애노테이션이 전부 비활성화 됩니다
    }
}
