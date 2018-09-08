package pe.com.gadolfolozano.sisenrollment;

import org.junit.Test;

import pe.com.gadolfolozano.sisenrollment.util.StringUtil;

import static org.junit.Assert.assertEquals;

/**
 * Created by adolfo on 7/09/18.
 */

public class StringUtilTest {
    @Test
    public void testFormatString() throws Exception {
        assertEquals("4", StringUtil.formatString("#", "4"));
        assertEquals("4.3", StringUtil.formatString("#.##", "43"));
        assertEquals("1", StringUtil.formatString("###.###.###-##", "1"));
        assertEquals("12", StringUtil.formatString("###.###.###-##", "12"));
        assertEquals("123", StringUtil.formatString("###.###.###-##", "123"));
        assertEquals("123.4", StringUtil.formatString("###.###.###-##", "1234"));
        assertEquals("123.45", StringUtil.formatString("###.###.###-##", "12345"));
        assertEquals("123.456", StringUtil.formatString("###.###.###-##", "123456"));
        assertEquals("123.456.7", StringUtil.formatString("###.###.###-##", "1234567"));
        assertEquals("123.456.78", StringUtil.formatString("###.###.###-##", "12345678"));
        assertEquals("123.456.789", StringUtil.formatString("###.###.###-##", "123456789"));
        assertEquals("123.456.789-0", StringUtil.formatString("###.###.###-##", "1234567890"));
        assertEquals("123.456.789-01", StringUtil.formatString("###.###.###-##", "123-4567........8.901"));
    }
}
