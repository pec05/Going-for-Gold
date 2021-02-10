package view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import model.Settings;

/**
 * The class ProgressTimer deals the time of the game
 * 
 * @author Peccio Leandro
 *
 */

public class ProgressTimer extends Thread{
	
	/**
	 * Indicates if the thread is running.
	 */
	private boolean isRunning = true;
	/**
	 * Indicates if the thread needs to be destroyed.
	 */
	private boolean awaitingDestroy = false;
	
	/**
	 * The GameAnchorPane on which it needs to interact.
	 */
	private GameAnchorPane gameAnchorPane;
	
	private static int TIME = Settings.getInstance().getTimerTotal();
	private Timeline tm;
	
	
	
	private IntegerProperty timeSeconds =
	        new SimpleIntegerProperty(TIME);
		
	/**
	 * Constructor of ProgressTimer
	 * Instantiates the GameAnchorPane.
	 */
	public ProgressTimer(GameAnchorPane gameAnchorPane) {
		// TODO Auto-generated constructor stub
		super();
		this.gameAnchorPane = gameAnchorPane;
		getTm().play();
		
	}
	
	/**
	 * this method decrements the game time
	 */
	public Timeline getTm() {
		
		if(tm == null)
		{
			tm = new Timeline();
			gameAnchorPane.getLblRemainTime().textProperty().bind(timeSeconds.asString());
			System.out.println(("time : " +TIME + "timesec : "  +timeSeconds.get()));
			gameAnchorPane.getProgressBar().progressProperty().bind(timeSeconds.divide(TIME*1.0));
			tm.setCycleCount(TIME);
			KeyFrame key = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					timeSeconds.setValue(timeSeconds.get()-1);
					System.out.println(timeSeconds.get());
					
					/*
					 * if timeSeconds is negative or equals zero
					 */
					if(timeSeconds.get()<= 0) {
						tm.stop();
						((UtilStackpane) gameAnchorPane.getParent()).hideAll();
						((UtilStackpane) gameAnchorPane.getParent()).getChildren().get(IListPane.ENDGAME).setVisible(true);
						
						/*
						 * transfer the data at endgameAnchorPane
						 */
						((UtilStackpane) gameAnchorPane.getParent()).getEndgameAnchorPane().setPlayername(gameAnchorPane.getPseudo());
						((UtilStackpane) gameAnchorPane.getParent()).getEndgameAnchorPane().setScore(gameAnchorPane.getGame().getScore());
						((UtilStackpane) gameAnchorPane.getParent()).getEndgameAnchorPane().setTheme(gameAnchorPane.getGame().getTheme());
						((UtilStackpane) gameAnchorPane.getParent()).getEndgameAnchorPane().getLblScore().setText("Your score : "+gameAnchorPane.getGame().getScore());
						
					}
				}
			
				
			});
			
				tm.getKeyFrames().add(key);
		}
		return tm;
	}
	

	/**
	 * This method Destroys the timer.
	 */
	public void destroyProgress() {
		awaitingDestroy = true;
	}
	
	/**
	 * This method addition time at the progressBar
	 */
	public void BonusTimer() {
		
		timeSeconds.setValue(timeSeconds.get() + Settings.getInstance().getBonusTimer());
		TIME = timeSeconds.get();
		getTm().stop();
		tm = null;	
		getTm().play();

	}


}
