package base;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Mundo implements Runnable {
	Panel panelPrincipal;
	private int aleatorio;
	private double probabilidad;
	private Sprite aux;

	// Medio Ambiente
	public static ArrayList<Sprite> medioAmbiente;
	public static ArrayList<Sprite> medioAmbienteNocivo;
	public static ArrayList<Sprite> enemigos;
	// ---------------------------------------------------------------------
	private static File fondo;
	public static Image imgenFondo = null;
	private static final String RUTA_IMG_FONDO01 = "imagenes" + File.separator + "medioAmbiente\\fondo01.png";
	private static final String RUTA_IMG_FONDO02 = "imagenes" + File.separator + "medioAmbiente\\fondo02.png";
	private static final String RUTA_IMG_FONDO03 = "imagenes" + File.separator + "medioAmbiente\\fondo03.png";
	private static final String RUTA_IMG_FONDO04 = "imagenes" + File.separator + "medioAmbiente\\fondo04.png";
	private static final String RUTA_IMG_FONDO05 = "imagenes" + File.separator + "medioAmbiente\\fondo05.png";

	public static String RUTA_IMG_GATO = "imagenes\\medioAmbiente\\gato.png";

	private final String RUTA_IMG_ROCA01 = "imagenes\\medioAmbiente\\roca01.png";
	private final String RUTA_IMG_ROCA02 = "imagenes\\medioAmbiente\\roca02.png";
	private final String RUTA_IMG_ROCA03 = "imagenes\\medioAmbiente\\roca03.png";
	private final String RUTA_IMG_ROCA04 = "imagenes\\medioAmbiente\\roca04.png";
	private final String RUTA_IMG_ROCA05 = "imagenes\\medioAmbiente\\roca05.png";

	private final String RUTA_IMG_ARBOL01 = "imagenes\\medioAmbiente\\arbol01.png";
	private final String RUTA_IMG_ARBOL02 = "imagenes\\medioAmbiente\\arbol02.png";
	private final String RUTA_IMG_CESPE01 = "imagenes\\medioAmbiente\\cespe01.png";
	private final String RUTA_IMG_CESPE02 = "imagenes\\medioAmbiente\\cespe02.png";
	private final String RUTA_IMG_TRONCO = "imagenes\\medioAmbiente\\tronco.png";

	static private final String RUTA_IMG_AGUA1 = "imagenes\\medioAmbiente\\agua1.png";
	static private final String RUTA_IMG_AGUA2 = "imagenes\\medioAmbiente\\agua2.png";
	// ---------------------------------------------------------------------
	private final String RUTA_IMG_FUEGO = "imagenes\\medioAmbiente\\fuego.png";
	private final String RUTA_IMG_ENEMIGO1 = "imagenes\\enemigos\\enemigo01.png";
	private final String RUTA_IMG_ENEMIGO2 = "imagenes\\enemigos\\enemigo02.png";
	private final String RUTA_IMG_ENEMIGO3 = "imagenes\\enemigos\\enemigo03.png";
	private final String RUTA_IMG_ENEMIGO4 = "imagenes\\enemigos\\enemigo04.png";

	public Mundo(Panel panelPrincipal) {
		this.panelPrincipal = panelPrincipal;
	}

	@Override
	public void run() {
		generarMundo();
	}

	public void generarMundo() {
		gestionarLago();
		gestionMedioAmbiente();
		gestionMedioAmbienteNocivo();
		gestionEnemigos();
		gestionArmas();
		gestionGato();
		limpiarSuperpuestos();
	}

	public void gestionGato() {
		medioAmbiente.add(new Sprite(32, 32, (Utilidades.aleatorio(0, (panelPrincipal.getWidth() - aleatorio))),
				(Utilidades.aleatorio(0, (panelPrincipal.getHeight() - aleatorio))), 0, 0, Color.green, RUTA_IMG_GATO,
				1));
	}

	public void gestionArmas() {
		// Arma
		// Baston Click
		Jugador.armas.add(new Sprite(32, 32, (Utilidades.aleatorio(0, (panelPrincipal.getWidth() - 32))),
				(Utilidades.aleatorio(0, (panelPrincipal.getHeight() - 32))), 0, 0, Color.green,
				Armas.RUTA_IMG_BASTON_CLICK));
		// Bason Espaco
		Jugador.armas.add(new Sprite(32, 32, (Utilidades.aleatorio(0, (panelPrincipal.getWidth() - 32))),
				(Utilidades.aleatorio(0, (panelPrincipal.getHeight() - 32))), 0, 0, Color.green,
				Armas.RUTA_IMG_BASTON_ESPACIO));
	}

	public void gestionMedioAmbiente() {
		generarInerte();
		generarMaleza();
	}

	public void generarInerte() {

		for (int i = 0; i < Utilidades.aleatorio(3, 15); i++) {
			aleatorio = Utilidades.aleatorio(34, 32);

			aux = new Sprite(aleatorio, aleatorio, (Utilidades.aleatorio(0, (panelPrincipal.getWidth() - aleatorio))),
					(Utilidades.aleatorio(0, (panelPrincipal.getHeight() - aleatorio))), Color.green);

			probabilidad = Utilidades.obtenerProbabilidad();
			if ((probabilidad > 0) && (probabilidad <= 0.20)) {
				aux.setAlto(Utilidades.aleatorio(34, 10));
				aux.setAncho(Utilidades.aleatorio(34, 10));
				aux.setRutaYCargar(RUTA_IMG_ROCA01);
			}
			if ((probabilidad > 0.20) && (probabilidad <= 0.40)) {
				aux.setAlto(Utilidades.aleatorio(34, 10));
				aux.setAncho(Utilidades.aleatorio(34, 10));
				aux.setRutaYCargar(RUTA_IMG_ROCA02);
			}
			if ((probabilidad > 0.40) && (probabilidad <= 0.60)) {
				aux.setAlto(Utilidades.aleatorio(34, 10));
				aux.setAncho(Utilidades.aleatorio(34, 10));
				aux.setRutaYCargar(RUTA_IMG_ROCA03);
			}
			if ((probabilidad > 0.60) && (probabilidad <= 0.80)) {
				aux.setRutaYCargar(RUTA_IMG_ROCA04);
			}
			if ((probabilidad > 0.80) && (probabilidad <= 1)) {
				aux.setRutaYCargar(RUTA_IMG_ROCA05);
			}
			medioAmbiente.add(aux);

		}
	}

	public void generarMaleza() {
		for (int i = 0; i < Utilidades.aleatorio(10, 15); i++) {
			aleatorio = Utilidades.aleatorio(34, 30);
			aux = new Sprite(aleatorio, aleatorio, (Utilidades.aleatorio(0, (panelPrincipal.getWidth() - aleatorio))),
					(Utilidades.aleatorio(0, (panelPrincipal.getHeight() - aleatorio))), Color.green);

			probabilidad = Utilidades.obtenerProbabilidad();
			if ((probabilidad > 0) && (probabilidad <= 0.20)) {
				aux.setAlto(Utilidades.aleatorio(64, 30));
				aux.setRutaYCargar(RUTA_IMG_ARBOL01);
			}
			if ((probabilidad > 0.20) && (probabilidad <= 0.40)) {
				aux.setAlto(Utilidades.aleatorio(64, 30));
				aux.setRutaYCargar(RUTA_IMG_ARBOL02);
			}
			if ((probabilidad > 0.40) && (probabilidad <= 0.60)) {
				aux.setAlto(Utilidades.aleatorio(34, 10));
				aux.setAncho(Utilidades.aleatorio(34, 10));
				aux.setRutaYCargar(RUTA_IMG_CESPE01);
			}
			if ((probabilidad > 0.60) && (probabilidad <= 0.80)) {
				aux.setAlto(Utilidades.aleatorio(34, 10));
				aux.setAncho(Utilidades.aleatorio(34, 10));
				aux.setRutaYCargar(RUTA_IMG_CESPE02);
			}
			if ((probabilidad > 0.80) && (probabilidad <= 1)) {
				aux.setAlto(33);
				aux.setRutaYCargar(RUTA_IMG_TRONCO);
			}

			medioAmbiente.add(aux);
		}
	}

	public void gestionMedioAmbienteNocivo() {
		// fuego
		aleatorio = Utilidades.aleatorio(34, 30);
		medioAmbienteNocivo.add(
				new Sprite(aleatorio, aleatorio, (Utilidades.aleatorio(0, (panelPrincipal.getWidth() - aleatorio))),
						(Utilidades.aleatorio(0, (panelPrincipal.getHeight() - aleatorio))), 0, 0, Color.green,
						RUTA_IMG_FUEGO, 1));
	}

	public void gestionEnemigos() {
		// Enemigo 1

		int aleatorioTamanio = Utilidades.aleatorio(34, 30);
		int aleatorioVelocidad = Utilidades.aleatorio(4, 5);
		enemigos.add(new Sprite(44, 44, (Utilidades.aleatorio(0, (panelPrincipal.getWidth() - aleatorioTamanio))),
				(Utilidades.aleatorio(0, (panelPrincipal.getHeight() - aleatorioTamanio))), aleatorioVelocidad,
				aleatorioVelocidad, Color.red, RUTA_IMG_ENEMIGO1, 2));

		aleatorioTamanio = Utilidades.aleatorio(34, 30);
		aleatorioVelocidad = Utilidades.aleatorio(4, 6);
		enemigos.add(new Sprite(44, 44, (Utilidades.aleatorio(0, (panelPrincipal.getWidth() - aleatorioTamanio))),
				(Utilidades.aleatorio(0, (panelPrincipal.getHeight() - aleatorioTamanio))), aleatorioVelocidad,
				aleatorioVelocidad, Color.red, RUTA_IMG_ENEMIGO2, 4));

		aleatorioTamanio = Utilidades.aleatorio(34, 30);
		aleatorioVelocidad = Utilidades.aleatorio(4, 6);
		enemigos.add(new Sprite(44, 44, (Utilidades.aleatorio(0, (panelPrincipal.getWidth() - aleatorioTamanio))),
				(Utilidades.aleatorio(0, (panelPrincipal.getHeight() - aleatorioTamanio))), aleatorioVelocidad,
				aleatorioVelocidad, Color.red, RUTA_IMG_ENEMIGO3, 6));

		aleatorioTamanio = Utilidades.aleatorio(34, 30);
		aleatorioVelocidad = Utilidades.aleatorio(4, 6);
		enemigos.add(new Sprite(44, 44, (Utilidades.aleatorio(0, (panelPrincipal.getWidth() - aleatorioTamanio))),
				(Utilidades.aleatorio(0, (panelPrincipal.getHeight() - aleatorioTamanio))), aleatorioVelocidad,
				aleatorioVelocidad, Color.red, RUTA_IMG_ENEMIGO4, 10));

	}

	public static void animacionAgua() {

		if (Utilidades.actuarConProbabilidad(0.111111f)) {

			for (int i = 0; i < medioAmbiente.size(); i++) {
				switch (medioAmbiente.get(i).getRuta()) {
				case RUTA_IMG_AGUA1: {
					medioAmbiente.get(i).setRuta(RUTA_IMG_AGUA2);
					medioAmbiente.get(i).cargarSprite();
					break;
				}
				case RUTA_IMG_AGUA2: {
					medioAmbiente.get(i).setRuta(RUTA_IMG_AGUA1);
					medioAmbiente.get(i).cargarSprite();
					break;
				}
				default:
					break;
				}
			}

		}
	}

	// Salta fallo de vec en cuando
	public void gestionarLago() {
		int aleatorioTamanio = Utilidades.aleatorio(34, 30);
		// Lago
		int aleatorioX = Utilidades.aleatorio(0, panelPrincipal.getWidth() - 400);
		int aleatorioY = Utilidades.aleatorio(0, panelPrincipal.getHeight());
		int aleatorioMultiplicadorX = Utilidades.aleatorioSinCero(10, 10);
		int aleatorioMultiplicadorY = Utilidades.aleatorioSinCero(-10, 10);
		// Crear partes del lago
		for (int i = 0; i < 6; i++) {
			aleatorioTamanio = Utilidades.aleatorio(34, 25);
			medioAmbiente.add(new Sprite(aleatorioTamanio * 3, aleatorioTamanio * 3,
					aleatorioX + (aleatorioMultiplicadorX * ((aleatorioTamanio) / 2)),
					aleatorioY + (aleatorioMultiplicadorY * ((aleatorioTamanio) / 2)), 0, 0, Color.green,
					RUTA_IMG_AGUA1));
			// Colacacion del Lago
			if (Utilidades.actuarConProbabilidad(0.50f)) {
				if (Utilidades.actuarConProbabilidad(0.50f)) {
					if (Utilidades.actuarConProbabilidad(0.50f)) {
						aleatorioMultiplicadorX++;
						aleatorioMultiplicadorY++;
					} else {
						aleatorioMultiplicadorX--;
						aleatorioMultiplicadorY--;
					}
				} else {
					if (Utilidades.actuarConProbabilidad(0.50f)) {
						aleatorioMultiplicadorX--;
						aleatorioMultiplicadorY++;
					} else {
						aleatorioMultiplicadorX++;
						aleatorioMultiplicadorY--;
					}
				}
			}
		}
	}

	/**
	 * Eliminar elementos del medio ambiente que colisionan
	 * 
	 * Si el medio ambiente colisiona con algo
	 * 
	 * Si es el jugador, se elimina el sprite del medio ambiente
	 * 
	 * Si no es el jugador, si no es agua, se elimina el sprite
	 */
	public void limpiarSuperpuestos() {

		for (int i = 0; i < medioAmbiente.size(); i++) {
			for (int j = 0; j < medioAmbiente.size(); j++) {

				if ((medioAmbiente.get(i).colisionaCon(medioAmbiente.get(j)) && i != j)
						|| medioAmbiente.get(j).colisionaCon(Jugador.player)) {

					if (medioAmbiente.get(j).colisionaCon(Jugador.player)) {
						medioAmbiente.set(j, new Sprite(1, 1, 100000, 100000, 1, 1, Color.CYAN, ""));

					} else {
						if (medioAmbiente.get(j).getRuta().contains(RUTA_IMG_AGUA1)
								|| medioAmbiente.get(j).getRuta().contains(RUTA_IMG_AGUA2)) {
						} else {

							medioAmbiente.set(j, new Sprite(1, 1, 10000, 10000, 1, 1, Color.CYAN, ""));

						}
					}

				}
			}
		}
	}

	public static void cargarFondo() {
		double probabilidad;
		probabilidad = Utilidades.obtenerProbabilidad();
		if ((probabilidad > 0) && (probabilidad <= 0.20)) {
			fondo = new File(RUTA_IMG_FONDO01);

		}
		if ((probabilidad > 0.20) && (probabilidad <= 0.40)) {
			fondo = new File(RUTA_IMG_FONDO02);

		}
		if ((probabilidad > 0.40) && (probabilidad <= 0.60)) {
			fondo = new File(RUTA_IMG_FONDO03);

		}
		if ((probabilidad > 0.60) && (probabilidad <= 0.80)) {
			fondo = new File(RUTA_IMG_FONDO04);

		}
		if ((probabilidad > 0.80) && (probabilidad <= 1)) {
			fondo = new File(RUTA_IMG_FONDO05);

		}

		try {
			imgenFondo = ImageIO.read(fondo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Image getImgenFondo() {
		return imgenFondo;
	}

	public void setImgenFondo(Image imgenFondo) {
		Mundo.imgenFondo = imgenFondo;
	}

}
