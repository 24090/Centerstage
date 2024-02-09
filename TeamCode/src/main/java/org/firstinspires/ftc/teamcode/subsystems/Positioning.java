package org.firstinspires.ftc.teamcode.subsystems;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class Positioning {
    public DcMotor motor1;
    public DcMotor motor2;
    public DcMotor motor3;
    LinearOpMode opMode1;
    double lengthBetweenEncodersVertically;
    double lengthBetweenEncodersHorizontally;
    MotorController motorController = new MotorController(null);
    double tickToCMConstant = (2 * Math.PI * 2.4)/2000;
    double[] initialPosition= {0,0};
    double[] currentPosition;
    double[] changeVector;
    int movements = 0;
    double heading = 0;
    public Positioning(LinearOpMode opMode){
        opMode1 = opMode;
    }

    public double[] updatePosition(){
        while (opMode1.opModeIsActive()){
            changeVector = new double[]{tickToCMConstant * ((motorController.getTicks(motor1) + motorController.getTicks(motor2)) / 2), tickToCMConstant * (motorController.getTicks(motor3) - lengthBetweenEncodersHorizontally * ((motorController.getTicks(motor2) - motorController.getTicks(motor1)) / lengthBetweenEncodersVertically)), tickToCMConstant * ((motorController.getTicks(motor2) - motorController.getTicks(motor1)) / lengthBetweenEncodersVertically)};
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
        return motorController.getEncoderTicks(motor) * tickToCMConstant;
    }
}
