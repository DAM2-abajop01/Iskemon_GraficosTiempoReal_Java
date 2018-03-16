package base;

public class Armas implements Runnable{
	public static String RUTA_IMG_BASTON_CLICK = "imagenes\\armas\\bastonEspacio.png";
	public static String RUTA_IMG_DISPARO_BASTON_CLICK = "imagenes\\armas\\disparoBastonEspacio.png";

	public static String RUTA_IMG_BASTON_ESPACIO = "imagenes\\armas\\bastonClick.png";
	public static String RUTA_IMG_DISPARO_BASTON_ESPACIO = "imagenes\\armas\\tarta.png";

	public static int VELOCIDAD_DISPARO = 20;
	public static int VELOCIDAD_X_DISPARO = 0;
	public static int VELOCIDAD_Y_DISPARO = VELOCIDAD_DISPARO;
	private Sonidos sonMuerteEnemigo;
	private final String RUTA_SONIDO_MUERTEENEMIGO = "sonidos//muerteenemigo.mp3";

	public Armas() {
		sonMuerteEnemigo = new Sonidos(RUTA_SONIDO_MUERTEENEMIGO);

	}

	@Override
	public
	synchronized void run() {
		//Colision de los disparos
		if (!Mundo.medioAmbiente.isEmpty()) {
		for (int i = 0; i < Mundo.medioAmbiente.size(); i++) {
			for (int j = 0; j < Jugador.disparos.size(); j++) {
				if (Jugador.disparos.get(j).colisionaCon(Mundo.medioAmbiente.get(i))) {
					Jugador.disparos.remove(j);
					
					Mundo.medioAmbiente.get(i).setVida(Mundo.medioAmbiente.get(i).getVida()-1);
					if ((Mundo.medioAmbiente.get(i).getVida() < 0 && Mundo.medioAmbiente.size() > 0)&& !Mundo.medioAmbiente.get(i).getRuta().contains("cespe")&& !Mundo.medioAmbiente.get(i).getRuta().contains("agua")) {
						Mundo.medioAmbiente.remove(i);
					}
				}
			}
		}
	}
		if (!Mundo.medioAmbienteNocivo.isEmpty()) {
			for (int i = 0; i < Mundo.medioAmbienteNocivo.size(); i++) {
				for (int j = 0; j < Jugador.disparos.size(); j++) {
					if (Mundo.medioAmbienteNocivo.size() > 0) {
						if (Jugador.disparos.get(j).colisionaCon(Mundo.medioAmbienteNocivo.get(i))) {
							Mundo.medioAmbiente.get(i).setVida(Mundo.medioAmbiente.get(i).getVida() - 1);
							Jugador.disparos.remove(j);
							if (Mundo.medioAmbiente.get(i).getVida() < 0 && Mundo.medioAmbienteNocivo.size() > 0) {	
								Mundo.medioAmbienteNocivo.remove(i);
							}

						}
					}
				}
			}
		}

		if (!Mundo.enemigos.isEmpty()) {
			for (int i = 0; i < Mundo.enemigos.size(); i++) {
				for (int j = 0; j < Jugador.disparos.size(); j++) {
					if ((Mundo.enemigos.size() > 0)&&(Jugador.disparos.get(j)!=null)) {
						if (Jugador.disparos.get(j).colisionaCon(Mundo.enemigos.get(i))) {
							Mundo.enemigos.get(i).setVida(Mundo.enemigos.get(i).getVida() - 1);
							Jugador.disparos.remove(j);

							if (Mundo.enemigos.get(i).getVida() < 0 && Mundo.enemigos.size() > 0) {
								Mundo.enemigos.remove(i);
								new Thread(sonMuerteEnemigo).start();

							}

						}
					}
				}
			}
		}		
	}

}
