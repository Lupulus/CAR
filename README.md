Laval Timoth�e
      Ajar


Le TP fonctionne correctement avec la commande nc, il s'execute avec la commande ftp mais cela provoque des comportements differents.

Fonctionnement de l'application:
	 Au lancement, l'application demarre avec le package Main:
			-La classe Serveur va rechercher via la classe Information toutes les informations necessaires a l'instanciation du serveur
                        puis va lancer le serveur et attendre une connexion d'un client.
 			- Lorsqu'un client se connecte, la classe lance un objet FtpRequest dans un thread ce qui permet de g�rer le multicompte

	Une fois qu'un client est connect�, l'application se d�roule dans le package Request:
			-La classe FtpRequest g�re la connection de message avec le client, chaque instruction du client sera
			divis�e puis traite via la HashMap  pour dispatcher a ProcessRequest
                        -ProcessRequest ne sert que d'interm�diaire entre la classe FtpRequest et les commandes, cette classe poss�de une fonction
                        processCommand() pour chaque commande et appelle la commande demand�e par le client

	Une fois qu'une commmande est envoy�e par le client et g�r�e par ProcessRequest:
			-La classe Command est une abstraite qui poss�de un constructeur et une fonction process qui est overrid�e
  			par chaque classe qui l'h�rite. Elle instancie un FtpRequest (double dispatch) pour lui renvoyer les modifications
  			� faire suite � la commande du client
