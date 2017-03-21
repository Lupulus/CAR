Laval Timothée
Bob Hajar

Le serveur ftp utilisé est: ftpserver-v1.0.jar
Il suffit d'executer la commande java -jar ftpserver-v1.0.jar
La passerelle se connecte directement sur le port après.

Passerelle fonctionnelle.

Pour se connecter, 
http://localhost:8080/rest/tp2/connect

Propose un formulaire puis donne accès aux autres fonctions:

/rest/tp2/connect 	Se connecter au serveur REST/FTP
/rest/tp2/dir/disconnect 	Se déconnecter du serveur
/rest/tp2/dir/list 	lister le répertoire courant
/rest/tp2/dir/list/a 	lister le répertoire "a"
/rest/tp2/dir/cd/a 	Se déplacer dans le repertoire distant "a"
/rest/tp2/dir/cdup 	Se déplacer dans le répertoire parent
/rest/tp2/dir/mkdir/a 	Créer un nouveau répertoire "a"
/rest/tp2/dir/pwd 	Afficher le chemin absolu
/rest/tp2/dir/delete/a 	Supprimer le répertoire "a"
/rest/tp2/dir/list/rename/a?newName=b 	Renommer le nom du répertoire a en b
/rest/tp2/dir/getFile/a 	Télécharger le fichier a
/rest/tp2/dir/sendFile/a 	uploader le fichier "a" dans le serveur FTP


