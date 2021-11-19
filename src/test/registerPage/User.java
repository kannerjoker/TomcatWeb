package test.registerPage;

public class User {
    private String id;
    private String username;
    private String password;
    public User(){}

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }
    public String toString(){
        return "id: " + id + "," +
                "username: " + username + "," +
                "password: " + password;
    }
}
