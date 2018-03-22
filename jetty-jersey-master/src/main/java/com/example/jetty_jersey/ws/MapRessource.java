package com.example.jetty_jersey.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/map")
public class MapRessource {

	public static class LoginClass {
		public String name;
		public String id;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/user")
	public LoginClass getLogin() {
		LoginClass instance = new LoginClass();
		instance.name = "hotel";
		instance.id = "1";

		return instance;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/pass")
	public void retrieveLogin(LoginClass instance) {
		System.out.println(instance.name);
		System.out.println(instance.id);
	}

}
