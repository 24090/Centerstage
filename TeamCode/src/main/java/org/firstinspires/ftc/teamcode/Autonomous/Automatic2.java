package org.firstinspires.ftc.teamcode.Autonomous;
import com.qualcomm.hardware.dfrobot.HuskyLens;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.subsystems.MotorController;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;
import java.util.List;

@Autonomous()
public class Automatic2 extends OpMode {
    MotorController motorController = new MotorController();
    HuskyLens huskyLens;
    private AprilTagProcessor aprilTagProcessor;
    private TfodProcessor tFod;
    private VisionPortal visionPortal2;
    private String positionOfPixel;

    @Override
    public void init(){
        WebcamName webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");
        aprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();
        tFod = TfodProcessor.easyCreateWithDefaults();
        visionPortal2 = VisionPortal.easyCreateWithDefaults(webcamName, tFod);
        motorController.init(hardwareMap);
    }
    @Override
    public void init_loop(){
        List<Recognition> currentRecognitions = tFod.getRecognitions();
        telemetry.addData("# Objects Detected", currentRecognitions.size());
        for (Recognition recognition : currentRecognitions) {
            double x = (recognition.getLeft() + recognition.getRight()) / 2 ;
            double y = (recognition.getTop()  + recognition.getBottom()) / 2 ;
            telemetry.addData(""," ");
            telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
            telemetry.addData("- Position", "%.0f / %.0f", x, y);
            telemetry.addData("- Size", "%.0f x %.0f", recognition.getWidth(), recognition.getHeight());
        }
    }
    @Override
    public void start(){
        double initialTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - initialTime < 525){
            motorController.masterMotorControl(-0.5,-0.5,-0.5,-0.5);
        }
        initialTime = System.currentTimeMillis();
        while (System.currentTimeMillis()-initialTime < 5000){
            motorController.masterMotorControl(0,0,0,0);
            if (tFod.getRecognitions().size() == 1){
                positionOfPixel = "middle";
            }
            List<Recognition> currentRecognitions = tFod.getRecognitions();
            telemetry.addData("# Objects Detected", currentRecognitions.size());
            for (Recognition recognition : currentRecognitions) {
                double x = (recognition.getLeft() + recognition.getRight()) / 2 ;
                double y = (recognition.getTop()  + recognition.getBottom()) / 2 ;
                telemetry.addData(""," ");
                telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
                telemetry.addData("- Position", "%.0f / %.0f", x, y);
                telemetry.addData("- Size", "%.0f x %.0f", recognition.getWidth(), recognition.getHeight());
            }
        }
        initialTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - initialTime < 450){
            motorController.masterMotorControl(0.5,0.5,-0.5,-0.5);
        }
        initialTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - initialTime < 5000){
            motorController.masterMotorControl(0,0,0,0);
            if (tFod.getRecognitions().size() == 1){
                positionOfPixel = "right";
            }
            List<Recognition> currentRecognitions = tFod.getRecognitions();
            telemetry.addData("# Objects Detected", currentRecognitions.size());
            for (Recognition recognition : currentRecognitions) {
                double x = (recognition.getLeft() + recognition.getRight()) / 2 ;
                double y = (recognition.getTop()  + recognition.getBottom()) / 2 ;
                telemetry.addData(""," ");
                telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
                telemetry.addData("- Position", "%.0f / %.0f", x, y);
                telemetry.addData("- Size", "%.0f x %.0f", recognition.getWidth(), recognition.getHeight());
            }
        }
        initialTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - initialTime < 900){
            motorController.masterMotorControl(-0.5,-0.5,0.5,0.5);
        }
        initialTime = System.currentTimeMillis();
        while (System.currentTimeMillis()-initialTime < 5000){
            motorController.masterMotorControl(0,0,0,0);
            if (tFod.getRecognitions().size() == 1){
                positionOfPixel = "left";
            }
            List<Recognition> currentRecognitions = tFod.getRecognitions();
            telemetry.addData("# Objects Detected", currentRecognitions.size());
            for (Recognition recognition : currentRecognitions) {
                double x = (recognition.getLeft() + recognition.getRight()) / 2 ;
                double y = (recognition.getTop()  + recognition.getBottom()) / 2 ;
                telemetry.addData(""," ");
                telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
                telemetry.addData("- Position", "%.0f / %.0f", x, y);
                telemetry.addData("- Size", "%.0f x %.0f", recognition.getWidth(), recognition.getHeight());
            }
        }
        motorController.masterMotorControl(0,0,0,0);
        telemetry.addData("Position", positionOfPixel);
    }
    public void loop(){
    }
}

