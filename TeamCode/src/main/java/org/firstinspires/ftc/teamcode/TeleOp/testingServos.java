package org.firstinspires.ftc.teamcode.TeleOp;

import static org.firstinspires.ftc.teamcode.TeleOp.servoPositions.*;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "ok")
public class testingServos extends LinearOpMode {
    //Initializing hardware
    private DcMotorEx arm;
    private Servo frontServoRight, frontServoLeft, backServoRight, backServoLeft, turnFrontServo, rotatingServo, turnBackServo;
    private GoBildaPinpointDriver odocomputer;
    @Override
    public void runOpMode() throws InterruptedException {
        //Variable finds
        frontServoRight = hardwareMap.get(Servo.class, "frontServoRight");
        frontServoLeft = hardwareMap.get(Servo.class, "frontServoLeft");
        backServoRight = hardwareMap.get(Servo.class, "backServoRight");
        backServoLeft = hardwareMap.get(Servo.class, "backServoLeft");
        turnFrontServo = hardwareMap.get(Servo.class, "turnFrontServo");
        turnBackServo = hardwareMap.get(Servo.class, "turnBackServo");
        rotatingServo = hardwareMap.get(Servo.class, "rotatingServo");
        arm = hardwareMap.get(DcMotorEx.class, "arm");
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        if (frontServoRightReverse) {
            frontServoRight.setDirection(Servo.Direction.REVERSE);
        }
        if (frontServoLeftReverse) {
            frontServoLeft.setDirection(Servo.Direction.REVERSE);
        }
        if (backServoRightReverse) {
            backServoRight.setDirection(Servo.Direction.REVERSE);
        }
        if (backServoLeftReverse) {
            backServoLeft.setDirection(Servo.Direction.REVERSE);
        }
        if (turnBackServoReverse) {
            turnBackServo.setDirection(Servo.Direction.REVERSE);
        }
        if (turnFrontServoReverse) {
            turnFrontServo.setDirection(Servo.Direction.REVERSE);
        }
        if (rotatingServoReverse) {
            rotatingServo.setDirection(Servo.Direction.REVERSE);
        }
        waitForStart();
        if (isStopRequested()) return;
        if (opModeIsActive()) {
            while(opModeIsActive()) {
                if (gamepad2.y) {
                    frontServoRight.setPosition(frontServoRightClose);
                    frontServoLeft.setPosition(frontServoLeftClose);
                } else if (gamepad2.a) {
                    frontServoRight.setPosition(frontServoRightOpen);
                    frontServoLeft.setPosition(frontServoLeftOpen);
                }
                if (gamepad2.x) {
                    backServoRight.setPosition(backServoRightClose);
                    backServoLeft.setPosition(backServoLeftClose);
                } else if (gamepad2.b) {
                    backServoLeft.setPosition(backServoLeftOpen);
                    backServoRight.setPosition(backServoRightOpen);
                }
                if (gamepad1.y) {
                    turnFrontServo.setPosition(turnFrontServoUp);
                } else if (gamepad1.a) {
                    turnFrontServo.setPosition(turnFrontServoDown);
                }
                if (gamepad2.dpad_up) {
                    turnBackServo.setPosition(turnBackServoBucket);
                } else if (gamepad2.dpad_down) {
                    turnBackServo.setPosition(turnBackServoNormal);
                } else if (gamepad2.dpad_right) {
                    turnBackServo.setPosition(turnBackServoSpecimenFront);
                } else if (gamepad2.dpad_left) {
                    turnBackServo.setPosition(turnBackServoSpecimenBack);
                }
                if (gamepad2.right_bumper) {
                    rotatingServo.setPosition(rotatingServoPositionNormal);
                }
                else if (gamepad2.left_bumper) {
                    rotatingServo.setPosition(rotatingServoPosition180);
                }
                telemetry.addData("arm", arm.getCurrentPosition());
                telemetry.update();
            }
        }
    }
}