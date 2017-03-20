package car.tp2.ftpclient;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.io.File;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;


public  class FtpExec {
	
FTPClient client;
	
	
	public FtpExec(){
		this.client= new FTPClient();
	
	}
	
	/*
	 * Connexion au serveur FTP
	 * à l'adresse localhost et au port 2121
	 * @param login
	 * @param password
	 * @return
	 */
	public String connect(String pseudo, String password) throws SocketException, IOException{
		
		this.client.connect("localhost",2121);	
		
		 if(this.client.login(pseudo, password))
			 return "Connection accepted to client "+pseudo;
		 else
			 return "Connection deny to client "+pseudo;			
		
	}
	
	/*
	 * Liste le contenu du repertoire
	 * @param repertoire
	 * @return
	 */
	public String ls(String directory) throws IOException{
		
		FTPFile[] files = this.client.listFiles(directory);	
		   String ret="";
					if(files.length==0)
						return "<p>Dosssier vide</p>";
					else{
						
						for(int i = 0; i<files.length;i++){

							ret+="<p>"+files[i].getName() +"</p>";
						}
					}
								
	     return ret;	
	}
	
	/*
	 * Cree un nouveau repertoire
	 * @param chemin repertoire
	 * @return
	 */
	public String mkdir(String newFile) throws SocketException, IOException{
	
		this.client.mkd(newFile);
		return "Make new directory "+newFile+" ok !";
		
		
		
	}
	/*
	 * Renomme un  fichier ou un repertoire
	 * @param oldName repertoire ou fichier
	 * @param newName le nouveau  nom
	 * @return
	 */
	public String renameFile(String oldName, String newName) throws SocketException, IOException{
		
		this.client.rename(oldName, newName);
		return "Rename file  "+oldName+" to "+newName+"\r\n";
		


	}
	/*
	 * Affiche le chemin absolu du repertoire courant
	 * @return
	 */
	public String pwd() throws SocketException, IOException{
		
		String path = this.client.printWorkingDirectory();
		 System.out.println("Absolute path:  "+path+"\r\n");
		 return path;
	}
	/*
	 * Deplace au repertoire parent
	 * @return
	 */
	public String cdup() throws SocketException, IOException{
			
		
		int response = this.client.cdup(); // renvoie le numero de la reponse, si ok 250
		if(response==250)
			 return "cdup ok";
		 
        return "cdup deny";		
	
	}
	/*
	 * Deplace le repertoire courant
	 * @param repertoire
	 * @return
	 */
	public String cd(String directory) throws  IOException{
		
		if(this.client.changeWorkingDirectory(directory))
			return "changing directory "+directory;
		return "Not changing directory";
	}
	/*
	 * Recuperer un fichier depuis le serveur FTP
	 * @param file le fichier
	 * @return resultat de la requete
	 */
	public String getFile(String file) throws SocketException, IOException{
	
		boolean success;
		OutputStream output = new BufferedOutputStream(new FileOutputStream(file));
		this.client.enterLocalPassiveMode();
		this.client.setFileType(FTP.BINARY_FILE_TYPE);
		
		InputStream input = this.client.retrieveFileStream(file);
		String result=null;
        byte[] bytesArray = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = input.read(bytesArray)) != -1) {
            output.write(bytesArray, 0, bytesRead);
        }

        success = this.client.completePendingCommand();
		if (success)
           result=  "File "+file+" has been downloaded successfully.";
        
        output.close();
        input.close();
        return result;

	}
	/*
	 * Upload un fichier sur le serveur FTP
	 * @param fichier
	 * @return
	 */
	 public String sendFile(String file) throws IOException{
		 File f= new File(file);
		 InputStream input = new FileInputStream(f);
		 this.client.enterLocalPassiveMode();
		 this.client.setFileType(FTP.BINARY_FILE_TYPE);

         OutputStream output = this.client.storeFileStream(this.client.printWorkingDirectory()+file);
         byte[] bytesIn = new byte[4096];
         int read = 0;
         while ((read = input.read(bytesIn)) != -1) {
             output.write(bytesIn, 0, read);
         }
         
         boolean success = this.client.completePendingCommand();
         input.close();
         output.close();
         
         if(success)
        	 return "File "+file+"is send to the server";
         return "Sending deny";
	 }
	 
	
	/*
	 * Supprime un fichier ou repertoire
	 * @param repertoire ou fichier
	 * @return
	 */
	public String deleteFile(String pathname) throws IOException{
		
		if(this.client.deleteFile(pathname))
			return "File deleted";
		return "File not deleted";
	}

	/*
	 * Deconnexion au serveur
	 * @return
	 */
	public String disconnet() throws IOException {
		
		this.client.disconnect();
		return "Deconnexion réussie";
	}
	
	public String getCurrentDirectory() throws IOException{
		return this.client.printWorkingDirectory();
}
	
}
