package view;


import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.stream.events.EndElement;

import exception.ExceptionEmptyField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Deck;
import model.LoginInterface;
import model.LoginProxy;
import model.ManagementUser;
import model.Stat;
import model.StatManagement;
import model.User;
import util.PasswordSha256;
import util.Serialisation;

/**
 * This class creates all the windows or a big part of them and all of this windows are put in the stack pane.
 */
public class UtilStackpane extends StackPane implements IListPane{
	
	/**
	 * Panel containing the main menu.
	 */
	private MenuGridPane menuGridPane;
	
	/**
	 * Panel containing the theme.
	 */
	private ThemeAnchorPane themeAnchorPane;
	
	/**
	 * Panel containing the main game.
	 */
	private GameAnchorPane gameAnchorPane;
	
	/**
	 * Panel containing the menu pause.
	 */
	private PauseAnchorPane pauseAnchorPane;
	
	/**
	 * Panel containing the rules.
	 */
	private RulesBP rulesBP;
	
	/**
	 * Panel containing the end of game.
	 */
	private EndgameAnchorPane endgameAnchorPane;
	
	/**
	 * Panel containing the menu of add question
	 */
	private AddQuestionGridPane addQuestionGridPane;
	
	/**
	 * Panel containing the admin window.
	 */
	private AdminWindowBP adminWindowBP;
	
	/**
	 * Panel containing the settings.
	 */
	private SettingsAP settingsAP;
	
	/**
	 * Panel containing the credits.
	 */
	private CreditBP creditBP;

	/**
	 * Panel containing the login menu .
	 */
	private LoginUserAP loginUserAP;
	
	/**
	 * The Deck instance.
	 */
	private Deck deck;
	
	/**
	 * The question's theme.
	 */
	private String theme;
	
	/**
	 * The pseudo user.
	 */
	private String pseudo;
	
	/**
	 * The ManagementUser instance.
	 */
	private static ManagementUser management;
	
	/**
	 * The StatManagement instance.
	 */
	private static StatManagement statManagement;
	
	/**
	 * Panel containing the scoreboard .
	 */
	private TableScoreAP tableScoreAP;
	
	
	/**
	 * Constructor of UtilStackpane.
	 * Adds all components to its layout.
	 */
	public UtilStackpane()   {

		this.getChildren().addAll(getAdminWindowBP(),
				getMenuGridPane(),
				getThemeAnchorPane(), 
				getGameAnchorPane(),
				getPauseAnchorPane(), 
				getRulesBP(),
				getEndgameAnchorPane(),
				getAddQuestionGridPane(), 
				getLoginUserAP(),
				getTableScoreAP(),
				getSettingsAP(), 
				getCreditBP());
		
		hideAll();
		setDeck(Serialisation.loadDeck());
		this.getChildren().get(MENUMAIN).setVisible(true);
		
	}
	
	/**
	 * This method returns the CreditBP creditBP and instantiates it if it's null.
	 * @return The CreditBP creditBP
	 */
	public CreditBP getCreditBP() {
		if(creditBP == null)
		{
			creditBP = new CreditBP();
			creditBP. getBtnBack().setOnAction(new EventHandler<ActionEvent>() {

				/*
				 * this panel is hide
				 * the panel menu is visible
				 */
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					hideAll();
					getChildren().get(MENUMAIN).setVisible(true);
					
					
				}
			});
		}
		return creditBP;
	}
	
	/*
	 * this method hide the different panel
	 */
	public void hideAll() {
		for(Node n : this.getChildren())
		{
			n.setVisible(false);
		}
	}

	public static ManagementUser getManagement() {
		
		if(management == null)
		{
			management = new ManagementUser();
			List<User> list = new ArrayList<User>();
			list = management.getInstanceManagementUser().getUsers();
			management.addAllUser(list);
		}
		return management;
	}
	

	public static StatManagement getStatManagement() {
		if(statManagement == null)
		{
			statManagement = new StatManagement();
			List<Stat> list = new ArrayList<Stat>();
			list = statManagement.getInstanceStatManagement().getStats();
			statManagement.addAllStat(list);
		}
		return statManagement;
	}

	public String getPseudo() {
		
			if(loginUserAP.getTxtLogin().getText()== null)
			{	
				pseudo = new String("Guest");
			}
			pseudo =  loginUserAP.getTxtLogin().getText();
			return pseudo;
			
		}
	
	
	/**
	 * This method returns the TableScoreAP tableScoreAP and instantiates it if it's null.
	 * @return The TableScoreAP tableScoreAP
	 */
	public TableScoreAP getTableScoreAP() {
		if(tableScoreAP == null) {
			tableScoreAP = new TableScoreAP(getStatManagement());
			tableScoreAP.getBtnBack().setOnAction(new EventHandler<ActionEvent>() {

				/*
				 * this panel is hide
				 * the panel menu is visible
				 */
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					hideAll();
					getChildren().get(MENUMAIN).setVisible(true);
					
				}
			});
		}
		return tableScoreAP;
	}
	
	/**
	 * This method returns the LoginUserAP loginUserAP and instantiates it if it's null.
	 * @return The LoginUserAP loginUserAP
	 */
	public LoginUserAP getLoginUserAP() {
		
		if(loginUserAP == null) {
			
			loginUserAP = new LoginUserAP(getManagement());
			
			loginUserAP.getBtnSkip().setOnAction(new EventHandler<ActionEvent>() {

				/*
				 * this panel is hide
				 * the panel theme is visible
				 */
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					hideAll();
					getChildren().get(THEME).setVisible(true);
						 
				}
			});
			
			loginUserAP.getBtnConnexion().setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						
						pseudo = loginUserAP.getTxtLogin().getText();
						String pw = loginUserAP.getPsfPassword().getText();
						
						/*
						 * compare user u with the list of users
						 */
						for(User u : loginUserAP.getManagementUser().getUsers()){
							
							try {
								if(u.getName().equals(pseudo) && u.getPassword().equals(PasswordSha256.sha256(pw)))
								{
									/*
									 * this panel is hide
									 * the panel theme is visible
									 */
									hideAll();
									getChildren().get(THEME).setVisible(true);
									
										getGameAnchorPane().setPseudo(getPseudo());
									
									
								}
								else 
								{
									loginUserAP.getTxtWrongLogin().setText("This password and this pseudo don't match or user doesn't exist.");
									
								}
							} catch (NoSuchAlgorithmException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							
						}
					}
				});
			
			loginUserAP.getBtnRegistration().setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					
					
					String pw = loginUserAP.getPsfPassword().getText();
					
					/*
					 * if the pseudo and password user is not in the list, the user is register
					 * hash the password with sha 256
					 */
					try {
						if(!loginUserAP.getManagementUser().getUsers().contains(new User(getPseudo(), PasswordSha256.sha256(pw)))){
							
							loginUserAP.getManagementUser().addUser(getPseudo(), PasswordSha256.sha256(pw));
							
								getGameAnchorPane().setPseudo(getPseudo());
							
							/*
							* this panel is hide
							* the panel theme is visible
							*/
							hideAll();
							getChildren().get(THEME).setVisible(true);
							loginUserAP.getManagementUser().saveJsonUser();
						}
						else {loginUserAP.getTxtWrongLogin().setText("This pseudo already exist. Please choose an other one.");}
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExceptionEmptyField e) {
						// TODO Auto-generated catch block
						Alert alert = new Alert(AlertType.ERROR,"Empty field",ButtonType.OK);
						alert.show();
					}
					
				}
			});
			};
		
		return loginUserAP;
	}
	
	
	/**
	 * This method returns the MenuGridPane menuGridPane and instantiates it if it's null.
	 * @return The MenuGridPane menuGridPane
	 */
	public MenuGridPane getMenuGridPane() {
		if(menuGridPane == null) {
			menuGridPane = new MenuGridPane();
			
			menuGridPane.getBtnNewGame().setOnAction(new EventHandler<ActionEvent>() {
				
				/*
				 * this panel is hide
				 * the panel login user is visible
				 */
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					hideAll();
					getChildren().get(LOGINUSER).setVisible(true);
	
				}
			});
			
			menuGridPane.getBtnRules().setOnAction(new EventHandler<ActionEvent>() {
				/*
				 * this panel is hide
				 * the panel rules is visible
				 */
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					hideAll();
					getChildren().get(RULES).setVisible(true);
					
				}
			});
			
			menuGridPane.getBtnCredits().setOnAction(new EventHandler<ActionEvent>() {
				/*
				 * this panel is hide
				 * the panel credits is visible
				 */
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					hideAll();
					getChildren().get(CREDITS).setVisible(true);
					
				}
			});
			
			menuGridPane.getBtnAdminMode().setOnAction(new EventHandler<ActionEvent>() {
				/*
				 * this panel is hide
				 * an alert is display to conenct at the panel admin
				 */
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

					getAlertLogin();

				}
			});
			
			menuGridPane.getBtnScoreboard().setOnAction(new EventHandler<ActionEvent>() {
				/*
				 * this panel is hide
				 * the panel scoreboard is visible
				 */
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					hideAll();
					getChildren().get(TABLESCORE).setVisible(true);
					
				}
			});
		}
		return menuGridPane;
	}

	/**
	 * This method returns the ThemeAnchorPane themeAnchorPane and instantiates it if it's null.
	 * @return The ThemeAnchorPane themeAnchorPane
	 */
	public ThemeAnchorPane getThemeAnchorPane() {
		if(themeAnchorPane == null) {
			themeAnchorPane = new ThemeAnchorPane();
		
			themeAnchorPane.getBtnBack().setOnAction(new EventHandler<ActionEvent>() {

				/*
				 * this panel is hide
				 * the panel menu is visible
				 */
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					hideAll();
					getChildren().get(MENUMAIN).setVisible(true);
					
				}
			});
			
			themeAnchorPane.getBtnTheme1().setOnAction(new EventHandler<ActionEvent>() {
				/*
				 * this panel is hide
				 * the panel game and start a game is visible
				 */
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					
					hideAll();
					getChildren().get(GAME).setVisible(true);
					
						getGameAnchorPane().startTimer();
					
					
						getGameAnchorPane().startGame(((Button)event.getSource()).getText());
					
					
					
				}
			});
			
			themeAnchorPane.getBtnTheme2().setOnAction(new EventHandler<ActionEvent>() {
				
				/*
				 * this panel is hide
				 * the panel game and start a game is visible
				 */
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					
					hideAll();
					getChildren().get(GAME).setVisible(true);
					
						getGameAnchorPane().startTimer();
					
					
						getGameAnchorPane().startGame(((Button)event.getSource()).getText());

				}
			});
			
			themeAnchorPane.getBtnTheme3().setOnAction(new EventHandler<ActionEvent>() {
				/*
				 * this panel is hide
				 * the panel game and start a game is visible
				 */
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					hideAll();
					getChildren().get(GAME).setVisible(true);
					
						getGameAnchorPane().startTimer();
					
					
						getGameAnchorPane().startGame(((Button)event.getSource()).getText());
					
				}
			});
			
		}
		return themeAnchorPane;
	}
	
	
	
	/**
	 * This method returns the RulesBP rulesBP and instantiates it if it's null.
	 * @return The RulesBP rulesBP
	 */
	public RulesBP getRulesBP() {
		if(rulesBP == null) {
			rulesBP = new RulesBP();
			
			rulesBP.getBtnBack().setOnAction(new EventHandler<ActionEvent>() {

				/*
				 * this panel is hide
				 * the panel menu is visible
				 */
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					hideAll();
					getChildren().get(MENUMAIN).setVisible(true);
					
				}
			});
			
		}
		return rulesBP;
	}
	
	/**
	 * This method returns the AdminWindowBP adminWindowBP and instantiates it if it's null.
	 * @return The AdminWindowBP adminWindowBP
	 */
	public AdminWindowBP getAdminWindowBP() {
		
		if(adminWindowBP == null)
		{
			adminWindowBP = new AdminWindowBP(deck);
			
			adminWindowBP.getBtnBack().setOnAction(new EventHandler<ActionEvent>() {

				/*
				 * this panel is hide
				 * the panel menu is visible
				 */
				@Override
				public void handle(ActionEvent event) {
					hideAll();
					getChildren().get(MENUMAIN).setVisible(true);
					
				}
			});
					
			adminWindowBP.getBtnAdd().setOnAction(new EventHandler<ActionEvent>() {
				
				/*
				 * this panel is hide
				 * the panel add question is visible
				 */
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					hideAll();
					getChildren().get(ADDQUESTION).setVisible(true);
				}
			});
			
			adminWindowBP.getBtnSettings().setOnAction(new EventHandler<ActionEvent>() {
				
				/*
				 * this panel is hide
				 * the panel settings is visible
				 */
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					hideAll();
					getChildren().get(SETTINGS).setVisible(true);
					
				}
			});
		}
		return adminWindowBP;
	}
	
	/**
	 * This method returns the AddQuestionGridPane addQuestionGridPane and instantiates it if it's null.
	 * @return The AddQuestionGridPane addQuestionGridPane
	 */
	public AddQuestionGridPane getAddQuestionGridPane() {
		if(addQuestionGridPane == null) {
			addQuestionGridPane = new AddQuestionGridPane();
		}
		return addQuestionGridPane;
	}
	
	/**
	 * This method returns the SettingsAP settingsAP and instantiates it if it's null.
	 * @return The SettingsAP settingsAP
	 */
	public SettingsAP getSettingsAP() {
		if(settingsAP == null) {
			settingsAP = new SettingsAP();
			
			settingsAP.getBtnBack().setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					hideAll();
					getChildren().get(ADMIN).setVisible(true);
				}
			});
		}
		return settingsAP;
	}

	public EndgameAnchorPane getEndgameAnchorPane() {
		if(endgameAnchorPane == null) {
			endgameAnchorPane = new EndgameAnchorPane();
			
			
			endgameAnchorPane.getBtnYes().setOnAction(new EventHandler<ActionEvent>() {
				
				/*
				 * the panel game, theme are destroy
				 * this panel is hide
				 * the panel theme is visible
				 */
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					
					endgameAnchorPane.getBtnSave().setVisible(true);
					destroyGamePane();
					
					destroyThemePane();
					getThemeAnchorPane();
					getGameAnchorPane().setPseudo(getPseudo());
					
					hideAll();
					getChildren().get(THEME).setVisible(true);
				}
			});
			
			
			endgameAnchorPane.getBtnNo().setOnAction(new EventHandler<ActionEvent>() {
				/*
				 * the panel game, theme are destroy
				 * this panel is hide
				 * the panel menu is visible
				 */
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					endgameAnchorPane.getBtnSave().setVisible(true);
					destroyGamePane();
					
					destroyThemePane();
					hideAll();
					getChildren().get(MENUMAIN).setVisible(true);		
				}
			});
		}
		return endgameAnchorPane;
	}

	/**
	 * This method returns the GameAnchorPane gameAnchorPane and instantiates it if it's null.
	 * @return The GameAnchorPane gameAnchorPane
	 */
	public GameAnchorPane getGameAnchorPane()   {
		if(gameAnchorPane == null) {
			gameAnchorPane = new GameAnchorPane(getManagement());
			/*
			 * start a game with the theme selected	
			 */
			getGameAnchorPane().startGame(theme);
			
		}
		return gameAnchorPane;
	}

	/**
	 * This method returns the PauseAnchorPane pauseAnchorPane and instantiates it if it's null.
	 * @return The PauseAnchorPane pauseAnchorPane
	 */
	public PauseAnchorPane getPauseAnchorPane() {
		if(pauseAnchorPane == null) {
			pauseAnchorPane = new PauseAnchorPane();
			
			
			PauseAnchorPane.getBtnMainMenu().setOnAction(new EventHandler<ActionEvent>() {
				
				/*
				 * the panel game, pause and theme are destroy
				 * this panel is hide
				 * the panel menu is visible
				 */
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					
					getGameAnchorPane().destroyPausePane();
					
					
					destroyGamePane();
					
					destroyThemePane();
					hideAll();
					getChildren().get(MENUMAIN).setVisible(true);
					
					
				}
			});
			
			PauseAnchorPane.getBtnReset().setOnAction(new EventHandler<ActionEvent>() {
				
				/*
				 * the panel game, pause and theme are destroy
				 * this panel is hide
				 * the panel theme is visible
				 */
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					
					
					getGameAnchorPane().destroyPausePane();
					
					
					destroyGamePane();
					
					
					destroyThemePane();
					getThemeAnchorPane();
					
					getGameAnchorPane().setPseudo(getPseudo());
					hideAll();
					getChildren().get(THEME).setVisible(true);
					
					
				}

				
			});
			PauseAnchorPane.getBtnContinue().setOnAction(new EventHandler<ActionEvent>() {

				/*
				 * the panel pause is destroy
				 * the thread is resume
				 */
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					
					
					getGameAnchorPane().destroyPausePane();
					
					getGameAnchorPane().getProgressTimer().getTm().play();
					
					
					getGameAnchorPane().getGameThread().resumeProgress();

				}
			});
			
		}
		return pauseAnchorPane;
	}

	
	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	
	/*
	 * this method destroy the panel game
	 */
	public void destroyGamePane()  {
		int pos = this.getChildren().indexOf(getGameAnchorPane());
		this.getChildren().remove(pos);
		gameAnchorPane = null;
		this.getChildren().add(GAME, getGameAnchorPane());
	}
	
	/*
	 * this method destroy the panel theme
	 */
	private void destroyThemePane() {
		// TODO Auto-generated method stub
		int pos = this.getChildren().indexOf(getThemeAnchorPane());
		this.getChildren().remove(pos);
		themeAnchorPane = null;
		this.getChildren().add(THEME, getThemeAnchorPane());
		
		
	}
	
	/*
	 * this method display an alert to connect at the panel admin
	 */
	public <T> void getAlertLogin() {
		
		Dialog<T> dial = new Dialog<>();
		dial.setResizable(true);
	
		
		dial.setTitle("Login Admin");
		dial.setHeaderText("Please enter your login details");
		ButtonType cancelButtonType = new ButtonType("Cancel",ButtonData.CANCEL_CLOSE);
		ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
		dial.getDialogPane().getButtonTypes().addAll(loginButtonType, cancelButtonType);
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField username = new TextField();
		username.setPromptText("Username");
		PasswordField password = new PasswordField();
		password.setPromptText("Password");

		grid.add(new Label("Username:"), 0, 0);
		grid.add(username, 1, 0);
		grid.add(new Label("Password:"), 0, 1);
		grid.add(password, 1, 1);
		
		dial.getDialogPane().setContent(grid);
		
		
		final Window window = dial.getDialogPane().getScene().getWindow();
		Stage stage = (Stage) window;
		stage.setMinHeight(100);
		stage.setMinWidth(100);
		stage.sizeToScene();
		
		
		
		
		Optional<ButtonType> result = (Optional<ButtonType>) dial.showAndWait();
		
		LoginInterface lp = new LoginProxy();
		if(result.get() == loginButtonType) {
			if(lp.connect(username.getText(), password.getText())){
				username.clear();
				password.clear();
				hideAll();
				getChildren().get(ADMIN).setVisible(true);
			}else {
				getAlertError();
			}
		}
	}
	
	/*
	 * this method display an alert error if paramaters to connect at the panel admin are not correct.
	 */
	public <T> void getAlertError() {
		Dialog<T> dial = new Dialog<>();
		dial.setResizable(true);
		dial.setTitle("Error...");
		dial.setHeaderText("Error...");
		dial.setContentText("The username or the password is wrong !");
		ButtonType btnOk = new ButtonType("OK", ButtonData.OK_DONE);
		dial.getDialogPane().getButtonTypes().add(btnOk);
		
		final Window window = dial.getDialogPane().getScene().getWindow();
		Stage stage = (Stage) window;
		stage.setMinHeight(100);
		stage.setMinWidth(100);
		stage.sizeToScene();
		
		stage.showAndWait();
		
		getAlertLogin();
	}
	
	

}
