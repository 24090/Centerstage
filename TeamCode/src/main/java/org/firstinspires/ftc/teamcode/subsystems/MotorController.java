package org.firstinspires.ftc.teamcode.subsystems;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import java.lang.Math;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class MotorController {
    public DcMotor motor1;
    double tickToCMConstant = (2 * Math.PI * 2.4)/2000;
    public DcMotor motor2;
    public DcMotor motor3;
    public DcMotor motor4;
    public DcMotor armMotor;
    public Odometry odometry;
    double tickToCMConstant = (2 * Math.PI * 2.4)/2000;
    double lengthBetweenEncodersVertically;
    double lengthBetweenEncodersHorizontally;
    double armConstant;
    public DcMotor linearMotor;
    double[] motorTargetPowers = {0,0,0,0};
    double[] currentPowers = {0,0,0,0};
    private Servo servo1;
    private Servo servo2;
    private Servo servo3;
    LinearOpMode opMode;
    double ticksPerRotation;

    private void init(HardwareMap hwMap){
        servo1 = hwMap.get(Servo.class, "servo");
        servo1.setDirection(Servo.Direction.FORWARD);
        servo2 = hwMap.get(Servo.class, "servo2");
        servo2.setDirection(Servo.Direction.REVERSE);
        servo3 = hwMap.get(Servo.class, "servo3");
        servo3.setDirection(Servo.Direction.FORWARD);
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
        linearMotor = hwMap.get(DcMotor.class, "linearMotor");
        linearMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        linearMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        armMotor = hwMap.get(DcMotor.class, "armMotor");
        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        ticksPerRotation = motor1.getMotorType().getTicksPerRev();
        odometry = new Odometry(opMode, motor1.getCurrentPosition(), motor2.getCurrentPosition(), motor3.getCurrentPosition());
        Thread update_thread = new Thread(() ->
        {
            while (opMode.opModeIsActive()) {
                odometry.update_odometry_data(motor1.getCurrentPosition(),motor2.getCurrentPosition(),motor3.getCurrentPosition());
            }
        });
    }
    public MotorController(LinearOpMode opMode1, HardwareMap hwMap){
        opMode = opMode1;
        init(hwMap);
    }
    public double getCentimetersTravelled(DcMotor motor){
        return motor.getCurrentPosition() * tickToCMConstant;
    }
    public void armMotor(double speed){
        armMotor.setPower(speed);
    }
    public MotorController(LinearOpMode opMode1){
        opMode = opMode1;
    }
    public void armMotor(double speed){
        armMotor.setPower(speed);
    }
    public void servo1SetPosition(double position){
        servo1.setPosition(position);
    }
    public void servo2SetPosition(double position){
        servo2.setPosition(position);
    }
    public void setLinearMotorPower(double power){
        linearMotor.setPower(power);
    }
    public void setLinearPosition(double position){
        if (33 < position && position < 60){
            if (linearMotor.getCurrentPosition() < (-1234/27)*(position-33)) {
                while (linearMotor.getCurrentPosition() < (-1234/27)*(position-33)){
                    linearMotor.setPower(0.1);
                }
                linearMotor.setPower(0);
            }
            if (linearMotor.getCurrentPosition() > (-1234/27)*(position-33)) {
                while (linearMotor.getCurrentPosition() > (-1234/27)*(position-33)){
                    linearMotor.setPower(-0.1);
                }
                linearMotor.setPower(0);
            }
        }
    }
    public void setArmAngle(double degree){
        if (-11 < degree && degree < 185){
            if (armMotor.getCurrentPosition() < -(1449/196)*(degree+11)){
                while (armMotor.getCurrentPosition() < -(1449/196)*(degree+11)){
                    armMotor.setPower(0.1);
                }
                armMotor.setPower(0);
            }
            if (armMotor.getCurrentPosition() > -(1449/196)*(degree+11)){
                while (armMotor.getCurrentPosition() > -(1449/196)*(degree+11)){
                    armMotor.setPower(-0.1);
                }
                armMotor.setPower(0);
            }
        }
    }
    public void armHold(){
        if (armMotor.getCurrentPosition() > -10 || armMotor.getCurrentPosition() < -1350){
            armMotor.setPower(0);
        }
        armMotor.setPower((-683 - armMotor.getCurrentPosition())/100);
    }
    public void servo3SetPosition(double position){
        servo3.setPosition(position);
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
    //function with power input
    public void masterMotorRotate(double[] motorRotations, double[] motorPowers) {
        // create threads for each motor and run them
        Thread motorThread1 = new Thread(() -> rotateMotor(motorRotations[0], motorPowers[0], motor1));
        Thread motorThread2 = new Thread(() -> rotateMotor(motorRotations[1], motorPowers[1],motor2));
        Thread motorThread3 = new Thread(() -> rotateMotor(-motorRotations[2], motorPowers[2], motor3));
        Thread motorThread4 = new Thread(() -> rotateMotor(-motorRotations[3], motorPowers[3], motor4));
        motorThread1.start();
        motorThread2.start();
        motorThread3.start();
        motorThread4.start();
        // wait until threads are done executing
        while (motorThread1.isAlive()||motorThread2.isAlive()||motorThread3.isAlive()||motorThread4.isAlive()){
        }
    }
    public void rotateMotor(double motorRotations, double motorPower, DcMotor motor){
        double initialRotation = getMotorRotations(motor);
        while ((opMode.opModeIsActive()) && (Math.abs(getMotorRotations(motor) - initialRotation) < Math.abs(motorRotations))){
            motor.setPower(motorPower * Math.signum(motorRotations));
        }
        motor.setPower(0);
    }
    public void masterMotorControla(double[] motorPowers){
        for (int i = 0; i < motorTargetPowers.length; i++){
            motorTargetPowers[i] = motorPowers[i];
        }
        for (int i = 0; i < motorTargetPowers.length; i++){
            currentPowers[i] = currentPowers[i] + 0.3*(motorTargetPowers[i] - currentPowers[i]);
        }
        motor1.setPower(-currentPowers[0]);
        motor2.setPower(-currentPowers[1]);
        motor3.setPower(currentPowers[2]);
        motor4.setPower(currentPowers[3]);

    }
    public void masterMotorControl(double[] motorPowers){
        motor1.setPower(-motorPowers[0]);
        motor2.setPower(-motorPowers[1]);
        motor3.setPower(motorPowers[2]);
        motor4.setPower(motorPowers[3]);

    }
    public void masterMotorControl2(double motorSpeed1, double motorSpeed2,double motorSpeed3,double motorSpeed4){
        double[] motorPowers = {motorSpeed1,motorSpeed2,motorSpeed3,motorSpeed4};
        for (int i = 0; i < motorTargetPowers.length; i++){
            motorTargetPowers[i] = motorPowers[i];
            currentPowers[i] = currentPowers[i] + 0.3*(motorTargetPowers[i] - currentPowers[i]);
        }
        motor1.setPower(-currentPowers[0]);
        motor2.setPower(-currentPowers[1]);
        motor3.setPower(currentPowers[2]);
        motor4.setPower(currentPowers[3]);
    }
    public void setPowerByVector(double amount_forward, double amount_sideways, double amount_turn){
        masterMotorControl(getPowersFromVector(amount_forward, amount_sideways, amount_turn));
    }
    public double[] getPowersFromVector(double amount_forward, double amount_sideways, double amount_turn){
        double RightwardPower = amount_forward + amount_sideways;
        double LeftwardPower = amount_forward - amount_sideways;
        double denominator = Math.max(Math.abs(amount_forward) + Math.abs(amount_sideways) + Math.abs(amount_turn), 1);
        return (denominator != 0
                ?new double[]{(LeftwardPower - amount_turn) / denominator, (RightwardPower - amount_turn) / denominator, (LeftwardPower + amount_turn) / denominator, (RightwardPower + amount_turn) / denominator}
                :new double[]{0,0,0,0});
    }
    public void outputOdometryData(){
        odometry.outputOdometryData();
    }
    public void setArmPosition(double rotaryPosition, double linearPosition, double servoPosition){
        Thread armMotor = new Thread(() -> setArmAngle(rotaryPosition));
        Thread linearMotor = new Thread(() -> setLinearPosition(linearPosition));
        Thread servoWrist = new Thread(() -> servo2SetPosition((90/-38)*(servoPosition)+0.388));
        armMotor.start();
        linearMotor.start();
        servoWrist.start();
    }
    public void moveByXYRobotOriented(double x, double y){
        double initialRotation1 = positioning.getCentimetersTravelled(motor1);
        double initialRotation2 = positioning.getCentimetersTravelled(motor2);
        double initialRotation3 = positioning.getCentimetersTravelled(motor3);
        while ((opMode.opModeIsActive()) && (((Math.abs(positioning.getCentimetersTravelled(motor1)-initialRotation1)) < y)||(Math.abs((positioning.getCentimetersTravelled(motor2)-initialRotation2)) < y)||(Math.abs((positioning.getCentimetersTravelled(motor3)-initialRotation3)) < x))){
            masterMotorControl(getPowersFromVector(y,x,0));
            }
        masterMotorControl2(0,0,0,0);
    }
    // 0 = 0.388
    // 90 = 0.08
    // y = (90/-38)(position) + 0.388
    //Robot Oriented, so it will not move to a position on the field, it will move by an x and y relative to the robot's heading (In cm).
    public void turnByDegrees(double degree){
        double initialRotation1 = positioning.getCentimetersTravelled(motor1);
        double initialRotation2 = positioning.getCentimetersTravelled(motor2);
        double initialRotation3 = positioning.getCentimetersTravelled(motor3);
        while ((opMode.opModeIsActive()) && ((Math.abs(positioning.getCentimetersTravelled(motor1)-initialRotation1) < degree*(lengthBetweenEncodersVertically/2))||(Math.abs(positioning.getCentimetersTravelled(motor2)-initialRotation2) < degree*(lengthBetweenEncodersVertically/2))||(Math.abs(positioning.getCentimetersTravelled(motor3)-initialRotation3) < degree*(lengthBetweenEncodersHorizontally/2)))){
            masterMotorControl2(-Math.signum(degree),-Math.signum(degree),Math.signum(degree),Math.signum(degree));
        }
        masterMotorControl2(0,0,0,0);
    }
    public double getCentimetersTravelled(){
        return motor1.getCurrentPosition() * tickToCMConstant;
    }
}
