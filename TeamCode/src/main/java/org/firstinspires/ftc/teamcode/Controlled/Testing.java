package org.firstinspires.ftc.teamcode.Controlled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.subsystems.MotorController;
@TeleOp()
public class Testing extends OpMode{
    MotorController motorController = new MotorController();
    String direction;
    String horizontalDirection;
    String verticalDirection;
    @Override
    public void init(){
        motorController.init(hardwareMap);
    }
    public void loop(){
        if (gamepad1.dpad_up){
            verticalDirection = "F";
        }
        if (gamepad1.dpad_down){
            verticalDirection = "B";
        }
        if (gamepad1.dpad_left){
            horizontalDirection = "L";
        }
        if (gamepad1.dpad_right){
            horizontalDirection = "R";
        }
        direction = verticalDirection + horizontalDirection;
        switch (direction){
            case "F":
                motorController.masterMotorControl(0.5,0.5,0.5,0.5);
                break;
            case "B":
                motorController.masterMotorControl(-0.5,-0.5,-0.5,-0.5);
                break;
            case "R":
                motorController.masterMotorControl(0.5,-0.5,0.5,-0.5);
                break;
            case "L":
                motorController.masterMotorControl(-0.5,0.5,-0.5,0.5);
                break;
            case "FL":
                motorController.masterMotorControl(0,0.5,0,0.5);
                break;
            case "FR":
                motorController.masterMotorControl(0.5,0,0.5,0);
                break;
            case "BL":
                motorController.masterMotorControl(-0.5,0,-0.5,0);
                break;
            case "BR":
                motorController.masterMotorControl(0,-0.5,0,-0.5);
                break;
        }
        if (gamepad1.left_bumper){
            motorController.servo1SetPosition(0.23);
        }
        if (gamepad1.right_bumper){
            motorController.servo1SetPosition(0.9);
        }

    }
}
