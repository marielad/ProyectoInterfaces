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

public class SelectCharacterTwo extends Background {

	private static final int MAX_SIZE  = 11;
	Bird REDBIRD = Birds.REDBIRD;
	Bird GREENBIRD = Birds.GREENBIRD;
	Bird BLUEBIRD = Birds.BLUEBIRD;
	Bird ORANGEBIRD = Birds.ORANGEBIRD;
	Bird YELLOWBIRD = Birds.YELLOWBIRD;
	Bird PURPLEBIRD = Birds.PURPLEBIRD;
	Bird REDBIRD2 = Birds.REDBIRD2;
	Bird GREENBIRD2 = Birds.GREENBIRD2;
	Bird BLUEBIRD2 = Birds.BLUEBIRD2;
	Bird ORANGEBIRD2 = Birds.ORANGEBIRD2;
	Bird YELLOWBIRD2 = Birds.YELLOWBIRD2;
	Bird PURPLEBIRD2 = Birds.PURPLEBIRD2;
	
	private int indice = 0, indiceArriba = 0;
	public static Bird pajarito, pajaritoArriba;
	private List<Bird> pajaritos = new ArrayList<>();
	private List<Bird> pajaritosArriba = new ArrayList<>();
	public static StringProperty nombre = new SimpleStringProperty();
	public static StringProperty nombreArriba = new SimpleStringProperty();
	
	@FXML
	private Pane paneNubes;

	@FXML
	private TextField playerText, playerArribaText;

	@FXML
	private Button previousButton, nextButton, previousArribaButton, nextArribaButton, playButton, backButton;

	public SelectCharacterTwo() throws IOException {
		super("/flappy/view/SelectCharacterTwo.fxml");

		pajaritos.add(REDBIRD);
		pajaritos.add(GREENBIRD);
		pajaritos.add(BLUEBIRD);
		pajaritos.add(ORANGEBIRD);
		pajaritos.add(PURPLEBIRD);
		pajaritos.add(YELLOWBIRD);
		
		pajaritosArriba.add(REDBIRD2);
		pajaritosArriba.add(GREENBIRD2);
		pajaritosArriba.add(BLUEBIRD2);
		pajaritosArriba.add(ORANGEBIRD2);
		pajaritosArriba.add(PURPLEBIRD2);
		pajaritosArriba.add(YELLOWBIRD2);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		previousButton.setOnAction(e -> previousButtonAction(e));
		nextButton.setOnAction(e -> nextButtonAction(e));
		previousArribaButton.setOnAction(e -> previousArribaButtonAction(e));
		nextArribaButton.setOnAction(e -> nextArribaButtonAction(e));
		playButton.setOnAction(e -> playButtonAction(e));
		backButton.setOnAction(e -> backButtonAction(e));

		pajarito = new Bird();
		pajaritoArriba = new Bird();
		
		paneNubes.getChildren().addAll(nubes, pajarito, pajaritoArriba);
		
		playButton.disableProperty().bind(playerText.textProperty().isEmpty().or(playerArribaText.textProperty().isEmpty()));

		playerText.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.length() < MAX_SIZE) {
		    	playerText.setText(newValue);
		    }
			if (newValue.length() == MAX_SIZE) {
		    	playerText.setText(oldValue);
		    }
		});
		playerArribaText.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.length() < MAX_SIZE) {
		    	playerArribaText.setText(newValue);
		    }
			if (newValue.length() == MAX_SIZE) {
		    	playerArribaText.setText(oldValue);
		    }
		});
		previousButton.setVisible(false);
		previousArribaButton.setVisible(false);
	}

	@Override
	public void start() {
		super.start();
		pajarito.aleteo();
		pajarito.setTranslateX(360);
		pajarito.setTranslateY(290);
		pajaritoArriba.aleteo();
		pajaritoArriba.setTranslateX(360);
		pajaritoArriba.setTranslateY(145);
		
	}

	@Override
	public void stop() {
		super.stop();
	}

	@FXML
	void nextButtonAction(ActionEvent event) {
		if (indice < 5) {
			paneNubes.getChildren().remove(nubes);
			paneNubes.getChildren().remove(pajarito);
			previousButton.setVisible(true);
			stop();
			indice++;
			pajarito = pajaritos.get(indice);
			paneNubes.getChildren().addAll(nubes, pajarito);
			start();
			if (indice == 5) {
				nextButton.setVisible(false);
			}
		}
	}

	@FXML
	void previousButtonAction(ActionEvent event) {
		if (indice > 0) {
			paneNubes.getChildren().remove(nubes);
			paneNubes.getChildren().remove(pajarito);
			nextButton.setVisible(true);
			stop();
			indice--;
			pajarito = pajaritos.get(indice);
			paneNubes.getChildren().addAll(nubes, pajarito);
			start();
			if (indice == 0) {
				previousButton.setVisible(false);
			}
		}
	}
	
	@FXML
	void nextArribaButtonAction(ActionEvent event) {
		if (indiceArriba < 5) {
			paneNubes.getChildren().remove(nubes);
			paneNubes.getChildren().remove(pajaritoArriba);
			previousArribaButton.setVisible(true);
			stop();
			indiceArriba++;
			pajaritoArriba = pajaritosArriba.get(indiceArriba);
			paneNubes.getChildren().addAll(nubes, pajaritoArriba);
			start();
			if (indiceArriba == 5) {
				nextArribaButton.setVisible(false);
			}
		}
	}

	@FXML
	void previousArribaButtonAction(ActionEvent event) {
		if (indiceArriba > 0) {
			paneNubes.getChildren().remove(nubes);
			paneNubes.getChildren().remove(pajaritoArriba);
			nextArribaButton.setVisible(true);
			stop();
			indiceArriba--;
			pajaritoArriba = pajaritosArriba.get(indiceArriba);
			paneNubes.getChildren().addAll(nubes, pajaritoArriba);
			start();
			if (indiceArriba == 0) {
				previousArribaButton.setVisible(false);
			}
		}
	}


	@FXML
	void playButtonAction(ActionEvent event) {
		FlappyApp.goTo(FlappyApp.juegoTwo);
		playerText.setText(playerText.getText().toUpperCase());
		nombre.bind(playerText.textProperty());
		playerArribaText.setText(playerArribaText.getText().toUpperCase());
		nombreArriba.bind(playerArribaText.textProperty());
		menuMusic.stop();
	}
	
	@FXML
	void backButtonAction(ActionEvent event) {
		FlappyApp.goTo(FlappyApp.menu);
	}

}
