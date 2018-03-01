package flappy.window;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import flappy.app.FlappyApp;
import flappy.sprites.Bird;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class TwoPlayer extends Background {

	private static final String REDBIRD = "/flappy/resources/redBird.png";
	private static final String BLUEBIRD = "/flappy/resources/blueBird.png";
	private static final String YELLOWBIRD = "/flappy/resources/yellowBird.png";
	private static final int MAX_SIZE  = 11;

	private List<Bird> pajaritos = new ArrayList<>();
	private List<Bird> pajaritosArriba = new ArrayList<>();
	public static Bird pajarito, pajaritoArriba;
	public static StringProperty nombre = new SimpleStringProperty();
	public static StringProperty nombreArriba = new SimpleStringProperty();
	private int indice = 0, indiceArriba = 0;

	@FXML
	private Pane paneNubes;

	@FXML
	private TextField playerText, playerArribaText;

	@FXML
	private Button previousButton, nextButton, previousArribaButton, nextArribaButton, playButton, backButton;

	public TwoPlayer() throws IOException {
		super("/flappy/view/SelectCharacter2.fxml");

		pajaritos.add(new Bird(REDBIRD));
		pajaritos.add(new Bird(BLUEBIRD));
		pajaritos.add(new Bird(YELLOWBIRD));
		
		pajaritosArriba.add(new Bird(REDBIRD));
		pajaritosArriba.add(new Bird(BLUEBIRD));
		pajaritosArriba.add(new Bird(YELLOWBIRD));

		pajarito = new Bird();
		pajaritoArriba = new Bird();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		previousButton.setOnAction(e -> previousButtonAction(e));
		nextButton.setOnAction(e -> nextButtonAction(e));
		previousArribaButton.setOnAction(e -> previousArribaButtonAction(e));
		nextArribaButton.setOnAction(e -> nextArribaButtonAction(e));
		playButton.setOnAction(e -> playButtonAction(e));
		backButton.setOnAction(e -> backButtonAction(e));
		
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
		paneNubes.getChildren().addAll(nubes, pajarito, pajaritoArriba);
		pajarito.aleteo();
		pajarito.setTranslateX(362);
		pajarito.setTranslateY(320);
		pajaritoArriba.aleteo();
		pajaritoArriba.setTranslateX(362);
		pajaritoArriba.setTranslateY(165);
		
	}

	@Override
	public void stop() {
		super.stop();
		paneNubes.getChildren().remove(nubes);
		paneNubes.getChildren().remove(pajarito);
		paneNubes.getChildren().remove(pajaritoArriba);
	}

	@FXML
	void nextButtonAction(ActionEvent event) {
		if (indice < 2) {
			previousButton.setVisible(true);
			stop();
			indice++;
			pajarito = pajaritos.get(indice);
			start();
			if (indice == 2) {
				nextButton.setVisible(false);
			}
		}
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
	void nextArribaButtonAction(ActionEvent event) {
		if (indiceArriba < 2) {
			previousArribaButton.setVisible(true);
			stop();
			indiceArriba++;
			pajaritoArriba = pajaritosArriba.get(indiceArriba);
			start();
			if (indiceArriba == 2) {
				nextArribaButton.setVisible(false);
			}
		}
	}

	@FXML
	void previousArribaButtonAction(ActionEvent event) {
		if (indiceArriba > 0) {
			nextArribaButton.setVisible(true);
			stop();
			indiceArriba--;
			pajaritoArriba = pajaritosArriba.get(indiceArriba);
			start();
			if (indiceArriba == 0) {
				previousArribaButton.setVisible(false);
			}
		}
	}


	@FXML
	void playButtonAction(ActionEvent event) {
		FlappyApp.irA(FlappyApp.juego);
		playerText.setText(playerText.getText().toUpperCase());
		nombre.bind(playerText.textProperty());
		playerArribaText.setText(playerArribaText.getText().toUpperCase());
		nombreArriba.bind(playerArribaText.textProperty());
		musicaMenu.stop();
	}
	
	@FXML
	void backButtonAction(ActionEvent event) {
		FlappyApp.irA(FlappyApp.menu);
	}

}
