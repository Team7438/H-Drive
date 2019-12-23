/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
//import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.DriveCmd;
/**
 * Add your docs here.
 */
public class DriveSub extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public double distanceRight;
  public double distanceLeft;
  public double rateRight;
  public double rateLeft;
  public double avgDistance;
  public double turningValue;
  public static PWMVictorSPX leftOne = new PWMVictorSPX(RobotMap.left1);
  public static PWMVictorSPX leftTwo = new PWMVictorSPX(RobotMap.left2);
  public static SpeedControllerGroup Gleft = new SpeedControllerGroup(leftOne, leftTwo);
  public double angle;
  public float roll;
  public float pitch;
  public double gyro;
  public static double kP = 0.15;
  public static PWMVictorSPX rightOne = new PWMVictorSPX(RobotMap.right1);
  public static PWMVictorSPX rightTwo = new PWMVictorSPX(RobotMap.right2);
  public static SpeedControllerGroup Gright = new SpeedControllerGroup(rightOne, rightTwo);
  public static Encoder m_encoderRight = new Encoder(RobotMap.rightEncoderPort1, RobotMap.rightEncoderPort2, false, Encoder.EncodingType.k4X);
  public static Encoder m_encoderLeft = new Encoder(RobotMap.leftEncoderPort1, RobotMap.leftEncoderPort2, true, Encoder.EncodingType.k4X);
  public static final DifferentialDrive DriveBase = new DifferentialDrive(Gleft, Gright);
  public static VictorSPX centerMotor = new VictorSPX(RobotMap.center);
  public double minTurnSpeed = .45;
  public double zValue;
  public double yValue;
  public double zAdjustedValue;
  public double yAdjustedValue;
  public double yFactor = 1.2;
  public double zFactor = 1.5;
  public double rateAvg;
  //public static ADXRS450_Gyro Gyro = new ADXRS450_Gyro(); 
  public static AHRS Gyro = new AHRS(SPI.Port.kMXP);

  public void robotDriver(Joystick joystickZero){
    // Experimental throttle curbve stuff -- Originall arcadeDeive line is below (commented out)
    yValue=joystickZero.getY();
    zValue=joystickZero.getZ();
    
    yAdjustedValue=squareInput(-yValue)/(yFactor);
    zAdjustedValue=squareInput(zValue);
    if (paddleTuner()<0){
      zFactor = 2;
      yFactor = 1.6;
    } else if (paddleTuner()>0){
      zFactor = 1.5;
      yFactor = 1.2;
    }
    if (Math.abs(zValue)>.1 && zValue<0){ 
      zAdjustedValue=(squareInput(zValue)/zFactor)-.2;
     }
    if (Math.abs(zValue)>.1 && zValue>0){ 
      zAdjustedValue=(squareInput(zValue)/zFactor)+.2;
    }
    
    //This next line overrides all the other stuff and feeds straight joystic values.
    //zAdjustedValue=zValue;
    DriveBase.arcadeDrive(yAdjustedValue, zAdjustedValue);
    //DriveBase.arcadeDrive(squareInput(-joystickZero.getY())/3, squareInput(joystickZero.getZ())/3);
    //centerMotor.setSpeed(-joystickZero.getX()/2);
    centerMotor.set(ControlMode.PercentOutput, -joystickZero.getX()/2);
  }

  public double squareInput(double input){
    if(input<0){
      return -(input*input);
    } else {
      return (input*input);
    }
  }
  public double paddleTuner(){
    return Robot.m_oi.joystickZero.getRawAxis(3);
  }

  public void encoderInit(){
    m_encoderRight.setDistancePerPulse((Math.PI * 6) / 1024);
		// m_encoderRight.setMaxPeriod(.1);
		m_encoderRight.setMinRate(1);
		m_encoderRight.setReverseDirection(false);
		m_encoderRight.setSamplesToAverage(7);

    m_encoderLeft.setDistancePerPulse((Math.PI * 6) / 1024);
		// m_encoderLeft.setMaxPeriod(.1);
		m_encoderLeft.setMinRate(1);
		m_encoderLeft.setReverseDirection(true);
    m_encoderLeft.setSamplesToAverage(7);
  }



  public void encoderReset(){
    m_encoderRight.reset();
    m_encoderLeft.reset();
  }

  public void gryoInit(){
    
  }

  public void gyroReset(){
    Gyro.reset();
  }

  public void gyroUpdate(){
    angle = Gyro.getAngle();
    roll = Gyro.getRoll();
    pitch = Gyro.getPitch();
    SmartDashboard.putNumber("Angle", angle);
    //SmartDashboard.putNumber("Roll", roll);
    //SmartDashboard.putNumber("pitch", pitch);
    //SmartDashboard.putNumber("Gyro", angle);
  }

  public void encoderUpdate(){
    distanceRight = m_encoderRight.getDistance();
    distanceLeft = m_encoderLeft.getDistance();
    rateRight = m_encoderRight.getRate();
    rateLeft = m_encoderLeft.getRate();
    rateAvg = (rateRight + rateLeft)/2;
    avgDistance = (distanceLeft + distanceRight)/2;
    SmartDashboard.putNumber("Right Distance", distanceRight);
    SmartDashboard.putNumber("Left Distance", distanceLeft);
    SmartDashboard.putNumber("turning", turningValue);

  }



  public void driveForward(double speed){
    double strturningValue = (0 - Gyro.getAngle()) * kP;
    //Invert the direction of the turn if we are going backwards
    //turningValue = Math.copySign(turningValue, speed);
    //DriveBase.setExpiration(.4);
    DriveBase.setSafetyEnabled(false);
    DriveBase.arcadeDrive(speed, strturningValue);
  }

  public void driveBackward(double speed){
    double strturningValue = (0 - Gyro.getAngle()) * kP;
    //Invert the direction of the turn if we are going backwards
    //turningValue = Math.copySign(turningValue, speed);
    turningValue = -turningValue;
    //DriveBase.setExpiration(.4);
    DriveBase.setSafetyEnabled(false);
    DriveBase.arcadeDrive(speed, strturningValue);
  }

  public void rotate(double degrees){
    double turningSpeed;
    turningSpeed = (degrees - Gyro.getAngle()) * .007;
    if(turningSpeed < minTurnSpeed && turningSpeed > 0) {
       turningSpeed = minTurnSpeed;
    }
    if (turningSpeed > -minTurnSpeed && turningSpeed < 0) {
      turningSpeed = -minTurnSpeed;
    }
    SmartDashboard.putNumber("turning", turningSpeed);

    // // Invert the direction of the turn if we are going backwards
    // // turningValue = Math.copySign(turningValue, speed);
    
  }

  public void driveStop(){
    DriveBase.arcadeDrive(0,0);
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DriveCmd());

  }
}
