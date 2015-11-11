package vv.spoon.call;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Reeprésente un noeud de l'abre des appels de méthode.
 *
 */
public class Node {

	/**
	 * Liste des noeuds enfants.
	 */
	private List<Node> children;
	/**
	 * Nom du noeud.
	 */
	private String name;
	/**
	 * Noeud parent.
	 */
	private Node parent;
	
	public Node(String pName){
		name=pName;
		children = new ArrayList<Node>();
	}
	
	/**
	 * Ajout d'un noeud à la liste des enfants, et affectation du noeud parent au noeud ajouter
	 * @param node : noeud à ajouter
	 */
	public void add(Node node){
		children.add(node);
		node.setParent(this);
	}

	public String getName() {
		return name;
	}

	public Iterator<Node> getChildren(){
		return children.iterator();
	}
	
	public Node getParent(){
		return parent;
	}
	
	private void setParent(Node node){
		parent = node;
	}
	
}
