import java.util.concurrent.ConcurrentHashMap;

public class Gradebook {

    private String _id;
    private String _name;
    private ConcurrentHashMap<String, Student> _students;

    public Gradebook() {
        _students = new ConcurrentHashMap<String, Student>();
    }

    public Gradebook(String Gradebook, String _name) {
        this.setName(_name);
        this.setID(Gradebook);
    }

    public Gradebook(String _name) {
        this.setName(_name);
    }

    public String getName() {
        return _name;
    }

    public void setName(String Name) {
        this._name = Name;
    }

    public String getID() {
        return _id;
    }

    public void setID(String Gradebook) {
        this._id = Math.abs((this._name + Gradebook).hashCode()) + "";
    }

    public ConcurrentHashMap<String, Student> getStudents() {
        return _students;
    }

    public void setStudents(ConcurrentHashMap<String, Student> Students) {
        this._students = Students;
    }

    public String getXML(){
        return "<Gradebook>\n" +
                "\t\t<id>\n" +
                this.getID() +
                "\t\t</id>\n" +
                "\t\t<name>\n" +
                this.getName() +
                "\t\t</name>\n" +
                "\t</Gradebook>";
    }


}
