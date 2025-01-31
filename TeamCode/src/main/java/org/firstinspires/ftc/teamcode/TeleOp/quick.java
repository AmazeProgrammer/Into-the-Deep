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
    private DcMotorEx linearslide1, linearslide2, linearslide3, arm;
    private Servo frontServoRight, frontServoLeft, backServoRight, backServoLeft, turnFrontServo, rotatingServo, turnBackServo;
    private GoBildaPinpointDriver odocomputer;
    @Override
    public void runOpMode() throws InterruptedException {
        //Initializing variables
        double y, x, z, theta, sin, cos, max, power;
        //Variable finds
        int post, post2;
        //Hardware Initialization
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        linearslide1 = hardwareMap.get(DcMotorEx.class, "linearslide1");
        linearslide2 = hardwareMap.get(DcMotorEx.class, "linearslide2");
        linearslide3 = hardwareMap.get(DcMotorEx.class, "linearslide3");
        frontServoRight = hardwareMap.get(Servo.class, "frontServoRight");
        frontServoLeft = hardwareMap.get(Servo.class, "frontServoLeft");
        backServoRight = hardwareMap.get(Servo.class, "backServoRight");
        backServoLeft = hardwareMap.get(Servo.class, "backServoLeft");
        turnFrontServo = hardwareMap.get(Servo.class, "turnFrontServo");
        turnBackServo = hardwareMap.get(Servo.class, "turnBackServo");
        rotatingServo = hardwareMap.get(Servo.class, "rotatingServo");
        arm = hardwareMap.get(DcMotorEx.class, "arm");
        odocomputer = hardwareMap.get(GoBildaPinpointDriver.class, "odocomputer");
        //RunModes and Reset
        odocomputer.resetPosAndIMU();
        linearslide1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearslide1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearslide2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearslide2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearslide3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearslide3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //Reversing
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        linearslide3.setDirection(DcMotorSimple.Direction.REVERSE);
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
                odocomputer.update();
                post = linearslide1.getCurrentPosition();
                if (-gamepad2.left_stick_y != 0 && ((post < tickRotation2 && post > 10) || (post < 10 && -gamepad2.right_stick_y > 0) || (post > tickRotation2 && -gamepad2.right_stick_y < 0))) {
                    linearslide1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    linearslide1.setPower(-gamepad2.left_stick_y);
                } else if (post > 10) {
                    linearslide1.setPower(0);
                    linearslide1.setTargetPosition(post);
                    linearslide1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    linearslide1.setVelocity(700);
                }
                post2 = linearslide3.getCurrentPosition();
                if (-gamepad2.right_stick_y != 0 && ((post2 < tickRotation3 && post2 > 10) || (post2 < 10 && -gamepad2.right_stick_y > 0) || (post2 > tickRotation3 && -gamepad2.right_stick_y < 0))) {
                    linearslide2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    linearslide3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    linearslide3.setPower(-gamepad2.right_stick_y);
                    linearslide2.setPower(-gamepad2.right_stick_y);
                } else if (post2 > 10) {
                    linearslide3.setPower(0);
                    linearslide2.setPower(0);
                    linearslide3.setTargetPosition(post2);
                    linearslide2.setTargetPosition(post2);
                    linearslide3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    linearslide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    linearslide3.setVelocity(700);
                    linearslide2.setVelocity(700);
                }
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
                if (post > 1500) {
                    turnFrontServo.setPosition(turnFrontServoDown);
                } else if (post < 1000) {
                    turnFrontServo.setPosition(turnFrontServoUp);
                }
                if (gamepad2.dpad_up) {
                    arm.setTargetPosition(armBucket);
                    arm.setVelocity(700);
                    turnBackServo.setPosition(turnBackServoBucket);
                } else if (gamepad2.dpad_down) {
                    arm.setTargetPosition(armNormal);
                    arm.setVelocity(700);
                    turnBackServo.setPosition(turnBackServoNormal);
                } else if (gamepad2.dpad_right) {
                    arm.setTargetPosition(armSpecimenFront);
                    arm.setVelocity(700);
                    turnBackServo.setPosition(turnBackServoSpecimenFront);
                } else if (gamepad2.dpad_left) {
                    arm.setTargetPosition(armSpecimenBack);
                    arm.setVelocity(700);
                    turnBackServo.setPosition(turnBackServoSpecimenBack);
                }
                if (gamepad2.right_bumper) {
                    rotatingServo.setPosition(rotatingServoPositionNormal);
                }
                else if (gamepad2.left_bumper) {
                    rotatingServo.setPosition(rotatingServoPosition180);
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
                telemetry.addData("post", post);
                telemetry.addData("post2", post2);
                telemetry.addData("post2", linearslide3.getCurrentPosition());
                telemetry.update();
            }
        }
    }
}