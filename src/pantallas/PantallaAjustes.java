package pantallas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import base.Frame;
import base.Jugador;
import base.Panel;
import base.Sprite;
import pantallas.Pantalla;

/**
 * 
 * @author Ismael Martin
 *
 */
public class PantallaAjustes implements Pantalla {

	// Panel sobre el que actuar
	Panel panelPrincipal;
	Sprite atras;
	private final String RUTA_IMG_ATRAS = "imagenes" + File.separator + "ajustes" + File.separator + "atras.png";
	Sprite pantallaCompleta;
	Boolean pantallaGrande = false;
	private final String RUTA_IMG_OPCIONPANTALLA = "imagenes" + File.separator + "ajustes" + File.separator
			+ "fondoOpcionPantalla.png";

	// Fondo
	private File fondo;
	private Image imagenFondo = null;
	private final String RUTA_IMG_FONDO = "imagenes" + File.separator + "\\medioAmbiente\\fondo02.png";

	// *******************************

	/*
	 * Constructor
	 */
	public PantallaAjustes(Panel panelJuego) {
		this.panelPrincipal = panelJuego;

		inicializarPantalla();
		redimensionarPantalla();
		atras = new Sprite(44, 44, 0 + 78, 0 + 50, 0, 0, Color.green, RUTA_IMG_ATRAS);
		pantallaCompleta = new Sprite(400, 75, (panelPrincipal.getWidth() / 2) - (200), 0 + 200, 0, 0, Color.green,
				RUTA_IMG_OPCIONPANTALLA);

	}

	@Override
	public void inicializarPantalla() {
		cargarFondo();

	}

	@Override
	public void pintarPantalla(Graphics g) {
		actualizarFondo(g);
		pintarAtras(g);
		pintarOpcionPantallaCompleta(g);
		// Escribir en grafico
		g.setFont(panelPrincipal.getFuente());
		g.drawString("AJUSTES", panelPrincipal.getWidth() - (panelPrincipal.getWidth() / 2) - 150,
				panelPrincipal.getHeight() - (panelPrincipal.getHeight() - 120));
		g.setFont(Panel.fuenteComentarios);
		
		g.drawString("Pantalla completa    " + pantallaGrande.toString(), (panelPrincipal.getWidth() / 2) - (100), 0 + 240);
		

	}

	public void pintarAtras(Graphics g) {
		atras.pintarSprite(g);

	}

	public void pintarOpcionPantallaCompleta(Graphics g) {
		pantallaCompleta.pintarSprite(g);

	}

	@Override
	public void ejecutarFrame() {
		try {

			Thread.sleep(25);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void pintarJugador(Graphics g) {
		Jugador.getPlayer().pintarSprite(g);
	}

	@Override
	public void moverRaton(MouseEvent e) {
	}

	@Override
	public void pulsarRaton(MouseEvent e) {

		if (atras.colisionaCon(new Sprite(1, 1, e.getX(), e.getY(), Color.BLACK))) {
			panelPrincipal.setPantalla(new PantallaBienvenida(panelPrincipal));
		}
		if (pantallaCompleta.colisionaCon(new Sprite(1, 1, e.getX(), e.getY(), Color.BLACK))) {

			if (pantallaGrande) {
				Frame.frame.dispose();
				Frame.frame.setUndecorated(false);
				Frame.frame.setVisible(true);
				pantallaGrande=false;
			} else {
				Frame.frame.dispose();
				Frame.frame.setUndecorated(true);
				Frame.frame.setVisible(true);
				pantallaGrande=true;
			}
		}
	}

	@Override
	public void redimensionarPantalla() {
		imagenFondo = imagenFondo.getScaledInstance(panelPrincipal.getWidth(), panelPrincipal.getHeight(),
				BufferedImage.SCALE_SMOOTH);
	}

	@Override
	public void mantenerPulsadoRaton(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Obtiene un aleatorio
	 * 
	 * @param minimo
	 * @param maximo
	 * @return aleatorio entre minimo y maximo
	 */
	public int aleatorio(int minimo, int cantidad) {
		Random r = new Random();
		int aleatorio = r.nextInt(cantidad) + minimo;
		return aleatorio;
	}

	public void cargarFondo() {
		fondo = new File(RUTA_IMG_FONDO);
		try {
			imagenFondo = ImageIO.read(fondo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actualizarFondo(Graphics g) {
		g.drawImage(imagenFondo, 0, 0, null);

	}

	@Override
	public void pulsarDerecha() {
		Jugador.getPlayer().moverSpriteDerecha();
		Jugador.playerAnimacionDerecha();
		Jugador.getPlayer().cargarSprite();

	}

	@Override
	public void pulsarIzquierda() {
		Jugador.getPlayer().moverSpriteIzquierda();
		Jugador.playerAnimacionIzquierda();
		Jugador.getPlayer().cargarSprite();

	}

	@Override
	public void pulsarArriba() {
		Jugador.getPlayer().moverSpriteArrriba();
		Jugador.playerAnimacionArriba();
		Jugador.getPlayer().cargarSprite();

	}

	@Override
	public void pulsarAbajo() {
		Jugador.getPlayer().moverSpriteAbajo();
		Jugador.playerAnimacionAbajo();
		Jugador.getPlayer().cargarSprite();

	}

	@Override
	public void pulsarArribaIzquierda() {
		Jugador.getPlayer().moverSpriteArribaIzquierda();
		Jugador.playerAnimacionIzquierda();
		Jugador.getPlayer().cargarSprite();

	}

	@Override
	public void pulsarArribaDerecha() {
		Jugador.getPlayer().moverSpriteArribaDerecha();
		Jugador.playerAnimacionDerecha();
		Jugador.getPlayer().cargarSprite();

	}

	@Override
	public void pulsarAbajoIzquierda() {
		Jugador.getPlayer().moverSpriteAbajoIzquierda();
		Jugador.playerAnimacionIzquierda();
		Jugador.getPlayer().cargarSprite();

	}

	@Override
	public void pulsarAbajoDerecha() {
		Jugador.getPlayer().moverSpriteAbajoDerecha();
		Jugador.playerAnimacionDerecha();
		Jugador.getPlayer().cargarSprite();

	}

	@Override
	public void pulsarIntro() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pulsarEspacio() {
		// TODO Auto-generated method stub

	}

	@Override
	public void ocultarRaton() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mostarRaton() {
		// TODO Auto-generated method stub

	}

}
