package actor;

import java.util.ArrayList;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class NodeActor extends UntypedActor{
	 public static enum Msg {
		    TRANSMIT, SENDBACK, GRAPH, DONE;
	  }
	 
	 public ArrayList<ActorRef> children = new ArrayList<ActorRef>();;
	 public ActorRef parent = null;
	 public boolean alreadyReceivedTRANSMIT = false;
	 
	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof ActorRef){
			children.add((ActorRef) msg);
		}else if(msg instanceof Msg){
			switch((Msg) msg){
				case TRANSMIT: 	if(!alreadyReceivedTRANSMIT){
									System.out.println("Message TRANSMIT sur acteur " + getSelf());
								   	if(children.size() != 0){
								   		parent = getSender();
								   		for(ActorRef child : children){child.tell(Msg.TRANSMIT, getSelf());};
								   	}else{
							   			getSender().tell(Msg.SENDBACK, getSelf());
								   	}
								   	alreadyReceivedTRANSMIT = true;
								}else{
									System.out.println("Message TRANSMIT déjà reçu sur acteur " + getSelf() +", renvoi d'un GRAPH");
									getSender().tell(Msg.GRAPH, ActorRef.noSender());
								}
								break;
				case SENDBACK: 	System.out.println("Message SENDBACK sur acteur " + getSelf());
							   	if(parent != null){ parent.tell(Msg.SENDBACK, getSelf());};
							   	break;
				case GRAPH:    System.out.println("Message GRAPH sur acteur " + getSelf());
								break;
				default: unhandled(msg);
			}
		}else unhandled(msg);
	}
	

}
