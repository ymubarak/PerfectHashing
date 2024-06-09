package controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AddBoxCtrl {

	@FXML
	private TextField numOfTrials;
	private static Stage stage;
	private static boolean Entered;
	private static int trials;
	private static Parent root;

	public static void setBox(Parent addBox) {
		root = addBox;
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.initStyle(StageStyle.UNDECORATED);
	}

	public static void display() {
		Entered = false;
		stage.showAndWait();
	}

	public void add() {
		try {
			trials = Integer.parseInt(numOfTrials.getText());
			Entered = true;
			close();
		} catch (Exception e) {
			// TODO: handle exception
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Invalid data entered ! ");
			alert.showAndWait();
		}
	}

	public void close() {
		stage.close();
	}

	public static boolean isEntered() {
		return Entered;
	}

	public static int getNumOfTrials() {
		return trials;
	}

}
