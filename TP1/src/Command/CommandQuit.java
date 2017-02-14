package Command;

import Request.FtpRequest;

public class CommandQuit extends Command{

	public CommandQuit(FtpRequest ftp) {
		super(ftp);
		process();
	}
	
	public void process(){
		super.send(221, "Deconnexion");
		try{
			if(ftp.getIsConnected()){
				ftp.getIn().close();
				ftp.getOut().close();
				ftp.send(221, "Connection closed");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
