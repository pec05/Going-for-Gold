package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import util.IConstant;

/**
 * The class holds the pause interface
 * 
 * @author Peccio Leandro
 *
 */
public class PauseAnchorPane extends AnchorPane{
	
	//BUTTONS
	/**
	* Every Button this class has to display.
	*/
	private static Button btnMainMenu, btnReset, btnContinue;
	//LABELS
	/**
	* Every Label this class has to display.
	*/
	private Label labelPause;
	
	
	/**
	 * Constructor of PauseAnchorPane.
	 * Adds all components to its layout.
	 */	
	public PauseAnchorPane() {
		// TODO Auto-generated constructor stub
	
		this.setId("pauseAP");
		this.setPrefSize(1200., 650.);
		VBox ButtonMenu = new VBox();
		ButtonMenu.getChildren().addAll(getLabelPause(), getBtnContinue(), getBtnReset(), getBtnMainMenu());
		ButtonMenu.setSpacing(10.0);
		ButtonMenu.setAlignment(Pos.CENTER);
		ButtonMenu.setId("buttonPause");
		PauseAnchorPane.setTopAnchor(ButtonMenu, 150.);PauseAnchorPane.setLeftAnchor(ButtonMenu, 350.);
		this.getChildren().add(ButtonMenu);
		
		
	}
	
	/**
	 * This method returns the Button btnMainMenu and instantiates it if it's null.
	 * @return The Button btnMainMenu
	 */
	public static Button getBtnMainMenu() {
		if(btnMainMenu == null) {
			btnMainMenu = new Button("Main Menu");
			btnMainMenu.setPrefSize(IConstant.PAUSE_BTN_WIDTH, IConstant.PAUSE_BTN_HEIGHT);
			btnMainMenu.setId("buttonPause");
		}
		return btnMainMenu;
	}

	/**
	 * This method returns the Button btnReset and instantiates it if it's null.
	 * @return The Button btnReset
	 */
	public static Button getBtnReset() {
		if(btnReset == null) {
			btnReset = new Button("Reset");
			btnReset.setPrefSize(IConstant.PAUSE_BTN_WIDTH, IConstant.PAUSE_BTN_HEIGHT);
			btnReset.setId("buttonPause");
		}
		return btnReset;
	}

	/**
	 * This method returns the Button btnContinue and instantiates it if it's null.
	 * @return The Button btnContinue
	 */
	public static Button getBtnContinue() {
		if(btnContinue == null) {
			btnContinue = new Button("Continue");
			btnContinue.setPrefSize(IConstant.PAUSE_BTN_WIDTH, IConstant.PAUSE_BTN_HEIGHT);
			btnContinue.setId("buttonPause");
		}
		return btnContinue;
	}

	/**
	 * This method returns the Button labelPause and instantiates it if it's null.
	 * @return The Button labelPause
	 */
	public Label getLabelPause() {
		if(labelPause == null) {
			labelPause = new Label("Pause");
		}
		return labelPause;
	}

	
	
}
