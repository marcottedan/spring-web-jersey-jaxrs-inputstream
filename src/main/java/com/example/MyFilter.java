package com.example;

import org.glassfish.jersey.server.ContainerRequest;
import org.springframework.stereotype.Component;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Form;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Component
public class MyFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext ctx) throws IOException {

        System.err.println("I'm in the filter");

        // Solution #1 doesn't work
        if (ctx instanceof ContainerRequest) {
            ContainerRequest request = (ContainerRequest) ctx;

            request.bufferEntity();
            Form f = request.readEntity(Form.class);
            System.err.println(f.asMap().toString());
        }

        // Solution #2 doesn't work either
        InputStream inputStream = ctx.getEntityStream();
        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader
                (inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }
        System.err.println(textBuilder.toString());
    }
}
