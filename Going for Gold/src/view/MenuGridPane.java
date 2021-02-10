package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import util.IConstant;

/**
 * The class holds the menu interface
 * 
 * @author Peccio Leandro
 *
 */
public class MenuGridPane extends GridPane{
	
	//BUTTONS
	/**
	* Every Button this class has to display.
	*/
	private Button btnNewGame, btnRules, btnCredits, btnScoreboard, btnLogin, btnAdminMode, btnQuit;
	
	//LABELS
	/**
	 * Every Label this class has to display.
	 */
	private Label lblSize1, lblSize2;

	/**
	 * Constructor of MenuGridPane.
	 * Adds all components to its layout.
	 */
	public MenuGridPane() {

		this.setVgap(15);

		this.setPadding(new Insets(75));
		this.setAlignment(Pos.CENTER);
		
		this.add(getLblSize1(),0, 0);
		this.add(getBtnNewGame(), 1, 1);	
		this.add(getBtnRules(), 1, 2);
		this.add(getBtnCredits(), 1, 3);
		this.add(getBtnScoreboard(), 1, 4);
		this.add(getBtnAdminMode(), 1, 6);
		this.add(getBtnQuit(), 1, 7);
		this.add(getLblSize2(),2, 7);

	}
	
	
	/**
	 * This method is a shadow effect when you pass the mouse on buttons
	 */
	public void shadow(Button button) {
		DropShadow shadow = new DropShadow();
		
		button.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			button.setEffect(shadow);	
		});
		button.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			button.setEffect(null);	
		});
		
	}

	/**
	 * This method returns the Button btnNewGame and instantiates it if it's null.
	 * @return The Button btnNewGame
	 */
	public Button getBtnNewGame() {
		if(btnNewGame == null)
		{
			btnNewGame = new Button("New game");
			btnNewGame.setMinSize(IConstant.MENU_BTN_WIDTH, IConstant.MENU_BTN_HEIGHT);
			shadow(btnNewGame);
			btnNewGame.setId("buttonMenu");
			
		
		}
		return btnNewGame;
	}


	/**
	 * This method returns the Button btnRules and instantiates it if it's null.
	 * @return The Button btnRules
	 */
	public Button getBtnRules() {
		if(btnRules == null)
		{
			btnRules = new Button("Rules");
			btnRules.setMinSize(IConstant.MENU_BTN_WIDTH, IConstant.MENU_BTN_HEIGHT);
			shadow(btnRules);
			btnRules.setId("buttonMenu");
		}
		return btnRules;
	}


	/**
	 * This method returns the Button btnCredits and instantiates it if it's null.
	 * @return The Button btnCredits
	 */
	public Button getBtnCredits() {
		if(btnCredits == null)
		{
			btnCredits = new Button("Credits");
			btnCredits.setMinSize(IConstant.MENU_BTN_WIDTH, IConstant.MENU_BTN_HEIGHT);
			shadow(btnCredits);
			btnCredits.setId("buttonMenu");
		}
		return btnCredits;
	}


	/**
	 * This method returns the Button btnScoreboard and instantiates it if it's null.
	 * @return The Button btnScoreboard
	 */
	public Button getBtnScoreboard() {
		if(btnScoreboard == null)
		{
			btnScoreboard = new Button("Scoreboard");
			btnScoreboard.setMinSize(IConstant.MENU_BTN_WIDTH, IConstant.MENU_BTN_HEIGHT);
			shadow(btnScoreboard);
			btnScoreboard.setId("buttonMenu");
		}
		return btnScoreboard;
	}


	/**
	 * This method returns the Button btnLogin and instantiates it if it's null.
	 * @return The Button btnLogin
	 */
	public Button getBtnLogin() {
		if(btnLogin == null)
		{
			btnLogin = new Button("Login");
			btnLogin.setMinSize(IConstant.MENU_BTN_WIDTH, IConstant.MENU_BTN_HEIGHT);
			shadow(btnLogin);
			btnLogin.setId("buttonMenu");
		}
		return btnLogin;
	}


	/**
	 * This method returns the Button btnAdminMode and instantiates it if it's null.
	 * @return The Button btnAdminMode
	 */
	public Button getBtnAdminMode() {
		if(btnAdminMode == null)
		{
			btnAdminMode = new Button("Admin mode");
			btnAdminMode.setMinSize(IConstant.MENU_BTN_WIDTH, IConstant.MENU_BTN_HEIGHT);
			shadow(btnAdminMode);
			btnAdminMode.setId("buttonMenu");
		}
		return btnAdminMode;
	}

	/**
	 * This method returns the Button btnQuit and instantiates it if it's null.
	 * @return The Button btnQuit
	 */
	public Button getBtnQuit() {
		if(btnQuit == null)
		{
			btnQuit = new Button("Quit");
			btnQuit.setMinSize(IConstant.MENU_BTN_WIDTH, IConstant.MENU_BTN_HEIGHT);
			shadow(btnQuit);
			btnQuit.setId("buttonMenu");
			btnQuit.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					System.exit(0);
					
				}
			});
		}
		return btnQuit;
	}
	
	/**
	 * This method returns the Label lblSize1 and instantiates it if it's null.
	 * @return The Label lblSize1
	 */
	public Label getLblSize1() {
		if(lblSize1 == null)
		{
			lblSize1 = new Label();
			lblSize1.setMinSize(IConstant.MENU_LBL_WIDTH, IConstant.MENU_LBL_HEIGHT);
			
			
		}
		return lblSize1;
	}
	
	/**
	 * This method returns the Label lblSize2 and instantiates it if it's null.
	 * @return The Label lblSize2
	 */
	public Label getLblSize2() {
		if(lblSize2 == null)
		{
			lblSize2 = new Label();
			lblSize2.setMinSize(IConstant.MENU_LBL_WIDTH, IConstant.MENU_LBL_HEIGHT);
			
		}
		return lblSize2;
	}
	
	
	
	

}
