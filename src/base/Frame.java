package base;

import java.awt.GridLayout;
import javax.swing.JFrame;

/**
 * @author Ismael Martín Ramírez
 */
public class Frame {

	public static JFrame frame;
	private Panel juego;

	public Frame() {
		frame = new JFrame("Iskemon");
		frame.setBounds(200, 200, 1100, 600);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void iniciar() {		
		iniciarComponentes();
		iniciarListened();
		frame.setVisible(true);
	}

	public void iniciarComponentes() {		
		frame.setLayout(new GridLayout());
		juego = new Panel();
		frame.add(juego);
	}

	public void iniciarListened() {
		
	}
}