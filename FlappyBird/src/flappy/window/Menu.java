package flappy.window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class Menu extends Screen implements Initializable {
	
    @FXML
    private BorderPane paneNubes;

    @FXML
    private BorderPane paneButtons;
    
    StackPane vistaMenu;
    
    @FXML
    private Button onePlayerButton, twoPlayerButton, highScoreButton, aboutButton, exitButton;

    public Menu() throws IOException {
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/flappy/vista/MenuView.fxml"));
		loader.setController(this);
		loader.load();
		
		vistaMenu = loader.getRoot();
    	
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		
	}
	
	@FXML
    void onePlayerButtonAction(ActionEvent event) {
		
	}
	
	@FXML
    void twoPlayerButtonAction(ActionEvent event) {
    	
    }
    
    @FXML
    void highScoreButtonAction(ActionEvent event) {
    	
    }

    @FXML
    void aboutButtonAction(ActionEvent event) {
    	
    }
	
}
