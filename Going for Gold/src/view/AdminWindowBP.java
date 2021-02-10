package view;

import java.io.File;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import model.Deck;
import model.Question;
import util.IConstant;
import util.Serialisation;

/**
* this class show all the questions in a table view.
* 
* @author Peccio Leandro
*
*/
public class AdminWindowBP extends BorderPane{
	
	//BUTTON
	/*
	 * Button to add a new question
	 * Button to back on menu
	 * Button to delete a question
	 * Button to update a question
	 * Button to load a new deck
	 * Button to modify the constant of the game
	 */
	private Button  btnAdd, btnBack, btnDelete, btnModify, btnLoadFile, btnSettings;
	
	//TableView
	/*
	* contains a list of questions
	*/
	private TableView<Question> tvQuestion;
	
	ObservableList<Question>data = null;
	
	/**
	 * The Deck instance.
	 */
	private Deck deck;
	 
	
	/**
	 * Constructor of AdminWindowBP.
	 * Adds all components to its layout.
	 */
	public AdminWindowBP(Deck deck) {
		this.setCenter(getTvQuestion());
	
		
		/////Left////
		VBox vbLeft = new VBox();
		vbLeft.setSpacing(10.);
		vbLeft.setPadding(new Insets(200., 10., 10., 100.));
		vbLeft.getChildren().addAll(getBtnDelete(), getBtnAdd(), getBtnBack(), getBtnModify(), getBtnLoadFile(), getBtnSettings());
		vbLeft.setSpacing(10.);
		this.setLeft(vbLeft);
		
		if (deck != null)
		{
			this.deck = deck;
		} else
		{
			deck = new Deck();
		}
	
	}
	
	/**
	 * This method creates a table view with all the questions that are in the deck.
	 * @return tvQuestion
	 */
	public TableView<Question> getTvQuestion() {
		if(tvQuestion == null) {
			tvQuestion = new TableView<Question>();
			tvQuestion.setPrefWidth(500.);
			
			data = FXCollections.observableArrayList(deck.getInstance().getQuestions());
			tvQuestion.setEditable(true);
			
			tvQuestion.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			tvQuestion.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			TableColumn<Question, String> colAuthor = new TableColumn<Question, String>("Author");
			TableColumn<Question, String> colTheme = new TableColumn<Question, String>("Theme");
			TableColumn<Question, String> colClues = new TableColumn<Question, String>("Clues");
			TableColumn<Question, String> colClues1 = new TableColumn<Question, String>("Clues1");
			TableColumn<Question, String> colClues2 = new TableColumn<Question, String>("Clues2");
			TableColumn<Question, String> colClues3 = new TableColumn<Question, String>("Clues3");
			TableColumn<Question, String> colAnswer = new TableColumn<Question, String>("Answer");
			
			colClues.getColumns().addAll(colClues1, colClues2, colClues3);
			
			colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
			colTheme.setCellValueFactory(new PropertyValueFactory<>("theme"));
			colClues1.setCellValueFactory(tmp -> new SimpleStringProperty(tmp.getValue().getClues().get(0)));
			colClues2.setCellValueFactory(tmp -> new SimpleStringProperty(tmp.getValue().getClues().get(1)));
			colClues3.setCellValueFactory(tmp -> new SimpleStringProperty(tmp.getValue().getClues().get(2)));
			colAnswer.setCellValueFactory(new PropertyValueFactory<>("answer"));
			
			tvQuestion.getColumns().addAll(colAuthor, colTheme, colClues, colAnswer);
			tvQuestion.setItems(data);
			
			tvQuestion.refresh();
		}
			
		return tvQuestion;
	}

	/**
	 * This method returns the Button btnDelete and instantiates it if it's null.
	 * @return The Button btnDelete
	 */
	public Button getBtnDelete() {
		if(btnDelete == null)
		{
			btnDelete = new Button("Delete");
			btnDelete.setPrefSize(IConstant.ADMIN_BTN_WIDTH, IConstant.ADMIN_BTN_HEIGHT);
			btnDelete.setId("btnAdmin");
			btnDelete.setOnAction(new EventHandler<ActionEvent>() {
				
				/**
				 * When you click on the button btnDelete, this method delete a question target of this tableview and the deck.json
				 */
				@Override
				public void handle(ActionEvent event) {
					
					List<Question> toRemove = getTvQuestion().getSelectionModel().getSelectedItems();
					System.out.println("deck : "+getDeck());
					getDeck().getInstance().removeAll(toRemove);
					getTvQuestion().getItems().removeAll(toRemove);
					getDeck().getInstance().saveJson();
					System.out.println("delete done");
				}
			});
		}
		return btnDelete;
	}
	
	/**
	 * This method returns the Button btnAdd and instantiates it if it's null.
	 * @return The Button btnAdd
	 */
	public Button getBtnAdd() 
	{
		if(btnAdd==null)
		{
			btnAdd = new Button("Add");
			btnAdd.setPrefSize(IConstant.ADMIN_BTN_WIDTH, IConstant.ADMIN_BTN_HEIGHT);
			btnAdd.setId("btnAdmin");
		
		}
		return btnAdd;
	}
	
	/**
	 * This method returns the Button btnBack and instantiates it if it's null.
	 * @return The Button btnBack
	 */
	public Button getBtnBack()
	{
		if(btnBack ==null)
		{
			btnBack= new Button("Back");
			btnBack.setPrefSize(IConstant.ADMIN_BTN_WIDTH, IConstant.ADMIN_BTN_HEIGHT);
			btnBack.setId("btnAdmin");
		}
		return btnBack;
	}
	
	/**
	 * This method returns the Button btnModify and instantiates it if it's null.
	 * @return The Button btnModify
	 */
	public Button getBtnModify() {
		if(btnModify == null) {
			btnModify = new Button("Modify");
			btnModify.setId("btnAdmin");
			btnModify.setPrefSize(IConstant.ADMIN_BTN_WIDTH, IConstant.ADMIN_BTN_HEIGHT);
			
			btnModify.setOnAction(new EventHandler<ActionEvent>() {
				
				/**
				 * When you click on the button btnModify, this method modify a question target of this tableview and the deck.json
				 */
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					if (getItemTvQuestions() != null) {
						((UtilStackpane) getParent()).getAddQuestionGridPane().getTextFieldAuthor().setText(getItemTvQuestions().getAuthor());
						((UtilStackpane) getParent()).getAddQuestionGridPane().getTextFieldTheme().setText(getItemTvQuestions().getTheme());
						((UtilStackpane) getParent()).getAddQuestionGridPane().getTextFieldCluesOne().setText(getItemTvQuestions().getClues().get(0));
						((UtilStackpane) getParent()).getAddQuestionGridPane().getTextFieldCluesTwo().setText(getItemTvQuestions().getClues().get(1));
						((UtilStackpane) getParent()).getAddQuestionGridPane().getTextFieldCluesThree().setText(getItemTvQuestions().getClues().get(2));
						((UtilStackpane) getParent()).getAddQuestionGridPane().getTextFieldAnswer().setText(getItemTvQuestions().getAnswer());
						
						List<Question> toRemove = getTvQuestion().getSelectionModel().getSelectedItems();
						Deck.getInstance().removeAll(toRemove);
						getTvQuestion().getItems().removeAll(toRemove);
						
						
						((UtilStackpane) getParent()).hideAll();
						((UtilStackpane) getParent()).getChildren().get(IListPane.ADDQUESTION).setVisible(true);
						
						
					}else {
						Alert a = new Alert(Alert.AlertType.ERROR,"You must choose a question to modify first !");
						a.show();
					}
				}
			});
		}
		return btnModify;
	}
	
	
	/**
	 * This method returns the getter of an item selected of the tableview 
	 */
	public Question getItemTvQuestions() {
		return getTvQuestion().getSelectionModel().getSelectedItem();
	} 

	/**
	 * This method returns the Button btnLoadFile and instantiates it if it's null.
	 * @return The Button btnLoadFile
	 */
	public Button getBtnLoadFile() {
		if(btnLoadFile == null) {
			btnLoadFile = new Button("Load File");
			
			btnLoadFile.setId("btnAdmin");
			btnLoadFile.setPrefSize(IConstant.ADMIN_BTN_WIDTH, IConstant.ADMIN_BTN_HEIGHT);
			
			btnLoadFile.setOnAction(new EventHandler<ActionEvent>() {
				
				/**
				 * When you click on the button btnLoadFile, this method open a dialog and we can selected a new file json 
				 */
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					FileChooser fc = new FileChooser();
					fc.setInitialDirectory(new File("../pgroupeA02"));
					fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON", "*.json"));
					fc.setTitle("Open a file");
					File selectedFile = fc.showOpenDialog(null);
					data.clear();
					if (selectedFile != null)
					{
						deck = Deck.fromJson(Serialisation.readString(selectedFile));
						data.addAll(deck.getQuestions());
					}else {
						//deck = Deck.getInstance();
						data.addAll(deck.getInstance().getQuestions());
					}
					
				}
			});
		}
		return btnLoadFile;
	}

	/**
	 * This method returns the Button btnSettings and instantiates it if it's null.
	 * @return The Button btnSettings
	 */
	public Button getBtnSettings() {
		if(btnSettings==null)
		{
			btnSettings = new Button("Settings");
			btnSettings.setPrefSize(IConstant.ADMIN_BTN_WIDTH, IConstant.ADMIN_BTN_HEIGHT);
			btnSettings.setId("btnAdmin");
		
		}
		return btnSettings;
	}

	public Deck getDeck()
	{
		return deck;
	}
	public void setDeck(Deck deck) 
	{
		this.deck = deck;
	}

	public ObservableList<Question> getData() {
		return data;
	}
	

}
