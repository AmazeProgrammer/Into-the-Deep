package org.firstinspires.ftc.teamcode.TeleOp;

import com.acmerobotics.dashboard.config.Config;

@Config
public class servoPositions {
    public static double frontServoRightOpen = 0.63;
    public static double frontServoLeftOpen = 0.5;
    public static double frontServoRightClose = 0.49;
    public static double frontServoLeftClose = 0.34;
    public static double backServoRightOpen = 0.1;
    public static double backServoLeftOpen = 0.48;
    public static double backServoRightClose = 0;
    public static double backServoLeftClose = 0.37;
    public static double turnFrontServoDown = 0;
    public static double turnFrontServoUp = 0;
    public static int turnBackServoSpecimenBack = 0;
    public static int turnBackServoSpecimenFront = 0;
    public static int turnBackServoBucket = 0;
    public static int turnBackServoNormal = 0;
    public static int armUp = 0;
    public static int armSpecimenBack = 0;
    public static int armSpecimenFront = 0;
    public static int armBucket = 0;
    public static int armNormal = 0;
    public static double rotatingServoPositionNormal = 0;
    public static double rotatingServoPosition180 = 0;
    public static int tickRotation3 = 4200;
    public static int tickRotation2 = 6000;
    //Reverse
    public static boolean frontServoRightReverse = true;
    public static boolean frontServoLeftReverse = false;
    public static boolean backServoRightReverse = true;
    public static boolean backServoLeftReverse = false;
    public static boolean turnFrontServoReverse = false;
    public static boolean turnBackServoReverse = false;
    public static boolean rotatingServoReverse = false;
}
