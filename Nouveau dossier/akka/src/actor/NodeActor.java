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
	 
	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof ActorRef){
			children.add((ActorRef) msg);
		}else if(msg instanceof Msg){
			switch((Msg) msg){
				case TRANSMIT: 	System.out.println("Message TRANSMIT sur acteur " + getSelf());
							   	if(children.size() != 0){
							   		parent = getSender();
							   		for(ActorRef child : children){child.tell(Msg.TRANSMIT, getSelf());};
							   	}else{
						   			getSender().tell(Msg.SENDBACK, getSelf());
							   	}
							   	break;
				case SENDBACK: 	System.out.println("Message SENDBACK sur acteur " + getSelf());
							   	if(parent != null){ parent.tell(Msg.SENDBACK, getSelf());};
							   	break;
				case GRAPH:    break;
				default: unhandled(msg);
			}
		}else unhandled(msg);
	}
	

}