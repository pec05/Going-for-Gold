package view;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import model.Deck;
import model.Game;
import model.ManagementUser;
import model.Question;
import model.Settings;
import util.IConstant;
import util.Levenshtein;

/**
 * The class holds the game's interface
 * 
 *
 * @author Peccio Leandro
 *
 */
public class GameAnchorPane extends AnchorPane{
	
	//LABELS
	/**
	 * Every Label this class has to display.
	 */
	private Label lblAnswer, lblRemainTime, lblScore;
	
	//BUTTONS
	/**
	* Every Button this class has to display.
	*/
	private Button btnPause, btnSkip,btnClear, btnBonusTime, btnBonusLetter, btnBonusNbLetter ;
	
	
	//TEXT
	/**
	* Every Text this class has to display.
	*/
	private Text txtThemeChoose,txtClues1, txtClues2, txtClues3, txtScore;
	
	//TEXTFIELD
	/**
	* Every TextField this class has to display.
	*/
	private TextField txtAnswer;
	private Text txtPseudo;
	
	/**
	 * The panel containing the Pause game.
	 */
	private PauseAnchorPane pauseAnchorPane;
	
	/**
	 * The game instance.
	 */
	private Game game;
	
	/**
	 * The ManagementUser instance.
	 */
	private ManagementUser mgUser;
	
	/**
	 * Game thread, handling the clues and stuff.
	 */
	private GameThread gameThread;
	
	/**
	 * The question's answer.
	 */
	private String answer;
	private String letterClue;
	
	/**
	 * The user's name.
	 */
	private String pseudo;
	
	/**
	 * The user's score.
	 */
	private int score;
	private ProgressBar pbScore;
	
	/**
	 * Progressbar showing the remaining time before the game is over.
	 */
	private ProgressBar progressBar;
	
	
	/**
	 * The thread handling the timer.
	 */
	private ProgressTimer progressTimer;
	

	/**
	 * Constructor of GameAnchorPane.
	 * Adds all components to its layout.
	 */
	public GameAnchorPane(ManagementUser mg)  {
		// TODO Auto-generated constructor stub
		
			
		this.mgUser = mg;
		
					
		//theme//
		HBox hboxTheme = new HBox();
		hboxTheme.getChildren().addAll(getTxtThemeChoose());
		hboxTheme.setPrefSize(IConstant.TITLE_NEON_WIDTH, IConstant.TITLE_NEON_HEIGHT);
		hboxTheme.setId("lblPick");
		hboxTheme.setAlignment(Pos.CENTER);
		setLeftAnchor(hboxTheme, IConstant.TITLE_NEON_LEFT); setTopAnchor(hboxTheme, IConstant.TITLE_NEON_TOP);
		
		//Bonus
		
		VBox vbBonus = new VBox();
		vbBonus.getChildren().addAll(getBtnBonusTime(), getBtnBonusLetter(), getBtnBonusNbLetter());
		vbBonus.setSpacing(10.);
		setLeftAnchor(vbBonus, 950.); setTopAnchor(vbBonus, 260.);

		//Clues//
		VBox VBoxClues = new VBox();
		VBoxClues.getChildren().addAll(getTxtClues1(), getTxtClues2(), getTxtClues3());
		VBoxClues.setId("clues");
		VBoxClues.setSpacing(30.);
		VBoxClues.setPadding(new Insets(20., 30., 30., 30.));
		
		
		VBoxClues.setMinSize(IConstant.VBOX_CLUES_WIDTH, IConstant.VBOX_CLUES_HEIGHT);
		setLeftAnchor(VBoxClues, IConstant.VBOX_CLUES_LEFT);setTopAnchor(VBoxClues, IConstant.VBOX_CLUES_TOP);
		
		//Score//
		
		VBox vbScore = new VBox();
		vbScore.getChildren().add(getPbScore());
		vbScore.setPrefSize(IConstant.SCORE_WIDTH, 200.);
		setLeftAnchor(vbScore, 100.); setTopAnchor(vbScore, 400.);
		
		HBox hbScore = new HBox();
		hbScore.getChildren().addAll(getLblScore(),getTxtScore());
		hbScore.setSpacing(10.);
		hbScore.setPadding(new Insets(10.));
		setLeftAnchor(hbScore, 100.);setTopAnchor(hbScore, 470.);
		hbScore.setMinWidth(IConstant.SCORE_WIDTH); hbScore.setMinHeight(IConstant.SCORE_HEIGHT);
		hbScore.setId("score");
		hbScore.setAlignment(Pos.CENTER);
		
		//user
		HBox hbUser = new HBox();
		hbUser.getChildren().add(getTxtPseudo());
		setLeftAnchor(hbUser, 100.);setTopAnchor(hbUser, 550.);
		hbUser.setId("userGameAP");
		hbUser.setPrefSize(IConstant.SCORE_WIDTH, IConstant.SCORE_HEIGHT);
		hbUser.setPadding(new Insets(10.));
		hbUser.setAlignment(Pos.CENTER);
	
				
		//Answer///
		
		HBox hBoxAnswer = new HBox();
		hBoxAnswer.getChildren().addAll(getLblAnswer(), getTxtAnswer(), getBtnSkip(), getBtnClear());
		hBoxAnswer.setSpacing(10.);
		setLeftAnchor(hBoxAnswer, 330.); setTopAnchor(hBoxAnswer, 500.);
		
		
		///Pause////
		setLeftAnchor(getBtnPause(), 1050.); setTopAnchor(getBtnPause(), 50.);
	
		//progressBar and lblRemainTime
		
		VBox vBoxTimer = new VBox();
		vBoxTimer.getChildren().addAll(getProgressBar());
		setLeftAnchor(vBoxTimer, 1080.);setTopAnchor(vBoxTimer, 420.);
		vBoxTimer.setSpacing(15.);
		
		
		setLeftAnchor(getLblRemainTime(), 1055.);setTopAnchor(getLblRemainTime(), 430.);
		
	
		
		/*
		 * Displays elements
		 */
		getChildren().addAll(hboxTheme,
				VBoxClues, hbScore,
				hbUser,
				hBoxAnswer,
				getBtnPause(),
				vbBonus,
				vBoxTimer,
				getLblRemainTime(),
				vbScore
				
				);
	}

	/**
	 * This method returns the ProgressBar pbScore and instantiates it if it's null.
	 * @return The ProgressBar pbScore
	 */
	public ProgressBar getPbScore() {
		
		if(pbScore == null) {
			pbScore = new ProgressBar();
			pbScore.getTransforms().setAll(new Rotate(-90, 0, 0));
			pbScore.setPrefSize(200., IConstant.SCORE_WIDTH);
			pbScore.setPadding(new Insets(15.));
			pbScore.setId("pbScore");
			pbScore.setProgress(0.);
		}
		return pbScore;
		
	}
	public String getPseudo() {
		return pseudo;
	}



	public void setPseudo(String pseudo) {

		this.pseudo = pseudo;
		getTxtPseudo().setText(this.pseudo);
		
	}

	/**
	 * This method returns the Text txtPseudo and instantiates it if it's null.
	 * @return The Text txtPseudo
	 */
	public Text getTxtPseudo() {
		
		if(txtPseudo == null)
		{
			txtPseudo = new Text("Guest");
			txtPseudo.setFill(Color.rgb(0, 150, 255));
			
		
		}
		return txtPseudo;
	}

	/**
	 * This method returns the Button btnBonusTime and instantiates it if it's null.
	 * @return The Button btnBonusTime
	 */
	public Button getBtnBonusTime() {
		
		if(btnBonusTime == null)
		{
			ImageView bonusTime=new ImageView(new Image("file:../../image/time+.png", 41.,41., false, false));
			btnBonusTime = new Button("");
			btnBonusTime.setGraphic(bonusTime);
			btnBonusTime.setPrefSize(IConstant.BONUS_BTN_WIDTH, IConstant.BONUS_BTN_HEIGHT);
			btnBonusTime.setId("btnBonus");
			btnBonusTime.setTooltip(new Tooltip("Bonus timer"));
			btnBonusTime.setOnAction(new EventHandler<ActionEvent>() {
				
				/** This method is use to design the button time, a bonus, but there is an action when you click on it.
				 * When you click on it. The program add more time at the game 
				 * And the bonus time button is set to disable, because a bonus is one time use per game.
				 * 
				 */
				@Override
				public void handle(ActionEvent event) {
										
					progressTimer.BonusTimer();
					btnBonusTime.setDisable(true);
					
				}
			});
			
		}
		return btnBonusTime;
	}


	public String getLetterClue() {
		
		if(letterClue == null)
		{
			letterClue = answer.substring(0, 1);
			
		}
		
		return letterClue;
	}


	/**
	 * This method returns the Button btnBonusLetter and instantiates it if it's null.
	 * @return The Button btnBonusLetter
	 */
	public Button getBtnBonusLetter() {
		
		if(btnBonusLetter == null)
		{
			ImageView bonusLetter=new ImageView(new Image("file:../../image/letter+.png", 41.,41., false, false));
			btnBonusLetter = new Button("");
			btnBonusLetter.setId("btnBonus");
			btnBonusLetter.setGraphic(bonusLetter);
			btnBonusLetter.setTooltip(new Tooltip("Bonus letter"));
			btnBonusLetter.setPrefSize(IConstant.BONUS_BTN_WIDTH, IConstant.BONUS_BTN_HEIGHT);
			btnBonusLetter.setOnAction(new EventHandler<ActionEvent>() {

				
				/** This method is use to design the button letter, a bonus, but there is an action when you click on it.
				 * When you click on it. The program display the first letter of this answer
				 * And the bonus letter button is set to disable, because a bonus is one time use per game.
				 * 
				 */
				@Override
				public void handle(ActionEvent event) {
					
					getTxtAnswer().setText(getLetterClue());
					btnBonusLetter.setDisable(true);
					
					
				}
				
			});
		}
		return btnBonusLetter;
	}


	/**
	 * This method returns the Button btnBonusNbLetter and instantiates it if it's null.
	 * @return The Button btnBonusNbLetter
	 */
	public Button getBtnBonusNbLetter() {
		
		if(btnBonusNbLetter == null)
		{
			 ImageView bonusNumber=new ImageView(new Image("file:../../image/number+.png", 41.,41., false, false));
			    
			    btnBonusNbLetter = new Button("");
			    btnBonusNbLetter.setId("btnBonus");
			    btnBonusNbLetter.setGraphic(bonusNumber);
			    btnBonusNbLetter.setTooltip(new Tooltip("Bonus number"));
				btnBonusNbLetter.setPrefSize(IConstant.BONUS_BTN_WIDTH, IConstant.BONUS_BTN_HEIGHT);
			
			btnBonusNbLetter.setOnAction(new EventHandler<ActionEvent>() {

				
				/** This method is use to design the button number letter, a bonus, but there is an action when you click on it.
				 * When you click on it. The program show the number of letter of this answer
				 * And the bonus letter button is set to disable, because a bonus is one time use per game.
				 * 
				 */
				@Override
				public void handle(ActionEvent event) {
					
					String tmp = "_";
					for(int i = 0; i<answer.length()-1; i++)
					{
						tmp += " _";
					}
					System.out.println(answer.length());
					System.out.println("tmp : "+tmp);
					getTxtAnswer().setText(tmp);
						
					
					btnBonusNbLetter.setDisable(true);
					
				}
				
			});
		}
		return btnBonusNbLetter;
	}
	
	/**
	 * This method returns the Label lblAnswer and instantiates it if it's null.
	 * @return The Label lblAnswer
	 */
	public Label getLblAnswer() {
		if(lblAnswer == null) {
			lblAnswer = new Label("Answer :");
			lblAnswer.setFont(Font.loadFont("file:Resources/fonts/banksia.ttf", 20));
		}
		return lblAnswer;
	}
	
	/**
	 * This method returns the Label remainTime
	 * @return The Label remainTime
	 */
	public Label getLblRemainTime() {
		if(lblRemainTime == null) {
			lblRemainTime = new Label("");
			lblRemainTime.setTextFill(Color.rgb(0, 150, 255));
			lblRemainTime.setMinWidth(IConstant.TIMER_WIDTH);
			lblRemainTime.setMinHeight(IConstant.TIMER_HEIGHT);
			
			lblRemainTime.setPadding(new Insets(10., 10., 10., 50.));
			lblRemainTime.setId("lblTimer");
		}
		return lblRemainTime;
	}
	
	/**
	 * This method returns the Text txtThemeChoose and instantiates it if it's null.
	 * @return The Text txtThemeChoose
	 */
	public Text getTxtThemeChoose() {
		if(txtThemeChoose == null) {
			txtThemeChoose = new Text("");			
			//txtThemeChoose.setFill(Color.rgb(IConstant.TITLE_NEON_COLOR_RED,IConstant.TITLE_NEON_COLOR_GREEN,IConstant.TITLE_NEON_COLOR_GREEN));
			txtThemeChoose.setFill(Color.rgb(0, 150, 255));
			txtThemeChoose.setTextAlignment(TextAlignment.CENTER);
			txtThemeChoose.minWidth(IConstant.TITLE_NEON_WIDTH);
			txtThemeChoose.minHeight(IConstant.TITLE_NEON_HEIGHT);
			
			
		}
		return txtThemeChoose;
	}

	/**
	 * This method returns the Text textClues1 and instantiates it if it's null.
	 * @return The Text textClues1
	 */
	public Text getTxtClues1() {
		if(txtClues1 == null) {
			txtClues1 = new Text("");
			txtClues1.setWrappingWidth(500.);
			txtClues1.setFont(Font.loadFont("file:Resources/fonts/banksia.ttf", IConstant.VBOX_CLUES_FONT_SIZE));
		}
		return txtClues1;
	}

	/**
	 * This method returns the Text textClues2 and instantiates it if it's null.
	 * @return The Text textClues2
	 */
	public Text getTxtClues2(){
		if(txtClues2 == null) {
			txtClues2 = new Text("");
			txtClues2.setWrappingWidth(500.);
			txtClues2.setFont(Font.loadFont("file:Resources/fonts/banksia.ttf", IConstant.VBOX_CLUES_FONT_SIZE));
		}
		return txtClues2;
	}

	/**
	 * This method returns the Text textClues3 and instantiates it if it's null.
	 * @return The Text textClues3
	 */
	public Text getTxtClues3() {
		if(txtClues3 == null) {
			txtClues3 = new Text("");
			txtClues3.setWrappingWidth(500.);
			txtClues3.setFont(Font.loadFont("file:Resources/fonts/banksia.ttf", IConstant.VBOX_CLUES_FONT_SIZE));
		}
		return txtClues3;
	}

	/**
	 * This method returns the TextField txtAnswer and instantiates it if it's null.
	 * @return The TextField txtAnswer
	 */
	public TextField getTxtAnswer() {
		if(txtAnswer == null) {
			txtAnswer = new TextField();
			txtAnswer.setOnKeyPressed(new EventHandler<KeyEvent>() {
				 
				//Checking answer on "Enter" key press
			    @Override
			    public void handle(KeyEvent event) {
			    	String userAnswer = getTxtAnswer().getText().toUpperCase();
			    	if(event.getCode() == KeyCode.ENTER) {
			    		validateAnswerInput(userAnswer);
			    	//	getProgressBar().setProgress(getGame().getScore());
			    		getTxtScore().setText(getScore()+"");
			    		getTxtAnswer().setText("");
			    	}
			        
			    }
			});
		}
		return txtAnswer;
	}

	/**
	 * This method returns the Label lblScore and instantiates it if it's null.
	 * @return The Label lblScore
	 */
	public Label getLblScore() {
		if(lblScore == null) {
			lblScore = new Label("Score");
			lblScore.setTextFill(Color.rgb(0, 150, 255));
		}
		return lblScore;
	}
	
	/**
	 * This method returns the Text txtScore and instantiates it if it's null.
	 * @return The Text txtScore
	 */
	public Text getTxtScore() {
		if(txtScore == null) {
			txtScore = new Text("0");
			txtScore.setFill(Color.rgb(0, 150, 255));
			
		}
		return txtScore;
	}

	/**
	 * This method returns the Button btnPause and instantiates it if it's null.
	 * @return The Button btnPause
	 */
	public Button getBtnPause() {
		if(btnPause == null) {
			btnPause = new Button("");
			btnPause.setId("btnPauseGAP");
			btnPause.setPrefSize(60., 60.);
			btnPause.setOnAction(new EventHandler<ActionEvent>() {
				
				/**
				 * This method creates and designs the button pause, if it is not existed. First it initialize the pause windows and set the timer to pause and the timer is interrupted
				 */
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					//Floutage
					//setEffect(new GaussianBlur(50.0));
					initPauseAnchorPane();
					gameThread.requestPause();
					//getProgressTimer().pauseProgress();
					progressTimer.getTm().stop();
					System.out.println("jeu en pause");
					 
					 
				}
			});
		}
		return btnPause;
	}
	
	/**
	 * This method verifies the user's answer.
	 * @param userAnswer answer given by the user.
	 */
	public void validateAnswerInput(String userAnswer) {
		if(answer.length()<=3){
			if(userAnswer==answer) {
				calculateAnswer();
			}
		} 
		else if(answer.length()==4) {
			if(Levenshtein.comparator(answer, userAnswer, 1)) {
				calculateAnswer();
			}
		}
		else if(Levenshtein.comparator(answer, userAnswer, Settings.getInstance().getDistance())) {
			calculateAnswer();
		}
		else {
			setScore(0);
			pbScore.setProgress(0.);
			getTxtScore().setText(0+"");
		}
		this.skipQuestion();
	}

	/**
	 * This method calculates the score when a user gives a good answer.
	 */
	private void calculateAnswer() {
		// TODO Auto-generated method stub
		setScore(getScore()+1);
		getPbScore().setProgress(getScore()*0.25);
		if(getScore() > getGame().getScore()) {
			getGame().setScore(getScore());
		}
		if(getScore() == 4){
			getGame().setScore(4);
			/*
			 * collects data and sends it in panel endgameAnchorPane
			 */
			
			((UtilStackpane) this.getParent()).getEndgameAnchorPane().setPlayername(this.getPseudo());
			((UtilStackpane) this.getParent()).getEndgameAnchorPane().setScore(this.getGame().getScore());
			((UtilStackpane) this.getParent()).getEndgameAnchorPane().setTheme(this.getGame().getTheme());
			
			/*
			 * destroy the timer ans show the panel endgameAnchorPane
			 */
			this.getProgressTimer().destroyProgress();
			((UtilStackpane) this.getParent()).hideAll();
			((UtilStackpane) this.getParent()).getChildren().get(IListPane.ENDGAME).setVisible(true);
			((UtilStackpane) this.getParent()).getEndgameAnchorPane().getLblScore().setText("Your score : "+this.getGame().getScore());
		}	
	}
	
	/**
	 * This method returns score of the game.
	 * @return score of the game
	 */
	public int getScore() {
		
		return score;
	}

	/**
	 * This method changes the score.
	 * @param score score of the game
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * This method returns the Button btnSkip and instantiates it if it's null.
	 * @return The Button btnSkip
	 */
	public Button getBtnSkip() {
		if(btnSkip == null) {
			btnSkip = new Button("");
			btnSkip.setId("btnSkip");
			btnSkip.setPrefSize(70., 50.);
			btnSkip.setOnAction(new EventHandler<ActionEvent>() {
				
				//Skipping question
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					skipQuestion();
					setScore(0);
					getPbScore().setProgress(0.);
					
					getTxtScore().setText("0");
					getTxtAnswer().setText("");
				}
			});
		}
		return btnSkip;
	}
	
	/**
	 * This method returns the Button clear and instantiates it if it's null.
	 * @return The Button clear
	 */
	public Button getBtnClear() {
		if(btnClear == null) {
			btnClear = new Button("Clear");
			btnClear.setId("btnClear");
			btnClear.setPrefSize(90., 50.);
			btnClear.setOnAction(new EventHandler<ActionEvent>() {
				
				//Clearing question
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					txtAnswer.setText("");
				}
			});
		}
		return btnClear;
	}

	/**
	 * This method returns the ProgressBar progressBar and instantiates it if it's null.
	 * @return The ProgressBar progressBar
	 */
	public ProgressBar getProgressBar() {
		if(progressBar == null) {
			progressBar = new ProgressBar();
			progressBar.setProgress(100);
			
			progressBar.getTransforms().setAll(
					new Rotate(-90, 0, 0)
			);
			progressBar.setPrefSize(150., 50.);
		}
		return progressBar;
	}

	/**
	 * This method returns the ProgressTimer progressTimer.
	 * @return The ProgressTimer progressTimer
	 */
	public ProgressTimer getProgressTimer() {
		return progressTimer;
	}

	/**
	 * This method show the pause window in front of the game window.
	 */
	public void initPauseAnchorPane()
	{
		this.getChildren().add(getPauseAnchorPane());
		int pos = this.getChildren().indexOf(getPauseAnchorPane());
		this.getChildren().get(pos).toFront();
		GameAnchorPane.setTopAnchor(getPauseAnchorPane(), 0.);
	}
	
	/**
	 * This method creates pause pane if it is not existed.
	 * @return pausePane
	 */
	public PauseAnchorPane getPauseAnchorPane()
	{
		if(pauseAnchorPane == null)
		{
			pauseAnchorPane = new PauseAnchorPane();
		}
		return pauseAnchorPane;
	}
	/**
	 * this method destroy the pause pane and set pause pane to null.
	 */
	public void destroyPausePane()
	{
		int pos = this.getChildren().indexOf(getPauseAnchorPane());
		this.getChildren().remove(pos);
		pauseAnchorPane = null;
	}
	
	/**
	 * This method skips a question.
	 */
	public void skipQuestion() {
		synchronized (gameThread) {
			gameThread.interrupt();
		}
	}
	
	/**
	 * This method returns Game.
	 * @return game
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * This method returns GameThread.
	 * @return GameThread
	 */
	public GameThread getGameThread() {
		return gameThread;
	}

	/**
	 * This method returns the answer of the question.
	 * @return The answer
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * This method changes the answer.
	 * @param answer answer of the question
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	/**
	 * This method starts a new Game.
	 * @param theme of question 
	 */
	public void startGame(String theme) {
		if(theme !=null) {
			game = new Game(theme);
			gameThread=new GameThread(this);
			gameThread.start();	

		}
	}
		

	/**
	 * This method starts a new progressTimer.
	 * 
	 */
	public void startTimer() {
		progressTimer = new ProgressTimer(this);
	}

}

/**
 * The class is the thread handling the clues.
 * 
 * @author Gilles Claude
 * @author Peccio Leandro
 * @author Tribuzio Alex
 *
 */

class GameThread extends Thread{
	
	private static Deck deck;
	
	/**
	 * The clue's index.
	 */
	private int clue;
	/**
	 * Indicates if the thread is running.
	 */
	private boolean isRunning;
	/**
	 * Indicates if the thread needs to be destroyed.
	 */
	private boolean awaitingTermination;
	
	/**
	 * The GameAnchorPane on which it needs to interact.
	 */
	private GameAnchorPane gap;

	/**
	 * Constructor of MainGameThread.
	 * Instantiates the PanelMainGame.
	 */
	public GameThread(GameAnchorPane gap) {
		deck.getInstance().getQuestions();
		
		
		
		this.gap = gap;
	}


	/*
	 * This method launches the timer.
	 */
	@Override
	public synchronized void run() {
		
		//Defining the thread to a "normal" state.
		
		/**
		 * Indicates if the thread is running.
		 */
		this.isRunning = true;
		/**
		 * Indicates if the thread needs to be destroyed.
		 */
		this.awaitingTermination = false;
		
		//Starting the ProgressTimer thread.
		gap.getProgressTimer().start();
		
		
		//Displays the clues for each question.
		for(Question q : Deck.getInstance().getQuestions()) {
				if(q.getTheme().equals(gap.getGame().getTheme())) {
					gap.getTxtThemeChoose().setText("Theme : " + q.getTheme());
					gap.setAnswer(q.getAnswer());
					for (clue = 0; clue < 3; clue++) {
						switch(clue) {
						case 0:
							gap.getTxtClues1().setText("");
							gap.getTxtClues1().setText(gap.getTxtClues1().getText()+q.getClues().get(clue));
							
							try {
								this.wait(Settings.getInstance().getClueDelay());
							} catch (InterruptedException e) {
								clue=3;
							}

						//Pause thread if pending requests
						checkPauseRequest();
							break;
						case 1:
							gap.getTxtClues2().setText("");
							gap.getTxtClues2().setText(gap.getTxtClues2().getText()+q.getClues().get(clue));
							
							try {
								this.wait(Settings.getInstance().getClueDelay());
							} catch (InterruptedException e) {
								clue=3;
							}
							
							//Pause thread if pending requests
							checkPauseRequest();
							break;
						case 2:
							gap.getTxtClues3().setText("");
							gap.getTxtClues3().setText(gap.getTxtClues3().getText()+q.getClues().get(clue));
							
							try {
								this.wait();
							} catch (InterruptedException e1) {
								clue=3;
							}

							//Pause thread if pending requests
							checkPauseRequest();
						}
					}
				
				//Resetting the labels.
				gap.getTxtClues1().setText("");
				gap.getTxtClues2().setText("");
				gap.getTxtClues3().setText("");
				
			}
				

		}
		//When the game is over and needs to be reset.
		gap.getProgressTimer().getTm().stop();
		((UtilStackpane) gap.getParent()).hideAll();
		((UtilStackpane) gap.getParent()).getChildren().get(IListPane.ENDGAME).setVisible(true);
		
		((UtilStackpane) gap.getParent()).getEndgameAnchorPane().setPlayername(gap.getPseudo());
		((UtilStackpane) gap.getParent()).getEndgameAnchorPane().setScore(gap.getGame().getScore());
		((UtilStackpane) gap.getParent()).getEndgameAnchorPane().setTheme(gap.getGame().getTheme());
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				((UtilStackpane) gap.getParent()).getEndgameAnchorPane().getLblScore().setText("Your score : "+gap.getGame().getScore());
				
				
			}
		});
		
		
	}


	/**
	 * This method pauses the thread.
	 */
	public synchronized void pauseProgress() {
		synchronized (this) {
			while(!isRunning) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * This method checks if a pause is needed.
	 */
	public void checkPauseRequest() {
		if(awaitingTermination) {
			synchronized (this) {
				try {
					this.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else if(!isRunning) {
			pauseProgress();
		}
	}
	
	/**
	 * This method resumes the thread.
	 */
	public void resumeProgress() {
		if(clue!=2) {
			synchronized (this) {
				isRunning = true;
				clue--;
				notify();
			}
		}
	}
	
	/**
	 * This method indicates to the thread that it needs to be paused.
	 */
	public void requestPause() {
		isRunning = false;
	}
	
		
}
