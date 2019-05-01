import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ConcurrentHashMap;

@Path("/secondary/")
public class secondaryService {

    private static ConcurrentHashMap<String, Gradebook> secondaryDB = new ConcurrentHashMap<String, Gradebook>();

    @POST
    @Path("{name}")
    public Response getGradeBookGrade(@PathParam("name") String name) throws UnsupportedEncodingException {
        return addUpdateGradeBook(name);
    }

    @PUT
    @Path("{name}")
    public Response updateGradeBookGrade(@PathParam("name") String name) throws UnsupportedEncodingException{
        return addUpdateGradeBook(name);
    }

    private Response addUpdateGradeBook(String Name) throws UnsupportedEncodingException{
        // String _grade = helpers.DecodeValue(Grade);
        String _name = helpers.DecodeValue(Name);

        Gradebook _gradeBook;

        if(!secondaryDB.containsKey(_name)){
            _gradeBook = new Gradebook("primary", _name);
            secondaryDB.putIfAbsent(_name, _gradeBook);
        }else{
            return Response.status(400).build();
        }

        return Response.status(200).build();
    }
}
