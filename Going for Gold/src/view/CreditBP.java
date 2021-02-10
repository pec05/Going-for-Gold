package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import util.IConstant;

/**
* This class is used to put the name of the developers' team, professors 
*
* @author Peccio Leandro
*
*/
public class CreditBP extends BorderPane {
		
		//BUTTON
		/*
		 * Button to back on the menu
		 */
		private Button btnBack;
		
		//TEXT
		/*
		 * text of the credits
		 */
		private Text txtCredits;

		/**
		 * Constructor of CreditBP.
		 * Adds all components to its layout.
		 */
		public  CreditBP() {
			
			this.setCenter(getTxtCredits());	

			HBox hb = new HBox();
			hb.getChildren().add(getBtnBack());
			this.setTop(hb);
			hb.setAlignment(Pos.TOP_LEFT);
			hb.setPadding(new Insets(IConstant.BTN_BACK_POSITION_TOP,0,0,IConstant.BTN_BACK_POSITION_LEFT));		
			
		}

		/**
		 * this method gives a shadow effect when we pass on the buttons
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
		 * This method returns the Text txtCredits and instantiates it if it's null.
		 * @return The Text txtCredits
		 */
		public Text getTxtCredits() {
			if(txtCredits == null)
			{
				String credit = "DEVELOPER : Leandro PECCIO - Claude GILLES - Alex TRIBUZIO\n\r"
						+ "SUPERVISOR : M. V. ALTARES - M. S. CRESPIN - M. L. GODEFROID.\n\r"
						+ "GRAPHIQUE DESIGNER : Katherine GILLES.\n\r";
				txtCredits = new Text(credit);
				txtCredits.setFont(Font.loadFont("file:Resources/fonts/banksia.ttf", 20.));
				txtCredits.setTextAlignment(TextAlignment.CENTER);
				txtCredits.prefWidth(500.);
				txtCredits.prefHeight(400.);
				txtCredits.setId("credit");
				}
			return txtCredits;
		}

		
}
