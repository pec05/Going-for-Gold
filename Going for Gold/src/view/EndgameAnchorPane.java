package view;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import model.Stat;
import model.StatManagement;
import util.IConstant;


/**
 * The class shows the end game screen
 * 
 * @author Peccio Leandro
 *
 */
public class EndgameAnchorPane extends AnchorPane{
	
	//BUTTON
	/*
	 * Button yes to restart a new game
	 * Button no to come back on menu
	 * Button save to record the score of the game
	 */
	private Button btnYes, btnNo, btnSave;
	
	//LABELS
	/**
	* Labels displaying what the text fields need.
	*/
	private Label lblScore, lblTryAgain;
	
	/**
	 * The user's score.
	 */ 
	private int score;
	
	/**
	 * The user's theme.
	 */
	private String theme;
	
	/**
	 * The user's name.
	 */
	private String playername;
	
	/**
	 * The StatManagement instance.
	 */
	private StatManagement gestionStats;

	/**
	 * Constructor of EndgameAnchorPane.
	 * Adds all components to its layout.
	 */
	public EndgameAnchorPane() {
		// TODO Auto-generated constructor stub
		
		setTopAnchor(getLblScore(), IConstant.TITLE_NEON_TOP); setLeftAnchor(getLblScore(), IConstant.TITLE_NEON_LEFT);
		setTopAnchor(getLblTryAgain(), 250.); setLeftAnchor(getLblTryAgain(), 500.);
		
		HBox hBoxBtn = new HBox();
		hBoxBtn.getChildren().addAll(getBtnYes(),getBtnNo(), getBtnSave());
		hBoxBtn.setSpacing(10.);
		setTopAnchor(hBoxBtn, 450.); setLeftAnchor(hBoxBtn, 350.);
		
		
		getChildren().addAll(getLblScore(),
				getLblTryAgain(),
				hBoxBtn);
		
		
	}

	/**
	 * This method returns the Button btnYes and instantiates it if it's null.
	 * @return The Button btnYes
	 */
	public Button getBtnYes() {
		if(btnYes == null) {
			btnYes = new Button("Yes");
			btnYes.setTextFill(Color.RED);
			btnYes.setId("btnEndGame");
			
			btnYes.setPrefSize(IConstant.ENDGAME_BTN_WIDTH, IConstant.ENDGAME_BTN_HEIGHT);
			

			
		}
		return btnYes;
	}

	/**
	 * This method returns the Button btnNo and instantiates it if it's null.
	 * @return The Button btnNo
	 */
	public Button getBtnNo() {
		if(btnNo == null) {
			btnNo = new Button("No");
			btnNo.setId("btnEndGame");
			
			btnNo.setPrefSize(IConstant.ENDGAME_BTN_WIDTH, IConstant.ENDGAME_BTN_HEIGHT);
			
		}
		return btnNo;
	}

	/**
	 * This method returns the Label lblScore and instantiates it if it's null.
	 * @return The Label lblScore
	 */
	public Label getLblScore() {
		if(lblScore == null) {
			lblScore = new Label("Your score : ");
			lblScore.setId("lblPick");
			lblScore.setTextFill(Color.GOLD);
			lblScore.setMinWidth(IConstant.TITLE_NEON_WIDTH);
			lblScore.setMinHeight(IConstant.TITLE_NEON_HEIGHT);
			lblScore.setAlignment(Pos.CENTER);
		}
		return lblScore;
	}

	/**
	 * This method returns the Label lblTryAgain and instantiates it if it's null.
	 * @return The Label lblTryAgain
	 */
	public Label getLblTryAgain() {
		if(lblTryAgain == null) {
			lblTryAgain = new Label("Try again ?");
			lblTryAgain.setTextFill(Color.rgb(0,150,255));
			lblTryAgain.setAlignment(Pos.CENTER);
			lblTryAgain.setId("endGame");
			lblTryAgain.setPrefSize(IConstant.ENDGAME_LBL_HEIGHT, IConstant.ENDGAME_LBL_HEIGHT);
			lblTryAgain.setPrefWidth(200.); lblTryAgain.setPrefHeight(200.);
		}
		return lblTryAgain;
	}
	
	/**
	 * This method returns the Button btnSave and instantiates it if it's null.
	 * @return The Button btnSave
	 */
	public Button getBtnSave() {
		if(btnSave == null) {
			btnSave = new Button("Save Score");
			btnSave.setId("btnEndGame");
			
			btnSave.setPrefSize(IConstant.ENDGAME_BTN_WIDTH, IConstant.ENDGAME_BTN_HEIGHT);
			
			btnSave.setOnAction(new EventHandler<ActionEvent>() {
				/**
				 * When you click on the button btnbtnSave, this method save the score on stat.json and add this score on the table score
				 */
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					Stat s = null;
					
					s= new Stat(getPlayername(), getScore(), getTheme());
					
					System.out.println("méthode tablescore : "+s);
					StatManagement.getInstanceStatManagement().addStat(s.clone());
					
					if(getParent() instanceof UtilStackpane) {
						
						UtilStackpane sp = (UtilStackpane) getParent();
						
						boolean x = true;
						if(x) {
							sp.getTableScoreAP().getTbwBestScore().getItems().add(s);
							
						}
					}
					
					
					
					Gson gson = new Gson();
					String json = gson.toJson(gestionStats);
					
					
					StatManagement.getInstanceStatManagement().saveJsonStats();
				    
				   getBtnSave().setVisible(false);
				}
			});
		}
		return btnSave;
	}
	
	public int getScore() {
		return score ;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getPlayername() {
		return playername;
	}

	public void setPlayername(String playername) {
		this.playername = playername;
	}

	
	

}
