package org.firstinspires.ftc.teamcode.Autonomous;
import com.qualcomm.hardware.dfrobot.HuskyLens;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.MotorController;


@Autonomous()
public class AutomaticFinalFarRed extends LinearOpMode {
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
<<<<<<< Updated upstream
        motorController = new MotorController();
        motorController.init(hardwareMap);
=======
        motorController = new MotorController(this, hardwareMap);
>>>>>>> Stashed changes
        telemetry.update();
        waitForStart();
        while (opModeIsActive() && positionOfPixel == "") {
            HuskyLens.Block[] blocks = huskyLens.blocks();
            for (int i = 0; i < blocks.length; i++){
                telemetry.addData("Block", blocks[i].toString());
                if ((100 < blocks[i].x && blocks[i].x < 180) && (120 < blocks[i].y && blocks[i].y < 200)) {
                    positionOfPixel = "middle";
                    telemetry.addData("Position", positionOfPixel);
                }
                if ((260 < blocks[i].x && blocks[i].x < 310) && (160 < blocks[i].y && blocks[i].y < 200)) {
                    positionOfPixel = "right";
                    telemetry.addData("Position", positionOfPixel);
                }
                if ((10 < blocks[i].x && blocks[i].x < 80) && (160 < blocks[i].y && blocks[i].y < 200)) {
                    positionOfPixel = "left";
                    telemetry.addData("Position", positionOfPixel);
                }
            }
            telemetry.update();
            }
            if (opModeIsActive()){
                motorController.servo1SetPosition(0.23);
                sleep(800);
                switch (positionOfPixel){
                    case "middle":
                        motorController.masterMotorRotate(this, 2.7,2.7,2.7,2.7);
                        motorController.servo1SetPosition(0.65);
                        sleep(800);
                        motorController.masterMotorRotate(this, -0.5,-0.5,-0.5,-0.5);
                        motorController.masterMotorRotate(this, 1.7,-1.7,1.7,-1.7);
                        motorController.masterMotorRotate(this, 1.7,1.7,1.7,1.7);
                        motorController.masterMotorRotate(this, 7,-7,7,-7);
                        break;
                    case "left":
                        motorController.masterMotorRotate(this, 2.3,2.3,2.3,2.3);
                        motorController.masterMotorRotate(this,1.5, 1.5, -1.5,-1.5);
                        motorController.servo1SetPosition(0.65);
                        sleep(800);
                        motorController.masterMotorRotate(this, 0.7,0.7,0.7,0.7);
                        motorController.masterMotorRotate(this, -0.7,-0.7,-0.7,-0.7);
                        motorController.masterMotorRotate(this, 2.2,-2.2,2.2,-2.2);
                        motorController.masterMotorRotate(this, -7.0,-7.0,-7.0,-7.0);
                        motorController.masterMotorRotate(this, -0.5,0.5,-0.5,0.5);
                        break;
                    case "right":
                        motorController.masterMotorRotate(this, 2,2,2,2);
                        motorController.masterMotorRotate(this,-1.5, -1.5, 1.5,1.5);
                        motorController.servo1SetPosition(0.65);
                        sleep(800);
                        motorController.masterMotorRotate(this, 0.7,0.7,0.7,0.7);
                        motorController.masterMotorRotate(this, -0.7,-0.7,-0.7,-0.7);
                        motorController.masterMotorRotate(this, -2.2,2.2,-2.2,2.2);
                        motorController.masterMotorRotate(this, 7.0,7.0,7.0,7.0);
                        motorController.masterMotorRotate(this, 0.5,-0.5,0.5,-0.5);
                        break;
                }
            }
        }
}

