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
                if ((130 < blocks[i].x && blocks[i].x < 180) && (140 < blocks[i].y && blocks[i].y < 200)) {
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
                motorController.servoSetPosition(0.23);
                sleep(800);
                switch (positionOfPixel){
                    case "middle":
                        motorController.motorMasterRotate("front",2.7,2.7,2.7,2.7);
                        motorController.servoSetPosition(0.65);
                        sleep(800);
                        motorController.motorMasterRotate("back",1.5,1.5,1.5,1.5);
                        break;
                    case "left":
                        motorController.motorMasterRotate("front",2.3,2.3,2.3,2.3);
                        motorController.motorMasterRotate("rotateLeft",1.4,1.4,1.4,1.4);
                        motorController.motorMasterRotate("front",0.6,0.6,0.6,0.6);
                        motorController.servoSetPosition(0.65);
                        sleep(800);
                        motorController.motorMasterRotate("back",0.8,0.8,0.8,0.8);
                        break;
                    case "right":
                        motorController.motorMasterRotate("front",2,2,2,2);
                        motorController.motorMasterRotate("rotateRight",1.4,1.4,1.4,1.4);
                        motorController.motorMasterRotate("front",0.6,0.6,0.6,0.6);
                        motorController.servoSetPosition(0.65);
                        sleep(800);
                        motorController.motorMasterRotate("back",0.6,0.6,0.6,0.6);
                }
            }
        }
}

