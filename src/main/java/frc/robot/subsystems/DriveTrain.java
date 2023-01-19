package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
    private VictorSPX m0VictorSPX = new VictorSPX(0);
    private VictorSPX m1VictorSPX = new VictorSPX(1);
    private VictorSPX m2VictorSPX = new VictorSPX(2);

    private VictorSPX m4VictorSPX = new VictorSPX(4);
    private VictorSPX m5VictorSPX = new VictorSPX(5);
    private VictorSPX m6VictorSPX = new VictorSPX(6);

    private XboxController controller;
    
    public DriveTrain(XboxController controller) {
        this.controller = controller;
    }
}
