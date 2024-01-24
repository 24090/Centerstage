package org.firstinspires.ftc.teamcode.subsystems;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class Positioning {
    public DcMotor motor1;
    public DcMotor motor2;
    public DcMotor motor3;
    public DcMotor motor4;
    LinearOpMode opMode1;
    boolean opModeIsActive;
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

    public void updatePosition(LinearOpMode opMode){
        while (opMode.opModeIsActive()){
            changeVector = new double[]{tickToCMConstant * ((motorController.getTicks(motor1) + motorController.getTicks(motor2)) / 2), tickToCMConstant * (motorController.getTicks(motor3) - lengthBetweenEncodersHorizontally * ((motorController.getTicks(motor2) - motorController.getTicks(motor1)) / lengthBetweenEncodersVertically)), tickToCMConstant * ((motorController.getTicks(motor2) - motorController.getTicks(motor1)) / lengthBetweenEncodersVertically)};
            if (movements == 0){
                heading = heading + changeVector[3];
                currentPosition[1] = initialPosition[1] + changeVector[1];
                currentPosition[2] = initialPosition[2] + changeVector[2];
                movements++;
            } else {
                heading = heading + changeVector[3];
                currentPosition[1] = currentPosition[1] + changeVector[1];
                currentPosition[2] = currentPosition[2] + changeVector[2];
            }
        }
    }
    Thread positionUpdater = new Thread(() -> {updatePosition(opMode1);});
    public double[] getPositionOnBoard(){
        return currentPosition;
    }


}
