package main.java.rest.car.ftpclient;

import java.util.Arrays;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.jaxrs.utils.ExceptionUtils;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import main.java.rest.car.config.Configuration;

public class ResponseFactory {

	/**
	 * Permet de construire une réponse HTTP de base en JSON.
	 * @param builder Builder permettant de générer l'objet JSON.
	 * @param json Element JSON
	 * @return Une réponse HTTP sous la forme d'un objet JSON.
	 */
	public static Response jsonResponse(ResponseBuilder builder, JsonElement json) {
		return builder.entity(new GsonBuilder().serializeNulls().setPrettyPrinting().create().toJson(json))
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

	
	/**
	 * Permet de construire une réponse HTTP de base en JSON.
	 * @param httpStatus Status de la reponse HTTP.
	 * @param json Element JSON.
	 * @return Une réponse HTTP sous la forme d'un objet JSON.
	 */
	public static Response jsonResponse(Status httpStatus, JsonElement json) {
		return jsonResponse(Response.status(httpStatus), json);
	}
	
	/**
	 * Permet de construire une réponse HTTP en JSON avec une exception comme argument.
	 * @param builder Builder permettant de générer l'objet JSON.
	 * @param e Exception à encapsuler dans la réponse. 
	 * @return Une réponse HTTP sous la forme d'un objet JSON.
	 */
	public static Response jsonExceptionResponse(ResponseBuilder builder, Exception e) {
		JsonObject ftpResp = new JsonObject();
		ftpResp.addProperty("class", e.getClass().getName());
		ftpResp.addProperty("message", e.getMessage());
		if (Configuration.getConfig().isDebugMode()) {
			String[] stackTraceLines = ExceptionUtils.getStackTrace(e).split("\n");
			JsonArray stArray = new JsonArray();
			Arrays.stream(stackTraceLines)
					.map(String::trim)
					.forEach(stArray::add);
			ftpResp.add("details", stArray);
		}
		JsonObject root = new JsonObject();
		root.add("exception", ftpResp);
		return jsonResponse(builder, root);
	}
	

	/**
	 * Permet de construire une réponse HTTP en JSON avec une exception comme argument.
	 * @param httpStatus Status de la reponse HTTP.
	 * @param e Exception à encapsuler dans la réponse. 
	 * @return Une réponse HTTP sous la forme d'un objet JSON.
	 */
	public static Response jsonExceptionResponse(Status httpStatus, Exception e) {
		return jsonExceptionResponse(Response.status(httpStatus), e);
	}
	
	
	/**
	 * Permet de construire une réponse HTTP en JSON avec un code et des messages. 
	 * @param builder Builder permettant de générer l'objet JSON.
	 * @param code spécifie un code, celui ci sera mis dans la clé "code" de l'objet JSON.
	 * @param message spécifie les messages, ceci seront mis dans la clé "messages" de l'objet JSON.
	 * @return Une réponse HTTP sous la forme d'un objet JSON.
	 */
	public static Response jsonFTPResponse(ResponseBuilder builder, int code, String[] messages) {
		JsonObject ftpResp = new JsonObject();
		ftpResp.addProperty("code", code);
		JsonArray mArray = new JsonArray();
		Arrays.stream(messages)
				.map(String::trim)
				.forEach(mArray::add);
		ftpResp.add("messages", mArray);
		JsonObject root = new JsonObject();
		root.add("ftpResponse", ftpResp);
		return jsonResponse(builder, root);
	}
	
	/**
	 * Permet de construire une réponse HTTP en JSON avec un code et des messages. 
	 * @param httpStatus Status de la reponse HTTP.
	 * @param code spécifie un code, celui ci sera mis dans la clé "code" de l'objet JSON.
	 * @param message spécifie les messages, ceci seront mis dans la clé "messages" de l'objet JSON.
	 * @return Une réponse HTTP sous la forme d'un objet JSON.
	 */
	public static Response jsonFTPResponse(Status httpStatus, int code, String[] messages) {
		return jsonFTPResponse(Response.status(httpStatus), code, messages);
	}
	
}