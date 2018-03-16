package base;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import pantallas.PantallaJuego;


public class Jugador implements Runnable {
	private Panel panelPrincipal;
	public static Sprite player;
	public static int vida = 5;
	public static int gatos = 0;
	private final int VELOCIDAD_PLAYER = 8;
	private int posicionXInicialJugador;
	private int posicionYInicialJugador;
	private int posXAnterior, posYAnterior;
	public static ArrayList<Sprite> disparos;
	public static ArrayList<Sprite> armas;
	public static ArrayList<Sprite> armasObtenidas;
	// ---------------------
	private static final String RUTA_IMG_CORAZON = "imagenes\\player\\corazon.png";
	// ------------ ---------
	public final static String RUTA_IMG_PLAYER_DERECHA = "imagenes\\player\\playerAndaDerecha.png";
	public final static String RUTA_IMG_PLAYER_DERECHA1 = "imagenes\\player\\playerAndaDerecha01.png";
	public final static String RUTA_IMG_PLAYER_DERECHA2 = "imagenes\\player\\playerAndaDerecha02.png";
	// ---------------------
	public final static String RUTA_IMG_PLAYER_IZQUIERDA = "imagenes\\player\\playerAndaIzquierda.png";
	public final static String RUTA_IMG_PLAYER_IZQUIERDA1 = "imagenes\\player\\playerAndaIzquierda01.png";
	public final static String RUTA_IMG_PLAYER_IZQUIERDA2 = "imagenes\\player\\playerAndaIzquierda02.png";
	// ---------------------
	public final static String RUTA_IMG_PLAYER_ARRIBA = "imagenes\\player\\playerAndaArriba.png";
	public final static String RUTA_IMG_PLAYER_ARRIBA1 = "imagenes\\player\\playerAndaArriba01.png";
	public final static String RUTA_IMG_PLAYER_ARRIBA2 = "imagenes\\player\\playerAndaArriba02.png";
	// ---------------------
	public final static String RUTA_IMG_PLAYER_ABAJO = "imagenes\\player\\playerAndaAbajo.png";
	public final static String RUTA_IMG_PLAYER_ABAJO1 = "imagenes\\player\\playerAndaAbajo01.png";
	public final static String RUTA_IMG_PLAYER_ABAJO2 = "imagenes\\player\\playerAndaAbajo02.png";
	private Sonidos sonQuitaVida;
	private final String RUTA_SONIDO_QUITAVIDA = "sonidos//quitavida.mp3";

	public Jugador() {

	}

	public Jugador(Panel panelPrincipal) {
		this.panelPrincipal = panelPrincipal;
		posicionXInicialJugador = panelPrincipal.getWidth() / 2;
		posicionYInicialJugador = panelPrincipal.getHeight() / 2;
		player = new Sprite(44, 44, posicionXInicialJugador, posicionYInicialJugador, VELOCIDAD_PLAYER,
				VELOCIDAD_PLAYER, Color.green, RUTA_IMG_PLAYER_ABAJO);
	
		sonQuitaVida = new Sonidos(RUTA_SONIDO_QUITAVIDA);

	}

	@Override
	public void run() {
		colisionJugadorConArmas();
		colisionJugadorConBordes();
		colisonJugadorConMedioAmbiente();
		colisionJugadorConEnemigos();
	}

	public void colisionJugadorConArmas() {
		if (!armas.isEmpty()) {
			if (armas.get(0).colisionaCon(player)) {
				armas.get(0).setPosX(player.getPosX() + player.getAncho() - 14);
				armas.get(0).setPosY(player.getPosY() - player.getAlto() / 15);
				armasObtenidas.add(armas.get(0));
			}
			if (armas.get(1).colisionaCon(player)) {
				armas.get(1).setPosX(player.getPosX() - player.getAncho() / 2);
				armas.get(1).setPosY(player.getPosY() - player.getAlto() / 2);
				armasObtenidas.add(armas.get(1));
			}
		}

	}

	public void colisionJugadorConEnemigos() {

		if (!Mundo.enemigos.isEmpty()) {
			for (int i = 0; i < Mundo.enemigos.size(); i++) {
				if (Mundo.enemigos.get(i).colisionaCon(player)) {
					vida--;
					Mundo.enemigos.get(i).setVelocidadX(Mundo.enemigos.get(i).getVelocidadX() * (-1));
					Mundo.enemigos.get(i).setVelocidadY(Mundo.enemigos.get(i).getVelocidadY() * (-1));
					new Thread(sonQuitaVida).start();

				}
			}
		}

	}

	/**
	 * Detectar si el jugador colisiona con rocas, arboles o cualquier objeto del
	 * entorno
	 */
	public void colisonJugadorConMedioAmbiente() {
		// Colision del jugador con roca
		for (int i = 0; i < Mundo.medioAmbiente.size(); i++) {
			if (player.colisionaCon(Mundo.medioAmbiente.get(i))) {
				player.setPosX(posXAnterior);
				player.setPosY(posYAnterior);
				
				if(Mundo.medioAmbiente.get(i).getRuta().contains("gato")) {
				Mundo.medioAmbiente.remove(i);
				Jugador.gatos++;
			}
			}
			
			
		}
		for (int i = 0; i < Mundo.medioAmbienteNocivo.size(); i++) {
			if (player.colisionaCon(Mundo.medioAmbienteNocivo.get(i))) {
				player.setPosX(posXAnterior);
				player.setPosY(posYAnterior);
				vida--;
				new Thread(sonQuitaVida).start();

			}
		}
		posXAnterior = player.getPosX();
		posYAnterior = player.getPosY();
	}

	public static void gestionVida(Graphics g) {
		switch (vida) {
		case 5: {
			for (int i = 0; i < 5; i++) {
				Sprite bastonClickObtenido = new Sprite(16, 16, 74, 28 + (i * 20), 0, 0, Color.RED, RUTA_IMG_CORAZON);
				bastonClickObtenido.pintarSprite(g);
			}
			break;
		}
		case 4: {
			for (int i = 0; i < 4; i++) {
				Sprite bastonClickObtenido = new Sprite(16, 16, 74, 28 + (i * 20), 0, 0, Color.RED, RUTA_IMG_CORAZON);
				bastonClickObtenido.pintarSprite(g);
			}
			break;
		}
		case 3: {
			for (int i = 0; i < 3; i++) {
				Sprite bastonClickObtenido = new Sprite(16, 16, 74, 28 + (i * 20), 0, 0, Color.RED, RUTA_IMG_CORAZON);
				bastonClickObtenido.pintarSprite(g);
			}
			break;
		}
		case 2: {
			for (int i = 0; i < 2; i++) {
				Sprite bastonClickObtenido = new Sprite(16, 16, 74, 28 + (i * 20), 0, 0, Color.RED, RUTA_IMG_CORAZON);
				bastonClickObtenido.pintarSprite(g);
			}
			break;
		}
		case 1: {
			for (int i = 0; i < 1; i++) {
				Sprite bastonClickObtenido = new Sprite(16, 16, 74, 28 + (i * 20), 0, 0, Color.RED, RUTA_IMG_CORAZON);
				bastonClickObtenido.pintarSprite(g);
			}
			break;
		}
		case 0: {

			break;
		}
		default:
			break;
		}
	}

	/**
	 * Comprueba si el jugador colisiona con los bordes
	 */
	public void colisionJugadorConBordes() {
		// Colision del jugador por bordes
		switch (player.colisionConQueBordePantalla(panelPrincipal.getWidth(), panelPrincipal.getHeight())) {
		case "arriba": {
			panelPrincipal.setPantalla(new PantallaJuego(panelPrincipal,player.getPosX(),panelPrincipal.getHeight()-(player.getAlto()+3)));
			break;
		}
		case "abajo": {
			panelPrincipal.setPantalla(new PantallaJuego(panelPrincipal,player.getPosX(),player.getAlto()+3));

			break;
		}
		case "izquierda": {
			panelPrincipal.setPantalla(new PantallaJuego(panelPrincipal,panelPrincipal.getWidth()-(player.getAncho()+3),player.getPosY()));
			break;
		}
		case "derecha": {
			panelPrincipal.setPantalla(new PantallaJuego(panelPrincipal,(player.getAncho()+3),player.getPosY()));

			break;
		}
		default:
			break;
		}
	}

	public static void playerAnimacionDerecha() {
		// Cambio de ruta del sprite del jugador al andar
		switch (player.getRuta()) {
		case RUTA_IMG_PLAYER_DERECHA: {
			player.setRuta(RUTA_IMG_PLAYER_DERECHA1);
			break;
		}
		case RUTA_IMG_PLAYER_DERECHA1: {
			player.setRuta(RUTA_IMG_PLAYER_DERECHA2);
			break;
		}
		case RUTA_IMG_PLAYER_DERECHA2: {
			player.setRuta(RUTA_IMG_PLAYER_DERECHA);
			break;
		}
		default:
			player.setRuta(RUTA_IMG_PLAYER_DERECHA);
			break;
		}
	}

	public static void playerAnimacionIzquierda() {

		switch (player.getRuta()) {
		case RUTA_IMG_PLAYER_IZQUIERDA: {
			player.setRuta(RUTA_IMG_PLAYER_IZQUIERDA1);
			break;
		}
		case RUTA_IMG_PLAYER_IZQUIERDA1: {
			player.setRuta(RUTA_IMG_PLAYER_IZQUIERDA2);
			break;
		}
		case RUTA_IMG_PLAYER_IZQUIERDA2: {
			player.setRuta(RUTA_IMG_PLAYER_IZQUIERDA);
			break;
		}
		default:
			player.setRuta(RUTA_IMG_PLAYER_IZQUIERDA);
			break;
		}
	}

	public static void playerAnimacionAbajo() {
		switch (player.getRuta()) {
		case RUTA_IMG_PLAYER_ABAJO: {
			player.setRuta(RUTA_IMG_PLAYER_ABAJO1);
			break;
		}
		case RUTA_IMG_PLAYER_ABAJO1: {
			player.setRuta(RUTA_IMG_PLAYER_ABAJO2);
			break;
		}
		case RUTA_IMG_PLAYER_ABAJO2: {
			player.setRuta(RUTA_IMG_PLAYER_ABAJO);
			break;
		}
		default:
			player.setRuta(RUTA_IMG_PLAYER_ABAJO);
			break;
		}
	}

	public static void playerAnimacionArriba() {
		switch (player.getRuta()) {
		case RUTA_IMG_PLAYER_ARRIBA: {
			player.setRuta(RUTA_IMG_PLAYER_ARRIBA1);
			break;
		}
		case RUTA_IMG_PLAYER_ARRIBA1: {
			player.setRuta(RUTA_IMG_PLAYER_ARRIBA2);
			break;
		}
		case RUTA_IMG_PLAYER_ARRIBA2: {
			player.setRuta(RUTA_IMG_PLAYER_ARRIBA);
			break;
		}
		default:
			player.setRuta(RUTA_IMG_PLAYER_ARRIBA);
			break;
		}
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		Jugador.vida = vida;
	}

	public int getPosXAnterior() {
		return posXAnterior;
	}

	public void setPosXAnterior(int posXAnterior) {
		this.posXAnterior = posXAnterior;
	}

	public int getPosYAnterior() {
		return posYAnterior;
	}

	public void setPosYAnterior(int posYAnterior) {
		this.posYAnterior = posYAnterior;
	}

	public Panel getPanelPrincipal() {
		return panelPrincipal;
	}

	public void setPanelPrincipal(Panel panelPrincipal) {
		this.panelPrincipal = panelPrincipal;
	}

	public static Sprite getPlayer() {
		return player;
	}

	public static void setPlayer(Sprite player) {
		Jugador.player = player;
	}

	public ArrayList<Sprite> getDisparos() {
		return disparos;
	}

	public ArrayList<Sprite> getArmas() {
		return armas;
	}

	public ArrayList<Sprite> getArmasObtenidas() {
		return armasObtenidas;
	}

}
