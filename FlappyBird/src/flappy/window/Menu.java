package flappy.window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import flappy.app.FlappyApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class Menu extends Screen implements Initializable {
	
    @FXML
    private BorderPane paneAnimation;

    @FXML
    private BorderPane paneButtons;
    
    StackPane vistaMenu;
    
    @FXML
    private Button onePlayerButton, twoPlayerButton, highScoreButton, aboutButton, exitButton;

    public Menu() throws IOException {
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/flappy/view/MenuView.fxml"));
		loader.setController(this);
		loader.load();
		
		vistaMenu = loader.getRoot();

    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		onePlayerButton.setOnAction(e -> onePlayerButtonAction(e));
		twoPlayerButton.setOnAction(e -> twoPlayerButtonAction(e));
		highScoreButton.setOnAction(e -> highScoreButtonAction(e));
		aboutButton.setOnAction(e -> aboutButtonAction(e));
		
    	paneAnimation.getChildren().clear();
		
		for (int i = 0; i < Screen.listaNubes.size(); i++) {
			
			paneAnimation.getChildren().add(Screen.listaNubes.get(i).getSpriteImage());
		
		}
		
	}
	
	@FXML
    void onePlayerButtonAction(ActionEvent event) {
		
	}
	
	@FXML
    void twoPlayerButtonAction(ActionEvent event) {
    	
    }
    
    @FXML
    void highScoreButtonAction(ActionEvent event) {
    	
    	try {
    		
			MejoresPuntuaciones nuevoPuntuacion = new MejoresPuntuaciones();
			FlappyApp.scene.setRoot(nuevoPuntuacion.getPuntuacionView());
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
    	
    }

    @FXML
    void aboutButtonAction(ActionEvent event) {
    	
    	try {
    		
			AcercaDe nuevoAcerca = new AcercaDe();
			FlappyApp.scene.setRoot(nuevoAcerca.getAcercaView());
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
    	
    }
    
    @FXML
    void exitButtonAction(ActionEvent event) {
    	FlappyApp.getPrimaryStage().close();
    }
    
    public StackPane getMenuView() {
		initialize(null, null);
		return vistaMenu;
	}
	
}
