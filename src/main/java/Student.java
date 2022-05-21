public class Student {
    private int id;
    private String name;
    private int age;
    private String language;

    public Student() {
    }

    public Student(int id, String name, int age, String language) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
