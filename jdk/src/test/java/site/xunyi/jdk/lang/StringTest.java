package site.xunyi.jdk.lang;

import org.junit.jupiter.api.Test;

public class StringTest {
    @Test
    public void testEquals(){
        // null instanceof String == false
        assert !"".equals((String) null);
    }


}
