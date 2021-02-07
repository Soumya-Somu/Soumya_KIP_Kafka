public class User {

    private String id;
    private String name;
    private int age;
    private String course;

    public User() {

    }

    //create a user function to get the values
    public User(String  id ,String name, int age,String course) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    //fetch the id and return it
    public String getID() {
        return this.id;
    }

    //fetch the name and return it
    public String getName() {
        return this.name;
    }

    //fetch the age and return it
    public int getAge() {
        return this.age;
    }

    //fetch the course and return it
    public String getCourse()
    {
        return this.course;
    }

    public String toString() {
        return "{id=" +id+ ", name='" + name + "', age=" + age + ", course=" + course + "}";
    }
}