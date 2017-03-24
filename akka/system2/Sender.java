import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.ActorRef;

public class Sender extends UntypedActor {

  public void onReceive(Object msg) {
    System.out.println("J'ai recu: " + msg);
  }
}
