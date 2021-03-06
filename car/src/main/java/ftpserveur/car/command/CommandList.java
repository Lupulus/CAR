package ftpserveur.car.command;

import ftpserveur.car.request.FtpRequest;

public class CommandList extends Command{

	public CommandList(FtpRequest ftp) {
		super(ftp);
	}
	
	@Override
	public boolean process(String arg){
		String[] files = ftp.getCurrentDirectory().list();
		if(files == null) return false;
		for(int i = 0; i<files.length; i++){
			ftp.getOut().println(files[i]);
			ftp.getOut().flush();
		}
		return true;
	}

}
