package base;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * @author Ismael Martín Ramírez
 */

public class Sprite {

	// Elementos
	private String ruta;
	private Image imagen, imgAux;
	private Graphics grafico;
	// Propiedades
	private Color color;
	private int ancho;
	private int alto;
	private int posX;
	private int posY;
	private int velocidadX;
	private int velocidadY;
	private int vida;

	// Sonido
	// Contructores
	public Sprite() {
	}

	// Constuctor clase
	public Sprite(int ancho, int alto, int posX, int posY, int velocidadX, int velocidadY, Color color, String ruta) {
		this.ancho = ancho;
		this.alto = alto;
		this.posX = posX;
		this.posY = posY;
		this.velocidadX = velocidadX;
		this.velocidadY = velocidadY;
		this.color = color;
		this.ruta = ruta;

		cargarSprite();
	}
	// Constuctor de medioAmbiente
		public Sprite(int ancho, int alto, int posX, int posY, Color color) {
			this.ancho = ancho;
			this.alto = alto;
			this.posX = posX;
			this.posY = posY;
			this.velocidadX = 0;
			this.velocidadY = 0;
			this.color = color;
			this.ruta="";
			this.vida=3;
		}

	public Sprite(int ancho, int alto, int posX, int posY, int velocidadX, int velocidadY, Color color, String ruta,
			int vida) {
		this.ancho = ancho;
		this.alto = alto;
		this.posX = posX;
		this.posY = posY;
		this.velocidadX = velocidadX;
		this.velocidadY = velocidadY;
		this.color = color;
		this.ruta = ruta;
		this.vida = vida;

		cargarSprite();
	}

	// Constuctor
	public Sprite(int ancho, int alto, int posX, int posY, int velocidadX, int velocidadY, Color color) {
		this.ancho = ancho;
		this.alto = alto;
		this.posX = posX;
		this.posY = posY;
		this.velocidadX = velocidadX;
		this.velocidadY = velocidadY;
		this.color = color;

		cargarSprite();
	}

	/**
	 * Procedimiento para inciar el sprite
	 */
	public void cargarSprite() {
		// Preparativos
		imagen = new BufferedImage(this.ancho, this.alto, BufferedImage.TYPE_INT_ARGB);
		grafico = imagen.getGraphics();
		try {
			// Reescalado
			imgAux = ImageIO.read(new File(this.ruta));
			imgAux = imgAux.getScaledInstance(this.ancho, this.alto, BufferedImage.SCALE_SMOOTH);
			// Dibujar
			grafico.drawImage(imgAux, 0, 0, null);
			// Si falla
		} catch (IOException e) {
			grafico.setColor(this.color);
			grafico.fillRect(0, 0, this.ancho, this.alto);
			grafico.dispose();
			// System.out.println("Imagen del Sprite no cargada.");
		}
	}

	/**
	 * Dibujar sprite
	 * 
	 * @param g
	 */
	public void pintarSprite(Graphics g) {
		g.drawImage(imagen, posX, posY, null);
	}

	/**
	 * Mover el sprite
	 */
	public void moverSprite() {
		this.posX = this.posX + this.velocidadX;
		this.posY = this.posY + this.velocidadY;
	}

	public void moverSpriteDerecha() {
		this.posX = this.posX + this.velocidadX;
	}

	public void moverSpriteIzquierda() {
		this.posX = this.posX - this.velocidadX;
	}

	public void moverSpriteArrriba() {
		this.posY = this.posY - this.velocidadY;
	}

	public void moverSpriteAbajo() {
		this.posY = this.posY + this.velocidadY;
	}

	public void moverSpriteArribaDerecha() {
		this.posX = this.posX + (this.velocidadX - 2);
		this.posY = this.posY - (this.velocidadY - 2);
	}

	public void moverSpriteArribaIzquierda() {
		this.posX = this.posX - (this.velocidadX - 2);
		this.posY = this.posY - (this.velocidadY - 2);
	}

	public void moverSpriteAbajoDerecha() {
		this.posX = this.posX + (this.velocidadX - 2);
		this.posY = this.posY + (this.velocidadY - 2);
	}

	public void moverSpriteAbajoIzquierda() {
		this.posX = this.posX - (this.velocidadX - 2);
		this.posY = this.posY + (this.velocidadY - 2);
	}

	/**
	 * Mover el sprite
	 * 
	 * @param anchoPantalla
	 * @param altoPantalla
	 */
	public void moverSpriteConColisionEnBordePantalla(int anchoPantalla, int altoPantalla) {
		if (posX >= anchoPantalla - ancho) {
			this.velocidadX = (-Math.abs(this.velocidadX));
		}
		if (posX <= 0) {
			this.velocidadX = (Math.abs(this.velocidadX));

		}
		if (posY >= altoPantalla - alto) {
			this.velocidadY = (-Math.abs(this.velocidadY));

		}
		if (posY <= 0) {
			this.velocidadY = (Math.abs(this.velocidadY));

		}

		this.posX = this.posX + this.velocidadX;
		this.posY = this.posY + this.velocidadY;
	}

	public void moverSpriteHaciaElRaton(int posXRaton, int posXAnteriorRaton, int posYRaton, int posYAnteriorRaton) {
		this.posX = this.posX + (posXRaton - posXAnteriorRaton);
		this.posY = this.posY + (posYRaton - posYAnteriorRaton);
	}

	/**
	 * Funcion para saber si el sprite colisiona
	 * 
	 * @param otro
	 *            sprite
	 * @return true si colisiona con el sprite de entra
	 */
	public boolean colisionaCon(Sprite otro) {
		// Ver si comparten algun espacio a lo ancho:
		boolean colisionAncho = false;
		if (posX < otro.getPosX()) {
			if (ancho + posX > otro.getPosX()) {
				colisionAncho = true;
			}
		} else {
			if (otro.getAncho() + otro.getPosX() > posX) {
				colisionAncho = true;
			}
		}

		// Ver si comparten algun espacio a lo alto:
		boolean colisionAlto = false;
		if (posY < otro.getPosY()) {
			if (alto > otro.getPosY() - posY) {
				colisionAlto = true;
			}
		} else {
			if (otro.getAlto() > posY - otro.getPosY()) {
				colisionAlto = true;
			}
		}

		return colisionAncho && colisionAlto;
	}

	/**
	 * Colision con los bordes de la pantalla
	 * 
	 * @param anchoPantalla
	 * @param altoPantalla
	 * @return
	 */
	public Boolean colisionConBordePantalla(int anchoPantalla, int altoPantalla) {
		if (posY - (alto / 4) < 0) {
			return true;
		}
		if (posY > (altoPantalla) - alto) {
			return true;
		}
		if (posX - (ancho / 4) < 0) {
			return true;
		}
		if (posX > (anchoPantalla) - ancho) {
			return true;
		}
		return false;
	}

	/**
	 * Colision con los bordes de la pantalla
	 * 
	 * @param anchoPantalla
	 * @param altoPantalla
	 * @return el borde con el que colisiona
	 */
	public String colisionConQueBordePantalla(int anchoPantalla, int altoPantalla) {
		if (posY - (alto / 4) < 0) {
			return "arriba";
		}
		if (posY > (altoPantalla) - alto) {
			return "abajo";
		}
		if (posX - (ancho / 4) < 0) {
			return "izquierda";
		}
		if (posX > (anchoPantalla) - ancho) {
			return "derecha";
		}
		return "";
	}
	public void colisionConMovimientoAlColisionarConBordePanta(int anchoPantalla, int altoPantalla) {		
		switch (this.colisionConQueBordePantalla(anchoPantalla, altoPantalla)) {
		case "arriba": {
			this.posY = this.posY + (this.alto+3);

			break;
		}
		case "abajo": {
			this.posY = this.posY - (this.alto-3);

			break;
		}
		case "izquierda": {
			this.posX = this.posX + (this.ancho+3);

			break;
		}
		case "derecha": {
			this.posX = this.posX - (this.ancho-3);
			break;
		}
		default:
			break;
		}
	}

	public void caminarConProbabilidad(int anchoPantalla, int altoPantalla) {
		// Probabilidad de camirala a la derecha
		if (Utilidades.actuarConProbabilidad(0.40f)) {
			this.setPosX(this.posX + 1);
			this.setPosY(this.posY);
		} else {
			this.setPosX(this.posX + Utilidades.aleatorio(-1, 3));
			this.setPosY(this.posY + Utilidades.aleatorio(-1, 3));
		}
		colisionConMovimientoAlColisionarConBordePanta(anchoPantalla, altoPantalla);

	}

	public void caminarSiguiendoPosicion(int xPosASeguir, int yPosASeguir,int anchoPantalla, int altoPantalla) {
		// Si aleatorio es menor que 0.50 irá hacia la posicion a seguir
		if (Utilidades.actuarConProbabilidad(0.70f)) {

			if ((xPosASeguir < this.posX) && (yPosASeguir < this.posY)) {
				this.posX = this.posX - 1;
				this.posY = this.posY - 1;
			}
			if ((xPosASeguir >= this.posX) && (yPosASeguir >= this.posY)) {
				this.posX = this.posX + 1;
				this.posY = this.posY + 1;
			}

			if ((xPosASeguir < this.posX) && (yPosASeguir >= this.posY)) {
				this.posX = this.posX - 1;
				this.posY = this.posY + 1;
			}
			if ((xPosASeguir >= this.posX) && (yPosASeguir < this.posY)) {
				this.posX = this.posX + 1;
				this.posY = this.posY - 1;
			}

		} else {
			this.posX = this.posX + Utilidades.aleatorio(-1, 3);
			this.posY = this.posY + Utilidades.aleatorio(-1, 3);

		}
		
		colisionConMovimientoAlColisionarConBordePanta(anchoPantalla, altoPantalla);


	}

	public void caminarConRelacion(int anchoPantalla, int altoPantalla) {

		double desviacion = 0.4;
		double media = 9;
		double x = desviacion * new Random().nextGaussian() + media;
		double y = desviacion * new Random().nextGaussian() + media;

		switch (Utilidades.aleatorio(0, 4)) {
		case 0:
			this.posX = this.posX - (int) x;
			this.posY = this.posY - (int) y;
			break;
		case 1:
			this.posX = this.posX - (int) x;
			this.posY = this.posY + (int) y;
			break;
		case 2:
			this.posX = this.posX + (int) x;
			this.posY = this.posY - (int) y;
			break;
		case 3:
			this.posX = this.posX + (int) x;
			this.posY = this.posY + (int) y;
			break;

		default:
			break;
		}

		// Aleatoriadad para dar un paso mas grande
		if (Utilidades.actuarConProbabilidad(0.20f)) {
			switch (Utilidades.aleatorio(0, 4)) {
			case 0:
				this.posX = this.posX + Utilidades.aleatorio(-16, 40);
				this.posY = this.posY + Utilidades.aleatorio(-16, 40);
				break;
			case 1:
				this.posX = this.posX - Utilidades.aleatorio(-16, 40);
				this.posY = this.posY - Utilidades.aleatorio(-16, 40);
				break;
			case 2:
				this.posX = this.posX - Utilidades.aleatorio(-16, 40);
				this.posY = this.posY + Utilidades.aleatorio(-16, 40);
				break;
			case 3:
				this.posX = this.posX + Utilidades.aleatorio(-16, 40);
				this.posY = this.posY + Utilidades.aleatorio(-16, 40);
				break;

			default:
				break;
			}
		}
		
	colisionConMovimientoAlColisionarConBordePanta(anchoPantalla, altoPantalla);

	}
	
	/*
	 * Get and Set
	 */
	public Image getBuffer() {
		return imagen;
	}

	public void setBuffer(Image buffer) {
		this.imagen = buffer;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getVelocidadX() {
		return velocidadX;
	}

	public void setVelocidadX(int velocidad) {
		this.velocidadX = velocidad;
	}

	public int getVelocidadY() {
		return velocidadY;
	}

	public void setVelocidadY(int velocidadY) {
		this.velocidadY = velocidadY;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	public void setRutaYCargar(String ruta) {
		this.ruta = ruta;
		cargarSprite();
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

}