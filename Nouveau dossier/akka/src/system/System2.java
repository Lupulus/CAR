package system;

import java.util.ArrayList;

import actor.Noeud;
import actor.Feuille;
import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class System2 {
	
 
	ActorSystem system2;
	ArrayList<ActorRef> actorsSyst2;
	ArrayList<ActorSelection> selectorSyst2;
	
	public void create() {
			system2 = ActorSystem.create("MySystem2"); 
    
			ActorSelection greeter1 = system2.actorSelection("akka.tcp://MySystem1@127.0.0.1:2552/user/greeter1");

			//greeter1.tell(Greeter.Msg.GREET, sender1);
	}
  
	public void createAllActors(){
		actorsSyst2.add( system2.actorOf( Props.create(Noeud.class), "acteur2") );
		actorsSyst2.add( system2.actorOf( Props.create(Feuille.class), "acteur4") );
		actorsSyst2.add( system2.actorOf( Props.create(Feuille.class), "acteur6") );
	}
	
	
	public void createAllSelector(){
		selectorSyst2.add( system2.actorSelection("akka.tcp://MySystem1@127.0.0.1:2552/user/greeter1") );
		selectorSyst2.add( system2.actorSelection("akka.tcp://MySystem1@127.0.0.1:2552/user/greeter3") );
		selectorSyst2.add( system2.actorSelection("akka.tcp://MySystem1@127.0.0.1:2552/user/greeter5") );
	}
}
