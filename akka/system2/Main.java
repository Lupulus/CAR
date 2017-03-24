import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.ActorSelection;

public class Main {
  public static void main(String args[]) {
    ActorSystem system = ActorSystem.create("MySystem2"); 
    ActorRef sender1 = system.actorOf( Props.create(Sender.class), "sender1" );
        
    ActorSelection greeter1 = system.actorSelection("akka.tcp://MySystem1@127.0.0.1:2552/user/greeter1");
    
    greeter1.tell(Greeter.Msg.GREET, sender1);
  }
}
