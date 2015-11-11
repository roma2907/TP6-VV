package vv.spoon.call;

/**
 * Thread qui est lancé à la mort du thread principal.
 *
 */
public class ShutdownHookLog extends Thread {

	/**
	 * Affiche le résultat final de l'abre.
	 */
    public void run() {
        TreeCall.show();
    }
}
