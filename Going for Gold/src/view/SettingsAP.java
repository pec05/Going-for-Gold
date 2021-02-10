package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Settings;

/**
 * The class holds the settings interface
 * 
 * @author Peccio Leandro
 *
 */
public class SettingsAP  extends AnchorPane{
	
	//SPINNERS
	/**
	* Every Spinner this class has to display.
	*/
	private Spinner<Integer> spClueDelay;
	private Spinner<Integer> spTimerTotal;
	private Spinner<Integer> spDistance;
	private Spinner<Integer> spBonusTimer;
	
	//LABELS
	/**
	* Every Label this class has to display.
	*/
	private Label lblClueDelay;
	private Label lblTimerTotal;
	private Label lblDistance;
	private Label lblBonusTimer;
	 
	//BUTTONS
	/**
	 * Every Button this class has to display.
	*/
	private Button btnBack, btnSave;
	
	/**
	 * Constructor of SettingsAP.
	 * Adds all components to its layout.
	 */
	public SettingsAP() {
		// TODO Auto-generated constructor stub
		VBox vbLabel = new VBox();
		vbLabel.getChildren().addAll(getLblClueDelay(),getLblTimerTotal(), getLblDistance(), getLblBonusTimer());
		vbLabel.setSpacing(27.);
		setLeftAnchor(vbLabel, 400.); setTopAnchor(vbLabel, 200.);
		
		VBox vbSpinner = new VBox();
		vbSpinner.getChildren().addAll(getSpClueDelay(),getSpTimerTotal(), getSpDistance(), getSpBonusTimer());
		vbSpinner.setSpacing(15.);
		setLeftAnchor(vbSpinner, 550.); setTopAnchor(vbSpinner, 200.);
		
		
		HBox hbButtons = new HBox();
		hbButtons.getChildren().addAll(getBtnBack(),getBtnSave());
		hbButtons.setSpacing(5.);
		setLeftAnchor(hbButtons,700.); setTopAnchor(hbButtons, 550.);
		
		getChildren().addAll(vbLabel,
				vbSpinner,
				hbButtons); 
		
		
		
	}

	/**
	 * This method returns the Spinner spClueDelay and instantiates it if it's null.
	 * @return The Spinner spClueDelay
	 */
	public Spinner<Integer> getSpClueDelay() {
		if(spClueDelay == null) {
			spClueDelay = new Spinner<>(Settings.getInstance().getClueDelay()/1000,Integer.MAX_VALUE,1);
		}
		return spClueDelay;
	}

	/**
	 * This method returns the Spinner spTimerTotal and instantiates it if it's null.
	 * @return The Spinner spTimerTotal
	 */
	public Spinner<Integer> getSpTimerTotal() {
		if(spTimerTotal == null) {
			spTimerTotal = new Spinner<>(Settings.getInstance().getTimerTotal(),Integer.MAX_VALUE,1);
		}
		return spTimerTotal;
	}

	/**
	 * This method returns the Spinner spDistance and instantiates it if it's null.
	 * @return The Spinner spDistance
	 */
	public Spinner<Integer> getSpDistance() {
		if(spDistance == null) {
			spDistance = new Spinner<>(Settings.getInstance().getDistance(),Integer.MAX_VALUE,1);
			
		}
		return spDistance;
	}
	
	/**
	 * This method returns the Spinner spBonusTimer and instantiates it if it's null.
	 * @return The Spinner spBonusTimer
	 */
	public Spinner<Integer> getSpBonusTimer() {
		if(spBonusTimer == null) {
			spBonusTimer = new Spinner<>(Settings.getInstance().getBonusTimer(),Integer.MAX_VALUE,1);
			
		}
		return spBonusTimer;
	}

	/**
	 * This method returns the Label lblClueDelay and instantiates it if it's null.
	 * @return The Label lblClueDelay
	 */
	public Label getLblClueDelay() {
		if(lblClueDelay == null) {
			lblClueDelay = new Label("Clue Delay");
			lblClueDelay.setTextFill(Color.rgb(0, 150, 255));
		}
		return lblClueDelay;
	}

	/**
	 * This method returns the Label lblTimerTotal and instantiates it if it's null.
	 * @return The Label lblTimerTotal
	 */
	public Label getLblTimerTotal() {
		if(lblTimerTotal == null) {
			lblTimerTotal = new Label("Timer total");
			lblTimerTotal.setTextFill(Color.rgb(0, 150, 255));
		}
		return lblTimerTotal;
	}

	/**
	 * This method returns the Label lblDistance and instantiates it if it's null.
	 * @return The Label lblDistance
	 */
	public Label getLblDistance() {
		if(lblDistance == null) {
			lblDistance = new Label("Distance");
			lblDistance.setTextFill(Color.rgb(0, 150, 255));
		}
		return lblDistance;
	}

	/**
	 * This method returns the Label lblBonusTimer and instantiates it if it's null.
	 * @return The Label lblBonusTimer
	 */
	public Label getLblBonusTimer() {
		if(lblBonusTimer == null) {
			lblBonusTimer = new Label("Bonus timer");
			lblBonusTimer.setTextFill(Color.rgb(0, 150, 255));
		}
		return lblBonusTimer;
	}

	/**
	 * This method returns the Button btnBack and instantiates it if it's null.
	 * @return The Button btnBack
	 */
	public Button getBtnBack() {
		if(btnBack == null) {
			btnBack = new Button("Back");
		}
		return btnBack;
	}

	/**
	 * This method returns the Button btnSave and instantiates it if it's null.
	 * @return The Button btnSave
	 */
	public Button getBtnSave() {
		if(btnSave == null) {
			btnSave = new Button("Save & Quit");
			
			btnSave.setOnAction(new EventHandler<ActionEvent>() {
				/** This method is use to design the button time, a bonus, but there is an action when you click on it.
				 * When you click on it. The program save the new constants.
				 * 
				 */
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					Settings.getInstance().setClueDelay(getSpClueDelay().getValue()*1000);
					Settings.getInstance().setTimerTotal(getSpTimerTotal().getValue());
					Settings.getInstance().setDistance(getSpDistance().getValue());
					Settings.getInstance().setBonusTimer(getSpBonusTimer().getValue());
					Settings.getInstance().saveSettings(); 
					Settings.getInstance().reloadConfig();
					
					((UtilStackpane) getParent()).hideAll();
					((UtilStackpane) getParent()).getChildren().get(IListPane.ADMIN).setVisible(true);
				}
			});
		}
		return btnSave;
	}

}
