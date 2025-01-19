package pedroPathing.constants;

import com.pedropathing.localization.*;
import com.pedropathing.localization.constants.*;

public class LConstants {
    static {
        ThreeWheelConstants.forwardTicksToInches = 0.0020107186487785013;
        ThreeWheelConstants.strafeTicksToInches = 0.002028160219112976;
        ThreeWheelConstants.turnTicksToInches = 0.002028161481753881;
        ThreeWheelConstants.leftY = 6.1850394;
        ThreeWheelConstants.rightY = -6.1692913;
        ThreeWheelConstants.strafeX = -6.6181102;
        ThreeWheelConstants.leftEncoder_HardwareMapName = "leftFront";
        ThreeWheelConstants.rightEncoder_HardwareMapName = "leftBack";
        ThreeWheelConstants.strafeEncoder_HardwareMapName = "rightFront";
        ThreeWheelConstants.leftEncoderDirection = Encoder.REVERSE;
        ThreeWheelConstants.rightEncoderDirection = Encoder.REVERSE;
        ThreeWheelConstants.strafeEncoderDirection = Encoder.FORWARD;
    }
}




