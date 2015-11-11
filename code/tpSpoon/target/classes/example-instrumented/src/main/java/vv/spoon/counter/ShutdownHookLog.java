package vv.spoon.counter;

/**
 * Thread qui est lancé à la mort du thread principal.
 *
 */
public class ShutdownHookLog extends Thread {
	
	/**
	 * Affiche le résultat final: combien de fois chaque méthode a été appelé.
	 */
	public void run() {
		CounterMethod.show();
	}
}
