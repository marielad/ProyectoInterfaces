package flappy.window;

import java.io.IOException;

import flappy.app.FlappyApp;
import flappy.sound.Sounds;
import flappy.sprites.Clouds;
import gamefx.Screen;
import gamefx.Sound;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public abstract class Background extends Screen {
	
	static final Clouds nubes = new Clouds(15);

	Sound musicaMenu = Sounds.MENU;
	Sound musicaJuego = Sounds.JUEGO;
	
	public Background(String url) throws IOException {
		super(url);
		nubes.mover();
	}

	@Override
	public void start() {
		super.start();
	}
	
	@Override
	public void stop() {
		super.stop();
	}
	
	@Override
	protected void onKeyPressed(KeyEvent e) {
		//Falta mutear la Screen Game con 'M'
		if (e.getCode().equals(KeyCode.ESCAPE)) {
			if(FlappyApp.primaryStage.getScene() != FlappyApp.menu.getScene()
					&& FlappyApp.primaryStage.getScene() != FlappyApp.juego.getScene()) {
				stop();
				FlappyApp.irA(FlappyApp.menu);
			}
		}
		
		if (e.getCode().equals(KeyCode.M)) {
			if (!musicaMenu.isMuted() || !musicaJuego.isMuted()) {
				musicaMenu.mute(true);
				musicaJuego.mute(true);
			} else {
				musicaMenu.mute(false);
				musicaJuego.mute(false);
			}
		}
	}

}
