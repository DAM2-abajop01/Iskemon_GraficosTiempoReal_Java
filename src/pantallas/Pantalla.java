package pantallas;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

/**
 * @author Ismael Martín Ramírez
 */

public interface Pantalla {

	/**
	 * Metodo para inciar la pantalla
	 */
	public void inicializarPantalla();

	/*
	 * Metodo para pintar la pantalla
	 */
	public void pintarPantalla(Graphics g);

	/**
	 * Acciones en cada frame
	 */
	public void ejecutarFrame();

	/**
	 * Actualizar fondo
	 */
	public void actualizarFondo(Graphics g);

	/**
	 * Accion al redimensionar
	 */
	public void redimensionarPantalla();

	/**
	 * Acciones que llevara acabo al pulsar intro
	 */
	public void pulsarIntro();
	/**
	 * Acciones que llevara acabo al la tecla espacio
	 */
	public void pulsarEspacio();
	
	/**
	 * Acciones que llevara acabo al pulsar d o derecha
	 */
	public void pulsarDerecha();

	/**
	 * Acciones que llevara acabo al pulsar a o izuqierda
	 */
	public void pulsarIzquierda();

	/**
	 * Acciones que llevara acabo al pulsar w o arriba
	 */
	public void pulsarArriba();

	/**
	 * Acciones que llevara acabo al pulsar s o abajo
	 */
	public void pulsarAbajo();
	/**
	 * Acciones que llevara acabo al pulsar a y w a la vez
	 */
	public void pulsarArribaIzquierda();
	/**
	 * Acciones que llevara acabo al pulsar d y w a la vez
	 */
	public void pulsarArribaDerecha();
	/**
	 * Acciones que llevara acabo al pulsar a y s a la vez
	 */
	public void pulsarAbajoIzquierda();
	/**
	 * Acciones que llevara acabo al pulsar d y s a la vez
	 */
	public void pulsarAbajoDerecha();

	/**
	 * Acciones que llevara acabo el raton cuando se mueva
	 */
	public void moverRaton(MouseEvent e);

	/**
	 * Acciones que llevara acabo el raton cuando se pulse
	 */
	public void pulsarRaton(MouseEvent e);

	/**
	 * Acciones que llevara acabo el raton cuando se presiona el raton
	 */
	public void mantenerPulsadoRaton(MouseEvent e);

	/**
	 * Ocultar puntero del raton
	 */
	public void ocultarRaton();

	/**
	 * Mostrar puntero del raton
	 */
	public void mostarRaton();

}