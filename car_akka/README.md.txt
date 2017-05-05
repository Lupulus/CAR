Timothée Laval
Hajar 

						CAR - TP Akka 

Compiler le TP puis le lancer:
	mvn package
	java -jar target/akka-v1.0.jar <système>

<système> est un argument qui correspond au nom d'un des systèmes trouvables dans le fichier systems.json

Au lancement, l'application chargera le système puis demandera le nom de l'acteur qui doit démarrer l'envoi de message
Les noms d'acteurs sont toujours act suivi d'un numéro.

Lancer 2 fois la commmande java -jar pour les systèmes répartis.