package org.firstinspires.ftc.teamcode.OpMode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.OpenCV.Camera.Camera;

@TeleOp(name = "SampleCameraOpMode", group = "Testing")
public class SampleCameraOpMode extends LinearOpMode {
    Camera camera = new Camera(hardwareMap);
    @Override
    public void runOpMode() throws InterruptedException {
        camera.switchToFirstPipeline();
        telemetry.addLine("Status: Initialized");
        waitForStart();
        while (opModeIsActive()) {
            // OpMode receives the information transmitted from the pipeline class
            // to the camera module class.
            telemetry.addLine(camera.getPiplineLocation());
            telemetry.update();
        }
    }
}
