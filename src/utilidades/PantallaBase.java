package utilidades;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import base.Jugador;
import base.Panel;
import pantallas.Pantalla;

/**
 * 
 * @author Ismael Martin
 *
 */
public class PantallaBase implements Pantalla {

	// Panel sobre el que actuar
	Panel panelPrincipal;
	// Fondo
	private File fondo;
	private Image imagenFondo = null;
	private final String RUTA_IMG_FONDO = "imagenes" + File.separator + "\\medioAmbiente\\fondo03.png";

	// *******************************

	/*
	 * Constructor
	 */
	public PantallaBase(Panel panelJuego) {
		this.panelPrincipal = panelJuego;
		inicializarPantalla();
		redimensionarPantalla();
		
	}

	@Override
	public void inicializarPantalla() {
		cargarFondo();

	}

	@Override
	public void pintarPantalla(Graphics g) {
		actualizarFondo(g);
		// Escribir en grafico
		g.setFont(panelPrincipal.getFuente());
		g.drawString("ISKEMON", panelPrincipal.getWidth() - (panelPrincipal.getWidth() / 2) - 100,
				panelPrincipal.getHeight() - (panelPrincipal.getHeight() - 150));
		

	}

	public void pintarSprite(Graphics g) {
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
