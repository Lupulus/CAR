package ftpserveur.car;

import java.util.HashMap;


	public class Information{
		
		private int port;
		private HashMap<String, String> users;
		
		public Information(){
			this.port = 2250;
			this.users = new HashMap<String, String>();
			this.users.put("timothee", "pass1234");
			this.users.put("hajar", "pwd4321");
		}
		public int getPortServor(){
			return this.port;
		}
		
		public HashMap<String, String> getUsers(){
			return this.users;
		}
		
	}

