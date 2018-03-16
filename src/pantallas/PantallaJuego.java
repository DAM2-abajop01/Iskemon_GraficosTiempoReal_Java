package pantallas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import base.Armas;
import base.Mundo;
import base.Jugador;
import base.Panel;
import base.Sonidos;
import base.Sprite;
import base.Toolbar;

/**
 * @author Ismael Martín Ramírez
 */

public class PantallaJuego implements Pantalla {
	// *******************************
	// Panel sobre el que actuar
	private Panel panelPrincipal;

	// *******************************
	// Hilos del juego. Con Jugador y disparos comprobamos las colisiones, con el
	// mundo la generacion
	private Jugador jugador;
	private Mundo mundo;
	private Armas disparos;
	private final String RUTA_SONIDO_ARMAESPACIO = "sonidos//disparoespacio.mp3";
	private Sonidos sonArmaEspacio;
	private final String RUTA_SONIDO_ARMACLICK = "sonidos//disparoclick.mp3";
	
	private Sonidos sonArmaClick;
	// ***************************************************
	// Toolbar
	private float tiempoIncial;
	private float tiempoActual;
	private float tiempoJuego;

	// *******************************

	/*
	 * Constructor para el primer inicio
	 */
	public PantallaJuego(Panel panelPrincipal) {
		this.panelPrincipal = panelPrincipal;
		jugador = new Jugador(panelPrincipal);

		// Iniciar Sprites
		Mundo.medioAmbiente = new ArrayList<>();
		Mundo.medioAmbienteNocivo = new ArrayList<>();
		Jugador.disparos = new ArrayList<>();
		Jugador.armas = new ArrayList<>();
		Jugador.armasObtenidas = new ArrayList<>();
		Mundo.enemigos = new ArrayList<>();

		inicializarPantalla();
		redimensionarPantalla();
	}
	/*
	 * Constructor para la carga de pantallas estando en juego
	 */
	public PantallaJuego(Panel panelPrincipal,int posX,int posY) {
		this.panelPrincipal = panelPrincipal;
		

		mundo = new Mundo(panelPrincipal);

		// Iniciar Sprites
		/**/
		Mundo.medioAmbiente = new ArrayList<>();
		Mundo.medioAmbienteNocivo = new ArrayList<>();
		Jugador.disparos = new ArrayList<>();
		Jugador.armas = new ArrayList<>();
		Jugador.armasObtenidas = new ArrayList<>();
		Mundo.enemigos = new ArrayList<>();

		inicializarPantalla();
		Jugador.getPlayer().setPosX(posX);
		Jugador.getPlayer().setPosY(posY);
		redimensionarPantalla();
	}
	// *******************************


	@Override
	public void inicializarPantalla() {
		sonArmaEspacio = new Sonidos(RUTA_SONIDO_ARMAESPACIO);
		sonArmaClick = new Sonidos(RUTA_SONIDO_ARMACLICK);


		Mundo.cargarFondo();
		jugador = new Jugador(panelPrincipal);
		disparos = new Armas();
		mundo = new Mundo(panelPrincipal);
		new Thread(mundo).start();
		// Iniciar Cronometro
		tiempoIncial = System.nanoTime();
	}

	@Override
	public void pintarPantalla(Graphics g) {
		actualizarFondo(g);
		actualizarCronometro();
		pintarTolbar(g);
		pintarMedioAmbiente(g);
		pintarMedioAmbienteNocivo(g);
		pintarArmas(g);

		if (!Mundo.enemigos.isEmpty()) {
			pintarEnemigo(g);
		}

		if (!Jugador.disparos.isEmpty()) {
			pintarDisparos(g);
			new Thread(disparos).start();
		}
		pintarJugador(g);
		// Comprueba las colisiones del jugador
		new Thread(jugador).start();

		if (jugador.getVida() < 0) {
			panelPrincipal.setPantalla(new PantallaFinal(panelPrincipal));

		}

	}

	@Override
	public void ejecutarFrame() {
		try {
			Thread.sleep(25);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

			Mundo.animacionAgua();

	}

	public void pintarJugador(Graphics g) {
		Jugador.getPlayer().pintarSprite(g);
	}

	public void pintarEnemigo(Graphics g) {
		if (!Mundo.enemigos.isEmpty()) {
			for (int i = 0; i < Mundo.enemigos.size(); i++) {
				if (Mundo.enemigos.get(i).getRuta().contains("01")) {
					Mundo.enemigos.get(i).moverSpriteConColisionEnBordePantalla(panelPrincipal.getWidth(),
							panelPrincipal.getHeight());
				}
				if (Mundo.enemigos.get(i).getRuta().contains("02")) {
					Mundo.enemigos.get(i).caminarConProbabilidad(panelPrincipal.getWidth(), panelPrincipal.getHeight());
				}
				if (Mundo.enemigos.get(i).getRuta().contains("03")) {
					Mundo.enemigos.get(i).caminarSiguiendoPosicion(jugador.getPosXAnterior(), jugador.getPosYAnterior(),
							panelPrincipal.getWidth(), panelPrincipal.getHeight());
				}
				if (Mundo.enemigos.get(i).getRuta().contains("04")) {
					Mundo.enemigos.get(i).caminarConRelacion(panelPrincipal.getWidth(), panelPrincipal.getHeight());
				}
				Mundo.enemigos.get(i).pintarSprite(g);
			}
		}
	}
	public void pintarMedioAmbiente(Graphics g) {
		for (int i = 0; i < Mundo.medioAmbiente.size(); i++) {
			Mundo.medioAmbiente.get(i).pintarSprite(g);
		}
	}
	public void pintarMedioAmbienteNocivo(Graphics g) {
		for (int i = 0; i < Mundo.medioAmbienteNocivo.size(); i++) {
			Mundo.medioAmbienteNocivo.get(i).pintarSprite(g);
		}
	}
	public void pintarArmas(Graphics g) {
		for (int i = 0; i < Jugador.armas.size(); i++) {
			Jugador.armas.get(i).pintarSprite(g);
		}
	}
	public void pintarDisparos(Graphics g) {
		for (int i = 0; i < Jugador.disparos.size(); i++) {
			Jugador.disparos.get(i).pintarSprite(g);
			Jugador.disparos.get(i).moverSprite();
			if (Jugador.disparos.get(i).colisionConBordePantalla(panelPrincipal.getWidth(),
					panelPrincipal.getHeight())) {
				Jugador.disparos.remove(i);
			}
		}
	}
	@Override
	public void actualizarFondo(Graphics g) {
		g.drawImage(Mundo.imgenFondo, 0, 0, null);
	}
	/**
	 * Cronometro
	 */
	public void pintarTolbar(Graphics g) {
		Toolbar.gestionTolbar(g, Jugador.armas, Jugador.armasObtenidas, tiempoJuego);
	}

	public void actualizarCronometro() {
		tiempoActual = System.nanoTime();
		tiempoJuego = tiempoActual - tiempoIncial;
	}

	@Override
	public void redimensionarPantalla() {
		Mundo.imgenFondo = Mundo.imgenFondo.getScaledInstance(panelPrincipal.getWidth(), panelPrincipal.getHeight(),
				BufferedImage.SCALE_SMOOTH);
	}

	@Override
	public void ocultarRaton() {
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
		this.panelPrincipal.getRootPane().setCursor(cursor);
	}

	@Override
	public void mostarRaton() {
		this.panelPrincipal.getRootPane().setCursor(null);
	}

	@Override
	public void moverRaton(MouseEvent e) {
	}

	@Override
	public void pulsarRaton(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {

			int xMouse = e.getX();
			int yMouse = e.getY();
			int x = Jugador.getPlayer().getPosX();
			int y = Jugador.getPlayer().getPosY();
			int vX = 0, vY = 0;

			if ((xMouse < x) && (yMouse < y)) {
				vX = -10;
				vY = -10;
			}
			if ((xMouse >= x) && (yMouse >= y)) {
				vX = 10;
				vY = 10;
			}

			if ((xMouse < x) && (yMouse >= y)) {
				vX = -10;
				vY = +10;
			}
			if ((xMouse >= x) && (yMouse < y)) {
				vX = +10;
				vY = -10;
			}

			if (Jugador.armasObtenidas.contains(Jugador.armas.get(1))) {
				Jugador.disparos.add(new Sprite(12, 12, ((Jugador.armas.get(1).getPosX() - 4)),
						((Jugador.armas.get(1).getPosY() - 4)), vX, vY, Color.WHITE,
						Armas.RUTA_IMG_DISPARO_BASTON_CLICK));
				new Thread(sonArmaClick).start();

			}
		}
	}

	@Override
	public void mantenerPulsadoRaton(MouseEvent e) {
	}

	@Override
	public void pulsarIntro() {
	}

	@Override
	public void pulsarEspacio() {
		if (Jugador.armasObtenidas.contains(Jugador.armas.get(0))) {
			Jugador.disparos.add(
					new Sprite(12, 12, ((Jugador.armas.get(0).getPosX() + 4) + Jugador.armas.get(0).getAncho() / 2),
							((Jugador.armas.get(0).getPosY() - 10) + Jugador.armas.get(0).getAlto() / 2),
							Armas.VELOCIDAD_X_DISPARO, Armas.VELOCIDAD_Y_DISPARO, Color.WHITE,
							Armas.RUTA_IMG_DISPARO_BASTON_ESPACIO));
			new Thread(sonArmaEspacio).start();

		}
	}

	@Override
	public void pulsarDerecha() {
		Jugador.getPlayer().moverSpriteDerecha();
		Jugador.playerAnimacionDerecha();
		Jugador.getPlayer().cargarSprite();

		Armas.VELOCIDAD_X_DISPARO = Armas.VELOCIDAD_DISPARO;
		Armas.VELOCIDAD_Y_DISPARO = 0;
	}

	@Override
	public void pulsarIzquierda() {
		Jugador.getPlayer().moverSpriteIzquierda();
		Jugador.playerAnimacionIzquierda();
		Jugador.getPlayer().cargarSprite();
		Armas.VELOCIDAD_X_DISPARO = (-Math.abs(Armas.VELOCIDAD_DISPARO));
		Armas.VELOCIDAD_Y_DISPARO = 0;
	}

	@Override
	public void pulsarArriba() {
		Jugador.getPlayer().moverSpriteArrriba();
		Jugador.playerAnimacionArriba();
		Jugador.getPlayer().cargarSprite();
		Armas.VELOCIDAD_X_DISPARO = 0;
		Armas.VELOCIDAD_Y_DISPARO = (-Math.abs(Armas.VELOCIDAD_DISPARO));
	}

	@Override
	public void pulsarAbajo() {
		Jugador.getPlayer().moverSpriteAbajo();
		Jugador.playerAnimacionAbajo();
		Jugador.getPlayer().cargarSprite();
		Armas.VELOCIDAD_X_DISPARO = 0;
		Armas.VELOCIDAD_Y_DISPARO = Armas.VELOCIDAD_DISPARO;
	}

	@Override
	public void pulsarArribaIzquierda() {
		Jugador.getPlayer().moverSpriteArribaIzquierda();
		Jugador.playerAnimacionIzquierda();
		Jugador.getPlayer().cargarSprite();
		Armas.VELOCIDAD_X_DISPARO = (-Math.abs(Armas.VELOCIDAD_DISPARO));
		Armas.VELOCIDAD_Y_DISPARO = (-Math.abs(Armas.VELOCIDAD_DISPARO));
	}

	@Override
	public void pulsarArribaDerecha() {
		Jugador.getPlayer().moverSpriteArribaDerecha();
		Jugador.playerAnimacionDerecha();
		Jugador.getPlayer().cargarSprite();
		Armas.VELOCIDAD_X_DISPARO = (Math.abs(Armas.VELOCIDAD_DISPARO));
		Armas.VELOCIDAD_Y_DISPARO = (-Math.abs(Armas.VELOCIDAD_DISPARO));
	}

	@Override
	public void pulsarAbajoIzquierda() {
		Jugador.getPlayer().moverSpriteAbajoIzquierda();
		Jugador.playerAnimacionIzquierda();
		Jugador.getPlayer().cargarSprite();
		Armas.VELOCIDAD_X_DISPARO = (-Math.abs(Armas.VELOCIDAD_DISPARO));
		Armas.VELOCIDAD_Y_DISPARO = (Math.abs(Armas.VELOCIDAD_DISPARO));
	}

	@Override
	public void pulsarAbajoDerecha() {
		Jugador.getPlayer().moverSpriteAbajoDerecha();
		Jugador.playerAnimacionDerecha();
		Jugador.getPlayer().cargarSprite();
		Armas.VELOCIDAD_X_DISPARO = (Math.abs(Armas.VELOCIDAD_DISPARO));
		Armas.VELOCIDAD_Y_DISPARO = (Math.abs(Armas.VELOCIDAD_DISPARO));
	}

}