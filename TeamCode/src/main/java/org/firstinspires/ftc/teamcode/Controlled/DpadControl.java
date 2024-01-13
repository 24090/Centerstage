package org.firstinspires.ftc.teamcode.Controlled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Conversions;
import org.firstinspires.ftc.teamcode.subsystems.MotorController;
@TeleOp()
public class DpadControl extends OpMode{
    MotorController motorController = new MotorController();
    Conversions converter = new Conversions();
    double horizontalDirection;
    double verticalDirection;
    @Override
    public void init(){
        motorController.init(hardwareMap);
    }
    public void loop(){
        verticalDirection = (converter.booleanToDouble(gamepad1.dpad_up) - converter.booleanToDouble(gamepad1.dpad_down));
        horizontalDirection = (converter.booleanToDouble(gamepad1.dpad_right) - converter.booleanToDouble(gamepad1.dpad_left));
        motorController.setPowerByVector(-verticalDirection, horizontalDirection, -gamepad1.right_stick_x);
        if (gamepad1.left_bumper){
            motorController.servo1SetPosition(0.23);
        }
        if (gamepad1.right_bumper){
            motorController.servo1SetPosition(0.9);
        }
        if (gamepad1.b){
            motorController.linearMotor(1.0);
        } else if (gamepad1.a) {
            motorController.linearMotor(-1.0);
        } else {
            motorController.linearMotor(0.0);
        }
    }
}
