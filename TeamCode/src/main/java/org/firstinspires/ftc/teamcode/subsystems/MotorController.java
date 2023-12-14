package org.firstinspires.ftc.teamcode.subsystems;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import java.lang.Math;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class MotorController {
    public DcMotor motor1;
    public DcMotor motor2;
    public DcMotor motor3;
    public DcMotor motor4;
    private Servo servo1;
    private Servo servo2;
    double ticksPerRotation;
    boolean startRotation;

    public void init(HardwareMap hwMap){
        servo1 = hwMap.get(Servo.class, "servo");
        servo1.setDirection(Servo.Direction.FORWARD);
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
        ticksPerRotation = motor1.getMotorType().getTicksPerRev();

    }
    public void servoSetPosition(Servo servo, double position){
        servo.setPosition(position);
    }
    public void servo1SetPosition(double position){
        servo1.setPosition(position);
    }
    public void servo2SetPosition(double position){
        servo2.setPosition(position);
    }
    public double getMotorRotations(DcMotor motor){
        return TicksToRotations(motor.getCurrentPosition());
    }
    public double TicksToRotations(double ticks){
        return ticks/(0.2*ticksPerRotation);
    }
    public double RotationsToTicks(double rotations){
        return rotations * (0.2*ticksPerRotation);
    }
    public void masterMotorRotate(LinearOpMode opMode, double motorRotations1, double motorRotations2, double motorRotations3, double motorRotations4) {
        // create threads for each motor and run them
        Thread motorThread1 = new Thread(() -> {rotateMotor(opMode,motorRotations1, motor1);});
        Thread motorThread2 = new Thread(() -> {rotateMotor(opMode,motorRotations2, motor2);});
        Thread motorThread3 = new Thread(() -> {rotateMotor(opMode,-motorRotations3, motor3);});
        Thread motorThread4 = new Thread(() -> {rotateMotor(opMode,-motorRotations4, motor4);});
        motorThread1.start();
        motorThread2.start();
        motorThread3.start();
        motorThread4.start();
        // wait until threads are done executing
        while (motorThread1.isAlive()||motorThread2.isAlive()||motorThread3.isAlive()||motorThread4.isAlive()){;}
    }
    public void rotateMotor(LinearOpMode opMode, double motorRotations, DcMotor motor){
        double initialRotation = getMotorRotations(motor);
        while ((opMode.opModeIsActive()) && (Math.abs(getMotorRotations(motor) - initialRotation) < Math.abs(motorRotations)))
        {
            motor.setPower(0.1 * Math.signum(motorRotations));
        }
        motor.setPower(0);
    }
    public void masterMotorControl(double motor1Speed, double motor2Speed, double motor3Speed, double motor4Speed){
        motor1.setPower(-motor1Speed);
        motor2.setPower(-motor2Speed);
        motor3.setPower(motor3Speed);
        motor4.setPower(motor4Speed);
    }
}

