 package view;
import java.util.ArrayList;
import java.util.Arrays;
import exception.ExceptionDouble;
import exception.ExceptionEmptyField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.Deck;
import model.Question;
import util.IConstant;


/**
* This class is used to creates new question and then this new question is put in the list that are in the deck.
* 
* @author Peccio Leandro
*
*/
public class AddQuestionGridPane extends GridPane {
	
	//LABELS
	/**
	* Labels displaying what the text fields need.
	*/
	private Label lblError, lblAuthor, lblTheme, lblCluesOne, lblCluesTwo, lblCLuesThree, lblAnswer;
	
	//TEXTFIELD
	/**
	* Text fields associated to the labels.
	*/
	private TextField textFieldAuthor, textFieldTheme, textFieldCluesOne, textFieldCluesTwo, textFieldCluesThree, textFieldAnswer;
	
	//BUTTON
	/*
	 * Button to save the new question.
	 */
	private Button btnOK;
	
	/**
	 * Constructor of AddQuestionGridPane.
	 * Adds all components to its layout.
	 */
	public AddQuestionGridPane() {
		// TODO Auto-generated constructor stub
		this.setHgap(10);
		this.setVgap(10);
		this.setPadding(new Insets(150., 10., 10., 300.));
		this.add(getLblAuthor(), 0, 1);
		this.add(getTextFieldAuthor(), 1, 1);
		this.add(getLblTheme(), 0, 2);
		this.add(getTextFieldTheme(), 1, 2);
		this.add(getLblCluesOne(), 0, 3);
		this.add(getTextFieldCluesOne(), 1, 3);
		this.add(getLblCluesTwo(), 0, 4);
		this.add(getTextFieldCluesTwo(), 1, 4);
		this.add(getLblCLuesThree(), 0, 5);
		this.add(getTextFieldCluesThree(), 1, 5);
		this.add(getLblAnswer(), 0, 6);
		this.add(getTextFieldAnswer(), 1, 6);
		this.add(getLblError(), 0, 8);
		this.add(getBtnOK(), 1, 8);
	
	}
	
	
	/**
	 * This method returns the TextField textFieldAuthor and instantiates it if it's null.
	 * @return The TextField textFieldAuthor
	 */
	public TextField getTextFieldAuthor() {
		if(textFieldAuthor == null)
		{
			textFieldAuthor = new TextField();
			textFieldAuthor.setPromptText("Enter the autor's name");
		}
		return textFieldAuthor;
	}

	/**
	 * This method returns the TextField textFieldTheme and instantiates it if it's null.
	 * @return The TextField textFieldTheme
	 */
	public TextField getTextFieldTheme() {
		if(textFieldTheme == null)
		{
			textFieldTheme = new TextField();
			textFieldTheme.setPromptText("Enter the theme");
		}
		return textFieldTheme;
	}

	/**
	 * This method returns the TextField textFieldCluesOne and instantiates it if it's null.
	 * @return The TextField textFieldCluesOne
	 */
	public TextField getTextFieldCluesOne() {
		if(textFieldCluesOne == null)
		{
			textFieldCluesOne = new TextField();
			textFieldCluesOne.setPromptText("Enter clue 1");
		}
		return textFieldCluesOne;
	}

	/**
	 * This method returns the TextField textFieldCluesTwo and instantiates it if it's null.
	 * @return The TextField textFieldCluesTwo
	 */
	public TextField getTextFieldCluesTwo() {
		if(textFieldCluesTwo== null)
		{
			textFieldCluesTwo = new TextField();
			textFieldCluesTwo.setPromptText("Enter clue 2");
		}
		return textFieldCluesTwo;
	}

	/**
	 * This method returns the TextField textFieldCluesThree and instantiates it if it's null.
	 * @return The TextField textFieldCluesThree
	 */
	public TextField getTextFieldCluesThree() {
		if(textFieldCluesThree == null)
		{
			textFieldCluesThree = new TextField();
			textFieldCluesThree.setPromptText("Enter clue 3");
		}
		return textFieldCluesThree;
	}

	/**
	 * This method returns the Button btnOK and instantiates it if it's null.
	 * @return The Button btnOK
	 */
	public Button getBtnOK() {
		if(btnOK == null)
		{
			btnOK = new Button("OK");
			btnOK.setPrefSize(IConstant.ADMIN_BTN_WIDTH, IConstant.ADMIN_BTN_HEIGHT);
			btnOK.setId("btnAdmin");
			btnOK.setOnAction(new EventHandler<ActionEvent>() {
				/**
				 * When you click on the button btnOk, this method creates a new question with the name of the author, the theme of the question,the clues of the question and the answer.
				 * If there is an error, the method throws an exception and the error message is shown. The method loads all the question in a deck then add the new question
				 * then this deck with the new question is saved in a json file.
				 */
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					Question q = null;
					try {
						q = new Question(getTextFieldAuthor().getText(), getTextFieldTheme().getText(), new ArrayList<String>(Arrays.asList(getTextFieldCluesOne().getText(), getTextFieldCluesTwo().getText(), getTextFieldCluesThree().getText())), getTextFieldAnswer().getText());
						Deck.getInstance().add(q.clone());
						
						if(btnOK.getParent().getParent() instanceof UtilStackpane) {
		
							UtilStackpane sp = (UtilStackpane) btnOK.getParent().getParent();
							
							boolean x = true;
							if(x) {
								sp.getAdminWindowBP().getTvQuestion().getItems().add(q);
							}
						}
						((UtilStackpane) getParent()).hideAll();
						((UtilStackpane) getParent()).getChildren().get(IListPane.ADMIN).setVisible(true);

						Deck.getInstance().saveJson();
						clearText();
						
					} catch (ExceptionEmptyField e) {
						// TODO Auto-generated catch block
						Alert alert = new Alert(AlertType.ERROR,"Empty field",ButtonType.OK);
						alert.show();
					}
					catch (ExceptionDouble e) {
						// TODO: handle exception
						Alert alert = new Alert(AlertType.ERROR,"doublon",ButtonType.OK);
						alert.show();
					}

				}
			});
		}
		return btnOK;
	}

	/**
	 * This method returns the TextField textFieldAnswer and instantiates it if it's null.
	 * @return The TextField textFieldAnswer
	 */
	public TextField getTextFieldAnswer() {
		if(textFieldAnswer == null)
		{
			textFieldAnswer = new TextField();
			textFieldAnswer.setPromptText("Enter the answer");
		}
		return textFieldAnswer;
	}


	/**
	 * This method returns the Label lblError and instantiates it if it's null.
	 * @return The Label lblError
	 */
	public Label getLblError() {
		if (lblError == null)
		{
			lblError = new Label();
			lblError.setTextFill(Color.web("red"));
		}
		return lblError;
	}
	
	/**
	 * This method returns the Label lblAuthor and instantiates it if it's null.
	 * @return The Label lblAuthor
	 */
	public Label getLblAuthor() {
		if (lblAuthor == null)
		{
			lblAuthor = new Label("Author : ");
			lblAuthor.setTextFill(Color.web("blue"));
		}
		return lblAuthor;
	}

	/**
	 * This method returns the Label lblTheme and instantiates it if it's null.
	 * @return The Label lblTheme
	 */
	public Label getLblTheme() {
		if (lblTheme == null)
		{
			lblTheme = new Label("Theme : ");
			lblTheme.setTextFill(Color.web("blue"));
		}
		return lblTheme;
	}

	/**
	 * This method returns the Label lblCluesOne and instantiates it if it's null.
	 * @return The Label lblCluesOne
	 */
	public Label getLblCluesOne() {
		if (lblCluesOne == null)
		{
			lblCluesOne = new Label("Clue 1 : ");
			lblCluesOne.setTextFill(Color.web("blue"));
		}
		return lblCluesOne;
	}

	/**
	 * This method returns the Label lblCluesTwo and instantiates it if it's null.
	 * @return The Label lblCluesTwo
	 */
	public Label getLblCluesTwo() {
		if (lblCluesTwo == null)
		{
			lblCluesTwo = new Label("Clue 2 : ");
			lblCluesTwo.setTextFill(Color.web("blue"));
		}
		return lblCluesTwo;
	}

	/**
	 * This method returns the Label lblCLuesThree and instantiates it if it's null.
	 * @return The Label lblCLuesThree
	 */
	public Label getLblCLuesThree() {
		if (lblCLuesThree == null)
		{
			lblCLuesThree = new Label("Clue 3 : ");
			lblCLuesThree.setTextFill(Color.web("blue"));
		}
		return lblCLuesThree;
	}

	/**
	 * This method returns the Label lblAnswer and instantiates it if it's null.
	 * @return The Label lblAnswer
	 */
	public Label getLblAnswer() {
		if (lblAnswer == null)
		{
			lblAnswer = new Label("Answer : ");
			lblAnswer.setTextFill(Color.web("blue"));
		}
		return lblAnswer;
	}


	/**
	 * This method clears the textfields .
	 */
	public void clearText() {
		getTextFieldAuthor().clear();
		getTextFieldTheme().clear();
		getTextFieldCluesOne().clear();
		getTextFieldCluesTwo().clear();
		getTextFieldCluesThree().clear();
		getTextFieldAnswer().clear();
	}
	
}
