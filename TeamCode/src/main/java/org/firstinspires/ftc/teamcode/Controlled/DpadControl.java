package org.firstinspires.ftc.teamcode.Controlled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.subsystems.Conversions;
import org.firstinspires.ftc.teamcode.subsystems.MotorController;
@TeleOp()
public class DpadControl extends LinearOpMode{
    MotorController motorController;
    Conversions converter = new Conversions();
    double horizontalDirection;
    double verticalDirection;
    @Override
    public void runOpMode(){
        motorController = new MotorController(this, hardwareMap);
        waitForStart();
        while(opModeIsActive()){
            verticalDirection = (converter.booleanToDouble(gamepad1.dpad_up) - converter.booleanToDouble(gamepad1.dpad_down));
            horizontalDirection = (converter.booleanToDouble(gamepad1.dpad_right) - converter.booleanToDouble(gamepad1.dpad_left));
            motorController.setPowerByVector(-verticalDirection, horizontalDirection, -gamepad1.right_stick_x);
            if (gamepad1.left_bumper){
                motorController.servo1SetPosition(0.23);
            }
            if (gamepad1.right_bumper){
                motorController.servo1SetPosition(0.9);
            }
        }
    }
}
