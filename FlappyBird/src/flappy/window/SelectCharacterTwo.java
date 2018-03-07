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
 * Clase multijugador, donde cada jugador selecciona un avatar y un nombre para la partida
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
	Bird FRANBIRD = Birds.FRANBIRD;
	Bird REDBIRD2 = Birds.REDBIRD2;
	Bird GREENBIRD2 = Birds.GREENBIRD2;
	Bird BLUEBIRD2 = Birds.BLUEBIRD2;
	Bird ORANGEBIRD2 = Birds.ORANGEBIRD2;
	Bird YELLOWBIRD2 = Birds.YELLOWBIRD2;
	Bird PURPLEBIRD2 = Birds.PURPLEBIRD2;
	Bird FRANBIRD2 = Birds.FRANBIRD2;
	
	private int indice = 0, indiceArriba = 0;
	public static Bird pajarito, pajaritoArriba;
	private List<Bird> pajaritos = new ArrayList<>();
	private List<Bird> pajaritosArriba = new ArrayList<>();
	public static StringProperty nombre = new SimpleStringProperty();
	public static StringProperty nombreArriba = new SimpleStringProperty();
	
	@FXML
	private Pane paneNubes, panePajarito;

	@FXML
	private TextField playerText, playerArribaText;

	@FXML
	private Button previousButton, nextButton, previousArribaButton, nextArribaButton, playButton, backButton;

	/**
	 * Constructor de SelectCharacterTwo() en el añadimos los avatares disponibles para su selección
	 *
	 */
	
	public SelectCharacterTwo() throws IOException {
		super("/flappy/view/SelectCharacterTwo.fxml");

		pajaritos.add(REDBIRD);
		pajaritos.add(GREENBIRD);
		pajaritos.add(BLUEBIRD);
		pajaritos.add(ORANGEBIRD);
		pajaritos.add(PURPLEBIRD);
		pajaritos.add(YELLOWBIRD);
		pajaritos.add(FRANBIRD);
		
		pajaritosArriba.add(REDBIRD2);
		pajaritosArriba.add(GREENBIRD2);
		pajaritosArriba.add(BLUEBIRD2);
		pajaritosArriba.add(ORANGEBIRD2);
		pajaritosArriba.add(PURPLEBIRD2);
		pajaritosArriba.add(YELLOWBIRD2);
		pajaritosArriba.add(FRANBIRD2);
	}
	
	/**
	 * En su inicialización creamos 2 pajaros por defecto, los agregamos al Pane
	 * correspondiente, controlamos que no se pueda iniciar el juego sin antes 
	 * introducir los nombres de los jugadores y controlamos que el nombre no sea
	 * mayor que 10.
	 *
	 */

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
		
		panePajarito.getChildren().addAll(pajarito, pajaritoArriba);
		
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
	
	/**
	 * En start() posicionamos los pajaritos y los animamos
	 */

	@Override
	public void start() {
		super.start();
		paneNubes.getChildren().add(nubes);
		pajarito.aleteo();
		pajarito.setTranslateX(360);
		pajarito.setTranslateY(290);
		pajaritoArriba.aleteo();
		pajaritoArriba.setTranslateX(360);
		pajaritoArriba.setTranslateY(145);
		
	}
	
	/**
	 * En stop() simplemente paramos el gameloop
	 */

	@Override
	public void stop() {
		super.stop();
	}
	
	/**
	 * Las siguientes funciones de evento tienen como objetivo cambiar entre
	 * los 6 diferentes pajaros a elegir teniendo en cuenta el pájaro en el
	 * que estamos para irnos desplazando por estos pajaros de inicio a fin en orden.
	 * También controla los botones y los desabilita en caso de que no hayan más
	 * pajaros en una dirección.
	 * @param event
	 */

	@FXML
	void nextButtonAction(ActionEvent event) {
		if (indice < 6) {
			paneNubes.getChildren().remove(nubes);
			panePajarito.getChildren().remove(pajarito);
			previousButton.setVisible(true);
			stop();
			indice++;
			pajarito = pajaritos.get(indice);
			panePajarito.getChildren().add(pajarito);
			start();
			if (indice == 6) {
				nextButton.setVisible(false);
			}
		}
	}

	@FXML
	void previousButtonAction(ActionEvent event) {
		if (indice > 0) {
			paneNubes.getChildren().remove(nubes);
			panePajarito.getChildren().remove(pajarito);
			nextButton.setVisible(true);
			stop();
			indice--;
			pajarito = pajaritos.get(indice);
			panePajarito.getChildren().add(pajarito);
			start();
			if (indice == 0) {
				previousButton.setVisible(false);
			}
		}
	}
	
	@FXML
	void nextArribaButtonAction(ActionEvent event) {
		if (indiceArriba < 6) {
			paneNubes.getChildren().remove(nubes);
			panePajarito.getChildren().remove(pajaritoArriba);
			previousArribaButton.setVisible(true);
			stop();
			indiceArriba++;
			pajaritoArriba = pajaritosArriba.get(indiceArriba);
			panePajarito.getChildren().add(pajaritoArriba);
			start();
			if (indiceArriba == 6) {
				nextArribaButton.setVisible(false);
			}
		}
	}

	@FXML
	void previousArribaButtonAction(ActionEvent event) {
		if (indiceArriba > 0) {
			paneNubes.getChildren().remove(nubes);
			panePajarito.getChildren().remove(pajaritoArriba);
			nextArribaButton.setVisible(true);
			stop();
			indiceArriba--;
			pajaritoArriba = pajaritosArriba.get(indiceArriba);
			panePajarito.getChildren().add(pajaritoArriba);
			start();
			if (indiceArriba == 0) {
				previousArribaButton.setVisible(false);
			}
		}
	}
	
	/**
	 * playButtonAction(ActionEvent event) tiene como objetivo iniciar el juego
	 * compartiendo los nombres introducidos con Game()
	 * @param event
	 */

	@FXML
	void playButtonAction(ActionEvent event) {
		FlappyApp.goTo(FlappyApp.juegoTwo);
		playerText.setText(playerText.getText().toUpperCase());
		nombre.bind(playerText.textProperty());
		playerArribaText.setText(playerArribaText.getText().toUpperCase());
		nombreArriba.bind(playerArribaText.textProperty());
		menuMusic.stop();
	}
	
	/**
	 * Volvemos al menu
	 * @param event
	 */
	
	@FXML
	void backButtonAction(ActionEvent event) {
		FlappyApp.goTo(FlappyApp.menu);
	}

}
