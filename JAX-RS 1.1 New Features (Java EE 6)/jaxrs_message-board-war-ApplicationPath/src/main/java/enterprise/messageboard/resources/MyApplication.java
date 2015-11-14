package enterprise.messageboard.resources;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

// By default, all root resource classes and providers packaged 
// with the Application subclass are included in the application. 
//
// The ApplicationPath annotation specifies the base 
// URI path segment to which all root resource class 
// URIs are relative. 
@ApplicationPath("app")
public class MyApplication extends Application {
}
