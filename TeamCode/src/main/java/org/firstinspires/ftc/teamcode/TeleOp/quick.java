package org.firstinspires.ftc.teamcode.TeleOp;

import static org.firstinspires.ftc.teamcode.TeleOp.servoPositions.*;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "quick")
public class quick extends LinearOpMode {
    //Initializing hardware
    private DcMotor leftFront, rightFront, leftBack, rightBack;
    private DcMotorEx linearslide1, linearslide2, linearslide3, linearslide4;
    private Servo frontServo1, frontServo2, backServo1, backServo2, turnFrontServo, turnBackServo, rotatingServo;
    private GoBildaPinpointDriver odocomputer;
    @Override
    public void runOpMode() throws InterruptedException {
        //Initializing variables
        double y, x, z, theta, sin, cos, max, power;
        //Variable finds
        int post, post2;
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        linearslide1 = hardwareMap.get(DcMotorEx.class, "linearslide1");
        linearslide2 = hardwareMap.get(DcMotorEx.class, "linearslide2");
        linearslide3 = hardwareMap.get(DcMotorEx.class, "linearslide3");
        linearslide4 = hardwareMap.get(DcMotorEx.class, "linearslide4");
        frontServo1 = hardwareMap.get(Servo.class, "frontServo1");
        frontServo2 = hardwareMap.get(Servo.class, "frontServo2");
        backServo1 = hardwareMap.get(Servo.class, "backServo1");
        backServo2 = hardwareMap.get(Servo.class, "backServo2");
        turnFrontServo = hardwareMap.get(Servo.class, "turnFrontServo");
        turnBackServo = hardwareMap.get(Servo.class, "turnBackServo");
        turnBackServo = hardwareMap.get(Servo.class, "turnBackServo");
        rotatingServo = hardwareMap.get(Servo.class, "rotatingServo");
        odocomputer = hardwareMap.get(GoBildaPinpointDriver.class, "odocomputer");
        odocomputer.resetPosAndIMU();
        linearslide1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearslide1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearslide2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearslide2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearslide3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearslide3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearslide4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearslide4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        linearslide1.setDirection(DcMotorSimple.Direction.REVERSE);
        linearslide2.setDirection(DcMotorSimple.Direction.REVERSE);
        frontServo2.setDirection(Servo.Direction.REVERSE);
        backServo2.setDirection(Servo.Direction.REVERSE);
        waitForStart();
        if (isStopRequested()) return;
        if (opModeIsActive()) {
            while(opModeIsActive()) {
                odocomputer.update();
                post = linearslide1.getCurrentPosition();
                if (-gamepad2.left_stick_y != 0 && ((post < tickRotation2 && post > 10) || (post < 10 && -gamepad2.right_stick_y > 0) || (post > tickRotation2 && -gamepad2.right_stick_y < 0))) {
                    linearslide2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    linearslide1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    linearslide2.setPower(-gamepad2.left_stick_y);
                    linearslide1.setPower(-gamepad2.left_stick_y);
                } else if (post > 10) {
                    linearslide2.setPower(0);
                    linearslide1.setPower(0);
                    linearslide2.setTargetPosition(post);
                    linearslide1.setTargetPosition(post);
                    linearslide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    linearslide1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    linearslide2.setVelocity(700);
                    linearslide1.setVelocity(700);
                }
                post2 = linearslide3.getCurrentPosition();
                if (-gamepad2.right_stick_y != 0 && ((post2 < tickRotation3 && post2 > 10) || (post2 < 10 && -gamepad2.right_stick_y > 0) || (post2 > tickRotation3 && -gamepad2.right_stick_y < 0))) {
                    linearslide3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    linearslide4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    linearslide3.setPower(-gamepad2.right_stick_y);
                    linearslide4.setPower(-gamepad2.right_stick_y);
                } else if (post2 > 10) {
                    linearslide3.setPower(0);
                    linearslide4.setPower(0);
                    linearslide3.setTargetPosition(post2);
                    linearslide4.setTargetPosition(post2);
                    linearslide3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    linearslide4.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    linearslide3.setVelocity(700);
                    linearslide4.setVelocity(700);
                }
                if (gamepad2.y) {
                    frontServo1.setPosition(frontServoClose);
                    frontServo2.setPosition(frontServoClose);
                } else if (gamepad2.b) {
                    frontServo1.setPosition(frontServoOpen);
                    frontServo2.setPosition(frontServoOpen);
                }
                if (gamepad2.a) {
                    backServo1.setPosition(backServoClose);
                    backServo2.setPosition(backServoClose);
                } else if (gamepad2.x) {
                    backServo2.setPosition(backServoOpen);
                    backServo1.setPosition(backServoOpen);
                }
                if (post > 1500) {
                    turnFrontServo.setPosition(turnFrontServoDown);
                } else if (post < 1000) {
                    turnFrontServo.setPosition(turnFrontServoUp);
                }
                if (gamepad2.dpad_up) {
                    turnBackServo.setPosition(turnBackServoBack);
                } else if (gamepad2.dpad_down) {
                    turnBackServo.setPosition(turnBackServoNormal);
                } else if (gamepad2.dpad_right) {
                    turnBackServo.setPosition(turnBackServoSpecimenFront);
                } else if (gamepad2.dpad_left) {
                    turnBackServo.setPosition(turnBackServoSpecimenBack);
                }
                if (gamepad2.right_bumper) {
                    rotatingServo.setPosition(rotatingServoPosition1);
                }
                else if (gamepad2.left_bumper) {
                    rotatingServo.setPosition(rotatingServoPosition2);
                }
                if (gamepad1.left_bumper) {
                    odocomputer.resetPosAndIMU();
                }
                double botHeading = odocomputer.getHeading();
                y = -gamepad1.left_stick_y;
                x = gamepad1.left_stick_x;
                z = gamepad1.right_stick_x;
                theta = Math.atan2(y,x);
                power = Math.hypot(x,y);
                sin = Math.sin((theta - botHeading) - Math.PI/4); //
                cos = Math.cos((theta - botHeading) - Math.PI/4);
                max = Math.max(Math.abs(sin), Math.abs(cos));

                leftFront.setPower(power * cos/max + z);
                leftBack.setPower(power * sin/max + z);
                rightFront.setPower(power * sin/max - z);
                rightBack.setPower(power * cos/max - z);

                double[] motor = {leftFront.getPower(), leftBack.getPower(), rightBack.getPower(), rightFront.getPower()};

                if ((power + Math.abs(z)) > 1) {
                    leftFront.setPower(motor[0]/(power + Math.abs(z)));
                    rightFront.setPower(motor[3]/(power + Math.abs(z)));
                    leftBack.setPower(motor[1]/(power + Math.abs(z)));
                    rightBack.setPower(motor[2]/(power + Math.abs(z)));
                }
                telemetry.update();
            }
        }
    }
}