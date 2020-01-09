/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveBackwardCmd extends Command {
  double driveDistance;
  double driveSpeed;
  double driveTollerance = 5;

  public DriveBackwardCmd(double distance, double speed) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.driveSub);
    driveDistance = distance;
    driveSpeed = speed;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.driveSub.encoderReset();
    Robot.driveSub.encoderUpdate();
    Robot.driveSub.gyroReset();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.driveSub.encoderUpdate();
    if(Robot.driveSub.avgDistance > -driveDistance){
      Robot.driveSub.driveBackward(-driveSpeed);
      System.out.println("DriveSpeed: " + driveSpeed);
    } else {
      driveSpeed = .25;
      Robot.driveSub.driveBackward(-driveSpeed);
    }
    
  }


  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(Robot.driveSub.avgDistance > -driveDistance){ 
      return false;
    } else {
      return true;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveSub.driveStop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
