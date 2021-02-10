package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.ManagementUser;
import util.IConstant;


/**
 * The class holds the connect an register interface
 * 
 * 
 * @author Peccio Leandro
 *
 */
public class LoginUserAP extends AnchorPane {
	
	/**
	 * The ManagementUser instance.
	 */
	private ManagementUser managementUser;
	
	//BUTTONS
	/**
	* Every Button this class has to display.
	*/
	private Button btnConnexion, btnRegistration, btnSkip;
	
	//LABELS
	/**
	* Every Label this class has to display.
	*/
	private Label lblLogin, lblPassWord;
	
	//TEXTFIELD
		/**
		* Every TextField this class has to display.
		*/
	private TextField txtLogin;
	private Text txtWrongLogin;
	
	//PasswordField
			/**
			* Every PasswordField this class has to display.
			*/
	private PasswordField psfPassword;
	 
	
	/**
	 * Constructor of LoginUserAP.
	 * Adds all components to its layout.
	 */
	public LoginUserAP(ManagementUser mgu) {
		
		if (mgu != null)
		{
			this.managementUser = mgu;
		} else
		{
			managementUser = new ManagementUser();
		}
		
		
		// login
		
				HBox hbLogin = new HBox();
				hbLogin.getChildren().addAll(getLblLogin(), getTxtLogin());
				hbLogin.setSpacing(15.);
				setLeftAnchor(hbLogin, 350.); setTopAnchor(hbLogin, 150.);
				
				//password 
				
				HBox hbPw = new HBox();
				hbPw.getChildren().addAll(getLblPassWord(), getPsfPassword());
				hbPw.setSpacing(15.);
				setLeftAnchor(hbPw, 350.); setTopAnchor(hbPw, 230.);
				
				//warning wrong login 
				
				setLeftAnchor(getTxtWrongLogin(), 350.); setTopAnchor(getTxtWrongLogin(), 300.);
				
				//button
				
				HBox hbButton= new HBox();
				hbButton.getChildren().addAll(getBtnConnexion(), getBtnRegistration(), getBtnSkip());
				hbButton.setSpacing(15.);
				setLeftAnchor(hbButton, 250.); setTopAnchor(hbButton, 400.);
		
				//display
				
				getChildren().addAll(hbLogin, hbPw, getTxtWrongLogin(), hbButton);
				
	}
	
	/**
	 * This method returns the ManagementUser managementUser.
	 * @return The ManagementUser managementUser
	 */
	public ManagementUser getManagementUser() {
		return managementUser;
	}


	/**
	 * This method returns the Label lblLogin and instantiates it if it's null.
	 * @return The Label lblLogin
	 */
	public Label getLblLogin() {
		
		if(lblLogin == null) {
			
			lblLogin = new Label("LOGIN : ");
			lblLogin.setTextAlignment(TextAlignment.CENTER);
			lblLogin.setPrefSize(IConstant.TXT_WIDTH, IConstant.TXT_HEIGHT);
			lblLogin.setTextFill(Color.rgb(0, 150, 255));
		}
		return lblLogin;
	}

	
	/**
	 * This method returns the Label lblPassWord and instantiates it if it's null.
	 * @return The Label lblPassWord
	 */
	public Label getLblPassWord() {
		
		if(lblPassWord == null) {
			
			lblPassWord = new Label("PASSWORD : ");
			lblPassWord.setPrefSize(IConstant.TXT_WIDTH, IConstant.TXT_HEIGHT);
			lblPassWord.setTextFill(Color.rgb(0, 150, 255));
		}
		return lblPassWord;
	}

	/**
	 * This method returns the Button btnConnexion and instantiates it if it's null.
	 * @return The Button btnConnexion
	 */
	public Button getBtnConnexion() {
		
		if(btnConnexion == null) {
			
			btnConnexion = new Button("Connexion");
			btnConnexion.setPrefSize(IConstant.BUTTON_WIDTH, IConstant.BUTTON_HEIGHT);
			btnConnexion.setId("btnLoginUser");
			
		}
		return btnConnexion;
	}

	/**
	 * This method returns the Button btnRegistration and instantiates it if it's null.
	 * @return The Button btnRegistration
	 */
	public Button getBtnRegistration() {
		
		if(btnRegistration == null) {
			
			btnRegistration = new Button("Registration");
			btnRegistration.setPrefSize(IConstant.BUTTON_WIDTH, IConstant.BUTTON_HEIGHT);
			btnRegistration.setId("btnLoginUser");
			
		}
		return btnRegistration;
	}

	/**
	 * This method returns the TextField txtLogin and instantiates it if it's null.
	 * @return The TextField txtLogin
	 */
	public TextField getTxtLogin() {
		
		if(txtLogin == null)
		{
			txtLogin = new TextField();
			txtLogin.setPromptText("Enter your login");
			txtLogin.setPrefSize(IConstant.TXT_WIDTH, IConstant.TXT_HEIGHT);
			
		}
		return txtLogin;
	}

	/**
	 * This method returns the PasswordField psfPassword and instantiates it if it's null.
	 * @return The PasswordField psfPassword
	 */
	public PasswordField getPsfPassword() {
		
		if(psfPassword == null)
		{
			psfPassword = new PasswordField();
			psfPassword.setPromptText("Enter your password");
			psfPassword.setPrefSize(IConstant.TXT_WIDTH, IConstant.TXT_HEIGHT);
		}
		return psfPassword;
	}

	/**
	 * This method returns the Button btnSkip and instantiates it if it's null.
	 * @return The Button btnSkip
	 */
	public Button getBtnSkip() {
		
		if(btnSkip == null)
		{
			btnSkip = new Button("Skip");
			btnSkip.setPrefSize(IConstant.BUTTON_WIDTH, IConstant.BUTTON_HEIGHT);
			btnSkip.setId("btnLoginUser");
		}
		return btnSkip;
	}

	/**
	 * This method returns the Text txtWrongLogin and instantiates it if it's null.
	 * @return The Text txtWrongLogin
	 */
	public Text getTxtWrongLogin() {
		if(txtWrongLogin == null)	
		{
			txtWrongLogin = new Text(" ");
			txtWrongLogin.setFont(Font.font("Arial", FontWeight.BOLD, 20));
			txtWrongLogin.setFill(Color.RED);
			txtWrongLogin.prefWidth(IConstant.TXT_WIDTH);
			txtWrongLogin.prefHeight(IConstant.TXT_HEIGHT);
			
		}
		return txtWrongLogin;
	}
	

}
