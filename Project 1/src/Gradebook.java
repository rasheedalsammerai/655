import java.util.concurrent.ConcurrentHashMap;

public class Gradebook {

    private int _id;
    private String _name;
    private ConcurrentHashMap<String, Student> _students;

    public Gradebook() {
        _students = new ConcurrentHashMap<String, Student>();
    }

    public Gradebook(int _id, String _name) {
        this.setID(_id);
        this.setName(_name);
    }

    public String getName() {
        return _name;
    }

    public void setName(String Name) {
        this._name = Name;
    }

    public int getID() {
        return _id;
    }

    public void setID(int ID) {
        this._id = ID;
    }

    public ConcurrentHashMap<String, Student> getStudents() {
        return _students;
    }

    public void setStudents(ConcurrentHashMap<String, Student> Students) {
        this._students = Students;
    }
}
