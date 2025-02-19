package org.firstinspires.ftc.teamcode.util.Subsystems;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.HardwareMap;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dev.frozenmilk.dairy.core.dependency.Dependency;
import dev.frozenmilk.dairy.core.dependency.annotation.SingleAnnotation;
import dev.frozenmilk.dairy.core.wrapper.Wrapper;
import dev.frozenmilk.mercurial.subsystems.Subsystem;
import kotlin.annotation.MustBeDocumented;

public class intakeLinearSlides implements Subsystem {
    public static final intakeLinearSlides INSTANCE = new intakeLinearSlides();
    private intakeLinearSlides() { }

    @Retention(RetentionPolicy.RUNTIME) @Target(ElementType.TYPE) @MustBeDocumented
    @Inherited
    public @interface Attach{}

    private Dependency<?> dependency = Subsystem.DEFAULT_DEPENDENCY.and(new SingleAnnotation<>(driveTrain.Attach.class));

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
}
