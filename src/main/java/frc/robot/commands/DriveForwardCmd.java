/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.Robot;

public class DriveForwardCmd extends Command {
  double driveDistance;
  double driveSpeed;
  double driveTollerance = 5;

  public DriveForwardCmd(double distance, double speed) {
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
    // Robot.driveSub.gyroUpdate();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.driveSub.encoderUpdate();
   // if(Robot.driveSub.avgDistance < driveDistance - (driveDistance/10)){
      if(Robot.driveSub.avgDistance < driveDistance){
      Robot.driveSub.driveForward(driveSpeed);
    } else {
      Robot.driveSub.driveForward(.25);
    }
    System.out.print("Average Distance:    ");
    System.out.println(Robot.driveSub.avgDistance);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(Robot.driveSub.distanceRight < driveDistance){ //change DISTANCERIGHT TO AVERAGE ONCE FIXED
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
