package web.rest;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import web.rest.services.PosicaoRestService;

@ApplicationPath("/LP3Rest")
public class AppRest extends Application {

    private Set<Object> services;

    public AppRest() {
        services = new HashSet<Object>();
        services.add(new PosicaoRestService());
    }
}
