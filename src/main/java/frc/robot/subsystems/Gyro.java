package frc.robot.subsystems;

// Initialize Gyro and Gyro subfunctions

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Gyro extends SubsystemBase {
    private final XboxController controller;
    private AHRS gyro;

    public Gyro(XboxController controller) {
        this.controller = controller;

        try {
            gyro = new AHRS(Port.kMXP);
        } catch (Exception e) {
            System.out.println("Gyro instantiating error: " + e);
        }

        System.out.println("Gyro Calibrated");
    }

}