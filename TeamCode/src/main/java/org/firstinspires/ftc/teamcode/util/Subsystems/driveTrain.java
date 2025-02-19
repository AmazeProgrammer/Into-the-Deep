package org.firstinspires.ftc.teamcode.util.Subsystems;

import androidx.annotation.NonNull;

import com.pedropathing.localization.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dev.frozenmilk.dairy.core.dependency.Dependency;
import dev.frozenmilk.dairy.core.dependency.annotation.SingleAnnotation;
import dev.frozenmilk.dairy.core.wrapper.Wrapper;
import dev.frozenmilk.mercurial.Mercurial;
import dev.frozenmilk.mercurial.commands.Lambda;
import dev.frozenmilk.mercurial.subsystems.Subsystem;
import kotlin.annotation.MustBeDocumented;

public class driveTrain implements Subsystem {
    private static GoBildaPinpointDriver imu;
    private static DcMotor[] motor = new DcMotor[4];
    public static final driveTrain INSTANCE = new driveTrain();
    private static boolean driveFieldCentric = true;

    private driveTrain() { }

    @Retention(RetentionPolicy.RUNTIME) @Target(ElementType.TYPE) @MustBeDocumented @Inherited
    public @interface Attach{}

    private Dependency<?> dependency = Subsystem.DEFAULT_DEPENDENCY.and(new SingleAnnotation<>(Attach.class));

    @NonNull
    @Override
    public Dependency<?> getDependency() {
        return dependency;
    }

    @Override
    public void setDependency(@NonNull Dependency<?> dependency) {
        this.dependency = dependency;
    }

    @Override
    public void preUserInitHook(@NonNull Wrapper opMode) {
    }
    @Override
    public void postUserInitHook(@NonNull Wrapper opMode) {
        HardwareMap hm = opMode.getOpMode().hardwareMap;
        motor = new DcMotor[]{hm.get(DcMotor.class, "leftFront"), hm.get(DcMotor.class, "rightFront"), hm.get(DcMotor.class, "leftBack"), hm.get(DcMotor.class, "rightBack")};
        imu = hm.get(GoBildaPinpointDriver.class, "imu");
        imu.resetPosAndIMU();
        setDefaultCommand(drive());
    }
    public static Lambda drive() {
        return new Lambda("drive")
                .addRequirements(INSTANCE)
                .setExecute(driveTrain::driveUpdate);
    }
    public static Lambda switchDrive() {
        return new Lambda("driveRobotCentric")
                .addRequirements(INSTANCE)
                .setInit(() -> driveFieldCentric = !driveFieldCentric);
    }
    public static Lambda resetHeading() {
        return new Lambda("resetHeading")
                .addRequirements(INSTANCE)
                .setInit(() -> imu.resetPosAndIMU());
    }
    public static void driveUpdate() {
        double y = Mercurial.gamepad1().leftStickY().state();
        double x = Mercurial.gamepad1().leftStickX().state();
        double z = Mercurial.gamepad1().rightStickX().state();
        if (driveFieldCentric) {
            imu.update();
            double botHeading = imu.getHeading();
            double rotX = 1.1 * (x * Math.cos(-botHeading) - y * Math.sin(-botHeading));
            double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

            double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(z), 1);

            motor[0].setPower((rotY + rotX + z) / denominator);
            motor[1].setPower((rotY - rotX - z) / denominator);
            motor[2].setPower((rotY - rotX + z) / denominator);
            motor[3].setPower((rotY + rotX - z) / denominator);
        }
        else {
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(z), 1);

            motor[0].setPower((y + x + z) / denominator);
            motor[1].setPower((y - x - z) / denominator);
            motor[2].setPower((y - x + z) / denominator);
            motor[3].setPower((y + x - z) / denominator);
        }
    }
}