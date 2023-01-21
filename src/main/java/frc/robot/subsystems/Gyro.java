// Initialize Gyro and Gyro subfunctions

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;


public class Gyro extends SubsystemBase {
    
    // Create a new Gyro
    public Gyro() {}

    // initialize Gyro once

    runOnce(
        () -> {

          /* one-time action goes here */
          gyroInit();
          
        }
    );

    // All Gyro functions

    public boolean gyroInit() {

        try {

            /* Communicate w/navX MXP via the MXP SPI Bus.                                     */
            /* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
            /* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
            AHRS ahrs = new AHRS(SPI.Port.kMXP);

            System.out.println("Success: initialization gyro");

        } catch (RuntimeException ex ) {

            System.out.println("Error instantiating navX MXP:  " + ex.getMessage(), true);

            return false;

        }

        return true; 

    }

}