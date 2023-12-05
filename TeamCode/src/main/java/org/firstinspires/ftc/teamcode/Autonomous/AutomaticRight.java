package org.firstinspires.ftc.teamcode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.subsystems.MotorController;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

@Autonomous()
public class AutomaticRight extends LinearOpMode {
    private AprilTagProcessor aprilTagProcessor;
    private TfodProcessor tFod;
    private VisionPortal visionPortal;
    MotorController motorController;
    private String positionOfPixel;
    // 55 degrees
    @Override
    public void runOpMode() {
        motorController = new MotorController();
        motorController.init(hardwareMap);
        waitForStart();
        if (opModeIsActive()) {
            motorController.servoSetPosition(0.9);
            motorController.masterMotorControl( -0.4,-0.4,-0.4,-0.4);
            sleep(200);
            motorController.masterMotorControl(0,0,0,0);
            sleep(200);
            motorController.masterMotorControl(-0.4,0.4,-0.4,0.4);
            sleep(1200);
            motorController.masterMotorControl(0,0,0,0);
            sleep(200);
            motorController.masterMotorControl( -0.4,-0.4,-0.4,-0.4);
            sleep(200);
            motorController.masterMotorControl(0,0,0,0);
            sleep(200);
            motorController.masterMotorControl( 0.4,0.4,0.4,0.4);
            sleep(350);
            motorController.masterMotorControl(0,0,0,0);
        }
    }
}

