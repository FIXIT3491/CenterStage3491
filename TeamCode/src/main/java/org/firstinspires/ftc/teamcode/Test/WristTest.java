package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.CaptainHook.CH;
@Disabled
@Autonomous(name="WristTest", group="Linear OpMode")

public class WristTest extends LinearOpMode {
    public CH ch = null;
    private ElapsedTime stepTimer = new ElapsedTime();
    private String Location;

    @Override
    public void runOpMode() {
        telemetry.update();

        ch = new CH(hardwareMap, this);

        telemetry.addData("Status", "initialized ");
        telemetry.update();

        ch.rightPincer.setPosition(0.55);

        waitForStart();
        stepTimer.reset();

        if (opModeIsActive()) {
            ch.armMove(2000);
        }
        sleep(1000);

        ch.rightPincer.setPosition(0.85);
        sleep(1000);
    }
}