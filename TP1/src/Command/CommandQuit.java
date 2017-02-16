package Command;

import Request.FtpRequest;

public class CommandQuit extends Command{

	public CommandQuit(FtpRequest ftp) {
		super(ftp);
	}
	
	@Override
	public boolean process(String arg){
		send(getAnswer().get("221"));
		try{
			ftp.getIn().close();
			ftp.getOut().close();
			ftp.getSocket().close();
			ftp.setIsClosed(true);
			return true;		
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
