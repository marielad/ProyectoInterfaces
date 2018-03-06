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
 * Clase de un jugador, donde el jugador selecciona un avatar y un nombre para la partida
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
	
	/**
	 * Constructor de SelectCharacter() en el añadimos los avatares disponibles para su selección
	 * También cremos un pájaro por defecto
	 */

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
	
	/**
	 * En su inicialización creamos un pájaro por defecto, lo agregamos al Pane
	 * correspondiente, controlamos que no se pueda iniciar el juego sin antes 
	 * introducir el nombre del jugador  y controlamos que el nombre no sea
	 * mayor que 10.
	 *
	 */

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
	
	/**
	 * En start() posicionamos el pajarito y los animamos
	 */

	@Override
	public void start() {
		super.start();
		paneNubes.getChildren().addAll(nubes, pajarito);
		pajarito.aleteo();
		pajarito.setTranslateX(362);
		pajarito.setTranslateY(228);
	}
	
	/**
	 * En stop() paramos el gameloop y borramos las nubes y el pajarito
	 */

	@Override
	public void stop() {
		super.stop();
		paneNubes.getChildren().remove(nubes);
		paneNubes.getChildren().remove(pajarito);
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
	
	/**
	 * playButtonAction(ActionEvent event) tiene como objetivo iniciar el juego
	 * compartiendo el nombre introducido con Game()
	 * @param event
	 */

	@FXML
	void playButtonAction(ActionEvent event) {
		FlappyApp.goTo(FlappyApp.juego);
		playerText.setText(playerText.getText().toUpperCase());
		nombre.bind(playerText.textProperty());
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
