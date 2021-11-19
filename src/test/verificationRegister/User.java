package test.verificationRegister;

public class User {
    String id;
    String name;
    String passwd;
    String number;
    public User(){}

    public void setNum(String number){
        this.number = number;
    }
    public String getNum(){
        return this.number;
    }


    public String getId(){
        return this.id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    public String toString(){
        return this.name + " : " + this.passwd;
    }
}
