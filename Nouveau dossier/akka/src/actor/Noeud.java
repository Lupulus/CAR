package actor; 

import akka.actor.ActorRef;

public class Noeud extends Actors {

  ActorRef[] children = new ActorRef[2];
  ActorRef parent = null;
  boolean firstSendBack = false;
  boolean firstGraphTransmit = false; 
  
  @Override
  public void onReceive(Object msg) {	  
	 
	
	if(msg == Msg.CHILD1){
		children[1] = getSender();
	}else if (msg == Msg.CHILD2){
		children[2] = getSender();
	}else if(msg == Msg.PARENT){
		parent = getSender();
	}else if (msg == Msg.TRANSMIT) {
		if(getSender() == parent){
			if(children[1] != null){
				children[1].tell(Msg.TRANSMIT, getSelf());
			}
			if(children[2] != null){
				children[2].tell(Msg.TRANSMIT, getSelf());
			}
		}else{
			getSender().tell(Msg.GRAPH, getSelf());
		}
      //getSender().tell("Et vous, ca va? ", getSelf());
    }else if(msg == Msg.SENDBACK){
    	if(parent != null){
			if(firstSendBack || children[1] == null || children[2] == null){
				firstSendBack = false;
				parent.tell(Msg.SENDBACK, getSelf());
			}else{
				firstSendBack = true;
			}
    	}else{
    		System.out.println("Le message a �t� transmis � tout le monde");
    	}
    }else if(msg == Msg.GRAPH){
    	System.out.println("Message graphe entre l'acteur" + getSelf() + "et l'acteur " + getSender());
    }else unhandled(msg);
  }

}
