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
<<<<<<< Updated upstream
    private Servo servo1;
    private Servo servo2;
=======
    public DcMotor armMotor;
    public Odometry odometry;
    double lengthBetweenEncodersVertically;
    double lengthBetweenEncodersHorizontally;
    double armConstant;
    double linearSlideConstant;
    public DcMotor linearMotor;
    double motor1Target;
    double motor2Target;
    double motor3Target;
    double motor4Target;
    private Servo servo1;
    private Servo servo2;
    private Servo servo3;
    LinearOpMode opMode;
>>>>>>> Stashed changes
    double ticksPerRotation;
    boolean startRotation;

    private void init(HardwareMap hwMap){
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
<<<<<<< Updated upstream
=======
        linearMotor = hwMap.get(DcMotor.class, "linearMotor");
        linearMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        linearMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        armMotor = hwMap.get(DcMotor.class, "armMotor");
        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armMotor.setDirection(DcMotorSimple.Direction.FORWARD);
>>>>>>> Stashed changes
        ticksPerRotation = motor1.getMotorType().getTicksPerRev();
        odometry = new Odometry(opMode, motor1.getCurrentPosition(), motor2.getCurrentPosition(), motor3.getCurrentPosition());
        Thread update_thread = new Thread(() ->
        {
            while (opMode.opModeIsActive()) {
                odometry.update_odometry_data(motor1.getCurrentPosition(),motor2.getCurrentPosition(),motor3.getCurrentPosition());
            }
        });
    }
<<<<<<< Updated upstream
=======
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
    Thread motorControl = new Thread(() -> masterMotorControlForThread(opMode,motor1Target, motor2Target, motor3Target, motor4Target));
    public void startMotors(){
        motorControl.start();
    }
>>>>>>> Stashed changes
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
    //function without power input
    public void masterMotorRotate(LinearOpMode opMode, double motorRotations1, double motorRotations2, double motorRotations3, double motorRotations4){
        masterMotorRotate(opMode, new double[]{motorRotations1, motorRotations2, motorRotations3, motorRotations4}, new double[]{0.1, 0.1, 0.1, 0.1});
    }
    //function with power input
    public void masterMotorRotate(LinearOpMode opMode, double[] motorRotations, double[] motorPowers) {
        // create threads for each motor and run them

        Thread motorThread1 = new Thread(() -> {rotateMotor(opMode,motorRotations[0], motorPowers[0], motor1);});
        Thread motorThread2 = new Thread(() -> {rotateMotor(opMode,motorRotations[1], motorPowers[1],motor2);});
        Thread motorThread3 = new Thread(() -> {rotateMotor(opMode,-motorRotations[2], motorPowers[2], motor3);});
        Thread motorThread4 = new Thread(() -> {rotateMotor(opMode,-motorRotations[3], motorPowers[3], motor4);});
        motorThread1.start();
        motorThread2.start();
        motorThread3.start();
        motorThread4.start();
        // wait until threads are done executing
        while (motorThread1.isAlive()||motorThread2.isAlive()||motorThread3.isAlive()||motorThread4.isAlive()){;}
    }
    public void rotateMotor(LinearOpMode opMode, double motorRotations, double motorPower, DcMotor motor){
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
    public void masterMotorControl(double motor1, double motor2, double motor3, double motor4){
        masterMotorControl(new double[]{motor1, motor2, motor3, motor4});

    }
    public void setPowerByVector(double amount_forward, double amount_sideways, double amount_turn){
        masterMotorControl(getPowersFromVector(amount_forward, amount_sideways, amount_turn));
    }
    public double[] getPowersFromVector(double amount_forward, double amount_sideways, double amount_turn){
        double RightwardPower = amount_forward + amount_sideways;
        double LeftwardPower = amount_forward - amount_sideways;
<<<<<<< Updated upstream
        double denominator = Math.max(Math.abs(amount_forward) + Math.abs(amount_sideways) + Math.abs(amount_turn), 1) * 2;
        return new double[] {(LeftwardPower - amount_turn) / denominator, (RightwardPower - amount_turn) / denominator, (LeftwardPower + amount_turn) / denominator, (RightwardPower + amount_turn) / denominator};
=======
        double denominator = (Math.abs(amount_forward) + Math.abs(amount_sideways) + Math.abs(amount_turn));
        return (denominator != 0
                ?new double[]{(LeftwardPower - amount_turn) / denominator, (RightwardPower - amount_turn) / denominator, (LeftwardPower + amount_turn) / denominator, (RightwardPower + amount_turn) / denominator}
                :new double[]{0,0,0,0});
    }
    public void moveByXYRobotOriented(double x, double y){
        double initialRotation1 = getCentimetersTravelled(motor1);
        double initialRotation2 = getCentimetersTravelled(motor2);
        double initialRotation3 = getCentimetersTravelled(motor3);
        while ((opMode.opModeIsActive()) && (((Math.abs(getCentimetersTravelled(motor1)-initialRotation1)) < y)||(Math.abs((getCentimetersTravelled(motor2)-initialRotation2)) < y)||(Math.abs((getCentimetersTravelled(motor3)-initialRotation3)) < x))){
            masterMotorControl(getPowersFromVector(y,x,0));
            }
        masterMotorControl2(0,0,0,0);
    }
    //Robot Oriented, so it will not move to a position on the field, it will move by an x and y relative to the robot's heading (In cm).
    public void turnByDegrees(double degree){
        double initialRotation1 = getCentimetersTravelled(motor1);
        double initialRotation2 = getCentimetersTravelled(motor2);
        double initialRotation3 = getCentimetersTravelled(motor3);
        while ((opMode.opModeIsActive()) && ((Math.abs(getCentimetersTravelled(motor1)-initialRotation1) < degree*(lengthBetweenEncodersVertically/2))||(Math.abs(getCentimetersTravelled(motor2)-initialRotation2) < degree*(lengthBetweenEncodersVertically/2))||(Math.abs(getCentimetersTravelled(motor3)-initialRotation3) < degree*(lengthBetweenEncodersHorizontally/2)))){
            masterMotorControl2(-Math.signum(degree),-Math.signum(degree),Math.signum(degree),Math.signum(degree));
        }
        masterMotorControl2(0,0,0,0);
>>>>>>> Stashed changes
    }
    public void outputOdometryData(){
        odometry.outputOdometryData();
    }
}
