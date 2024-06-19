package underrim.underrimgame;

import java.util.HashMap;
import java.io.IOException;
import java.nio.file.Paths;
import static java.nio.file.Files.readAllLines;
public class Main {
    static HashMap<String, String> descriptions = new HashMap<String, String>();
    static HashMap<String, String> nextMoves = new HashMap<String, String>();

    public static void main(String[] args) {
        loadData();
        String currentPosition = "v1-1";
        System.out.println(nextMoves.get(currentPosition));
        System.out.println(descriptions.get(currentPosition));
    }

    public static void loadData() {
        try {
            int lineCounter = 0;
            String key = "";
            for (String line : readAllLines(Paths.get("src/main/resources/descriptions.txt"))) {
                if (lineCounter % 2 == 0) {
                    key = line;
                } else {
                    descriptions.put(key, line);
                }
                lineCounter++;
            }
            lineCounter = 0;
            for (String line : readAllLines(Paths.get("src/main/resources/nextMoves.txt"))) {
                if (lineCounter % 2 == 0) {
                    key = line;
                } else {
                    nextMoves.put(key, line);
                }
                lineCounter++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}