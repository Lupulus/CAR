package car.tp2.ftpclient;

import javax.ws.rs.core.Response;

public interface FtpRequest {
	public Response exec(FtpExec executor) throws Exception;
}
