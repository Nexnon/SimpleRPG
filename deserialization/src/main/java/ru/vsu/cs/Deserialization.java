package ru.vsu.cs;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Deserialization<T> {

    public List<T> deserializeJsonToObjects(String filePath, Class<T> objectType) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        File file = new File(filePath);

        List<T> objects = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, objectType));

        return objects;
    }

    public static void main(String[] args) {
        Deserialization<Player> deserializer = new Deserialization<>();
        String filePath = "player.json";
        try {
            List<Player> deserializedObjects = deserializer.deserializeJsonToObjects(filePath, Player.class);
            for (Player player : deserializedObjects) {
                System.out.println(player.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
