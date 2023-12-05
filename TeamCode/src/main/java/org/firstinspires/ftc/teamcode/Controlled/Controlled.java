package org.firstinspires.ftc.teamcode.Controlled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.subsystems.MotorController;
@TeleOp()
public class Controlled extends OpMode {
    MotorController motorController1 = new MotorController();
    String verticalDirection;
    String horizontalDirection;
    String turningDirection;
    String direction;
    double velocity;
    double velocity2;

    @Override
    public void init() {
        telemetry.addData("Hello", "World");
        motorController1.init(hardwareMap);
        motorController1.servo2SetPosition(0.23);
    }

    @Override
    public void loop() {
        double xValue2 = gamepad1.right_stick_x;
        double yValue2 = gamepad1.right_stick_y;
        if (gamepad1.dpad_up) {
            verticalDirection = "Front";
        } else if (!(gamepad1.dpad_up || gamepad1.dpad_down)) {
            verticalDirection = "";
        } else {
            verticalDirection = "Back";
        }
        if (!(gamepad1.dpad_right || gamepad1.dpad_left)) {
            horizontalDirection = "";
        } else if (gamepad1.dpad_left) {
            horizontalDirection = "Left";
        } else if (gamepad1.dpad_right) {
            horizontalDirection = "Right";
        }
        if (xValue2 > 0){
            turningDirection = "Turning Right";
        } else if (xValue2 < 0){
            turningDirection = "Turning Left";
        }
        if (xValue2 != 0){
            direction = turningDirection;
        } else {
            direction = horizontalDirection + verticalDirection;
        }
        if (xValue2 != 0){
            telemetry.addData("Turning", direction);
        } else {
            telemetry.addData("Direction", horizontalDirection + " " + verticalDirection);
        }
        velocity = -0.5;
        velocity2 = -Math.sqrt(xValue2 * xValue2 + yValue2 * yValue2)/2;
        telemetry.addData("Velocity", velocity);
        switch (direction) {
            case "Front":
                motorController1.masterMotorControl(velocity, velocity, velocity, velocity);
                break;
            case "RightFront":
                motorController1.masterMotorControl(velocity, 0.0, velocity, 0.0);
                break;
            case "Right":
                motorController1.masterMotorControl(velocity, -velocity, velocity, -velocity);
                break;
            case "RightBack":
                motorController1.masterMotorControl(0.0, -velocity, 0.0, -velocity);
                break;
            case "Back":
                motorController1.masterMotorControl(-velocity, -velocity, -velocity, -velocity);
                break;
            case "LeftBack":
                motorController1.masterMotorControl(-velocity, 0.0, -velocity, 0.0);
                break;
            case "Left":
                motorController1.masterMotorControl(-velocity, velocity, -velocity, velocity);
                break;
            case "LeftFront":
                motorController1.masterMotorControl(0.0, velocity, 0.0, velocity);
                break;
            case "":
                motorController1.masterMotorControl(0.0, 0.0, 0.0, 0.0);
                break;
            case "Turning Right":
                motorController1.masterMotorControl(-velocity2,-velocity2,velocity2,velocity2);
                break;
            case "Turning Left":
                motorController1.masterMotorControl(velocity2,velocity2, -velocity2, -velocity2);
                break;
            }
            if (gamepad1.left_bumper){
                motorController1.servoSetPosition(0.23);
            }
            if (gamepad1.right_bumper){
                motorController1.servoSetPosition(0.9);
            }
            if (gamepad1.b){
                motorController1.servo2SetPosition(0.5);
            }
        }
    }

