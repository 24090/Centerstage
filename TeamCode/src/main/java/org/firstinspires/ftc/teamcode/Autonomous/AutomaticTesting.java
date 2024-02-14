package org.firstinspires.ftc.teamcode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.MotorController;
import org.firstinspires.ftc.teamcode.subsystems.Positioning;


@Autonomous()
public class AutomaticTesting extends LinearOpMode {
    MotorController motorController;
    Positioning positioning;
    private String positionOfPixel = "";
    @Override
    public void runOpMode() {
        positioning = new Positioning(this);
        motorController = new MotorController(this);
        motorController.init(hardwareMap);
        while (positionOfPixel == "" && opModeIsActive()){
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
                    telemetry.addData("positionOfPixel", "middle");
                    break;
                case "left":
                    telemetry.addData("positionOfPixel", "left");
                    break;
                case "right":
                    telemetry.addData("positionOfPixel", "right");
                    break;
            }
            telemetry.update();
            sleep(1000);
        }
    }
}

