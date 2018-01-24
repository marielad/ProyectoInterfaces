package flappy.menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import flappy.animacion.MovimientoNube;
import flappy.app.GameApp;
import flappy.juego.Juego;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class Menu implements Initializable {
	
	BorderPane vistaMenu;
	
	MovimientoNube movimiento;

	@FXML
    private Button onePlayerButton, twoPlayerButton, highScoreButton, aboutButton, exitButton;
    
	public Menu() throws IOException {
		
		movimiento = new MovimientoNube();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/flappy/vista/MenuView.fxml"));
		loader.setController(this);
		loader.load();
		
		vistaMenu = loader.getRoot();
		
		vistaMenu.getChildren().addAll(movimiento.getListaNubesRapidas());
		vistaMenu.getChildren().addAll(movimiento.getListaNubesLentas());
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		onePlayerButton.setOnAction(e -> onePlayerButtonAction(e));
		twoPlayerButton.setOnAction(e -> twoPlayerButtonAction(e));
		highScoreButton.setOnAction(e -> highScoreButtonAction(e));
		aboutButton.setOnAction(e -> aboutButtonAction(e));
		exitButton.setOnAction(e -> exitButtonAction(e));
		
	}
	
    @FXML
    void onePlayerButtonAction(ActionEvent event) {
    	
    	try {
    		
			Juego nuevoJuego = new Juego();
			GameApp.scene.setRoot(nuevoJuego.getJuegoView());
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
    	
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

    @FXML
    void exitButtonAction(ActionEvent event) {
    	GameApp.getPrimaryStage().close();
    }

    public BorderPane getVistaMenu() {
		return vistaMenu;
	}

}