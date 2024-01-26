package org.firstinspires.ftc.teamcode.Controlled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Conversions;
import org.firstinspires.ftc.teamcode.subsystems.MotorController;
@TeleOp()
public class Controlled extends LinearOpMode {
    MotorController motorController = new MotorController(this);

    @Override
    public void runOpMode() {
        motorController.init(hardwareMap);
            while(opModeIsActive()) {
            motorController.setPowerByVector(gamepad1.left_stick_y, gamepad1.left_stick_x, -gamepad1.right_stick_x);
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
                if (gamepad1.x){
                    motorController.autoMotor(0.5);
                }
    }
}
}


