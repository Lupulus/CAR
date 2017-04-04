package car_tp3.car_akka;

import java.util.Scanner;

import com.typesafe.config.ConfigFactory;

import actor.NodeActor;
import actor.NodeActorsManager;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import config.ConfigManager;
import config.SystemConfiguration;


public class Main {

	public static void main(String args[]) throws InterruptedException {
		/** ActorSystem system1 = ActorSystem.create("Dragostea");
		ActorSystem system2 = ActorSystem.create("DeinTi");
		
		
		ActorRef acteur1 = system1.actorOf( Props.create(NodeActor.class), "acteur1" );
	    ActorRef acteur2 = system2.actorOf( Props.create(NodeActor.class), "acteur2" );
	    ActorRef acteur3 = system1.actorOf( Props.create(NodeActor.class), "acteur3" );
	    ActorRef acteur4 = system2.actorOf( Props.create(NodeActor.class), "acteur4" );
	    ActorRef acteur5 = system1.actorOf( Props.create(NodeActor.class), "acteur5" );
	    ActorRef acteur6 = system2.actorOf( Props.create(NodeActor.class), "acteur6" );

		//ActorSelection selectGreeter1 = system1.actorSelection("akka.tcp://DeinTi@127.0.0.1:2552/user/greeter1");
		//selectGreeter1.tell(NodeActor.Msg.TRANSMIT, ActorRef.noSender());
		 
	    acteur1.tell(acteur2, ActorRef.noSender());
	    acteur1.tell(acteur5, ActorRef.noSender());
	    
	    acteur2.tell(acteur3, ActorRef.noSender());
	    acteur2.tell(acteur4, ActorRef.noSender());
	    
	    acteur4.tell(acteur6, ActorRef.noSender());
	    acteur5.tell(acteur6, ActorRef.noSender());
	    
	    acteur1.tell(NodeActor.Msg.TRANSMIT, ActorRef.noSender()); **/
		
		
		

		/**
		 * Point d'entrée du programme.
		 * Démarre un système d'acteur Akka et bloque jusqu'à son arrêt.
		 * @param args 1 argument obligatoire : le nom du système à charger
		 */			
			if (args.length == 0) {
				Log.error("Indiquer le nom du système à charger en argument, parmi ceux-là :");
				ConfigManager.getConfig().getSystems().keySet().forEach(Log::error);
				return;
			}
			
			// chargement de la configuration
			SystemConfiguration config = ConfigManager.getConfig().getSystems().get(args[0]);
			
			if (config == null) {
				Log.error("Le système " + args[0] + " n'existe pas.");
				ConfigManager.getConfig().getSystems().keySet().forEach(Log::error);
				return;
			}
			
			// configuration du système
			ActorSystem system;
			if (config.getAkkaConnectConfig() == null) {
				system = ActorSystem.create(args[0]);
			}
			else {
				String remoteHost = config.getAkkaConnectConfig().getHost();
				int remotePort = config.getAkkaConnectConfig().getPort();
				system = ActorSystem.create(args[0],
						ConfigFactory.parseString(
								ConfigManager.generateAkkaConnectConfig(remoteHost, remotePort)));
			}
			
			// system.shutdown() sera appelé en cas d'utilisation de Ctrl + C
			Runtime.getRuntime().addShutdownHook(new Thread(() -> {
				Log.error("Arrêt forcé");
				system.shutdown();
			}));
			
			NodeActorsManager nodeActorsManager = new NodeActorsManager(system);
			
			// création des acteurs
			config.getNodes().forEach(node -> {
				if (node.getConnectURL() == null) {
					nodeActorsManager.createLocalActor(node.getName());
				}
				else {
					nodeActorsManager.createDistantActor(node.getName(), node.getConnectURL());
				}
			});
			
			// liaison des acteurs entre eux
			config.getNodes().forEach(node -> {
				node.getSuccessors().forEach(succName -> {
					nodeActorsManager.addSuccessorLink(node.getName(), succName);
				});
			});
			
			Log.info("Veuillez indiquer le noeud de départ");
			Scanner sc = new Scanner(System.in);
			String start = sc.nextLine();
			NodeActorsManager.get(start).tell(NodeActor.Msg.TRANSMIT, ActorRef.noSender());
	} 
	
	
}	
