package org.firstinspires.ftc.teamcode.subsystems;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
public class MotorController {
    public DcMotor motor1;
    public DcMotor motor2;
    public DcMotor motor3;
    public DcMotor motor4;
    private Servo servo;
    private Servo servo2;

    public void init(HardwareMap hwMap){
        servo = hwMap.get(Servo.class, "servo");
        servo.setDirection(Servo.Direction.FORWARD);
        servo2 = hwMap.get(Servo.class, "servo2");
        servo2.setDirection(Servo.Direction.FORWARD);
        motor1 = hwMap.get(DcMotor.class, "motor1");
        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor1.setDirection(DcMotorSimple.Direction.FORWARD);
        motor2 = hwMap.get(DcMotor.class, "motor2");
        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor2.setDirection(DcMotorSimple.Direction.FORWARD);
        motor3 = hwMap.get(DcMotor.class, "motor3");
        motor3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor3.setDirection(DcMotorSimple.Direction.FORWARD);
        motor4 = hwMap.get(DcMotor.class, "motor4");
        motor4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor4.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor4.setDirection(DcMotorSimple.Direction.FORWARD);
    }
    public void servoSetPosition(double position){
        servo.setPosition(position);
    }
    public void servo2SetPosition(double position){
        servo2.setPosition(position);
    }
    public void masterMotorControl(double motor1Speed, double motor2Speed, double motor3Speed, double motor4Speed){
        motor1.setPower(-motor1Speed);
        motor2.setPower(-motor2Speed);
        motor3.setPower(motor3Speed);
        motor4.setPower(motor4Speed);
    }
}

