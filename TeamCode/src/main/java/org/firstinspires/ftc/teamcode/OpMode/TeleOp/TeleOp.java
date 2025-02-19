package org.firstinspires.ftc.teamcode.OpMode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.util.Subsystems.driveTrain;

import dev.frozenmilk.mercurial.Mercurial;
import dev.frozenmilk.mercurial.bindings.BoundGamepad;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp")
@Mercurial.Attach
@driveTrain.Attach
public class TeleOp extends OpMode {
    private BoundGamepad gamepad1 = Mercurial.gamepad1();
    @Override
    public void init() {
        gamepad1.a()
                .onTrue(driveTrain.switchDrive());
        gamepad1.options()
                .onTrue(driveTrain.resetHeading());
    }

    @Override
    public void loop() {
    }
}
