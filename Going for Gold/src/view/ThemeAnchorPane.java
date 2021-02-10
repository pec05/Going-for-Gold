package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import model.Deck;
import util.IConstant;


/**
 * The class holds the theme interface
 * 
 * 
 * @author Peccio Leandro
 *
 */
public class ThemeAnchorPane extends AnchorPane{
	
	//LABELS
	/**
	* Every Label this class has to display.
	*/
	private Label lblText;
	
	//BUTTONS
	/**
	* Every Button this class has to display.
	*/
	private Button btnTheme1;
	private Button btnTheme2;
	private Button btnTheme3;
	private Button btnTheme4;
	private Button btnBack;
	
	/**
	 * a theme table
	 */
	private String themeList[];
	
	private static Deck deck;
		
	/**
	 * Constructor of ThemeAnchorPane.
	 * Adds all components to its layout.
	 */
	public ThemeAnchorPane() {
		deck.getInstance().getQuestions();
		
		
		// TODO Auto-generated constructor stub
		setTopAnchor(getLblText(), IConstant.TITLE_NEON_TOP);setLeftAnchor(getLblText(), IConstant.TITLE_NEON_LEFT);
		
		//top left button
		setTopAnchor(getBtnTheme1(), IConstant.THEME_BTNTOP_TOP);setLeftAnchor(getBtnTheme1(), IConstant.THEME_BTNLEFT_LEFT);
		//top right button
		setTopAnchor(getBtnTheme2(), IConstant.THEME_BTNTOP_TOP);setLeftAnchor(getBtnTheme2(), IConstant.THEME_BTNRIGHT_LEFT);
		//bottom left button
		setTopAnchor(getBtnTheme3(), IConstant.THEME_BTNBOTTOM_TOP);setLeftAnchor(getBtnTheme3(), IConstant.THEME_BTNLEFT_LEFT);
		//bottom right button
		setTopAnchor(getBtnTheme4(), IConstant.THEME_BTNBOTTOM_TOP);setLeftAnchor(getBtnTheme4(), IConstant.THEME_BTNRIGHT_LEFT);
		
		
		setTopAnchor(getBtnBack(), IConstant.BTN_BACK_POSITION_TOP);setLeftAnchor(getBtnBack(), IConstant.BTN_BACK_POSITION_LEFT);
		
		getChildren().addAll(
				getLblText(),
				getBtnTheme1(),
				getBtnTheme2(),
				getBtnTheme3(),
				getBtnTheme4(),
				getBtnBack());
		
		displayTheme();
		
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
	* This method returns the Label lblText and instantiates it if it's null.
	* @return The Label lblText
	*/	
	public Label getLblText() {
		if(lblText == null)
		{
			lblText = new Label("PICK YOUR THEME");
			//lblText.setTextFill(Color.rgb(IConstant.TITLE_NEON_COLOR_RED,IConstant.TITLE_NEON_COLOR_GREEN,IConstant.TITLE_NEON_COLOR_GREEN));
			lblText.setTextFill(Color.rgb(0, 150, 255));
			lblText.setMinWidth(IConstant.TITLE_NEON_WIDTH);
			lblText.setMinHeight(IConstant.TITLE_NEON_HEIGHT);
			lblText.setAlignment(Pos.CENTER);
			lblText.setId("lblPick");
		}
		return lblText;
	}
	

	/**
	* This method returns the Button btnTheme1 and instantiates it if it's null.
	* @return The Button btnTheme1
	*/
	public Button getBtnTheme1() {
		if(btnTheme1 == null)
		{
			btnTheme1 = new Button("theme1");
			btnTheme1.setPrefSize(IConstant.THEME_BTN_WIDTH, IConstant.THEME_BTN_HEIGHT);
			shadow(btnTheme1);
			
			
			
			
		}
		return btnTheme1;
	}
	
	/**
	* This method returns the Button btnTheme2 and instantiates it if it's null.
	* @return The Button btnTheme2
	*/
	public Button getBtnTheme2() {
		if(btnTheme2 == null)
		{
			btnTheme2 = new Button("theme2");
			btnTheme2.setPrefSize(IConstant.THEME_BTN_WIDTH, IConstant.THEME_BTN_HEIGHT);
			shadow(btnTheme2);
			
			
		}
		return btnTheme2;
	}

	/**
	* This method returns the Button btnTheme3 and instantiates it if it's null.
	* @return The Button btnTheme3
	*/
	public Button getBtnTheme3() {
		if(btnTheme3 == null)
		{
			btnTheme3 = new Button("theme3");
			btnTheme3.setPrefSize(IConstant.THEME_BTN_WIDTH, IConstant.THEME_BTN_HEIGHT);
			shadow(btnTheme3);
			
			
			
		}
		return btnTheme3;
	}

	/**
	* This method returns the Button btnTheme4 and instantiates it if it's null.
	* @return The Button btnTheme4
	*/
	public Button getBtnTheme4() {
		if(btnTheme4 == null)
		{
			btnTheme4 = new Button("Hidden Theme");
			btnTheme4.setPrefSize(IConstant.THEME_BTN_WIDTH, IConstant.THEME_BTN_HEIGHT);
			shadow(btnTheme4);
			
			btnTheme4.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
				
					((UtilStackpane) getParent()).hideAll();
					((UtilStackpane) getParent()).getChildren().get(IListPane.GAME).setVisible(true);
					
						((UtilStackpane)getParent()).getGameAnchorPane().startTimer();
					
						((UtilStackpane)getParent()).getGameAnchorPane().startGame(themeList[3]);
					
				}
			});
		}
		return btnTheme4;
	}
	
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
	* This method display the theme in which button 
	*/
	public void displayTheme() {
		/*
		 * the deck is shuffle
		 */
		Deck.getInstance().shuffleQuestions();
		
		themeList = new String[4];
		
		boolean isComplete = false;
		while(!isComplete) {
			isComplete = true;
			for(int i=0;i<4;i++) {
				themeList[i] = Deck.getInstance().drawTheme();
				
				/*
				 * compare the first index of theme with the second
				 * compare the first index of theme with the third
				 */
				if(themeList[0].equals(themeList[1]) || themeList[0].equals(themeList[2]) ) {
					isComplete = false;
				}
			
				/*
				 * compare the second index of theme with the third
				 */
				if(themeList[1]!= null) {
					if(themeList[1].equals(themeList[2])) {
						isComplete = false;
					}
				}
				
				/*
				 * compare the index 0, 1 , 2 with the last for the mytery theme
				 */
				if(i==3) {
					for(int j=0;j<3;j++) {
						if(themeList[i].equals(themeList[j])) {
							isComplete = false;
						}
					}
				}
			}
		}
		
		/*
		 * set the text on btn theme
		 */
		getBtnTheme1().setText(themeList[0]);
		getBtnTheme2().setText(themeList[1]);
		getBtnTheme3().setText(themeList[2]);
		
	}
	

	
}







