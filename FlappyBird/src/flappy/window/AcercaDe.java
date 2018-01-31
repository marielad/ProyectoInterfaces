package flappy.window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import flappy.app.FlappyApp;
import flappy.components.Sprite;
import flappy.components.SpritePane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class AcercaDe implements Initializable {
	
    @FXML
    private SpritePane paneNubes;

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
		
		
		
		Sprite nube = new Sprite();
		nube.setImage(new Image("/flappy/resources/flappyCloud.png"));
		
		
		Sprite nube2 = new Sprite();
		nube2.setImage(new Image("/flappy/resources/flappyCloud.png"));
		
		
		paneNubes.getSpriteList().addAll(nube,nube2);
		
	}
	
	@FXML
    void volverButtonAction(ActionEvent event) {
		
    	try {
    		
			Menu nuevoMenu = new Menu();
			FlappyApp.scene.setRoot(nuevoMenu.getMenuView());
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
    public StackPane getAcercaView() {
		return vistaAcerca;
	}
	
}