package com.example;

import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Component
@Path("/hello")
public class HelloController {

    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @POST
    public String onHelloRequest(@FormParam("foo") String foo) {
        System.out.println(foo);
        return foo + "bar";
    }
}
