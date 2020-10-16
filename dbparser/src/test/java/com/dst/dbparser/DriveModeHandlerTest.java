package com.dst.dbparser;

import com.dst.dbparser.locarus.DriveModeHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DriveModeHandlerTest {

    @Test
    public void getMode() {
        double one = new DriveModeHandler(128).getMode();
        double two = new DriveModeHandler(32768).getMode();
        double three = new DriveModeHandler(32896).getMode();
        double four = new DriveModeHandler(8388608).getMode();
        double five = new DriveModeHandler(8388736).getMode();

        Assertions.assertEquals(1, one);
        Assertions.assertEquals(2, two);
        Assertions.assertEquals(3, three);
        Assertions.assertEquals(4, four);
        Assertions.assertEquals(5, five);
    }

}
