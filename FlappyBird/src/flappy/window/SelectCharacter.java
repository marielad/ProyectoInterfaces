package flappy.window;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import flappy.app.FlappyApp;
import flappy.sprites.Bird;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class SelectCharacter extends Background {

	private static final String REDBIRD = "/flappy/resources/redBird.png";
	private static final String BLUEBIRD = "/flappy/resources/blueBird.png";
	private static final String YELLOWBIRD = "/flappy/resources/yellowBird.png";

	private List<Bird> pajaritos = new ArrayList<>();
	public static Bird pajarito;
	private int indice = 0;

	@FXML
	private Pane paneNubes;

	@FXML
	private TextField playerText;

	@FXML
	private Button previousButton, nextButton, playButton, backButton;

	public SelectCharacter() throws IOException {
		super("/flappy/view/SelectCharacter.fxml");

		pajaritos.add(new Bird(REDBIRD));
		pajaritos.add(new Bird(BLUEBIRD));
		pajaritos.add(new Bird(YELLOWBIRD));

		pajarito = new Bird();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		previousButton.setOnAction(e -> previousButtonAction(e));
		nextButton.setOnAction(e -> nextButtonAction(e));
		playButton.setOnAction(e -> playButtonAction(e));
		backButton.setOnAction(e -> backButtonAction(e));
		
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
	void playButtonAction(ActionEvent event) {
		FlappyApp.irA(FlappyApp.juego);
		musicaMenu.stop();
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
		FlappyApp.irA(FlappyApp.menu);
	}

}
