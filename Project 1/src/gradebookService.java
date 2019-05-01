import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Path("/gradebook/")
public class gradebookService {

    private static ConcurrentHashMap<String, Gradebook> gradeBookDB = new ConcurrentHashMap<String, Gradebook>();

    @GET
     @Produces("application/xml")
    //@Produces("application/text")
    public String getGradeBooks(){
       // return "Hello Gradebook!";
        return getGradeBookListXML();
    }

    /*
    @GET
    @Path("{name}")
    @Produces("application/xml")
    public Response getGradeBook(@PathParam("name") String name) throws UnsupportedEncodingException {

        String decoded = name;//decodeValue(name);

        if(!gradeBookDB.containsKey(decoded)){
            return Response.status(404).build();
            //_gradeBook = new GradeBook(decoded);
            //gradeBookDB.putIfAbsent(name, _gradeBook);
        }

        //GradeBook gradeBook = gradeBookDB.get(name);

        return getGradeBookResponse(gradeBookDB.get(name));
    }
*/
    @POST
    @Path("{name}")
    public Response getGradeBookGrade(@PathParam("name") String name) throws UnsupportedEncodingException{
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

        if(!gradeBookDB.containsKey(_name)){
            _gradeBook = new Gradebook("primary", _name);
            gradeBookDB.putIfAbsent(_name, _gradeBook);
        }else{
            return Response.status(400).build();
        }

        //return getGradeBookResponse(_gradeBook);
        return Response.status(200).build();
    }

    private Response getGradeBookListResponse(){
        return Response.status(200).entity(getGradeBookListXML()).build();
    }

    private String getGradeBookListXML(){
        StringBuilder _gradeBookList = new StringBuilder();

        //_gradeBookList.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><student-list>");

        //for (int x = 0; x < studentDB.mappingCount(); x++) {
        for(String key : gradeBookDB.keySet()){
            _gradeBookList.append(gradeBookDB.get(key).getXML());
        }

        //_gradeBookList.append("</student-list>");

        return helpers.getXMLwrapper(_gradeBookList, "gradebook-list").toString();
    }

      /**/

}
