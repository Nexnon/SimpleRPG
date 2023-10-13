package ru.vsu.cs;

import java.io.IOException;
import java.util.List;

public class GenerateSerialize {

    public static void main (String args[]) throws IOException {
        List<Player> players = Generator.generate(100_000);

        String filePath ="player.json";

        Serialization<Player> serialization = new Serialization<>();
        serialization.serializeObjectsToJson(players, filePath);
    }

}
