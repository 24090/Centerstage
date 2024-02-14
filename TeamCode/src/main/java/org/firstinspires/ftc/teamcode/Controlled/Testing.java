package org.firstinspires.ftc.teamcode.Controlled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.subsystems.MotorController;

@TeleOp()
public class Testing extends LinearOpMode{
    MotorController motorController;
    double[] currentPosition;
    boolean rightOpen = false;
    double positionServo;
    boolean leftOpen = false;
    @Override
    public void runOpMode(){
        motorController = new MotorController(this, hardwareMap);
        motorController.servo2SetPosition(0.0);
        positionServo = 0.0;
        waitForStart();
        while(opModeIsActive()){
            if (gamepad2.right_bumper){
                if (rightOpen){
                    motorController.servo1SetPosition(0.37);
                    sleep(200);
                    rightOpen = false;
                } else {
                    motorController.servo1SetPosition(0.46);
                    sleep(200);
                    rightOpen = true;
                }
            }
            if (gamepad2.left_bumper){
                if (leftOpen){
                    motorController.servo3SetPosition(0.39);
                    sleep(200);
                    leftOpen = false;
                } else {
                    motorController.servo3SetPosition(0.3);
                    sleep(200);
                    leftOpen = true;
                }
            }
            if (gamepad2.a){
                if (leftOpen){
                    if (rightOpen){
                        motorController.servo3SetPosition(0.39);
                        motorController.servo1SetPosition(0.37);
                        sleep(200);
                        leftOpen = false;
                        rightOpen = false;
                    } else{
                        motorController.servo3SetPosition(0.39);
                        motorController.servo1SetPosition(0.46);
                        sleep(200);
                        leftOpen = false;
                        rightOpen = true;
                    }
                } else {
                    if (rightOpen){
                        motorController.servo3SetPosition(0.3);
                        motorController.servo1SetPosition(0.37);
                        sleep(200);
                        leftOpen = true;
                        rightOpen = false;
                    } else {
                        motorController.servo3SetPosition(0.3);
                        motorController.servo1SetPosition(0.46);
                        sleep(200);
                        leftOpen = true;
                        rightOpen = true;
                    }
                }
            }
            positionServo += gamepad2.left_trigger/100;
            positionServo -= gamepad2.right_trigger/100;
            sleep(20);
            if (positionServo > 1){
                positionServo = 1.0;
            }
            if (positionServo < 0){
                positionServo = 0.0;
            }
            motorController.servo2SetPosition(positionServo);
            if (gamepad2.dpad_down){
                motorController.setLinearPosition(47);
            } else if (gamepad2.dpad_up){
                motorController.setLinearMotorPower(-0.1);
            } else {
                motorController.setLinearMotorPower(0.0);
            }
            if (gamepad2.b){
                motorController.setArmAngle(90);
            } else if (gamepad2.y){
                motorController.armMotor(-0.5);
            } else {
                motorController.armMotor(0.0);
            }
            telemetry.addData("ticks", motorController.linearMotor.getCurrentPosition());
            telemetry.addData("ticks2", motorController.armMotor.getCurrentPosition());
            telemetry.update();
        }
    }
}
