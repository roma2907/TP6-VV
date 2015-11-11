package vv.spoon.call;

import java.util.Iterator;

import vv.spoon.call.ShutdownHookLog;

/**
 * Classe représentant un arbre avec les appels de méthode effectuées.
 *
 */
public class TreeCall {

	private static Node currentNode;
	private static Node root;
	
	/**
	 * Ajout d'un nouveau noeud à l'abre, le nouveau noeud est ajouté au noeud courant et le noeud courant devient node.
	 * Cette méthode est appelé à chaque fois qu'on entre dans une méthode pour créer un nouveau noeud.
	 * @param node : noeud à ajouter
	 */
	public static void add(Node node){
		if(currentNode != null){
			currentNode.add(node);
		}else{
			ShutdownHookLog shutdownHook = new ShutdownHookLog();
			Runtime.getRuntime().addShutdownHook(shutdownHook);
			root = node;
		}
		currentNode = node;
	}
	
	/**
	 * Remonte dans l'arborescence.
	 * Cette méthode est appelé à la fin des parcours des méthodes.
	 */
	public static void up(){
		currentNode = currentNode.getParent();
	}
	
	/**
	 * on remonte dans l'arborescence tant que e noeud courant ne correspond pas au noeud passé en paramètre.
	 * Cette méthode est appelé lorsqu'on catch une excpetion.
	 */
	public static void up(String name){
		while(!currentNode.getName().equals(name)){
			currentNode = currentNode.getParent();
		}
	}
	
	/**
	 * Affiche l'arbre
	 */
	public static void show(){
		System.out.println(rec(root,0));
	}
	
	/**
	 * Parcours récursivement l'arbre et affiche chaque noeud puis ses enfants.
	 * @param node : node en train d'ête analyser
	 * @param tabulation : nombre de tabuation qu'il faut 
	 * @return l'arbre sous forme de string du noeud mis en paramètre
	 */
	private static String rec(Node node,int tabulation){
		StringBuilder builder = new StringBuilder();
		//on ajoute l'espacement et le nom de la méthode
		builder.append(tab(tabulation));
		builder.append(node.getName());
		//parcours de tous les enfants
		Iterator<Node> it = node.getChildren();
		while(it.hasNext()){
			//on appelle récursivement
			builder.append(rec(it.next(),tabulation+1));
		}
		return builder.toString();
	}
	
	/**
	 * Retourne l'espacement nécéssaire selon la profondeur du noeud.
	 * @param nb : pronfondeur du noeud
	 * @return un String représentant l'espacement nécéssaire avant un noeud
	 */
	private static String tab(int nb){
		StringBuilder builder = new StringBuilder();
		if(nb>0){
			builder.append("\n\t");
		}
		for(int i=0;i<nb;i++)
		{
			builder.append("|\t");
		}
		return builder.toString();
	}

}
