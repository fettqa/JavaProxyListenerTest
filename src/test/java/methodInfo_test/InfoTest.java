package methodInfo_test;


import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
@TestMethodInfo(priority = Priority.Critical, author = "Test user", lastModified = "20.08.2019")
public class InfoTest {

    public void annotation() {

        assertEquals(1, 1);
    }

}

