package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.CS.RT;
import org.firstinspires.ftc.teamcode.setup.CH;
import org.firstinspires.ftc.teamcode.setup.VP;

@Autonomous(name="CompAutoRedBack", group="Linear OpMode")

public class CompAutoRedBack extends LinearOpMode {
    public CH ch = null;
    private VP vp = null;
    private ElapsedTime stepTimer = new ElapsedTime();
    private String Location;

    @Override
    public void runOpMode() {
        telemetry.update();

        ch = new CH(hardwareMap, this);
        vp = new VP(hardwareMap, this);

        vp.initCompVision();

        telemetry.addData("Status", "initialized ");
        telemetry.update();

        waitForStart();

        stepTimer.reset();
        if (opModeIsActive())
        {
            Location = vp.TensorDetect();

            vp.visionPortal.setProcessorEnabled(vp.tfod,false); // turn off tesnor flow (tesnor is on purpose because tensor makes me wanna snore)
            ch.EncoderMove(750);

            if (Location == "left") {
                ch.imuTurn(57);
                ch.EncoderMove(RT.E_SPIKE_LEFT_RIGHT);
            } else if (Location == "right") {
                ch.imuTurn(-50);
                ch.EncoderMove(RT.E_SPIKE_LEFT_RIGHT);
            } else {
                ch.EncoderMove(RT.E_SPIKE_LEFT_CENTER);
            }
            ch.moveRobot(-0.4, 0, 0);
            sleep(500);
            ch.moveRobot(0, 0, 0);

            ch.imuTurn(100);

            vp.visionPortal.setActiveCamera(vp.webcam1);
            vp.setManualExposure(6, 250);  // Use low exposure time to reduce motion blur
            ch.moveAprilTag(vp);

        } // if active
    } // run op mode
} //linear op mode