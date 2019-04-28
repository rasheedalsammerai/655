import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Path("/student/")
public class studentservice {
    private static ConcurrentHashMap<String, Student> studentDB = new ConcurrentHashMap<String, Student>();
    private static AtomicInteger idCounter = new AtomicInteger();

    @GET
    @Produces("application/xml")
    public Response getStudents(){
        return getStudentListResponse();
    }

    @GET
    @Path("{name}")
    @Produces("application/xml")
    public Response getStudent(@PathParam("name") String name) throws UnsupportedEncodingException {

        String decoded = helpers.DecodeValue(name);

        if(!studentDB.containsKey(decoded)){
            return Response.status(404).build();
            //_student = new Student(decoded);
            //studentDB.putIfAbsent(name, _student);
        }

        //Student student = studentDB.get(name);

        return getStudentResponse(studentDB.get(name));
    }

    @POST
    @Path("{name}/grade/{grade}")
    public Response getStudentGrade(@PathParam("name") String name, @PathParam("grade") String grade) throws UnsupportedEncodingException{
        return addUpdateStudent(name, grade);
    }

    @PUT
    @Path("{name}/grade/{grade}")
    public Response updateStudentGrade(@PathParam("name") String name, @PathParam("grade") String grade) throws UnsupportedEncodingException{
        return addUpdateStudent(name, grade);
    }

    private Response addUpdateStudent(String Name, String Grade) throws UnsupportedEncodingException{
        String _grade = helpers.DecodeValue(Grade);
        String _name = helpers.DecodeValue(Name);

        if(!helpers.isValidGrade(_grade.toUpperCase())){
            return Response.status(400).build();
        }

        Student _student;

        if(studentDB.containsKey(_name)){
            _student = studentDB.get(_name);
            _student.setGrade(_grade);

        }else{
            _student = new Student(_name, _grade);
            studentDB.putIfAbsent(_name, _student);
        }

        //return getStudentResponse(_student);
        return Response.status(200).build();
    }
    /*
    private boolean validGrade(String Grade){
        if(Grade.length() > 2) return false;

        System.out.println("Grade Received: (" + Grade + ")" + " Length: " + Grade.length());

        Pattern p = Pattern.compile("^([ABCDEFIWZ\\+\\-]{1,2})$");
        Matcher m = p.matcher(Grade);
        //boolean b = m.matches();
        if(m.matches()){
            Pattern p2 = Pattern.compile("(^[EFIWZ].)");
            Matcher m2 = p2.matcher(Grade);

            return !m2.matches();
        }
        return false;
    }
    */

    @DELETE
    @Path("{name}")
    public Response deleteStudent(@PathParam("name") String name) throws UnsupportedEncodingException {

        String decoded = helpers.DecodeValue(name);

        if(!studentDB.containsKey(decoded)){
            return Response.status(404).build();
        }
        studentDB.remove(decoded);
        //Student student = studentDB.get(name);

        //return getStudentResponse(studentDB.get(name));
        return Response.status(200).build();
    }

    private String getStudentXML(Student student) {
        StringBuilder _student = new StringBuilder(student.getXML());
        return getXMLwrapper(_student).toString();
    }

    private String getStudentListXML(){
        StringBuilder _studentList = new StringBuilder();

        //_studentList.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><student-list>");

        //for (int x = 0; x < studentDB.mappingCount(); x++) {
        for(String key : studentDB.keySet()){
            _studentList.append(studentDB.get(key).getXML());
        }

        //_studentList.append("</student-list>");

        return getXMLwrapper(_studentList).toString();
    }
/*
    private String decodeValue(String Value) throws UnsupportedEncodingException{
        System.out.println("Value Received: (" + Value + ")");
        String decoded = URLDecoder.decode(Value, StandardCharsets.UTF_8.toString());
        System.out.println("Decoded Value: (" + decoded + ")");

        //return decoded;
        return Value;
    }

 */
    private StringBuilder getXMLwrapper(StringBuilder XML){
        XML.insert(0, "<?xml version=\"1.0\" encoding=\"UTF-8\"?><student-list>").append("</student-list>");
        return XML;
    }


    private Response getStudentListResponse(){
        return Response.status(200).entity(getStudentListXML()).build();
    }

    private Response getStudentResponse(Student student){
        return Response.status(200).entity(getStudentXML(student)).build();
    }
}
