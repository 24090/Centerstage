package org.firstinspires.ftc.teamcode.Autonomous;
import com.qualcomm.hardware.dfrobot.HuskyLens;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.MotorController;

@Autonomous()
public class AutomaticLeft extends LinearOpMode {
    MotorController motorController;
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
        motorController = new MotorController();
        motorController.init(hardwareMap);
        telemetry.update();
        waitForStart();
        while (opModeIsActive() && positionOfPixel == "") {
            HuskyLens.Block[] blocks = huskyLens.blocks();
            for (int i = 0; i < blocks.length; i++){
                telemetry.addData("Block", blocks[i].toString());
                if ((130 < blocks[i].x && blocks[i].x < 180) && (155 < blocks[i].y && blocks[i].y < 200)) {
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
            telemetry.update();
            }
            if (opModeIsActive()){
                switch (positionOfPixel){
                    case "middle":
                        motorController.masterMotorRotate(2.5,2.5,2.5,2.5);
                        motorController.servo1SetPosition(0.23);
                        motorController.masterMotorRotate(-1.5,-1.5,-1.5,-1.5);
                        break;
                    case "left":
                        motorController.masterMotorRotate(1.7,1.7,1.7,1.7);
                        motorController.masterMotorControl(-0.1,-0.1,0.1,0.1);
                        sleep(1100);
                        motorController.masterMotorControl(0,0,0,0);
                        break;
                    case "right":
                        motorController.masterMotorRotate(1.7,1.7,1.7,1.7);
                        motorController.masterMotorControl(0.1,0.1,-0.1,-0.1);
                        sleep(1100);
                        motorController.masterMotorControl(0,0,0,0);
                        break;
                }
            }
        }
}

