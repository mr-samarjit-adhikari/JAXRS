package helloworld;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

@ApplicationPath("/resources")
public class MyApplication extends ResourceConfig {
    
    public MyApplication() {
        super(Hello.class);
        register(RolesAllowedDynamicFeature.class); ///Specific to jersey
    }
}