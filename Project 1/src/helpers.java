import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class helpers {

    public static String DecodeValue(String Value) throws UnsupportedEncodingException {
        System.out.println("Value Received: (" + Value + ")");
        String decoded = URLDecoder.decode(Value, StandardCharsets.UTF_8.toString());
        System.out.println("Decoded Value: (" + decoded + ")");

        //return decoded;
        return Value;
    }

    public static boolean isValidGrade(String Grade){
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
    public static StringBuilder getXMLwrapper(StringBuilder XML, String ListObjectType){
        XML.insert(0, "<?xml version=\"1.0\" encoding=\"UTF-8\"?><"+ ListObjectType + ">").append("</" + ListObjectType + ">");
        return XML;
    }
}
