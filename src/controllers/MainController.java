package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.HashTableN;
import model.HashTableN2;
import model.ReadFile;

public class MainController implements Initializable {

	@FXML
	private LineChart<Number, Number> lineChart1;

	@FXML
	private PieChart pieChart1;

	@FXML
	private LineChart<Number, Number> lineChart2;

	@FXML
	private PieChart pieChart2;

	@FXML
	private Text orderLabel1;

	@FXML
	private Text orderLabel2;

	private double AverageSpaceRatio, AverageSpaceOrder;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			Parent addBox = FXMLLoader.load(getClass().getResource("/view/addBox.fxml"));
			AddBoxCtrl.setBox(addBox);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Test1Sample1() {
		AddBoxCtrl.display();
		if (AddBoxCtrl.isEntered()) {
			setLineChart1(100, AddBoxCtrl.getNumOfTrials());
			setPieChart1();
		}
	}

	public void Test1Sample2() {
		AddBoxCtrl.display();
		if (AddBoxCtrl.isEntered()) {
			setLineChart1(1000, AddBoxCtrl.getNumOfTrials());
			setPieChart1();
		}
	}

	public void Test1Sample3() {
		AddBoxCtrl.display();
		if (AddBoxCtrl.isEntered()) {
			setLineChart1(10000, AddBoxCtrl.getNumOfTrials());
			setPieChart1();
		}
	}

	public void Test1Sample4() {
		AddBoxCtrl.display();
		if (AddBoxCtrl.isEntered()) {
			setLineChart1(18000, AddBoxCtrl.getNumOfTrials());
			setPieChart1();
		}
	}

	public void Test2Sample1() {
		AddBoxCtrl.display();
		if (AddBoxCtrl.isEntered()) {
			setLineChart2(100, AddBoxCtrl.getNumOfTrials());
			setPieChart2();
		}
	}

	public void Test2Sample2() {
		AddBoxCtrl.display();
		if (AddBoxCtrl.isEntered()) {
			setLineChart2(100, AddBoxCtrl.getNumOfTrials());
			setPieChart2();
		}
	}

	public void Test2Sample3() {
		AddBoxCtrl.display();
		if (AddBoxCtrl.isEntered()) {
			setLineChart2(10000, AddBoxCtrl.getNumOfTrials());
			setPieChart2();
		}
	}

	public void Test2Sample4() {
		AddBoxCtrl.display();
		if (AddBoxCtrl.isEntered()) {
			setLineChart2(1000000, AddBoxCtrl.getNumOfTrials());
			setPieChart2();
		}
	}

	private void setLineChart1(int i, int t) {
		File file = new File(getClass().getResource("/samples/sample" + i + ".txt").getFile());
		int[] keys = ReadFile.read(file);
		lineChart1.getData().clear();
		AverageSpaceRatio = 0;
		AverageSpaceOrder = 0;
		XYChart.Series<Number, Number> chartData = new XYChart.Series<Number, Number>();
		for (int j = 1; j <= t; j++) {
			HashTableN2 h2 = new HashTableN2(keys);
			AverageSpaceRatio += h2.spaceRatio();
			AverageSpaceOrder += h2.spaceOrder();
			;
			chartData.getData().add(new XYChart.Data<Number, Number>(j, h2.getRehashsNum()));
		}
		AverageSpaceRatio /= t;
		lineChart1.getData().addAll(chartData);
	}

	private void setPieChart1() {
		pieChart1.getData().clear();
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("Vacancy " + (100 - AverageSpaceRatio) + "%", 100 - AverageSpaceRatio),
				new PieChart.Data("Occupation " + AverageSpaceRatio + "%", AverageSpaceRatio));
		pieChart1.getData().addAll(pieChartData);
		orderLabel1.setText("Average Space Order = " + AverageSpaceOrder + " N");
	}

	private void setLineChart2(int i, int t) {
		File file = new File(getClass().getResource("/samples/sample" + i + ".txt").getFile());
		int[] keys = ReadFile.read(file);
		lineChart2.getData().clear();
		AverageSpaceRatio = 0;
		AverageSpaceOrder = 0;
		XYChart.Series<Number, Number> chartData = new XYChart.Series<Number, Number>();
		for (int j = 1; j <= t; j++) {
			HashTableN h = new HashTableN(keys);
			AverageSpaceRatio += h.spaceRatio();
			AverageSpaceOrder += h.spaceOrder();
			chartData.getData().add(new XYChart.Data<Number, Number>(j, h.getRehashsNum()));
		}
		AverageSpaceRatio /= t;
		AverageSpaceOrder /= t;
		lineChart2.getData().addAll(chartData);
	}

	private void setPieChart2() {
		pieChart2.getData().clear();
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("Vacancy " + (100 - AverageSpaceRatio) + "%", 100 - AverageSpaceRatio),
				new PieChart.Data("Occupation " + AverageSpaceRatio + "%", AverageSpaceRatio));
		pieChart2.getData().addAll(pieChartData);
		orderLabel2.setText("Average Space Order = " + AverageSpaceOrder + " N");
	}

	public void close(ActionEvent actionEvent) {
		Node source = (Node) actionEvent.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

	public void minimize(ActionEvent actionEvent) {
		((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).setIconified(true);
	}

}
