Timoth�e Laval
Hajar 

						CAR - TP Akka 

Compiler le TP puis le lancer:
	mvn package
	java -jar target/akka-v1.0.jar <syst�me>

<syst�me> est un argument qui correspond au nom d'un des syst�mes trouvables dans le fichier systems.json

Au lancement, l'application chargera le syst�me puis demandera le nom de l'acteur qui doit d�marrer l'envoi de message
Les noms d'acteurs sont toujours act suivi d'un num�ro.

Lancer 2 fois la commmande java -jar pour les syst�mes r�partis.