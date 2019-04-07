/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.commands.Autofront;
import frc.robot.commands.CentreHatch;
import frc.robot.commands.DriveCmd;
import frc.robot.commands.AutoRight1;
import frc.robot.commands.AutoRight2;
import frc.robot.commands.AutoRight3;
import frc.robot.commands.AutoRightHatch;
import frc.robot.commands.AutoLeft1;
import frc.robot.commands.AutoLeft2;
import frc.robot.commands.AutoLeft3;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.ResetElevatorEncoderCommand;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.ElevatorTilt;
import frc.robot.subsystems.ElevatorWinch;
import frc.robot.subsystems.CargoLoader;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.FrontClimber;
import frc.robot.commands.ElevatorTiltCmd;
import frc.robot.commands.ElevatorTune;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
// import frc.robot.subsystems.MecanumDriver;
import frc.robot.subsystems.HatchRelease;
import frc.robot.chenyxVision.AutoRun;
// import frc.robot.subsystems.PIDElevator;
import frc.robot.chenyxVision.HUD;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */


public class Robot extends TimedRobot {

  public static final String MecanumDriver = null;
  public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
  public static OI m_oi;
  public static DriveSub driveSub = new DriveSub();
  public static CargoLoader cargoLoader = new CargoLoader();
  public static HatchRelease hatchRelease = new HatchRelease();
  public static ElevatorTilt elevatorTilt = new ElevatorTilt();
  public static ElevatorWinch elevatorWinch = new ElevatorWinch();
  public static FrontClimber frontClimber = new FrontClimber();
  //public static UsbCamera camera;
  private static HUD hud = HUD.getInstance();
 
  public Command elevatorTuning = new ElevatorTune();
  public Command drivingCmd = new DriveCmd();
  // public static PIDElevator pIDElevatorWinch = new PIDElevator();
  // public static DoubleSolenoid hatchPusher = new DoubleSolenoid(RobotMap.hatchSole1, RobotMap.hatchSole2);

  // public static MecanumDriver mecanumDriver = new MecanumDriver();

  public AHRS ahrs;
  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    //camera = CameraServer.getInstance().startAutomaticCapture();

    m_oi = new OI();
    
    //m_chooser.addDefault("Default Auto", new ExampleCommand());
    m_chooser.addObject("Center", new Autofront());
    m_chooser.addObject("Left1", new AutoLeft1());
    m_chooser.addObject("Left2", new AutoLeft2());
    m_chooser.addObject("Left3", new AutoLeft3());
    m_chooser.addObject("centerhatch", new CentreHatch());
    m_chooser.addObject("Right1", new AutoRight1());
    m_chooser.addObject("Right2", new AutoRight2());
    m_chooser.addObject("Right3", new AutoRight3());
    m_chooser.addObject("RightHatch1", new AutoRightHatch());
    SmartDashboard.putData("Auto mode", m_chooser);
  
    driveSub.encoderReset();
    driveSub.encoderInit();
    driveSub.gyroReset();
    driveSub.gryoInit();
  
    System.out.printf("robotInit");

    //try {
		// 	/***********************************************************************
		// 	 * navX-MXP:
		// 	 * - Communication via RoboRIO MXP (SPI, I2C, TTL UART) and USB.            
		// 	 * - See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface.
		// 	 * 
		// 	 * navX-Micro:
		// 	 * - Communication via I2C (RoboRIO MXP or Onboard) and USB.
		// 	 * - See http://navx-micro.kauailabs.com/guidance/selecting-an-interface.
		// 	 * 
		// 	 * Multiple navX-model devices on a single robot are supported.
		// 	 ************************************************************************/
    //        ahrs = new AHRS(SPI.Port.kMXP);
    //     } catch (RuntimeException ex ) {
    //         DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
    //     }
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    Scheduler.getInstance().run();
    driveSub.encoderUpdate();
    driveSub.gyroUpdate();
    Robot.elevatorWinch.eleEncoderUpdate();
    Robot.elevatorWinch.updateElevatorStatus();

   }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      Robot.elevatorWinch.ElevatorEncoderReset();
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    if (m_oi.joystickZero.getRawButton(9)) {
      m_autonomousCommand.cancel();
      elevatorTuning.start();
      drivingCmd.start();
    }
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    SmartDashboard.putData("Reset Winch Encoders", new ResetElevatorEncoderCommand());
    SmartDashboard.putData("ElevatorTilt: Forward", new ElevatorTiltCmd(Value.kForward));
    SmartDashboard.putData("ElevatorTilt: Back", new ElevatorTiltCmd(Value.kReverse));
    driveSub.gyroReset();
    driveSub.encoderReset();
    // hatchPusher.set(DoubleSolenoid.Value.kForward);
    // hatchPusher.set(DoubleSolenoid.Value.kOff);
    Robot.hatchRelease.pushBack();
    
    //Initialize Elevator Tilt (if neccessary)
    // Robot.elevatorTilt.eleUp();
   
    // if (Robot.hatchRelease.hatchout() == false){
    //   Robot.hatchRelease.hatchPusher.set(DoubleSolenoid.Value.kOff);
    //   }
    if (!elevatorTuning.isRunning()) {
      elevatorTuning.start(); // Starts elevator control using the POV control on the joystick
    }
    
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    driveSub.gyroUpdate();
    //dashSub.commandUpdate();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
