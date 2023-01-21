// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.MjpegServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.VideoSource.ConnectionStrategy;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  XboxController controller = new XboxController(0);

  // declaring motor controllers
  VictorSPX m0Spx = new VictorSPX(0);

  // the pneumatics (compressor, solenoid, pressure switch)
  Compressor compressor = new Compressor(PneumaticsModuleType.CTREPCM);
  Solenoid solenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 0);

  // gyro
  AHRS gyroAhrs = new AHRS();

  // camera init
  // UsbCamera cam1UsbCamera = new UsbCamera("USB Camera 0", 0);
  // this shit doesn't work 
  // use the start automatic capture from cameraserver

  // http://roborio-team-frc.local:1181/
  // for viewing the camera results 1181 is the default port result
  UsbCamera cam1UsbCamera;
  UsbCamera cam2UsbCamera;
  
  MjpegServer m1MjpegServer = new MjpegServer("Camera 1 Output", 1200); // this is for output of camera 
  MjpegServer m2MjpegServer = new MjpegServer("Camera 2 Output", 1201); // this is for output of camera 
  // onto the shuffleboard
  

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();

    compressor.enableDigital();

    gyroAhrs.calibrate();

    cam1UsbCamera = CameraServer.startAutomaticCapture(0);
    cam2UsbCamera = CameraServer.startAutomaticCapture(1);

    cam1UsbCamera.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
    
    m1MjpegServer.setSource(cam1UsbCamera);

    
    // just testing
    SmartDashboard.putString("Fuck", "Mommy is hot");
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();

    // m0Spx.set(ControlMode.PercentOutput, 1);

    if (controller.getLeftBumper()) {
      // forward means shooting the di** out of the tube
      // it is coming out
      solenoid.set(true);
    } 
    if (controller.getRightBumper()) {
      // reverse is when it is going back in
      solenoid.set(false);
    }

    if (controller.getAButton()) {
      System.out.println("A button xbox pressed");
      compressor.enableDigital();
    }
    
    if (controller.getBButtonPressed()) {
      System.out.println("B button xbox pressed");
      compressor.disable();
    }
    if (controller.getAButtonReleased()) {
      compressor.enableDigital();
    }

    // System.out.println(gyroAhrs.getAngle());
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {
    compressor.disable();
  }

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
