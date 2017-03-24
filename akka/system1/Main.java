import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class Main {
  public static void main(String args[]) {
    ActorSystem system = ActorSystem.create("MySystem1"); 
    System.out.println("J'ecoute...");
    ActorRef greeter1 = system.actorOf( Props.create(Greeter.class), "greeter1" );
  }
}
