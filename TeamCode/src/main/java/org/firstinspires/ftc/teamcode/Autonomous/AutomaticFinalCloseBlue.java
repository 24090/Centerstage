package org.firstinspires.ftc.teamcode.Autonomous;
import com.qualcomm.hardware.dfrobot.HuskyLens;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.MotorController;


@Autonomous()
public class AutomaticFinalCloseBlue extends LinearOpMode {
    MotorController motorController;
    private String positionOfPixel = "";
    HuskyLens huskyLens;
    @Override
    public void runOpMode() {
        huskyLens = hardwareMap.get(HuskyLens.class, "HuskyLens");
        if (!huskyLens.knock()) {
            telemetry.addData(">>", "Problem communicating with " + huskyLens.getDeviceName());
        } else {
            telemetry.addData(">>", "Press start to continue");
        }
        huskyLens.selectAlgorithm(HuskyLens.Algorithm.COLOR_RECOGNITION);
        motorController = new MotorController(this, hardwareMap);
        while (opModeIsActive() && positionOfPixel == "") {
            HuskyLens.Block[] blocks = huskyLens.blocks();
            for (HuskyLens.Block block : blocks) {
                telemetry.addData("Block", block.toString());
                if (block.id == 2) {
                    if ((130 < block.x && block.x < 180) && (150 < block.y && block.y < 200)) {
                        positionOfPixel = "middle";
                        telemetry.addData("Position", positionOfPixel);
                    }
                    if ((260 < block.x && block.x < 310) && (180 < block.y && block.y < 200)) {
                        positionOfPixel = "right";
                        telemetry.addData("Position", positionOfPixel);
                    }
                    if ((10 < block.x && block.x < 50) && (180 < block.y && block.y < 200)) {
                        positionOfPixel = "left";
                        telemetry.addData("Position", positionOfPixel);
                    }
                }
            }
            telemetry.update();
            waitForStart();
            }
            if (opModeIsActive()){
                motorController.servo1SetPosition(0.23);
                sleep(800);
                switch (positionOfPixel){
                    case "middle":
                        motorController.masterMotorRotate(2.5,2.5,2.5,2.5);
                        motorController.servo1SetPosition(0.65);
                        sleep(800);
                        motorController.masterMotorRotate(-0.7,-0.7,-0.7,-0.7);
                        sleep(800);
                        motorController.masterMotorRotate(-1.3,-1.3,1.3,1.3);
                        motorController.masterMotorRotate(-2.3,-2.3,-2.3,-2.3);
                        motorController.masterMotorRotate(-0.8,0.8,-0.8,0.8);
                        motorController.servo1SetPosition(0.9);
                        sleep(800);
                        sleep(800);
                        break;
                    case "left":
                        motorController.masterMotorRotate(2.3,2.3,2.3,2.3);
                        motorController.masterMotorRotate(1.5, 1.5, -1.5,-1.5);
                        motorController.servo1SetPosition(0.65);
                        sleep(800);
                        motorController.masterMotorRotate(0.6,0.6,0.6,0.6);
                        motorController.masterMotorRotate(-0.5,-0.5,-0.5,-0.5);
                        motorController.masterMotorRotate(-0.7,0.7,-0.7,0.7);
                        motorController.masterMotorRotate(0.5,0.5,0.5,0.5);
                        motorController.masterMotorRotate(1.0,1.0,1.0,1.0);
                        motorController.masterMotorRotate(-3.0, -3.0, 3.0,3.0);
                        sleep(800);
                        motorController.masterMotorRotate(0.1, 0.1, -0.1,-0.1);
                        motorController.masterMotorRotate(-1.7,-1.7,-1.7,-1.7);
                        motorController.masterMotorRotate(-0.8,0.8,-0.8,0.8);
                        motorController.masterMotorRotate(-0.3, -0.3, 0.3,0.3);
                        motorController.servo2SetPosition(0.9);
                        sleep(800);
                        sleep(800);
                        break;
                    case "right":
                        motorController.masterMotorRotate(2,2,2,2);
                        motorController.masterMotorRotate(-1.5, -1.5, 1.5,1.5);
                        motorController.servo1SetPosition(0.65);
                        sleep(800);
                        motorController.masterMotorRotate(0.7,0.7,0.7,0.7);
                        sleep(800);
                        motorController.masterMotorRotate(0.1, 0.1, -0.1,-0.1);
                        motorController.masterMotorRotate(-3.5,-3.5,-3.5,-3.5);
                        motorController.servo2SetPosition(0.9);
                        sleep(800);
                        sleep(800);
                        break;
                }
            }
        }
}

