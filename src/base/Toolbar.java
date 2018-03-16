package base;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Toolbar {

	public static void gestionTolbar(Graphics g, ArrayList<Sprite> armas, ArrayList<Sprite> armasObtenidas,
			float tiempoJuego) {
		// Cronometro
		// Escribir en grafico
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		// Formato para darle dos decilames
		DecimalFormat format = new DecimalFormat("#.##");
		g.drawString("Time: " + (format.format(tiempoJuego / 1000000000)), 20, 20);
		// Armas en toolbar
		g.drawRect(25, 25, 34, 34);
		g.drawRect(25, 75, 34, 34);

		if (!Jugador.armasObtenidas.isEmpty()) {
			if (Jugador.armasObtenidas.contains(armas.get(1))) {
				Sprite bastonEspacioObtenido = new Sprite(30, 30, 28, 28, 0, 0, Color.RED,
						Armas.RUTA_IMG_BASTON_ESPACIO);
				bastonEspacioObtenido.pintarSprite(g);
			}
			if (Jugador.armasObtenidas.contains(armas.get(0))) {
				Sprite bastonClickObtenido = new Sprite(30, 30, 28, 78, 0, 0, Color.RED, Armas.RUTA_IMG_BASTON_CLICK);
				bastonClickObtenido.pintarSprite(g);
			}
		}

		Sprite gatos = new Sprite(30, 30, 22, 128, 0, 0, Color.RED, Mundo.RUTA_IMG_GATO);
		g.setColor(Color.GRAY);
		g.fillRect(25, 125, 34, 34);
		gatos.pintarSprite(g);
		g.setColor(Color.WHITE);

		g.drawString("x" + Jugador.gatos, 40, 158);

		// Vida
		Jugador.gestionVida(g);
	}
}
