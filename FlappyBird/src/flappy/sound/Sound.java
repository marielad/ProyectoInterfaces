package flappy.sound;

import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Sound {

	private final static int SINCOMIENZO = 0;
	private final static int SONANDO = 1;
	private final static int PAUSADO = 2;
	private final static int TERMINADO = 3;

	private final Player reproductor;

	private final Object cierreReproductor = new Object();

	private int estadoReproductor = SINCOMIENZO;
	
	public Sound(final InputStream inputStream) throws JavaLayerException {

		this.reproductor = new Player(inputStream);

	}

	public void play() throws JavaLayerException {

		synchronized (cierreReproductor) {

			switch (estadoReproductor) {

			case SINCOMIENZO:

				final Runnable r = new Runnable() {

					public void run() {

						playInternal();

					}
				};

				final Thread t = new Thread(r);
				t.setDaemon(true);
				t.setPriority(Thread.MAX_PRIORITY);
				estadoReproductor = SONANDO;
				t.start();
				break;

			case PAUSADO:
				resume();
				break;

			default:
				break;

			}

		}

	}

	public boolean pause() {

		synchronized (cierreReproductor) {

			if (estadoReproductor == SONANDO) {

				estadoReproductor = PAUSADO;

			}

			return estadoReproductor == PAUSADO;

		}

	}

	public boolean resume() {

		synchronized (cierreReproductor) {

			if (estadoReproductor == PAUSADO) {

				estadoReproductor = SONANDO;
				cierreReproductor.notifyAll();

			}

			return estadoReproductor == SONANDO;
		}

	}

	public void stop() {

		synchronized (cierreReproductor) {

			estadoReproductor = TERMINADO;
			cierreReproductor.notifyAll();

		}

	}

	private void playInternal() {

		while (estadoReproductor != TERMINADO) {

			try {

				if (!reproductor.play(1)) {

					break;
				}

			} catch (final JavaLayerException e) {

				break;

			}

			synchronized (cierreReproductor) {

				while (estadoReproductor == PAUSADO) {

					try {

						cierreReproductor.wait();

					} catch (final InterruptedException e) {

						break;
					}
				}
			}
		}

		close();
	}

	public void close() {

		synchronized (cierreReproductor) {

			estadoReproductor = TERMINADO;

		}

		try {

			reproductor.close();

		} catch (final Exception e) {

			e.printStackTrace();

		}
	}

}