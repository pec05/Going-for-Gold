package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import util.IConstant;


/**
 * The class holds the rules interface
 * 
 * @author Peccio Leandro
 *
 */
public class RulesBP extends BorderPane{
	
	//BUTTONS
	/**
	* Every Button this class has to display.
	*/
	private Button btnBack;
	
	//LABELS
	/**
	* Every Label this class has to display.
	*/
	private Label lblRules;
	
	//TEXT
	/**
	* Every Text this class has to display.
	*/
	private Text txtRules;

	
	/**
	 * Constructor of RulesBP.
	 * Adds all components to its layout.
	 */
	public RulesBP() {
		
		HBox hbRules = new HBox();
		this.setCenter(getTxtRules());	
		
		HBox hb = new HBox();
		hb.getChildren().add(getBtnBack());
		this.setTop(hb);
		hb.setAlignment(Pos.TOP_LEFT);
		hb.setPadding(new Insets(IConstant.BTN_BACK_POSITION_TOP,0,0,IConstant.BTN_BACK_POSITION_LEFT));		
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
	 * This method returns the Button btnBack and instantiates it if it's null.
	 * @return The Button btnBack
	 */
	public Button getBtnBack() {
		if(btnBack == null)
		{
			btnBack = new Button("");
			btnBack.setPrefSize(IConstant.BTN_BACK_WIDTH, IConstant.BTN_BACK_HEIGHT);
			shadow(btnBack);
			btnBack.setId("buttonBack");
		}
		return btnBack;
	}
	
	/**
	 * This method returns the Label lblRules and instantiates it if it's null.
	 * @return The Label lblRules
	 */
	public Label getLblRules() {
		return lblRules;
	}
	
	/**
	 * This method returns the Text txtRules and instantiates it if it's null.
	 * @return The Text txtRules
	 */
	public Text getTxtRules() {
		if(txtRules == null)
		{
			String rules = "Answer as many questions as possible in a row.\n\r"
					+ "You have 4 points, YOU WIN !\n\r"
					+ "But a wrong answer back you to zero.\n\r"
					+ "3 bonuses help you to reach the 4 points :\n\r"
					+"     - BONUS TIMER : gives you an extra 30 seconds;\n"
					+"     - BONUS LETTER : gives you the first letter of the answer;\n"
					+"     - BONUS NUMBER : gives you the number of letters in the answer.\n\r"
					+"GOOD LUCK !";
			txtRules = new Text(rules);
			txtRules.setFont(Font.loadFont("file:Resources/fonts/banksia.ttf", 20.));
			txtRules.setTextAlignment(TextAlignment.CENTER);
		}
		return txtRules;
	}


	

}
