public class Student {
    private int id;
    private String name;
    private String grade;

    public Student(String Name) {
        this.setName(Name);
    }

    public Student(String Name, String Grade) {
        this(Name);
        this.setGrade(Grade);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getXML(){
        return "<student>\n" +
                "\t\t<grade>\n" +
                this.getGrade() +
                "\t\t</grade>\n" +
                "\t\t<name>\n" +
                this.getName() +
                "\t\t</name>\n" +
                "\t</student>";
    }
}
