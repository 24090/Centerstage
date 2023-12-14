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
    double ticksPerRotation;

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
        ticksPerRotation = motor1.getMotorType().getTicksPerRev();

    }
    public void servoSetPosition(double position){
        servo.setPosition(position);
    }
    public void servo2SetPosition(double position){
        servo2.setPosition(position);
    }
    public double getRotations1(){
        return motor1.getCurrentPosition()/ticksPerRotation;
    }
    public void motor1Rotations(double motorRotations1){
        double initialRotation = motor1.getCurrentPosition()/(0.2*ticksPerRotation);
        while ((motor1.getCurrentPosition()/(0.2*ticksPerRotation)) - initialRotation < motorRotations1){
            motor1.setPower(0.5);
        }
        motor1.setPower(0);
    }
    public void motor2Rotations(double motorRotations2){
        double initialRotation = motor2.getCurrentPosition()/(0.2*ticksPerRotation);
        while ((motor2.getCurrentPosition()/(0.2*ticksPerRotation)) - initialRotation < motorRotations2){
            motor2.setPower(0.5);
        }
        motor2.setPower(0);
    }
    public void motor3Rotations(double motorRotations3){
        double initialRotation = motor3.getCurrentPosition()/(0.2*ticksPerRotation);
        while ((motor3.getCurrentPosition()/(0.2*ticksPerRotation)) - initialRotation < motorRotations3){
            motor3.setPower(0.5);
        }
        motor3.setPower(0);
    }
    public void motor4Rotations(double motorRotations4){
        double initialRotation = motor4.getCurrentPosition()/(0.2*ticksPerRotation);
        while ((motor4.getCurrentPosition()/(0.2*ticksPerRotation)) - initialRotation < motorRotations4){
            motor4.setPower(0.5);
        }
        motor4.setPower(0);
    }
    public void motorMasterRotate(String direction,double mtr1, double mtr2, double mtr3, double mtr4) {
        double initialRotation4 = motor4.getCurrentPosition() / (0.2 * ticksPerRotation);
        double initialRotation3 = motor3.getCurrentPosition() / (0.2 * ticksPerRotation);
        double initialRotation2 = motor2.getCurrentPosition() / (0.2 * ticksPerRotation);
        double initialRotation1 = motor1.getCurrentPosition() / (0.2 * ticksPerRotation);
        if (direction == "front") {
            while (((motor4.getCurrentPosition() / (0.2 * ticksPerRotation)) - initialRotation4 < mtr4) &&
                    ((motor3.getCurrentPosition() / (0.2 * ticksPerRotation)) - initialRotation3 < mtr3) &&
                    ((motor2.getCurrentPosition() / (0.2 * ticksPerRotation)) - initialRotation2 < mtr2) &&
                    ((motor1.getCurrentPosition() / (0.2 * ticksPerRotation)) - initialRotation1 < mtr1)) {
                motor1.setPower(0.1);
                motor2.setPower(0.1);
                motor3.setPower(-0.1);
                motor4.setPower(-0.1);
            }
        }
        if (direction == "back") {
            while (((motor4.getCurrentPosition() / (0.2 * ticksPerRotation)) - initialRotation4 > -mtr4) &&
                    ((motor3.getCurrentPosition() / (0.2 * ticksPerRotation)) - initialRotation3 > -mtr3) &&
                    ((motor2.getCurrentPosition() / (0.2 * ticksPerRotation)) - initialRotation2 > -mtr2) &&
                    ((motor1.getCurrentPosition() / (0.2 * ticksPerRotation)) - initialRotation1 > -mtr1)) {
                motor1.setPower(-0.1);
                motor2.setPower(-0.1);
                motor3.setPower(0.1);
                motor4.setPower(0.1);
            }
        }
        if (direction == "rotateLeft"){
            while (((motor4.getCurrentPosition() / (0.2 * ticksPerRotation)) - initialRotation4 > -mtr4) &&
                    ((motor3.getCurrentPosition() / (0.2 * ticksPerRotation)) - initialRotation3 > -mtr3) &&
                    ((motor2.getCurrentPosition() / (0.2 * ticksPerRotation)) - initialRotation2 < mtr2) &&
                    ((motor1.getCurrentPosition() / (0.2 * ticksPerRotation)) - initialRotation1 < mtr1)) {
                motor1.setPower(0.1);
                motor2.setPower(0.1);
                motor3.setPower(0.1);
                motor4.setPower(0.1);
            }
        }
        if (direction == "rotateRight"){
            while (((motor4.getCurrentPosition() / (0.2 * ticksPerRotation)) - initialRotation4 < mtr4) &&
                    ((motor3.getCurrentPosition() / (0.2 * ticksPerRotation)) - initialRotation3 < mtr3) &&
                    ((motor2.getCurrentPosition() / (0.2 * ticksPerRotation)) - initialRotation2 > -mtr2) &&
                    ((motor1.getCurrentPosition() / (0.2 * ticksPerRotation)) - initialRotation1 > -mtr1)) {
                motor1.setPower(-0.1);
                motor2.setPower(-0.1);
                motor3.setPower(-0.1);
                motor4.setPower(-0.1);
            }
        }
        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);
    }
    public double getRotations(){
        return motor1.getCurrentPosition()/(0.2*ticksPerRotation);
    }
    public double getTicksPerRotation(){
        return (0.2*ticksPerRotation);
    }
    public void masterMotorControl(double motor1Speed, double motor2Speed, double motor3Speed, double motor4Speed){
        motor1.setPower(-motor1Speed);
        motor2.setPower(-motor2Speed);
        motor3.setPower(motor3Speed);
        motor4.setPower(motor4Speed);
    }
}

