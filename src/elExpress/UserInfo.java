package elExpress;

public class UserInfo {
    private int num;
    private String name;
    private String gender;
    private int age;
    private String birthPlace;
    private String qq;
    private String email;

    public UserInfo(){}

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getBirthPlace() {
        return birthPlace;
    }
    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getQQ() {
        return qq;
    }
    public void setQQ(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }

    public void setName(String na){
        this.name = na;
    }
    public String getName(){
        return this.name;
    }
}
