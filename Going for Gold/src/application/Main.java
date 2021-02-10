package application;
	

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.UtilStackpane;
import javafx.scene.Scene;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		

		try {	
			
			UtilStackpane sp = new UtilStackpane();

			sp.setId("pane");

			Scene scene = new Scene(sp,1200,650);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();	
			primaryStage.setTitle("Going For Gold");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
