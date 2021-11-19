package ascTest;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestAsc {
    public static void main(String[] args) {
        String originalSrc = "E:\\Users\\kan\\Desktop\\截取\\Daf\\20210929\\2021-03-10-18-05-02\\21\\2021-03-10-18-05-02_F.csv";
        String trimSrc = "E:\\Users\\kan\\Desktop\\截取\\Daf\\20210929\\2021-03-10-18-05-02\\21\\2021-03-10-18-05-02.csv";
        List<String> originalAsc = new ArrayList<>();
        List<String> trimAsc = new ArrayList<>();
        TestAsc ta = new TestAsc();
        //读取
        List<String> originalList = ta.getAsc(1,new File(originalSrc));
        //排序
        originalList = ta.sortAsc(originalList);
        System.out.println("-------------------------------");
        //输出
        ta.buildAsc(ta.getFinalAsc(originalList),"E:\\Users\\kan\\Desktop\\截取\\Daf\\20210929\\2021-03-10-18-05-02\\21\\2021-03-10-18-05-02_F.asc");
        System.out.println("-------------------------------");
        /*for (String s : originalList) {
            System.out.println(s);
        }*/
        System.out.println("-------------------------------");
        //读取
        List<String> trimList = ta.getAsc(0,new File(trimSrc));
        //排序
        trimList = ta.sortAsc(trimList);
        System.out.println("-------------------------------");
        //输出
        ta.buildAsc(ta.getFinalAsc(trimList),"E:\\Users\\kan\\Desktop\\截取\\Daf\\20210929\\2021-03-10-18-05-02\\21\\2021-03-10-18-05-02.asc");
    }

    public List<String> getAsc(int offset,File file){
        BufferedReader br = null;
        List<String> list = new ArrayList();
        try {
            br = new BufferedReader(new FileReader(file));
            String str;
            String[] arrStrs;
            long count = 0;
            while((str=br.readLine())!=null){
                count++;
                //排除5行头数据
                if(count > 5){
                    arrStrs = str.split(" ");
                    str = "";
                    //排除1行尾数据
                    if(arrStrs.length >2){
                        for (int i = 0; i < arrStrs.length-3-offset; i++) {
                            if(i == arrStrs.length-3-offset -1){
                                str += arrStrs[i];
                                break;
                            }
                            str += arrStrs[i] + " ";
                        }
                        list.add(str);
//                        System.out.println(str);
                    }
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return list;
    }
    public List<String> sortAsc(List<String> list){
        Collections.sort(list,new Comparator<String>(){
            @Override
            public int compare(String s1,String s2){
                Double a1 = Double.valueOf(s1.split(" ")[0]);
                Double a2 = Double.valueOf(s2.split(" ")[0]);
                if(a1>a2){
                    return 1;
                }else if(a1 == a2){
                    return 0;
                }else{
                    return -1;
                }
            }
        });
        return list;
    }
    public List<String> getFinalAsc(List<String> list){
        List newList = new ArrayList();
        String str;
        String[] arrStrs;
        for (int i = 0; i < list.size(); i++) {
            str = "";
            arrStrs = list.get(i).split(" ");
            for (int j = 1; j < arrStrs.length; j++) {
                if(j == arrStrs.length - 1){
                    str += arrStrs[j];
                    break;
                }
                str += arrStrs[j] + " ";
            }
            newList.add(str);
        }
        return newList;
    }
    public void buildAsc(List<String> list,String file){
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(new File(file)));
            for (String s : list) {
                bw.write(s + ",");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
