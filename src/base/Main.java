package base;

import java.awt.EventQueue;

/**
 * @author Ismael Mart�n Ram�rez
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