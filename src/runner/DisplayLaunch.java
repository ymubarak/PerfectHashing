package runner;

import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class DisplayLaunch {

	private static Stage primaryStage;
	private static Parent root;
	private static Scene scene;
	private String styleSheet;

	public DisplayLaunch(Stage ps) {
		primaryStage = ps;
		initializeGui();
		runSplash();

	}

	private void initializeGui() {
		try {
			root = FXMLLoader.load(getClass().getResource("/view/mainPanel.fxml"));
			styleSheet = getClass().getResource("/view/application.css").toExternalForm();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void activateApp() {
		try {
			scene = new Scene(root,1400,800);
			scene.getStylesheets().add(styleSheet);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void runSplash() {

		StackPane splash = null;
		try {
			splash = FXMLLoader.load(getClass().getResource("/view/SplashScreen.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(splash, 650, 500);
		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.show();
		// fade in
		FadeTransition fadeIn = new FadeTransition();
		fadeIn.setDuration(Duration.seconds(3));
		fadeIn.setNode(splash);
		fadeIn.setFromValue(0);
		fadeIn.setToValue(1);
		fadeIn.play();
		// fade out
		FadeTransition fadeout = new FadeTransition();
		fadeout.setDuration(Duration.seconds(3));
		fadeout.setNode(splash);
		fadeout.setFromValue(1);
		fadeout.setToValue(0);
		fadeIn.setOnFinished(e -> fadeout.play());
		fadeout.setOnFinished(e -> {
			primaryStage.hide();
			activateApp();
		});

	}
}
