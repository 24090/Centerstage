package org.firstinspires.ftc.teamcode.subsystems;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

// keeps track of current x, y and rotation in global coordinates
// see https://ftc-docs.firstinspires.org/en/latest/game_specific_resources/field_coordinate_system/field-coordinate-system.html
// that coordinate system, but in cm
// for use with dead wheels
public class Odometry {
    // uses first and last items in motor history to estimate speed in ticks/second
    double tickToCMConstant = (2 * Math.PI * 2.4)/2000;
    // TODO: put in correct values for encoder offsets
    double horizontalEncoderOffset = 31.1;
    double verticalEncoderOffset = 1;
    double xPosition;
    double yPosition;
    double angle;
    double pod1Change;
    double pod2Change;
    double pod3Change;
    double lastPod1;
    double lastPod2;
    double lastPod3;
    LinearOpMode opMode;
    // Time, Encoder Ticks
    public Odometry(LinearOpMode opMode, double pod1, double pod2, double pod3){
        this.opMode = opMode;
        lastPod1 = pod1;
        lastPod2 = pod2;
        lastPod3 = pod3;
    }
    public void update_odometry_data(double pod1, double pod2, double pod3) {
        pod1Change = -lastPod1 + pod1;
        pod2Change = -lastPod2 + pod2;
        pod3Change = -lastPod3 + pod3;
        lastPod1 = pod1;
        lastPod2 = pod2;
        lastPod3 = pod3;

        double xChange = tickToCMConstant * (pod1Change + pod2Change)/2;
        double angleChange = tickToCMConstant * (pod1Change - pod2Change)/horizontalEncoderOffset;
        double yChange = tickToCMConstant * (pod3Change - verticalEncoderOffset * angleChange);

        angle += angleChange;
        xPosition += Math.cos(angle) * xChange - Math.sin(angle) * yChange;
        yPosition += Math.sin(angle) * xChange + Math.cos(angle) * yChange;
    }
    public void outputOdometryData(){
        telemetry.addData("X\t", xPosition);
        telemetry.addData("Y\t", yPosition);
        telemetry.addData("Angle\t", angle);
        telemetry.update();
    }
}