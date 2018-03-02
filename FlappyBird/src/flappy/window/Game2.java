package flappy.window;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import flappy.app.FlappyApp;
import flappy.database.ScoreDB;
import flappy.sprites.Score;
import flappy.sprites.Bird;
import flappy.sprites.Tube;
import flappy.sprites.Tubes;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

/**
 * 
 * @author Jorge Delgado, Mariela Dorta
 *
 */
public class Game2 extends Background {

	public static final int POSICIONX_PAJARITO = 200;
	public static final int POSICIONY_PAJARITO = 75;
	public static final int POSICIONZ_PAJARITO = 75;
	public static final int ESPACIO_ENTRE_TUBOS = 200;

	private Bird pajarito, pajarito2;
	private Tubes tuberias;
	private Score puntuacion, puntuacion2;

	private Boolean pausado = false;
	private StringProperty puntuacionTexto, puntuacionTexto2, nombreTexto, nombreTexto2;
	
	@FXML
    private VBox buttonsBox, overBox;
	
	@FXML
	private Label scoreLabel;

    @FXML
    private Button resumeButton, optionsButton, exitButton, tryAgainButton, exitButton1;

	@FXML
	private Pane paneNubes, paneJuego, panePuntuacion;

	public Game2() throws IOException {
		super("/flappy/view/GameView.fxml");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		buttonsBox.setVisible(false);
		overBox.setVisible(false);
		
		tryAgainButton.setOnAction(e -> tryAgainButtonAction(e));
		resumeButton.setOnAction(e -> resumeButtonAction(e));
		optionsButton.setOnAction(e -> optionsButtonAction(e));
		exitButton.setOnAction(e -> exitButtonAction(e));
		exitButton1.setOnAction(e -> exitButtonAction(e));

	}
	
	@Override
	protected void onKeyPressed(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ESCAPE)) {
			if (!pausado) {
				pause();
				buttonsBox.setVisible(true);
			}
		}
		if (e.getCode().equals(KeyCode.SPACE)) {
			if (!pajarito.isPausado()) {
				pajarito.jump();
			}
		}
		if (e.getCode().equals(KeyCode.UP)) {
			if (!pajarito2.isPausado()) {
				pajarito2.jump();
			}
		}
	}
	
	@FXML
    void exitButtonAction(ActionEvent event) {
    	resume();
		stop();
		FlappyApp.irA(FlappyApp.menu);
		buttonsBox.setVisible(false);
		overBox.setVisible(false);
    }

	@FXML
    void optionsButtonAction(ActionEvent event) {
		FlappyApp.irA(FlappyApp.opciones);
    }
	
	@FXML
    void tryAgainButtonAction(ActionEvent event) {
		overBox.setVisible(false);
		resume();
		stop();
		start();
    }

	@FXML
    void resumeButtonAction(ActionEvent event) {
		resume();
		buttonsBox.setVisible(false);
    }
	
	@Override
	public void start() {
		super.start();
		paneNubes.getChildren().add(nubes);
		pajarito = TwoPlayer.pajarito;
		pajarito.setTranslateX(100);
		pajarito.setTranslateY(200);
		
		pajarito2 = TwoPlayer.pajaritoArriba;
		pajarito2.setTranslateX(100);
		pajarito2.setTranslateY(250);
		
		puntuacion = new Score();
		puntuacion.setTranslateX(873);
		puntuacion.setTranslateY(5);
		nombreTexto = TwoPlayer.nombre;		
		puntuacionTexto = new SimpleStringProperty(this, "puntuacionTexto", "SCORE:");
		panePuntuacion.getChildren().add(puntuacion);
		puntuacion.getPuntuacion().textProperty().bind(puntuacionTexto.concat(pajarito.getScore().asString()));
		
		puntuacion2 = new Score();
		puntuacion2.setTranslateX(5);
		puntuacion2.setTranslateY(5);
		nombreTexto2 = TwoPlayer.nombreArriba;		
		puntuacionTexto2 = new SimpleStringProperty(this, "puntuacionTexto2", "SCORE:");
		panePuntuacion.getChildren().add(puntuacion2);
		puntuacion2.getPuntuacion().textProperty().bind(puntuacionTexto2.concat(pajarito2.getScore().asString()));
		
		tuberias = new Tubes(FlappyApp.ANCHO, FlappyApp.ALTO, 7);
		paneJuego.getChildren().addAll(tuberias, pajarito, pajarito2);
		
		tuberias.play();
		musicaJuego.play();
		pajarito.start();
		pajarito2.start();
	}
	
	@Override
	public void stop() {
		super.stop();
		pajarito.setScore(0);
		pajarito2.setScore(0);
		musicaJuego.stop();
		pajarito.stop();
		tuberias.stop();
		paneJuego.getChildren().remove(tuberias);
		paneJuego.getChildren().remove(pajarito);
		paneJuego.getChildren().remove(pajarito2);
		paneNubes.getChildren().remove(nubes);
		panePuntuacion.getChildren().remove(puntuacion);
		panePuntuacion.getChildren().remove(puntuacion2);
		pausado = false;
	}
	
	private void pause() {
		super.stop();
		musicaJuego.pause();
		tuberias.pause();
		pajarito.pause();
		nubes.pause();
		pausado = true;
	}
	
	private void resume() {
		super.start();
		musicaJuego.resume();
		tuberias.play();
		pajarito.resume();
		nubes.resume();
		pausado = false;
	}

	protected void loop(long now) {
		if (!tuberias.getChildren().isEmpty()) {

			if (tuberias.getChildren().get(0).getTranslateX() <= -getWidth() - ESPACIO_ENTRE_TUBOS - 75) {
				Tube tuberia;

				if (Math.random() < 0.25) {
					tuberia = new Tube(getWidth(), getHeight(),false, true);
				} else if (Math.random() > 0.75) {
					if (Math.random() > 0.5) {
						tuberia = new Tube(getWidth(), getHeight(),true, false);
					} else {
						tuberia = new Tube(getWidth(), getHeight(),true, true);
					}
				} else {
					tuberia = new Tube(getWidth(), getHeight(),false, false);
				}
				tuberia.setTranslateX(tuberias.getChildren().get(tuberias.getChildren().size() - 1).getTranslateX() + (ESPACIO_ENTRE_TUBOS));
				tuberia.setTranslateZ(POSICIONZ_PAJARITO);
				
				tuberias.getChildren().add(tuberia);
				tuberias.getChildren().remove(0);
			}
			
			for (Node tuberia : tuberias.getChildren()) {
				tuberia.setTranslateX(tuberia.getTranslateX() - 3);
			}
			
		}
		checkCollisions();
	}

	public void checkCollisions() {
		for (Node node : tuberias.getChildren()) {

			Ellipse shapeAux = new Ellipse();
			
			Ellipse shapeAux2 = new Ellipse();
			
			if (node instanceof Tube) { 
				Tube tuberia = (Tube) node;
				Shape intersection = Shape.intersect(tuberia.getShape(), pajarito.getShape());
				Shape intersection2 = Shape.intersect(tuberia.getShape(), pajarito2.getShape());
				if (pajarito.getTranslateY() >= getHeight() || pajarito.getTranslateY() <= 0 || intersection.getBoundsInLocal().getWidth() != -1) {
					if (!pausado) {
						pajarito.stop();
					}
				}
				
				if (pajarito2.getTranslateY() >= getHeight() || pajarito2.getTranslateY() <= 0 || intersection2.getBoundsInLocal().getWidth() != -1) {
					if (!pausado) {
						pajarito2.stop();
					}
				}
				
				if (pajarito.isPausado() && pajarito2.isPausado()) {
					if (!pausado) {
						gameOver();
					}
				}
				
				shapeAux.centerXProperty().bind(pajarito.translateXProperty().add(pajarito.widthProperty().divide(2)));
		        shapeAux.centerYProperty().bind(pajarito.translateYProperty().add(pajarito.heightProperty().divide(2)));
		        shapeAux.rotateProperty().bind(pajarito.rotateProperty());
		        shapeAux.setRadiusX(1);
		        shapeAux.setRadiusY(1);
		        shapeAux.setVisible(false);
		        
				shapeAux2.centerXProperty().bind(pajarito2.translateXProperty().add(pajarito2.widthProperty().divide(2)));
				shapeAux2.centerYProperty().bind(pajarito2.translateYProperty().add(pajarito2.heightProperty().divide(2)));
				shapeAux2.rotateProperty().bind(pajarito2.rotateProperty());
				shapeAux2.setRadiusX(1);
				shapeAux2.setRadiusY(1);
				shapeAux2.setVisible(false);
				
				Shape intersectionTwo = Shape.intersect(tuberia.getMiddleShape(), shapeAux);
		        if (intersectionTwo.getBoundsInLocal().getWidth() != -1) {
		          pajarito.setScore(pajarito.getScore().get()+1);
		        }
		        Shape intersectionThree = Shape.intersect(tuberia.getMiddleShape(), shapeAux2);
		        if (intersectionThree.getBoundsInLocal().getWidth() != -1) {
		          pajarito2.setScore(pajarito2.getScore().get()+1);
		        }
			}
		}
	}
	
	private void gameOver() {
		pause();
		
		try {
			ScoreDB conn = new ScoreDB();
			
			PreparedStatement pstmt =  conn.getConexion().prepareStatement("INSERT INTO Puntuaciones VALUES (DEFAULT, ?, ?)");
			pstmt.setString(1, nombreTexto.get());
			pstmt.setInt(2, pajarito.getScore().get());
			
			pstmt.execute();
			pstmt.close();
			
			PreparedStatement pstmt2 =  conn.getConexion().prepareStatement("INSERT INTO Puntuaciones VALUES (DEFAULT, ?, ?)");
			pstmt2.setString(1, nombreTexto2.get());
			pstmt2.setInt(2, pajarito2.getScore().get());
			
			pstmt2.execute();
			pstmt2.close();
			
			conn.closeConexion();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		scoreLabel.textProperty().bind(nombreTexto.concat(" ").concat(puntuacionTexto.concat(pajarito.getScore().asString()).concat("  |  ")
				.concat(nombreTexto2.concat(" ").concat(puntuacionTexto2.concat(pajarito2.getScore().asString()
				)))));
		
		overBox.setVisible(true);
	}
	
}