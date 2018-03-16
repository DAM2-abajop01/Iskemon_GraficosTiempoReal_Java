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
import javax.swing.JOptionPane;

import base.Jugador;
import base.Panel;
import base.Sonidos;
import base.Sprite;

/**
 * 
 * @author Ismael Martin
 *
 */
public class PantallaFinal implements Pantalla {

	// Panel sobre el que actuar
	Panel panelPrincipal;
	private Color colorInicio;
	private Sprite aristocrata;
	private Sprite salir;
	// Fondo
	private File fondo;
	private Image imagenFondo = null;
	private final String RUTA_IMG_FONDO = "imagenes" + File.separator + "medioAmbiente\\fondo04.png";
	private final String RUTA_IMG_ARISTOCRATA = "imagenes" + File.separator + "aristocrata.png";
	private final String RUTA_IMG_SALIR = "imagenes" + File.separator + "ajustes" + File.separator + "atras.png";
	private final String RUTA_SONIDO_INICIO = "sonidos//fondoinicio.mp3";
	private Sonidos sonInicio;
	// String texto aristocrata
	private String texto01 = "", texto02 = "", texto03 = "", texto04 = "", titulo = "";
	private int contador, contadorTitulo = 0, posicionCreditos = 0;

	// *******************************

	/*
	 * Constructor
	 */
	public PantallaFinal(Panel panelJuego) {
		colorInicio = new Color(aleatorio(0, 255), aleatorio(0, 255), aleatorio(0, 255));

		this.panelPrincipal = panelJuego;
		posicionCreditos = panelJuego.getHeight() + 40;
		panelPrincipal.setBounds(0, 0, 1400, 800);
		inicializarPantalla();
		redimensionarPantalla();
		aristocrata = new Sprite(64, 64, panelPrincipal.getWidth() - 200, panelPrincipal.getHeight() - 300, 0, 0,
				Color.green, RUTA_IMG_ARISTOCRATA);
		salir = new Sprite(44, 44, 0 + 78, 0 + 50, 0, 0, Color.green, RUTA_IMG_SALIR);
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
		creditos(g);

		// Escribir en grafico
		g.setColor(colorInicio);
		g.setFont(panelPrincipal.getFuente());
		g.drawString(titulo, (panelPrincipal.getWidth() / 2) - 400,
				panelPrincipal.getHeight() - (panelPrincipal.getHeight() - 120));

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

	public void creditos(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(Panel.fuenteComentarios);
		g.drawString("Realizado por:", panelPrincipal.getWidth() / 2 - 600, posicionCreditos);
		g.setColor(Color.BLACK);
		g.setFont(panelPrincipal.getFuente());
		g.drawString("Ismael Martín R.", panelPrincipal.getWidth() / 2 - 600, posicionCreditos + 50);
		g.setColor(Color.WHITE);
		g.setFont(Panel.fuenteComentarios);
		g.drawString("Musica:", panelPrincipal.getWidth() / 2 - 600, posicionCreditos + 100);
		g.setColor(Color.BLACK);
		g.setFont(panelPrincipal.getFuente());
		g.drawString("Mozart. Concierto para fagot Kv 191.III. Rondó", panelPrincipal.getWidth() / 2 - 600,
				posicionCreditos + 150);
		g.setColor(Color.WHITE);
		g.setFont(Panel.fuenteComentarios);
		g.drawString("Gráficos:", panelPrincipal.getWidth() / 2 - 600, posicionCreditos + 200);
		g.setColor(Color.BLACK);
		g.setFont(panelPrincipal.getFuente());
		g.drawString("Kenney Vleugels (www.Kenney.nl)", panelPrincipal.getWidth() / 2 - 600, posicionCreditos + 250);

	}

	public void pintarAjustes(Graphics g) {
		salir.pintarSprite(g);
	}

	public void pintarAristocrata(Graphics g) {
		aristocrata.pintarSprite(g);
	}

	@Override
	public void ejecutarFrame() {
		try {

			Thread.sleep(25);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		animacionTexto();
		animacionTitulo();
		animacionCreditos();

	}

	public void animacionCreditos() {
		posicionCreditos -= 2;
	}

	public void animacionTexto() {

		switch (contador) {
		case 50: {
			texto01 = "   ¡¡Vaya!! ";
			break;
		}
		case 90: {
			if (Jugador.gatos == 0) {
				texto02 = "¡¡¡" + Jugador.gatos + " gatos!!! ";
			} else {
				texto02 = "¡¡¡" + Jugador.gatos + " gato!!! ";
			}

			break;
		}
		case 150: {
			texto03 = " ...quieres ";

			break;
		}
		case 190: {
			texto04 = "buscar mas?";
			colorInicio = new Color(aleatorio(0, 255), aleatorio(0, 255), aleatorio(0, 255));

			break;
		}
		default:
			break;
		}
		if (contador > 400) {
			texto01 = "";
			texto02 = "";
			texto03 = "";
			texto04 = "";
			contador = 0;
		} else {
			contador++;
		}

	}

	public void animacionTitulo() {

		switch (contadorTitulo) {
		case 50: {
			titulo = "   Te ";
			break;
		}
		case 60: {
			titulo += " quedaste ";
			break;
		}
		case 70: {
			titulo += " sin ";
			break;
		}
		case 80: {
			titulo += "vidas";
			break;
		}
		default:
			break;
		}
		if (contadorTitulo > 200) {
			titulo = "";
			contadorTitulo = 0;
		} else {
			contadorTitulo++;
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
		// Colision del aristocrata con el click del raton
		if (aristocrata.colisionaCon(new Sprite(1, 1, e.getX(), e.getY(), Color.BLACK))) {
			Jugador.gatos = 0;
			Jugador.vida = 5;
			Jugador.armas = null;
			Jugador.armasObtenidas = null;
			Jugador.disparos = null;
			panelPrincipal.setPantalla(new PantallaJuego(panelPrincipal));
		}
		if (salir.colisionaCon(new Sprite(1, 1, e.getX(), e.getY(), Color.BLACK))) {
			int resp = JOptionPane.showConfirmDialog(null, "¿Deseas salir del juego?", "¡Alerta!",
					JOptionPane.YES_NO_OPTION);
			if (resp == 0) {
				System.exit(0);
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
