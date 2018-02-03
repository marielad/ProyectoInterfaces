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

public class AcercaDe implements Initializable {
	
    @FXML
    private BorderPane paneAnimation;

    @FXML
    private BorderPane paneButtons;
    
    StackPane vistaAcerca;
    
    @FXML
    private Button volverButton;
    
	public AcercaDe() throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/flappy/view/AboutView.fxml"));
		loader.setController(this);
		loader.load();

		vistaAcerca = loader.getRoot();

	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		volverButton.setOnAction(e -> volverButtonAction(e));
		
		for (int i = 0; i < Screen.listaNubes.size(); i++) {
			
			paneAnimation.getChildren().add(Screen.listaNubes.get(i).getSpriteImage());
		
		}
		
	}
	
	@FXML
    void volverButtonAction(ActionEvent event) {
		
		FlappyApp.scene.setRoot(FlappyApp.menuControl.getMenuView());
		
	}
	
    public StackPane getAcercaView() {
		return vistaAcerca;
	}
	
}