import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.ActorRef;

public class Greeter extends UntypedActor {

  public static enum Msg {
    GREET, DONE;
  }

  @Override
  public void onReceive(Object msg) {
    if (msg == Msg.GREET) {
      System.out.println("Bonjour!");
      getSender().tell("Et vous, ca va? ", getSelf());
    } else unhandled(msg);
  }

}
