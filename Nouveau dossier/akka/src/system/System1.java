package system;

import actor.Noeud;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class System1 {
  public void create() {
    ActorSystem system = ActorSystem.create("MySystem1"); 
    System.out.println("J'ecoute...");
    ActorRef greeter1 = system.actorOf( Props.create(Noeud.class), "greeter1" );
  }
}
