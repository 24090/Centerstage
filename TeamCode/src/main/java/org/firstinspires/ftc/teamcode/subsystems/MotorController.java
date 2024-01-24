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
    DcMotor[] motorList = {motor1,motor2,motor3,motor4};
    public DcMotor autoMotor;
    public DcMotor linearMotor;
    private Servo servo1;
    private Servo servo2;
    LinearOpMode opMode;
    Positioning positioning = new Positioning(null);
    double ticksPerRotation;

    public void init(HardwareMap hwMap){
        servo1 = hwMap.get(Servo.class, "servo");
        servo1.setDirection(Servo.Direction.FORWARD);
        servo2 = hwMap.get(Servo.class, "servo2");
        servo2.setDirection(Servo.Direction.REVERSE);
        motor1 = hwMap.get(DcMotor.class, "motor1");
        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motor1.setDirection(DcMotorSimple.Direction.FORWARD);
        motor2 = hwMap.get(DcMotor.class, "motor2");
        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motor2.setDirection(DcMotorSimple.Direction.FORWARD);
        motor3 = hwMap.get(DcMotor.class, "motor3");
        motor3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motor3.setDirection(DcMotorSimple.Direction.FORWARD);
        motor4 = hwMap.get(DcMotor.class, "motor4");
        motor4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor4.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motor4.setDirection(DcMotorSimple.Direction.FORWARD);
        autoMotor = hwMap.get(DcMotor.class, "automotor");
        autoMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        autoMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        autoMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        linearMotor = hwMap.get(DcMotor.class, "linearmotor");
        linearMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        linearMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        ticksPerRotation = motor1.getMotorType().getTicksPerRev();
    }
    public MotorController(LinearOpMode opMode1){
        opMode = opMode1;
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
    public double getEncoderTicks(DcMotor motor){
        return motor.getCurrentPosition();
    }
    public double TicksToRotations(double ticks){
        return ticks/(0.2*ticksPerRotation);
    }
    public void masterMotorRotate(double motorRotations1, double motorRotations2, double motorRotations3, double motorRotations4){
        masterMotorRotate(new double[]{motorRotations1, motorRotations2, motorRotations3, motorRotations4}, new double[]{1.0, 1.0, 1.0, 1.0});
    }
    public int getTicks(DcMotor motor){
        return motor.getCurrentPosition();
    }
    //function with power input
    public void masterMotorRotate(double[] motorRotations, double[] motorPowers) {
        // create threads for each motor and run them

        Thread motorThread1 = new Thread(() -> {rotateMotor(motorRotations[0], motorPowers[0], motor1);});
        Thread motorThread2 = new Thread(() -> {rotateMotor(motorRotations[1], motorPowers[1],motor2);});
        Thread motorThread3 = new Thread(() -> {rotateMotor(-motorRotations[2], motorPowers[2], motor3);});
        Thread motorThread4 = new Thread(() -> {rotateMotor(-motorRotations[3], motorPowers[3], motor4);});
        motorThread1.start();
        motorThread2.start();
        motorThread3.start();
        motorThread4.start();
        // wait until threads are done executing
        while (motorThread1.isAlive()||motorThread2.isAlive()||motorThread3.isAlive()||motorThread4.isAlive()){;}
    }
    public void rotateMotor(double motorRotations, double motorPower, DcMotor motor){
        double initialRotation = getMotorRotations(motor);
        while ((opMode.opModeIsActive()) && (Math.abs(getMotorRotations(motor) - initialRotation) < Math.abs(motorRotations)))
        {
            motor.setPower(motorPower * Math.signum(motorRotations));
        }
        motor.setPower(0);
    }
    public void masterMotorControl(double[] motorPowers){
        motor1.setPower(-motorPowers[0]);
        motor2.setPower(-motorPowers[1]);
        motor3.setPower(motorPowers[2]);
        motor4.setPower(motorPowers[3]);
    }
    public void autoMotor(double speed){
        autoMotor.setPower(speed);
    }
    public void linearMotor(double speed){
        linearMotor.setPower(speed);
    }
    public void setPowerByVector(double amount_forward, double amount_sideways, double amount_turn){
        masterMotorControl(getPowersFromVector(amount_forward, amount_sideways, amount_turn));
    }
    public double[] getPowersFromVector(double amount_forward, double amount_sideways, double amount_turn){
        double RightwardPower = amount_forward + amount_sideways;
        double LeftwardPower = amount_forward - amount_sideways;
        double denominator = (Math.abs(amount_forward) + Math.abs(amount_sideways) + Math.abs(amount_turn));
        return (denominator != 0
                ?new double[]{(LeftwardPower - amount_turn) / denominator, (RightwardPower - amount_turn) / denominator, (LeftwardPower + amount_turn) / denominator, (RightwardPower + amount_turn) / denominator}
                :new double[]{0,0,0,0});
    }
    public void moveByXYVector(double x, double y, double[] motorPowers){
        int i = 0;
        double initialRotation1 = positioning.getCentimetersTravelled(motor1);
        double initialRotation2 = positioning.getCentimetersTravelled(motor2);
        double initialRotation3 = positioning.getCentimetersTravelled(motor3);
        while ((opMode.opModeIsActive()) && ((positioning.getCentimetersTravelled(motor1)-initialRotation1 < y)||(positioning.getCentimetersTravelled(motor3)-initialRotation3 < y)||(positioning.getCentimetersTravelled(motor2)-initialRotation2 < x))) {
            for (DcMotor motor: motorList) {
                motor.setPower(getPowersFromVector(y,x,0)[i]);
            }
        }
        for (DcMotor motor: motorList) {
            motor.setPower(0);
        }
    }
    //Robot Oriented, so it will not move to a position on the field, it will move by an x and y relative to the robot's heading (In cm).
    public void turnByDegrees(){

    }
}
