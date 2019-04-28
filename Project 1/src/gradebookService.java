import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Path("/gradebook/")
public class gradebookService {

    private static ConcurrentHashMap<String, Student> studentDB = new ConcurrentHashMap<String, Student>();
    private static AtomicInteger idCounter = new AtomicInteger();

    @GET
    // @Produces("application/xml")
    @Produces("application/text")
    public String getStudents(){
        return "Hello Gradebook!";
    }
    
}
