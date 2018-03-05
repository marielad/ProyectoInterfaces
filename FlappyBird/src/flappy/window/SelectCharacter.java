package flappy.window;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import flappy.app.FlappyApp;
import flappy.sprites.Bird;
import flappy.sprites.Birds;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * 
 * @author Jorge Delgado, Mariela Dorta
 *
 */
public class SelectCharacter extends Background {

	private static final int MAX_SIZE  = 11;
	Bird REDBIRD = Birds.REDBIRD;
	Bird GREENBIRD = Birds.GREENBIRD;
	Bird BLUEBIRD = Birds.BLUEBIRD;
	Bird ORANGEBIRD = Birds.ORANGEBIRD;
	Bird YELLOWBIRD = Birds.YELLOWBIRD;
	Bird PURPLEBIRD = Birds.PURPLEBIRD;

	private List<Bird> pajaritos = new ArrayList<>();
	public static Bird pajarito;
	public static StringProperty nombre = new SimpleStringProperty();
	private int indice = 0;

	@FXML
	private Pane paneNubes;

	@FXML
	private TextField playerText;

	@FXML
	private Button previousButton, nextButton, playButton, backButton;

	public SelectCharacter() throws IOException {
		super("/flappy/view/SelectCharacter.fxml");

		pajaritos.add(REDBIRD);
		pajaritos.add(GREENBIRD);
		pajaritos.add(BLUEBIRD);
		pajaritos.add(ORANGEBIRD);
		pajaritos.add(PURPLEBIRD);
		pajaritos.add(YELLOWBIRD);

		pajarito = new Bird();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		previousButton.setOnAction(e -> previousButtonAction(e));
		nextButton.setOnAction(e -> nextButtonAction(e));
		playButton.setOnAction(e -> playButtonAction(e));
		backButton.setOnAction(e -> backButtonAction(e));
		
		playButton.disableProperty().bind(playerText.textProperty().isEmpty());

		playerText.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.length() < MAX_SIZE) {
		    	playerText.setText(newValue);
		    }
			if (newValue.length() == MAX_SIZE) {
		    	playerText.setText(oldValue);
		    }
		});
		
		previousButton.setVisible(false);
	}

	@Override
	public void start() {
		super.start();
		paneNubes.getChildren().addAll(nubes, pajarito);
		pajarito.aleteo();
		pajarito.setTranslateX(362);
		pajarito.setTranslateY(228);
	}

	@Override
	public void stop() {
		super.stop();
		paneNubes.getChildren().remove(nubes);
		paneNubes.getChildren().remove(pajarito);
	}

	@FXML
	void nextButtonAction(ActionEvent event) {
		if (indice < 5) {
			previousButton.setVisible(true);
			stop();
			indice++;
			pajarito = pajaritos.get(indice);
			start();
			if (indice == 5) {
				nextButton.setVisible(false);
			}
		}
	}

	@FXML
	void playButtonAction(ActionEvent event) {
		FlappyApp.goTo(FlappyApp.juego);
		playerText.setText(playerText.getText().toUpperCase());
		nombre.bind(playerText.textProperty());
		menuMusic.stop();
	}

	@FXML
	void previousButtonAction(ActionEvent event) {
		if (indice > 0) {
			nextButton.setVisible(true);
			stop();
			indice--;
			pajarito = pajaritos.get(indice);
			start();
			if (indice == 0) {
				previousButton.setVisible(false);
			}
		}
	}

	@FXML
	void backButtonAction(ActionEvent event) {
		FlappyApp.goTo(FlappyApp.menu);
	}

}
