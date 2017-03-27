import actor.NodeActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;


public class Main {

	public static void main(String args[]) throws InterruptedException {
		ActorSystem system1 = ActorSystem.create("Dragostea");
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
	    
	    acteur5.tell(acteur6, ActorRef.noSender());
	    
	    acteur1.tell(NodeActor.Msg.TRANSMIT, ActorRef.noSender());
	} 
	
	
}	
