Laval Timothée
      Ajar

Fonctionnement de l'application:
	 Au lancement, l'application démarre avec le package Main:
			-La classe Serveur va rechercher via la classe Information toutes les informations nécessaires à l'instanciation du serveur
                        puis va lancer le serveur et attendre une connexion d'un client.
 			- Lorsqu'un client se connecte, la classe lance un objet FtpRequest dans un thread ce qui permet de gérer le multicompte

	Une fois qu'un client est connecté, l'application se déroule dans le package Request:
			-La classe FtpRequest gère la connection de message avec le client, chaque instruction du client sera
			divisée puis traitée via le switch(ou la HashMap si j'ai eu le temps) pour dispatcher à ProcessRequest
                        -ProcessRequest ne sert que d'intermédiaire entre la classe FtpRequest et les commandes, cette classe possède une fonction 
                        processCommand() pour chaque commande et appelle la commande demandée par le client

	Une fois qu'une commmande est envoyée par le client et gérée par ProcessRequest:
			-La classe Command est une abstraite qui possède un constructeur et une fonction process qui est overridée
  			par chaque classe qui l'hérite. Elle instancie un FtpRequest (double dispatch) pour lui renvoyer les modifications
  			à faire suite à la commande du client