package base;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

import pantallas.Pantalla;
import pantallas.PantallaBienvenida;

/**
 * @author Ismael Martín Ramírez
 */

public class Panel extends JPanel implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Pantalla inicio
	Pantalla pantallaActual;
	private Font fuente;
	public static Font fuenteComentarios;


	// Teclado multiple funcional pero sin funcionalidad
	private Set<Character> teclasPulsadasALaVez = new HashSet<Character>();
	Character a = 'a';
	Character w = 'w';
	Character s = 's';
	Character d = 'd';
	Character teclaVacia = ' ';
	Boolean teclaVaciaPulsa = false;

	/*
	 * Constructor
	 */
	public Panel() {
		// Inicio del programa
		pantallaActual = new PantallaBienvenida(this);		
		fuente = new Font("Monaco", Font.PLAIN, 60);
		fuenteComentarios = new Font("Monaco", Font.BOLD, 16);
	
		listened();
		new Thread(this).start();

	}// Fin del constructor

	/**
	 * Metodo sobreescrito para pintar el componente, frames por segundo
	 */
	@Override
	protected void paintComponent(Graphics g) {
		pantallaActual.pintarPantalla(g);
		accionDelTeclado();

	}

	/**
	 * Run del panel
	 */
	@Override
	public void run() {
		while (true) {

			// Repintar
			this.repaint();
			pantallaActual.ejecutarFrame();
		}
	}

	/**
	 * Metodo para cambia la pantalla
	 * 
	 * @param pantallaActual
	 */
	public void setPantalla(Pantalla pantallaActual) {
		this.pantallaActual = pantallaActual;
	}

	public Font getFuente() {
		return fuente;
	}

	public void setFuente(Font fuente) {
		this.fuente = fuente;
	}

	/**
	 * Metodo que incia todos los listenes del panel
	 */
	public void listened() {
		// Al presionar el raton
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				pantallaActual.pulsarRaton(e);
			}
		});
		// Al mover el raton
		this.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				pantallaActual.moverRaton(e);
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				pantallaActual.moverRaton(e);
			}
		});
		// Reescala la imagen de fondo si se hace resize la ventana
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				pantallaActual.redimensionarPantalla();
			}
		});
		// Eventos de teclado
		setFocusable(true);
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public synchronized void keyReleased(KeyEvent e) {
				teclasPulsadasALaVez.remove(e.getKeyChar());
			}

			@Override
			public synchronized void keyPressed(KeyEvent e) {
				teclasPulsadasALaVez.add(e.getKeyChar());
			}
		});

	}

	public void accionDelTeclado() {
		// System.out.println("presed -> " + e.getKeyCode());
		// System.out.println(teclasPulsadasALaVez);
		// Dos teclas funcionales y tecla vacia
		if ((teclasPulsadasALaVez.size() > 3 && teclasPulsadasALaVez.size() <= 4)) {
			if (teclasPulsadasALaVez.contains(a) && teclasPulsadasALaVez.contains(w)
					&& teclasPulsadasALaVez.contains(teclaVacia)) {
				pantallaActual.pulsarArribaIzquierda();
			}
			if (teclasPulsadasALaVez.contains(a) && teclasPulsadasALaVez.contains(s)
					&& teclasPulsadasALaVez.contains(teclaVacia)) {
				pantallaActual.pulsarAbajoIzquierda();
			}
			if (teclasPulsadasALaVez.contains(d) && teclasPulsadasALaVez.contains(w)
					&& teclasPulsadasALaVez.contains(teclaVacia)) {
				pantallaActual.pulsarArribaDerecha();
			}
			if (teclasPulsadasALaVez.contains(d) && teclasPulsadasALaVez.contains(s)
					&& teclasPulsadasALaVez.contains(teclaVacia)) {
				pantallaActual.pulsarAbajoDerecha();
			}
		}
		// Dos teclas funcionales y tecla vacia
		if ((teclasPulsadasALaVez.size() > 1 && teclasPulsadasALaVez.size() <= 3)) {
			if (teclasPulsadasALaVez.contains(a) && teclasPulsadasALaVez.contains(w)
					&& teclasPulsadasALaVez.contains(teclaVacia)) {
				pantallaActual.pulsarArribaIzquierda();
			}
			if (teclasPulsadasALaVez.contains(a) && teclasPulsadasALaVez.contains(s)
					&& teclasPulsadasALaVez.contains(teclaVacia)) {
				pantallaActual.pulsarAbajoIzquierda();
			}
			if (teclasPulsadasALaVez.contains(d) && teclasPulsadasALaVez.contains(w)
					&& teclasPulsadasALaVez.contains(teclaVacia)) {
				pantallaActual.pulsarArribaDerecha();
			}
			if (teclasPulsadasALaVez.contains(d) && teclasPulsadasALaVez.contains(s)
					&& teclasPulsadasALaVez.contains(teclaVacia)) {
				pantallaActual.pulsarAbajoDerecha();
			}
		}
		// Dos teclas presionada.
		if (teclasPulsadasALaVez.size() > 1 && teclasPulsadasALaVez.size() <= 2) {
			if (teclasPulsadasALaVez.contains(a) && teclasPulsadasALaVez.contains(w)) {
				pantallaActual.pulsarArribaIzquierda();
			}
			if (teclasPulsadasALaVez.contains(a) && teclasPulsadasALaVez.contains(s)) {
				pantallaActual.pulsarAbajoIzquierda();
			}
			if (teclasPulsadasALaVez.contains(d) && teclasPulsadasALaVez.contains(w)) {
				pantallaActual.pulsarArribaDerecha();
			}
			if (teclasPulsadasALaVez.contains(d) && teclasPulsadasALaVez.contains(s)) {
				pantallaActual.pulsarAbajoDerecha();
			}
		}
		// Dos teclas una fulcional y otra vacia.
		if (teclasPulsadasALaVez.size() > 1 && teclasPulsadasALaVez.size() <= 2) {
			if (teclasPulsadasALaVez.contains(a) && teclasPulsadasALaVez.contains(teclaVacia)) {
				pantallaActual.pulsarIzquierda();
			}
			if (teclasPulsadasALaVez.contains(d) && teclasPulsadasALaVez.contains(teclaVacia)) {
				pantallaActual.pulsarDerecha();
			}
			if (teclasPulsadasALaVez.contains(w) && teclasPulsadasALaVez.contains(teclaVacia)) {
				pantallaActual.pulsarArriba();
			}
			if (teclasPulsadasALaVez.contains(s) && teclasPulsadasALaVez.contains(teclaVacia)) {
				pantallaActual.pulsarAbajo();
			}
		}

		// Dos teclas una fulcional y otra vacia.
		if (teclasPulsadasALaVez.size() == 1) {
			if (teclasPulsadasALaVez.contains(a)) {
				pantallaActual.pulsarIzquierda();
			}
			if (teclasPulsadasALaVez.contains(d)) {
				pantallaActual.pulsarDerecha();
			}
			if (teclasPulsadasALaVez.contains(w)) {
				pantallaActual.pulsarArriba();
			}
			if (teclasPulsadasALaVez.contains(s)) {
				pantallaActual.pulsarAbajo();
			}

		}
		// Tecla vacia pulsada
		if (teclasPulsadasALaVez.contains(teclaVacia)) {
			pantallaActual.pulsarEspacio();
			teclasPulsadasALaVez.remove(teclaVacia);
		}

	}

}