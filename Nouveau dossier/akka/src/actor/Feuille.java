package actor;
import akka.actor.Props;
import akka.actor.UntypedActor;
import actor.Actors.Msg;
import akka.actor.ActorRef;

public class Feuille extends Actors {
	  
  @Override
  public void onReceive(Object msg) {	  
		 
		if(msg == Msg.TRANSMIT) {
	      System.out.println("Bonjour!");
	      getSender().tell(Msg.SENDBACK, getSelf());
	    }else unhandled(msg);
	    

	  }
}
