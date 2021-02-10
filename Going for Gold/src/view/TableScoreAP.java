package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import model.Stat;
import model.StatManagement;
import util.IConstant;

/**
* this class show all the stats in a table view.
* 
 * @author Peccio Leandro
 *
 */
public class TableScoreAP extends AnchorPane {
	
	//LABEL
	/**
	 * Every Label this class has to display.
	 */
	private Label lblNeon;
	
	//TableView
	/*
	* contains a list of stats
	*/
	private TableView<Stat> tbwBestScore;
	
	/**
	 * The StatManagement instance.
	 */
	private StatManagement statManagement;
	
	ObservableList<Stat> data;
	
	//BUTTON
	/**
	 * Every Buttons this class has to display.
	 */
	private Button btnBack;
	
	
	/**
	 * Constructor of TableScoreAP.
	 * Adds all components to its layout.
	 */
	public TableScoreAP(StatManagement sm) {
			
		this.statManagement = sm;
						
			
			getChildren().addAll(getLblNeon(), getTbwBestScore(),getBtnBack());
			
			setLeftAnchor(getLblNeon(), IConstant.TITLE_NEON_LEFT); setTopAnchor(getLblNeon(), IConstant.TITLE_NEON_TOP);
			setLeftAnchor(getTbwBestScore(),IConstant.TITLE_NEON_LEFT);setTopAnchor(getTbwBestScore(), 250.);
			setLeftAnchor(getBtnBack(), IConstant.BTN_BACK_POSITION_LEFT); setTopAnchor(getBtnBack(), IConstant.BTN_BACK_POSITION_TOP);
			
		}


	/**
	 * This method returns the Button btnBack and instantiates it if it's null.
	 * @return The Button btnBack
	 */
		public Button getBtnBack() {
			if(btnBack == null)
			{
				btnBack = new Button(" ");
				btnBack.setId("buttonBack");
				btnBack.setPrefSize(IConstant.BTN_BACK_WIDTH, IConstant.BTN_BACK_HEIGHT);
			} 
			return btnBack;
		}

		/**
		 * This method returns the Label lblNeon and instantiates it if it's null.
		 * @return The Label lblNeon
		 */
		public Label getLblNeon() {
			
			if(lblNeon == null)
			{
				lblNeon =  new Label("Best Scores");
				lblNeon.setId("lblPick");
				lblNeon.setPrefSize(IConstant.TITLE_NEON_WIDTH, IConstant.TITLE_NEON_HEIGHT);
				lblNeon.setTextFill(Color.rgb(0, 150, 255));
				lblNeon.setAlignment(Pos.CENTER);
			}
			return lblNeon;
		}


		/**
		 * This method creates a table view with all the stats that are in the stats.json.
		 * @return tbwBestScore
		 */
		public TableView<Stat> getTbwBestScore() {
			
			if(tbwBestScore == null)
			{
				tbwBestScore = new TableView<Stat>();
				
				tbwBestScore.setPrefSize(700.,330.);
								
				data = FXCollections.observableArrayList(getStatManagement().getInstanceStatManagement().getStats());

				tbwBestScore.setEditable(true);
				
				tbwBestScore.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
				tbwBestScore.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
				
				TableColumn<Stat, String> colPlayer = new TableColumn<Stat, String>("Player");
				TableColumn<Stat,Integer > colScore = new TableColumn<Stat, Integer>("Score");
				TableColumn<Stat,String > colTheme = new TableColumn<Stat, String>("Theme");
			
				
				colPlayer.setCellValueFactory(new PropertyValueFactory<>("playername"));
				colScore.setCellValueFactory(new PropertyValueFactory<>("score"));
				colTheme.setCellValueFactory(new PropertyValueFactory<>("theme"));
				
				tbwBestScore.getColumns().addAll(colPlayer, colScore, colTheme);
				tbwBestScore.setItems(data);
				
				tbwBestScore.refresh();
			}
			return tbwBestScore;
		}



		public StatManagement getStatManagement() {
			return statManagement;
		}

		
		
		
		
		

}
