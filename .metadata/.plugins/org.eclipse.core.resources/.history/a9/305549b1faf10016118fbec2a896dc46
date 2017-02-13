package Command;

import Main.FtpRequest;

public class CommandQuit extends Command{

	public CommandQuit(FtpRequest ftp) {
		super(ftp);
		process();
	}
	
	public void process(){
		super.send(221, "Deconnexion");
		try{
			ftp.getIn().close();
			ftp.getOut().close();
			ftp.getSocket().close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
