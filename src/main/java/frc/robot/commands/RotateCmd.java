/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.DriveSub;

public class RotateCmd extends Command {
  public double rotateDegrees = 0.0;
  public boolean firstTime = true;
  private double goalAngle = 0.0;
	private boolean isDone = false;
	private double speed = .5; 
	private double tolerance = 10;
	private double currentAngle;
  private double turningSpeed;  
  private double minTurnSpeed = .45;
  private double maxTurnSpeed = .55;
  public RotateCmd(double degrees) {
  // Use requires() here to declare subsystem dependencies
  // eg. requires(chassis);
  requires(Robot.driveSub);
  //rotateDegrees = degrees; 
  goalAngle = degrees;
    

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.driveSub.gyroReset();
    //Robot.driveSub.gyroUpdate();
    System.out.print("initialize, Angle: ");
    System.out.println(DriveSub.Gyro.getAngle());
    isDone = false;  
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //Robot.driveSub.gyroUpdate();
    //turningSpeed = (goalAngle - DriveSub.Gyro.getAngle()) * .007;
    currentAngle = DriveSub.Gyro.getAngle();
    turningSpeed = (goalAngle - currentAngle) * .01;
    
    //  if(turningSpeed < minTurnSpeed && turningSpeed > 0) {
    //     turningSpeed = minTurnSpeed;
    //     System.out.println("Setting Min Speed =: " + turningSpeed);
    //  }
    //  if (turningSpeed < -minTurnSpeed && turningSpeed < 0) {
    //    turningSpeed = minTurnSpeed;
    //    System.out.println("Setting Min Speed =: " + turningSpeed);
    //  }
    
  //   if(turningSpeed < minTurnSpeed && turningSpeed > 0) {
  //     turningSpeed = minTurnSpeed;
  //   }
  //   if (turningSpeed < -minTurnSpeed && turningSpeed < 0) {
  //     turningSpeed = -minTurnSpeed;
  //  }
  //  turningSpeed = .45;
    
    //SmartDashboard.putNumber("Gyro: ", currentAngle);
    if((Math.abs(goalAngle) - Math.abs(currentAngle)) < (Math.abs(goalAngle) * .75)) {
      turningSpeed=minTurnSpeed;
    } else {
        turningSpeed=maxTurnSpeed;
    }
    // System.out.print("Turning Angle:    ");
    // System.out.print(DriveSub.Gyro.getAngle());
    // System.out.print("Turning Speed:    ");
    // System.out.println(turningSpeed);
    if(Math.abs(goalAngle - currentAngle) < tolerance) {  //if within tolerance
    	DriveSub.DriveBase.arcadeDrive(0, 0);
    	isDone = true;
    } else if(currentAngle < goalAngle) {  //If left of target angle 
        DriveSub.DriveBase.arcadeDrive(0, turningSpeed);  //turn clockwise
    } else if(currentAngle > goalAngle){  //If right of target angle
        DriveSub.DriveBase.arcadeDrive(0, -turningSpeed);  //turn counterclockwise
    }
  }

    @Override
  protected boolean isFinished() {
    //System.out.print("isFinished, Angle: ");
    //System.out.println(DriveSub.Gyro.getAngle());
    return isDone;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    //Robot.driveSub.driveStop();
    System.out.print("All Done - driveStop - Final Angle: ");
    System.out.println(DriveSub.Gyro.getAngle());
    //Need to reset gyro angle to zere here, as it does not always reset otherwise
    Robot.driveSub.gyroReset();
    //System.out.print("All Done: driveStop : Final Angle");
    //System.out.println(DriveSub.Gyro.getAngle());
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  // Robot.driveSub.driveStop(); Maybe add this in?
  isDone = true;
  }
}
