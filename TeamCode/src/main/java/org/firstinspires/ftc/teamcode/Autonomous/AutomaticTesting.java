package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.hardware.dfrobot.HuskyLens;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.MotorController;


@Autonomous()
public class AutomaticTesting extends LinearOpMode {
    MotorController motorController;
    private String positionOfPixel = "";
    HuskyLens huskyLens;
    boolean autoComplete = false;
    @Override
    public void runOpMode() {
        while (positionOfPixel == ""){
            if (gamepad1.left_bumper) {
                positionOfPixel = "left";
            }
            if (gamepad1.right_bumper) {
                positionOfPixel = "right";
            }
            if (gamepad1.b) {
                positionOfPixel = "middle";
            }

        }
        waitForStart();
        if (opModeIsActive()){
            motorController.servo1SetPosition(0.23);
            sleep(800);
            switch (positionOfPixel) {
                case "middle":
                    break;
                case "left":
                    break;
                case "right":
                    break;
            }
        }
    }
}

