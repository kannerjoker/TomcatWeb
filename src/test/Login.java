package test;

public class Login {
    private int id;
    private String user;
    private String passwd;
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setUser(String user){
        this.user = user;
    }
    public String getUser(){
        return this.user;
    }
    public void setPasswd(String passwd){
        this.passwd = passwd;
    }
    public String getPasswd(){
        return this.passwd;
    }
    public String toString(){
        return "id: " + this.id + "," +
                "user: " + this.user + "," +
                "passwd: " + this.passwd;
    }
}
