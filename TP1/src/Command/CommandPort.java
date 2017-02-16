package Command;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

import Request.FtpRequest;

public class CommandPort extends Command{
	

	public CommandPort(FtpRequest ftp){
		super(ftp);
	}
	
	@Override
	public boolean process(String arg){
		/**Integer[] infos = Arrays.stream(arg.split(","))
				.map(Integer::parseInt)
				.toArray(Integer[]::new);
		**/
		String[] infos = arg.split(",");
		
		Integer[] infosInt = new Integer[infos.length];
		
		for (int i = 0; i < infos.length; ++i) {
			infosInt[i] = Integer.parseInt(infos[i]);
		}
		
		
		int port = infosInt[4] * 256 + infosInt[5];
		String host = infosInt[0] + "." + infosInt[1] + "." + infosInt[2] + "." + infosInt[3];
		
		try {
			ftp.getRequest().setSocket(new Socket(InetAddress.getByName(host),port));
			send(getAnswer().get("2000"));
			return true;
		} catch (IOException e1) {
			send(getAnswer().get("425"));
			e1.printStackTrace();
			return false;
		} 
	}
}