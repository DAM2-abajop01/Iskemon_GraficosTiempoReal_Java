package base;

import java.util.Random;

public class Utilidades {
	/**
	 * Obtiene un aleatorio
	 * 
	 * @param minimo
	 * @param cantidad
	 * @return aleatorio entre la cantidad desde el minimo
	 */
	public static int aleatorio(int minimo, int cantidad) {
		Random r = new Random();
		int aleatorio = r.nextInt(cantidad) + minimo;
		return aleatorio;
	}

	/**
	 * Obtiene un aleatorio
	 * 
	 * @param minimo
	 * @param cantidad
	 * @return aleatorio entre la cantidad desde el minimo
	 */
	public static int aleatorioSinCero(int minimo, int cantidad) {
		Random r = new Random();
		int aleatorio = r.nextInt(cantidad) + minimo;
		if (aleatorio == 0) {
			aleatorioSinCero(minimo, cantidad);
			return -1;
		} else {
			return aleatorio;
		}
	}

	/**
	 * Funcion que obtiene mediante probabilidad un booleano
	 * 
	 * @return aleatorio true si la probabilidad salio, falso ni no
	 */
	public static boolean actuarConProbabilidad(float probabilidad) {
		Random r = new Random();
		float tantoPorcierto = r.nextFloat();
		if (tantoPorcierto < probabilidad) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Funcion para obtener un numero entre 0 y 1
	 * 
	 * @return un numero entre 0 y 1
	 */
	public static double obtenerProbabilidad() {
		Random r = new Random();
		double probabilidad = r.nextDouble();
		if (probabilidad < 0.09999999f) {
			obtenerProbabilidad();
		}
		return probabilidad;

	}

}
