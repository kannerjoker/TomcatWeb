package elExpress.test;

import elExpress.JdbcUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class PrePareTestSource {
    String str = "";
    int length = 100;
    private static JdbcUtils ju;
    private static JdbcTemplate jt;
    static Connection conn;
    static{
        ju = new JdbcUtils();
        jt = new JdbcTemplate(ju.getDataSource());
        conn = ju.getConnection();
    }

    public static void main(String[] args) {
        PrePareTestSource pts = new PrePareTestSource();
        pts.createDataSource();
        pts.insertData();

        pts.preName();
        pts.preGender();
        pts.preAge();
        pts.preBirthPlace();
        pts.preQQ();
        pts.preEmail();
    }

    public void createDataSource(){
        try {
            conn.prepareStatement("use mysqlPractice;").execute();
            ResultSet rs = conn.createStatement().executeQuery("show tables;");
            boolean isUser = false;
            while(rs.next()){
                String s = rs.getString(1);
                if("User".equals(s)){
                    isUser = true;
                    break;
                }
            }

            if(isUser){
                conn.prepareStatement("drop table User;").execute();
            }
            String sql = "create table if not exists User(num int primary key auto_increment,name varchar(20),gender varchar(6),\n" +
                    "                       age int,birthPlace varchar(15),qq varchar(15),email varchar(30));";
            conn.prepareStatement(sql).execute();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String preName(){
        str = "";
        for (int i = 0; i < length; i++) {
            if(i==length){
                str += "'test" + (i+1) + "'";
                break;
            }
            str += "'test" + (i+1) + "',";
        }
        return str;
    }
    public String preGender(){
        str = "";
        for (int i = 0; i < length; i++) {
            if(i==length-1){
                str += "'male'";
                break;
            }
            if(i%2==0){
                str += "'female',";
            }else{
                str += "'male',";
            }
        }
        return str;
    }
    public String preAge(){
        str = "";
        for (int i = 0; i < length; i++) {
            if(i==length-1){
                str += "1" + i;
                break;
            }
            str += "1" + i +",";
        }
        return str;
    }
    public String preBirthPlace(){
        Random ra = new Random();
        str = "";
        for (int i = 0; i < length; i++) {
            int nu1 = ra.nextInt(26)+65;
            String s = (char)nu1 + "";
            for (int j = 0; j < 5; j++) {
                int nu2 = ra.nextInt(26)+97;
                s += (char)nu2;
            }
            if(i==length-1){
                str += "'" + s + "'";
                break;
            }
            str += "'" + s + "',";
        }
        return str;
    }
    public String preQQ(){
        str = "";
        Random ra = new Random();
        int len = ra.nextInt(2) + 8;
        for (int i = 0; i < length; i++) {
            String s = "";
            for (int j = 0; j < len; j++) {
                int num = ra.nextInt(10);
                if(j==0&&num==0){
                    j--;
                    continue;
                }
                s += num;
            }
            if(i==length-1){
                str +=  "'" + s +"'";
                break;
            }
            str += "'" + s +"',";
        }
        return str;
    }
    public String preEmail(){
        str = "";
        String s = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random ra = new Random();
        //10条数据
        for (int i = 0; i < length; i++) {
            String ss = "";
            //每条数据长
            for (int j = 0; j < 18; j++) {
                ss += s.charAt(ra.nextInt(s.length()));
            }
            if(i==length-1){
                str += "'" + ss + "/@qq.com'";
                break;
            }
            str += "'" + ss + "@qq.com',";
        }
        return str;
    }
    @Test
    public void insertData(){
        try {
            String sql;
            String[] arrName = this.preName().split(",");
            String[] arrGender = this.preGender().split(",");
            String[] arrAge = this.preAge().split(",");
            String[] arrBirth = this.preBirthPlace().split(",");
            String[] arrQQ = this.preQQ().split(",");
            String[] arrEmail = this.preEmail().split(",");

            PreparedStatement ps = null;
            for (int i = 1; i <= length; i++) {
                sql = "insert into User values(" + i + ","  + arrName[i-1] + "," + arrGender[i-1] + "," + Integer.valueOf(arrAge[i-1]) + ","
                        + arrBirth[i-1] + "," + arrQQ[i-1] + "," + arrEmail[i-1] + ");";
                System.out.println(sql);
                conn = ju.getConnection();
                ps = conn.prepareStatement(sql);
                ps.execute();
                conn.close();
                ps.close();
            }

            System.out.println("insertOk");
            ju.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } /*catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    }
}
