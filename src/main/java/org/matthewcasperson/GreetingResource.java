package org.matthewcasperson;

import io.quarkus.oidc.IdToken;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @Inject
    @IdToken
    JsonWebToken idToken;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String hello() {
        String html = """
                <html>
                    <body>
                        <h1>Hello %s</h1>
                    </body>
                </html>
                """;
        return html.formatted(idToken.getClaim("email").toString());
    }
}