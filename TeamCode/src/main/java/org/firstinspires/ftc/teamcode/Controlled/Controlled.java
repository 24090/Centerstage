package org.firstinspires.ftc.teamcode.Controlled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.subsystems.MotorController;
@TeleOp()
public class Controlled extends OpMode {
    MotorController motorController1 = new MotorController();
    String turningDirection;
    boolean isTurning;
    double velocity;
    double velocity2;

    @Override
    public void init() {
        motorController1.init(hardwareMap);
        motorController1.servo2SetPosition(0.23);
    }

    @Override
    public void loop() {
        motorController1.setPowerByVector(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
    }
}

