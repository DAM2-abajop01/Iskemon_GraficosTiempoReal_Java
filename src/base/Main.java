package base;

import java.awt.EventQueue;

/**
 * @author Ismael Martín Ramírez
 */

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame ventana = new Frame();
					ventana.iniciar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	

	}
}