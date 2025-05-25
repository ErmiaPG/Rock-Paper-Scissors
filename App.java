package minigame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Random;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;

public class App extends Application {

    private static Scene scene;
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }


    private Label resultLabel;
    private Random random = new Random();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Rock, Paper, Scissors");

        // Labels and Buttons
        resultLabel = new Label("Choose your move:");
        Button rockButton = new Button("Rock");
        Button paperButton = new Button("Paper");
        Button scissorsButton = new Button("Scissors");

        // Button actions
        rockButton.setOnAction(e -> play("Rock"));
        paperButton.setOnAction(e -> play("Paper"));
        scissorsButton.setOnAction(e -> play("Scissors"));

        VBox vbox = new VBox(10, resultLabel, rockButton, paperButton, scissorsButton);
        vbox.setPadding(new Insets(20));
        vbox.setStyle("-fx-alignment: center;");

        Scene scene = new Scene(vbox, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void play(String userChoice) {
        String[] choices = {"Rock", "Paper", "Scissors"};
        String computerChoice = choices[random.nextInt(3)];
        String result;

        if (userChoice.equals(computerChoice)) {
            result = "It's a tie!";
        } else if (
            (userChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
            (userChoice.equals("Paper") && computerChoice.equals("Rock")) ||
            (userChoice.equals("Scissors") && computerChoice.equals("Paper"))
        ) {
            result = "You win!";
        } else {
            result = "Computer wins!";
        }

        resultLabel.setText("Your choice: " + userChoice + "\nComputer's choice: " + computerChoice + "\n" + result);
    }

    public static void main(String[] args) {
        launch(args);
    }
}