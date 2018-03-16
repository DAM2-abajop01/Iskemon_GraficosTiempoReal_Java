package base;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 * @author Ismael Martín Ramírez
 */

public class Sonidos implements Runnable{
	private String ruta;

	public Sonidos(String ruta) {
		this.ruta = ruta;
	}

	@Override
	public void run() {
		try {
			FileInputStream fis;
			Player player;
			fis = new FileInputStream(ruta);
			BufferedInputStream bis = new BufferedInputStream(fis);
			player = new Player(bis); // Llamada a constructor de la clase Player
			player.play(); // Llamada al método play

		} catch (JavaLayerException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}