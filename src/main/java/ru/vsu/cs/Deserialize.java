package ru.vsu.cs;

import java.io.IOException;
import java.util.List;

public class Deserialize {

    public static void main(String[] args) throws IOException {
        Deserialization<Player> deserializer = new Deserialization<>();
        String filePath = "player.json"; // Specify the path to your serialized JSON file
        try {
            List<Player> deserializedObjects = deserializer.deserializeJsonToObjects(filePath, Player.class);
            // Now you have the deserialized objects in the deserializedObjects list.
            for (Player obj : deserializedObjects) {
                System.out.println(obj.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
