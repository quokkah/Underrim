package underrim.UnderrimGame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

import static java.nio.file.Files.readAllLines;
public class Main extends Application {
    static HashMap<String, String> descriptions = new HashMap<>();
    static HashMap<String, String> nextMoves = new HashMap<>();
    static HashMap<String, String> choices = new HashMap<>();
    static TextArea textArea = new TextArea();
    static String currentLocation = "";
    static String[] choicesSplit = new String[4];

    @Override
    public void start(Stage primaryStage) {
        Font font = Font.loadFont("file:src/main/resources/underrim/UnderrimGame/fonts/CONSOLA.TTF", 20);
        primaryStage.setTitle("Underrim");
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");

        // Create three sections
        Region header = createSection(0.10);
        header.setStyle("-fx-background-color: #31363F");
        Region body = createSection(0.75);
        body.setStyle("-fx-background-color: #222831");
        Region footer = createSection(0.15);
        footer.setStyle("-fx-background-color: #31363F");

        // Create Children of sections
        Button button1 = new Button();
        Button button2 = new Button();
        Button button3 = new Button();
        Button button4 = new Button();

        StackPane headerPane = new StackPane(header, new Label("Header"));
        StackPane bodyPane = new StackPane(body, textArea);
        StackPane footerPane = new StackPane(footer, button1, button2, button3, button4);
        button1.setTranslateX(-550);
        button1.setText("Option 1");
        button2.setTranslateX(-200);
        button2.setText("Option 2");
        button3.setTranslateX(200);
        button3.setText("Option 3");
        button4.setTranslateX(550);
        button4.setText("Option 4");
        textArea.setWrapText(true);
        textArea.setPadding(new Insets(5));
        textArea.setFont(font);
        textArea.setEditable(false);

        VBox vbox = new VBox(headerPane, bodyPane, footerPane);

        Scene scene = new Scene(vbox, 800, 600);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("stylesheets/stylesheet.css")).toExternalForm());
        primaryStage.show();

        System.out.println("Running on JRE Version: " + System.getProperty("java.version"));
        loadData();
    }

    private Region createSection(double relativeHeight) {
        StackPane section = new StackPane();
        section.setPrefHeight(relativeHeight * 1000);
        return section;
    }

    public static void loadData() {
        try {
            for (String line : readAllLines(Paths.get("src/main/resources/underrim/UnderrimGame/locationData/descriptions.txt"))) {
                String[] lineSplit = line.split(": ");
                descriptions.put(lineSplit[0], lineSplit[1]);
            }
            for (String line : readAllLines(Paths.get("src/main/resources/underrim/UnderrimGame/locationData/nextMoves.txt"))) {
                String[] lineSplit = line.split(": ");
                nextMoves.put(lineSplit[0], lineSplit[1]);
            }
            for (String line : readAllLines(Paths.get("src/main/resources/underrim/UnderrimGame/locationData/choices.txt"))) {
                String[] lineSplit = line.split(": ");
                choices.put(lineSplit[0], lineSplit[1]);
            }
            for (String line : readAllLines(Paths.get("src/main/resources/underrim/UnderrimGame/playerData/progress.txt"))) {
                String[] lineSplit = line.split(": ");
                if (Objects.equals(lineSplit[0], "currentLocation")) {
                    currentLocation = lineSplit[1];
                }
            }
            if (Objects.equals(currentLocation, "")) {
                System.out.println("ERROR - Player data not found");
            }
        } catch (IOException e) {
            System.out.println("ERROR - Could not locate data");
            throw new RuntimeException(e);
        }
        progressEvent(true);
    }

    public static void progressEvent(boolean startedGame) {
        if (startedGame) {
            currentLocation = "M1-1";
        }
        textArea.setText(descriptions.get(currentLocation));
        choicesSplit = (choices.get(currentLocation)).split(", ");
        for (int choiceCounter = 1; choiceCounter <= choicesSplit.length; choiceCounter++) {
            textArea.appendText("\n[" + choiceCounter + "] " + choicesSplit[choiceCounter - 1]);
        }
    }
}