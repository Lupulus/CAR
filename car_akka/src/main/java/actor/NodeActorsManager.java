package actor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.util.Timeout;
import scala.concurrent.Await;
import scala.concurrent.duration.Duration;

/**
 * Gestionnaire d'acteur contenant la liste des référence d'acteur (associé à leur nom)
 * que le système d'acteur associé aura instancié.
 */
public class NodeActorsManager {
	
	private static Map<String, ActorRef> actors = new HashMap<>();
	
	private ActorSystem system;
	
	/**
	 * Créer une nouvelle instance de gestionnaire d'acteur
	 * associé au système d'acteur passé en paramètre.
	 * 
	 * @param s le système d'acteur {@link ActorSystem} qui va permettre de récupérer les {@link ActorRef}.
	 */
	public NodeActorsManager(ActorSystem s) {
		system = s;
	}
	
	
	/**
	 * Récupère l'{@link ActorRef} dont le nom est passé en paramètre.
	 * @param name le nom de l'acteur dont on veut récupérer la référence.
	 * @return la référence de l'acteur demandée, ou null si elle n'existe pas.
	 */
	public static ActorRef get(String name) {
		return actors.get(name);
	}
	
	/**
	 * Récupère le système d'acteur associé à ce gestionnaire d'acteur.
	 * 
	 * Il s'agit du système d'acteur passé en paramètre du constructeur de ce
	 * gestionnaire d'acteur.
	 * @return l'instance d'{@link ActorSystem} 
	 */
	public ActorSystem getSystem() {
		return system;
	}
	
	/**
	 * Crée un acteur local de type {@link NodeActor} et sauvegarde sa
	 * référence pour être récupéré via {@link #get(String)}.
	 * 
	 * Si un acteur avec ce nom est déjà référencé, une erreur est affichée
	 * et la méthode n'a aucun effet.
	 * 
	 * @param name le nom de l'acteur local qu'on veut instancier.
	 */
	public void createLocalActor(String name) {
		if (actors.containsKey(name)) {
			//Log.error("Un acteur avec le nom '"+name+"' existe déjà");
			return;
		}
		actors.put(name, system.actorOf(Props.create(NodeActor.class), name));
		//Log.info("Création de l'acteur local '" + name + "'");
	}
	
	public void createDistantActor(String name, String url) {
		if (actors.containsKey(name)) {
			return;
		}
		try {
			/* pour les acteurs distant, on doit récupérer l'ActorRef qui n'est
			 * pas directement disponible.
			 */
			//Log.info("Récupération de l'acteur distant '" + name + "' depuis " + url);
			actors.put(name, Await.result(system.actorSelection(url).resolveOne(new Timeout(30, TimeUnit.SECONDS)),Duration.Inf()));
		} catch (Exception e) {
			throw new RuntimeException("Impossible de récuperer l'ActorRef de '"+name+"'", e);
		}
	}
	
	/**
	 * Supprime l'acteur dont le nom est passé en paramètre,
	 * du gestionnaire d'acteur courant.
	 * 
	 * Si l'acteur indiqué n'existe pas, l'état du gestionnaire
	 * d'acteur sera inchangé.
	 * 
	 * @param name le nom de l'acteur à supprimer.
	 */
	public void removeActor(String name) {
		ActorRef a = actors.remove(name);
		if (a != null)
			System.out.println("Suppression de l'acteur '" + name + "'");
		else
			System.out.println("L'acteur '" + name + "' n'existe pas");
	}
	
	
	public void addSuccessorLink(String source, String dest) {
		if (!actors.containsKey(source)) {
			System.out.println("L'acteur '"+source+"' n'existe pas");
			return;
		}
		if (!actors.containsKey(dest)) {
			System.out.println("L'acteur '"+dest+"' n'existe pas");
			return;
		}
		
		get(source).tell(get(dest), ActorRef.noSender());
		//System.out.println("L'acteur '" + source + "' a pour successeur l'acteur '" + dest + "'");
}

	
	
	
}