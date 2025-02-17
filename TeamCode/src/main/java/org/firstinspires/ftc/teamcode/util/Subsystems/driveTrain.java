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
    public static GoBildaPinpointDriver imu;
    public static DcMotor[] motor = new DcMotor[4];
    public static final driveTrain INSTANCE = new driveTrain();

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
    @Override
    public void preUserInitLoopHook(@NonNull Wrapper opMode) {}
    @Override
    public void preUserLoopHook(@NonNull Wrapper opMode) {}
    @Override
    public void postUserInitLoopHook(@NonNull Wrapper opMode) {}
    @Override
    public void postUserLoopHook(@NonNull Wrapper opMode) {}
    @Override
    public void preUserStopHook(@NonNull Wrapper opMode) {}
    @Override
    public void postUserStopHook(@NonNull Wrapper opMode) {}
    @Override
    public void cleanup(@NonNull Wrapper opMode) {}
    public static Lambda drive() {
        return new Lambda("drive")
                .addRequirements(INSTANCE)
                .addExecute(driveTrain::driveUpdate);
    }
    public static Lambda resetHeading() {
        return new Lambda("resetHeading")
                .addRequirements(INSTANCE)
                .setInit(() -> imu.resetPosAndIMU());
    }
    public static void driveUpdate() {
        double botHeading = imu.getHeading();
        double y = Mercurial.gamepad1().leftStickY().state();
        double x = Mercurial.gamepad1().leftStickX().state();
        double z = Mercurial.gamepad1().rightStickX().state();
        double theta = Math.atan2(y,x);
        double power = Math.hypot(x,y);
        double sin = Math.sin((theta - botHeading) - Math.PI/4); //
        double cos = Math.cos((theta - botHeading) - Math.PI/4);
        double max = Math.max(Math.abs(sin), Math.abs(cos));

        motor[0].setPower(power * cos/max + z);
        motor[1].setPower(power * sin/max + z);
        motor[2].setPower(power * sin/max - z);
        motor[3].setPower(power * cos/max - z);

        if ((power + Math.abs(z)) > 1) {for (DcMotor thismotor : motor) {thismotor.setPower(thismotor.getPower()/power + Math.abs(z));}}
    }
}