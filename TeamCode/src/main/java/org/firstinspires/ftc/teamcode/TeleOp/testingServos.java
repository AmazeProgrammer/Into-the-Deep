package org.firstinspires.ftc.teamcode.TeleOp;

import static org.firstinspires.ftc.teamcode.TeleOp.servoPositions.*;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "quick")
public class testingServos extends LinearOpMode {
    //Initializing hardware
    private DcMotorEx linearslide1, linearslide2, linearslide3, turnBackServo;
    private Servo frontServo1, frontServo2, backServo1, backServo2, turnFrontServo, rotatingServo;
    private GoBildaPinpointDriver odocomputer;
    @Override
    public void runOpMode() throws InterruptedException {
        //Variable finds
        int post, post2, post3;
        linearslide1 = hardwareMap.get(DcMotorEx.class, "linearslide1");
        linearslide2 = hardwareMap.get(DcMotorEx.class, "linearslide2");
        linearslide3 = hardwareMap.get(DcMotorEx.class, "linearslide3");
        frontServo1 = hardwareMap.get(Servo.class, "frontServo1");
        frontServo2 = hardwareMap.get(Servo.class, "frontServo2");
        backServo1 = hardwareMap.get(Servo.class, "backServo1");
        backServo2 = hardwareMap.get(Servo.class, "backServo2");
        turnFrontServo = hardwareMap.get(Servo.class, "turnFrontServo");
        turnBackServo = hardwareMap.get(DcMotorEx.class, "turnBackServo");
        rotatingServo = hardwareMap.get(Servo.class, "rotatingServo");
        linearslide1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearslide1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearslide2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearslide2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearslide3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearslide3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        turnBackServo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        turnBackServo.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        waitForStart();
        if (isStopRequested()) return;
        if (opModeIsActive()) {
            while(opModeIsActive()) {
                post = linearslide1.getCurrentPosition();
                post3 = linearslide3.getCurrentPosition();
                post2 = linearslide2.getCurrentPosition();
                if (gamepad2.y) {
                    frontServo1.setPosition(frontServoClose);
                    frontServo2.setPosition(frontServoClose2);
                } else if (gamepad2.a) {
                    frontServo1.setPosition(frontServoOpen);
                    frontServo2.setPosition(frontServoOpen2);
                }
                if (gamepad2.x) {
                    backServo1.setPosition(backServoClose);
                    backServo2.setPosition(backServoClose2);
                } else if (gamepad2.b) {
                    backServo2.setPosition(backServoOpen2);
                    backServo1.setPosition(backServoOpen);
                }
                if (gamepad1.y) {
                    turnFrontServo.setPosition(turnFrontServoDown);
                } else if (gamepad1.a) {
                    turnFrontServo.setPosition(turnFrontServoUp);
                }
                if (gamepad2.dpad_up) {
                    turnBackServo.setTargetPosition(turnBackServoBack);
                    turnBackServo.setVelocity(700);
                } else if (gamepad2.dpad_down) {
                    turnBackServo.setTargetPosition(turnBackServoNormal);
                    turnBackServo.setVelocity(700);
                } else if (gamepad2.dpad_right) {
                    turnBackServo.setTargetPosition(turnBackServoSpecimenFront);
                    turnBackServo.setVelocity(700);
                } else if (gamepad2.dpad_left) {
                    turnBackServo.setTargetPosition(turnBackServoSpecimenBack);
                    turnBackServo.setVelocity(700);
                }
                if (gamepad2.right_bumper) {
                    rotatingServo.setPosition(rotatingServoPosition1);
                }
                else if (gamepad2.left_bumper) {
                    rotatingServo.setPosition(rotatingServoPosition2);
                }
                telemetry.addData("post", post);
                telemetry.addData("post2", post2);
                telemetry.addData("post3", post3);
                telemetry.update();
            }
        }
    }
}