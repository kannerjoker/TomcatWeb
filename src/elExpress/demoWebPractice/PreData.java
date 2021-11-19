package elExpress.demoWebPractice;

import elExpress.test.PrePareTestSource;
import org.testng.annotations.Test;

public class PreData {
    @Test
    public void preData(){
        PrePareTestSource pts = new PrePareTestSource();
        pts.createDataSource();
        pts.insertData();
    }
}
