package runner;

import java.net.Inet4Address;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.stage.Stage;

public class GuiRunner extends Application {
	public static void main(String[] args) {
		launch(args);
		try {
			System.out.println(Inet4Address.getLocalHost().getHostAddress());
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void start(Stage primaryStage) {
		new DisplayLaunch(primaryStage);
	}

}

