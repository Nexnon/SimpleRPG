package ru.vsu.cs;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Serialization<T> {

    public void serializeObjectsToJson(List<T> objects, String filePath){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        try {
            File file = new File(filePath);
            objectMapper.writeValue(file, objects);
            System.out.println("Objects serialized and saved to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main (String args[]) {
        List<Player> players = Generator.generate(100_000);

        String filePath ="player.json";

        Serialization<Player> serialization = new Serialization<>();
        serialization.serializeObjectsToJson(players, filePath);
    }
}
