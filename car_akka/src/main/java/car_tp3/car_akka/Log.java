package car_tp3.car_akka;

/**
 * Classe avec des méthodes statiques pour l'affichage des messages dans le terminal
 */
public class Log {
	
	/**
	 * Affiche un message en tant que message informatif (le plus courant).
	 * 
	 * Une implémentation rapide serait de simplement envoyer le message sur la
	 * sortie standard.
	 * @param message le message à afficher
	 */
	public static void info(String message) {
		System.out.println(message);
	}
	

	/**
	 * Affiche un message en tant que message d'erreur.
	 * 
	 * Une implémentation rapide serait de simplement envoyer le message sur la
	 * sortie d'erreur.
	 * @param message le message à afficher
	 */
	public static void error(String message) {
		System.err.println(message);
	}
	
	
	
	
}