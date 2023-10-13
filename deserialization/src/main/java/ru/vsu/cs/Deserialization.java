package ru.vsu.cs;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Deserialization<T> {

    public List<T> deserializeJsonToObjects(String filePath, Class<T> objectType) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        File file = new File(filePath);

        // Deserialize JSON from file into a list of objects
        List<T> objects = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, objectType));

        return objects;
    }
}
