package com.dst.dbparser;

import com.dst.dbparser.locarus.MotorSpeedHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MotorSpeedHandlerTest {

    @Test
    public void getSpeed(){
        // Должно быть кратно 16
        int speed1 = 3408;
        int speed2 = 2576;

//        int speed1shifted = (speed1 + 8000) / 16;
//        int speed2shifted = (speed2 + 8000) / 16;

        double sp1 = new MotorSpeedHandler(false, 32969).getSpeed();
        double sp2 = new MotorSpeedHandler(true, 9765376).getSpeed();

        Assertions.assertEquals(speed1, sp1);
        Assertions.assertEquals(speed2, sp2);
    }
}
