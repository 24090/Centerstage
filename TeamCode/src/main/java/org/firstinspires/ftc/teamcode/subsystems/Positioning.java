package org.firstinspires.ftc.teamcode.subsystems;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class Positioning {
    LinearOpMode opMode1;
    double lengthBetweenEncodersVertically;
    double lengthBetweenEncodersHorizontally;
    MotorController motorController = new MotorController(null);
    double tickToCMConstant = (2 * Math.PI * 2.4)/2000;
    public DcMotor motor1 = motorController.motor1;
    public DcMotor motor2 = motorController.motor2;
    public DcMotor motor3 = motorController.motor3;
    double[] initialPosition= {0,0};
    double[] currentPosition = {0,0,0};
    double[] changeVector;
    int movements = 0;
    double heading = 0;
    public Positioning(LinearOpMode opMode){
        opMode1 = opMode;
    }
    public void init(HardwareMap hardwareMap){
        motorController.init(hardwareMap);
    }

    public double[] updatePosition(){
        while (opMode1.opModeIsActive()){
            changeVector = new double[]{tickToCMConstant * ((motorController.motor1.getCurrentPosition() + motorController.motor2.getCurrentPosition()) / 2), tickToCMConstant * (motorController.motor3.getCurrentPosition() - lengthBetweenEncodersHorizontally * ((motorController.motor2.getCurrentPosition() - motorController.motor1.getCurrentPosition()) / lengthBetweenEncodersVertically)), tickToCMConstant * ((motorController.motor2.getCurrentPosition() - motorController.motor1.getCurrentPosition()) / lengthBetweenEncodersVertically)};
            if (movements == 0){
                heading = heading + changeVector[2];
                currentPosition[0] = initialPosition[0] + Math.cos(heading)*changeVector[0] - Math.sin(heading)*changeVector[1];
                currentPosition[1] = initialPosition[1] + Math.sin(heading)*changeVector[0] + Math.cos(heading)*changeVector[1];
                movements++;
            } else {
                heading = heading + changeVector[2];
                currentPosition[0] = currentPosition[0] + Math.cos(heading)*changeVector[0] - Math.sin(heading)*changeVector[1];
                currentPosition[1] = currentPosition[1] + Math.sin(heading)*changeVector[0] + Math.cos(heading)*changeVector[1];
                movements++;
            }
        }
        return currentPosition;
    }
    public double getCentimetersTravelled(DcMotor motor) {
        return motor.getCurrentPosition() * tickToCMConstant;
    }
}
