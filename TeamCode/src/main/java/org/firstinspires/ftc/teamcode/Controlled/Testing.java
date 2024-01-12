package org.firstinspires.ftc.teamcode.Controlled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Conversions;
import org.firstinspires.ftc.teamcode.subsystems.MotorController;
@TeleOp()
public class Testing extends OpMode{
    MotorController motorController = new MotorController();
    Conversions converter = new Conversions();
    double horizontalDirection;
    double verticalDirection;
    @Override
    public void init(){
        motorController.init(hardwareMap);
    }
    public void loop(){
        verticalDirection = (converter.booleanToDouble(gamepad1.dpad_up) - converter.booleanToDouble(gamepad1.dpad_down))/2;
        horizontalDirection = (converter.booleanToDouble(gamepad1.dpad_right) - converter.booleanToDouble(gamepad1.dpad_left))/2;
        motorController.setPowerByVector(verticalDirection, horizontalDirection, -gamepad1.right_stick_x);
        if (gamepad1.left_bumper){
            motorController.servo1SetPosition(0.23);
        }
        if (gamepad1.right_bumper){
            motorController.servo1SetPosition(0.9);
        }
    }
}
