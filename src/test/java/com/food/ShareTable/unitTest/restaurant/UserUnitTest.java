package com.food.ShareTable.unitTest.restaurant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.food.ShareTable.user.enitity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Date;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserUnitTest {

    @Test
    public void testJsonGetterOnUser() throws JsonProcessingException {
        User userJson = new User("123","abc","abc@gamil.com", "pw","55555555", new Date());

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        String jsonStr = mapper.writeValueAsString(userJson);

        System.out.println(jsonStr);

    }

    @Test
    public void testJsonConstructor() throws JsonProcessingException {
        String json = "{\n" +
                "  \"id\": 123,\n" +
                "  \"userName\": \"gold\",\n" +
                "  \"email\": \"happy@gmail.com\",\n" +
                "  \"password\": \"12314124\",\n" +
                "  \"contactNum\": \"123121231\",\n" +
                "  \"date\": \"12-12-2020 13:45:10\"\n" +
                "}";

        //map to constructor json property
        User user = new ObjectMapper()
                .readerFor(User.class)
                .readValue(json);

        System.out.println(user.getId());
        System.out.println(user.getUserName());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(user.getContactNum());
        System.out.println(user.getCreateDate());

    }

    @Test
    public void testJsonSetter() throws JsonProcessingException {
        String json = "{\n" +
                "  \"id\": 123,\n" +
                "  \"userName\": \"gold\",\n" +
                "  \"email\": \"happy@gmail.com\",\n" +
                "  \"password\": \"12314124\",\n" +
                "  \"contactNum\": \"123121231\",\n" +
                "  \"createDate\": \"12-12-2020 13:45:10\"\n" +
                "}";

        //map by each field using json setter
        User user = new ObjectMapper()
                .readerFor(User.class)
                .readValue(json);

        System.out.println(user.getId());
        System.out.println(user.getUserName());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(user.getContactNum());
        System.out.println(user.getCreateDate());

    }

    @Test
    public void testMissingSomeFields() throws JsonProcessingException {
        String json = "{\n" +
                "  \"userName\": \"gold\",\n" +
                "  \"email\": \"happy@gmail.com\",\n" +
                "  \"password\": \"12314124\",\n" +
                "  \"contactNum\": \"123121231\",\n" +
                "  \"createDate\": \"12-12-2020 13:45:10\"\n" +
                "}";

        //map by each field using json setter
        User user = new ObjectMapper()
                .readerFor(User.class)
                .readValue(json);

        System.out.println(user.getId());
        System.out.println(user.getUserName());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(user.getContactNum());
        System.out.println(user.getCreateDate());
    }

    @Test
    public void testNonNullJson() throws JsonProcessingException {
        String json = "{\n" +
                "  \"id\": 123,\n" +
                "  \"userName\": \"gold\",\n" +
                "  \"email\": \"happy@gmail.com\",\n" +
                "  \"password\": \"12314124\",\n" +
                "  \"contactNum\": \"123121231\",\n" +
                "  \"createDate\": \"12-12-2020 13:45:10\"\n" +
                "}";

        //map by each field using json setter
        User user = new ObjectMapper()
                .readerFor(User.class)
                .readValue(json);

        System.out.println(user.getId());
        System.out.println(user.getUserName());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(user.getContactNum());
        System.out.println(user.getCreateDate());
    }
}
