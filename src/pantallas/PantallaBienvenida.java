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

import base.Jugador;
import base.Panel;
import base.Sonidos;
import base.Sprite;

/**
 * 
 * @author Ismael Martin
 *
 */
public class PantallaBienvenida implements Pantalla {
	// Panel sobre el que actuar
	Panel panelPrincipal;
	private Color colorInicio;
	private Sprite aristocrata;
	private Sprite ajustes;
	// Fondo
	private File fondo;
	private Image imagenFondo = null;
	private final String RUTA_IMG_FONDO = "imagenes" + File.separator + "medioAmbiente\\fondoPortada.png";
	private final String RUTA_IMG_ARISTOCRATA = "imagenes" + File.separator + "aristocrata.png";
	private final String RUTA_IMG_AJUSTES = "imagenes" + File.separator + "ajustes" + File.separator + "ajustes.png";
	private final String RUTA_SONIDO_FONDO = "sonidos//fondo.mp3";
	private Sonidos sonFondo;
	private final String RUTA_SONIDO_INICIO = "sonidos//fondoinicio.mp3";
	private Sonidos sonInicio;
	// String texto aristocrata
	private String texto01 = "", texto02 = "", texto03 = "", texto04 = "";
	private int contador;

	// *******************************

	/*
	 * Constructor
	 */
	public PantallaBienvenida(Panel panelJuego) {
		
		colorInicio = Color.CYAN;
		this.panelPrincipal = panelJuego;
		panelPrincipal.setBounds(0, 0, 1400, 800);
		inicializarPantalla();
		redimensionarPantalla();
		aristocrata = new Sprite(64, 64, panelPrincipal.getWidth() - 200, panelPrincipal.getHeight() - 300, 0, 0,
				Color.green, RUTA_IMG_ARISTOCRATA);
		ajustes = new Sprite(44, 44, 0+ 78,0+ 50, 0, 0,Color.green, RUTA_IMG_AJUSTES);
		new Jugador(panelJuego);
		Jugador.getPlayer().setPosY(panelPrincipal.getHeight() - 180);
	}

	@Override
	public void inicializarPantalla() {
		cargarFondo();
		sonInicio = new Sonidos(RUTA_SONIDO_INICIO);
		new Thread(sonInicio).start();

	}

	@Override
	public void pintarPantalla(Graphics g) {
		actualizarFondo(g);
		// Escribir en grafico
		g.setColor(Color.WHITE);
		g.setFont(panelPrincipal.getFuente());
		g.drawString("ISKEMON", panelPrincipal.getWidth() - (panelPrincipal.getWidth() / 2) - 125,
				panelPrincipal.getHeight() - (panelPrincipal.getHeight() - 120));
		g.setColor(colorInicio);
g.drawLine(panelPrincipal.getWidth() - (panelPrincipal.getWidth() / 2) - 125, panelPrincipal.getHeight() - (panelPrincipal.getHeight() - 130), panelPrincipal.getWidth() - (panelPrincipal.getWidth() / 2) - (-150), panelPrincipal.getHeight() - (panelPrincipal.getHeight() - 130));
		// Bocadillo
		g.setColor(Color.WHITE);
		g.fillOval(panelPrincipal.getWidth() - 370, panelPrincipal.getHeight() - 420, 126, 126);
		g.fillOval(panelPrincipal.getWidth() - 270, panelPrincipal.getHeight() - 300, 16, 16);

		g.setColor(Color.BLACK);
		g.setFont(panelPrincipal.getFuente());
		g.setFont(Panel.fuenteComentarios);
		g.drawString(texto01, panelPrincipal.getWidth() - 350, panelPrincipal.getHeight() - 380);
		g.drawString(texto02, panelPrincipal.getWidth() - 350, panelPrincipal.getHeight() - 360);
		g.drawString(texto03, panelPrincipal.getWidth() - 350, panelPrincipal.getHeight() - 340);
		g.drawString(texto04, panelPrincipal.getWidth() - 350, panelPrincipal.getHeight() - 320);

		aristocrata.setPosX(panelPrincipal.getWidth() - 250);
		aristocrata.setPosY(panelPrincipal.getHeight() - 300);
		pintarAristocrata(g);
		pintarJugador(g);
		pintarAjustes(g);

	}
	public void pintarAjustes(Graphics g) {
		ajustes.pintarSprite(g);
	}
	public void pintarAristocrata(Graphics g) {
		aristocrata.pintarSprite(g);
	}

	@Override
	public void ejecutarFrame() {
		try {

			// color de Inicio
			colorInicio = new Color(aleatorio(0, 255), aleatorio(0, 255), aleatorio(0, 255));
			Thread.sleep(25);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		animacionTexto();

	}

	public void animacionTexto() {

		switch (contador) {
		case 50: {
			texto01 = " He perdido ";
			break;
		}
		case 90: {
			texto02 = " mis gatos ";
			break;
		}
		case 150: {
			texto03 = "...¿me los ";
			break;
		}
		case 190: {
			texto04 = "encuentras?";
			break;
		}
		default:
			break;
		}
		if (contador > 300) {
			texto01 = "";
			texto02 = "";
			texto03 = "";
			texto04 = "";
			contador = 0;
		} else {
			contador++;
		}

		/*
		 * if(texto01.equalsIgnoreCase("")) { texto01 =" He perdido "; }else {
		 * texto01=""; }
		 */

	}

	public void pintarJugador(Graphics g) {
		Jugador.getPlayer().pintarSprite(g);
	}

	@Override
	public void moverRaton(MouseEvent e) {
	}

	@Override
	public void pulsarRaton(MouseEvent e) {
		//Colision del aristocrata con el click del raton
		if (aristocrata.colisionaCon(new Sprite(1, 1, e.getX(), e.getY(), Color.BLACK))) {
			panelPrincipal.setPantalla(new PantallaJuego(panelPrincipal));
			sonFondo = new Sonidos(RUTA_SONIDO_FONDO);
			new Thread(sonFondo).start();
		}
		if (ajustes.colisionaCon(new Sprite(1, 1, e.getX(), e.getY(), Color.BLACK))) {
			panelPrincipal.setPantalla(new PantallaAjustes(panelPrincipal));
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
