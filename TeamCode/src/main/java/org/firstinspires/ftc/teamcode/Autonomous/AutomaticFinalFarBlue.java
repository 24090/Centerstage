package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.hardware.dfrobot.HuskyLens;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.MotorController;
import org.firstinspires.ftc.teamcode.subsystems.Positioning;


@Autonomous()
public class AutomaticFinalFarBlue extends LinearOpMode {
    MotorController motorController;
    Positioning positioning;
    private String positionOfPixel = "";
    HuskyLens huskyLens;
    boolean autoComplete = false;
    @Override
    public void runOpMode() {
        huskyLens = hardwareMap.get(HuskyLens.class, "HuskyLens");
        if (!huskyLens.knock()) {
            telemetry.addData(">>", "Problem communicating with " + huskyLens.getDeviceName());
        } else {
            telemetry.addData(">>", "Press start to continue");
        }
        huskyLens.selectAlgorithm(HuskyLens.Algorithm.COLOR_RECOGNITION);
        motorController = new MotorController(this);
        positioning = new Positioning(this);
        positioning.startPositionUpdater();
        motorController.init(hardwareMap);
        telemetry.update();
        while (opModeIsActive() && positionOfPixel == "") {
            HuskyLens.Block[] blocks = huskyLens.blocks();
            for (int i = 0; i < blocks.length; i++) {
                telemetry.addData("Block", blocks[i].toString());
                if (blocks[i].id == 2) {
                    if ((130 < blocks[i].x && blocks[i].x < 180) && (150 < blocks[i].y && blocks[i].y < 200)) {
                        positionOfPixel = "middle";
                        telemetry.addData("Position", positionOfPixel);
                    }
                    if ((260 < blocks[i].x && blocks[i].x < 310) && (180 < blocks[i].y && blocks[i].y < 200)) {
                        positionOfPixel = "right";
                        telemetry.addData("Position", positionOfPixel);
                    }
                    if ((10 < blocks[i].x && blocks[i].x < 50) && (180 < blocks[i].y && blocks[i].y < 200)) {
                        positionOfPixel = "left";
                        telemetry.addData("Position", positionOfPixel);
                    }
                }
            }
        }
            waitForStart();
            telemetry.update();
            if (opModeIsActive()){
                motorController.servo1SetPosition(0.23);
                sleep(800);
                switch (positionOfPixel){
                    case "middle":
                        motorController.masterMotorRotate(2.7,2.7,2.7,2.7);
                        motorController.servo1SetPosition(0.65);
                        sleep(800);
                        motorController.masterMotorRotate(-0.5,-0.5,-0.5,-0.5);
                        motorController.masterMotorRotate(2.15,-2.15,2.15,-2.15);
                        motorController.masterMotorRotate(2.0,2.0,2.0,2.0);
                        break;
                    case "left":
                        motorController.masterMotorRotate(2,2,2,2);
                        motorController.masterMotorRotate(1.5, 1.5, -1.5,-1.5);
                        motorController.servo1SetPosition(0.65);
                        sleep(800);
                        motorController.masterMotorRotate(0.7,0.7,0.7,0.7);
                        motorController.masterMotorRotate(-0.7,-0.7,-0.7,-0.7);
                        motorController.masterMotorRotate(-2.2,2.2,-2.2,2.2);
                        break;
                    case "right":
                        motorController.masterMotorRotate(2.3,2.3,2.3,2.3);
                        motorController.masterMotorRotate(-1.5, -1.5, 1.5,1.5);
                        motorController.servo1SetPosition(0.65);
                        sleep(800);
                        motorController.masterMotorRotate(0.7,0.7,0.7,0.7);
                        motorController.masterMotorRotate(-0.7,-0.7,-0.7,-0.7);
                        motorController.masterMotorRotate(2.2,-2.2,2.2,-2.2);
                        break;
                }
            }
        }
}

