// Initialize Gyro and Gyro subfunctions

import com.kauailabs.navx.frc.AHRS;

public class Gyro extends SubsystemBase {
    
    public Gyro() {
        try {

            /* Communicate w/navX MXP via the MXP SPI Bus.                                     */
            /* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
            /* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
            ahrs = new AHRS(SPI.Port.kMXP);

            System.out.println("Success: initialization gyro");

        } catch (RuntimeException ex ) {

            System.out.println("Error instantiating navX MXP:  " + ex.getMessage(), true);

        }
    }

}