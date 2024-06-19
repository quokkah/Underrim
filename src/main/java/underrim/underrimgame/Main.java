package underrim.UnderrimGame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.HashMap;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

import static java.nio.file.Files.readAllLines;
public class Main extends Application {
    static HashMap<String, String> descriptions = new HashMap<>();
    static HashMap<String, String> nextMoves = new HashMap<>();
    StackPane root = new StackPane();
    Scene scene = new Scene(root);

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Underrim");
        primaryStage.setScene(scene);
        primaryStage.setX(0);
        primaryStage.setY(0);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();
        System.out.println("Running on JRE Version: " + System.getProperty("java.version"));

        loadData();
    }

    public static void loadData() {
        try {
            int lineCounter = 0;
            String key = "";
            for (String line : readAllLines(Paths.get("src/main/resources/underrim/UnderrimGame/descriptions.txt"))) {
                if (lineCounter % 2 == 0) {
                    key = line;
                } else {
                    descriptions.put(key, line);
                }
                lineCounter++;
            }
            lineCounter = 0;
            for (String line : readAllLines(Paths.get("src/main/resources/underrim/UnderrimGame/nextMoves.txt"))) {
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