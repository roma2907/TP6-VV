package vv.spoon.counter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * Cette classe gère le nombre d'appel de chaque méthode.
 */
public class CounterMethod {

	/**
	 * map contient le nombre d'appel de chaque méthode.
	 */
	private static HashMap<String,Integer> map;
	
	/**
	 * Ajout d'une méthode à la map qui compte le nombre de méthode.
	 * @param method
	 */
	public static void add(String method){
		if(map == null){
			//on prépare le thread final
			ShutdownHookLog shutdownHook = new ShutdownHookLog();
			Runtime.getRuntime().addShutdownHook(shutdownHook);
			map = new HashMap<String,Integer>();
		}
		if(map.containsKey(method)){
			//on incrémente le compteur des méthodes
			map.put(method,map.get(method)+1);
		}else{
			//si la clé n'existe pas dans la map on l'ajoute
			map.put(method, 1);
		}
	}
	
	/**
	 * Affiche les méthodes et le nombre de fois qu'elles ont été appelées.
	 */
	public static void show(){
		StringBuilder builder = new StringBuilder();
		//parcours de chaque méthode
		Iterator<Entry<String,Integer>> it = map.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, Integer> current = it.next();
			builder.append(current.getKey());
			builder.append(":");
			builder.append(current.getValue());
			builder.append("\n");
		}
		System.out.println(builder.toString());
	}
}
