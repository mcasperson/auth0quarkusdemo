package org.matthewcasperson;

import io.quarkus.oidc.IdToken;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/")
public class GreetingResource {

    @Inject
    @IdToken
    JsonWebToken idToken;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String hello() {
        return
                "<html>\n" +
                "    <body>\n" +
                "        <h1>Hello " + idToken.getClaim("email") + "</h1>\n" +
                "    </body>\n"+
                "</html>\n";
    }
}