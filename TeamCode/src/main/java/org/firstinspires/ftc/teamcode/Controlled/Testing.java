package org.firstinspires.ftc.teamcode.Controlled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.subsystems.MotorController;
@TeleOp()
public class Testing extends OpMode{
    MotorController motorController = new MotorController();
    @Override
    public void init(){
        motorController.init(hardwareMap);
    }
    public void loop(){
        if (gamepad1.b){
            motorController.motorMasterRotate("back",1,1,1,1);
        }

    }
}
