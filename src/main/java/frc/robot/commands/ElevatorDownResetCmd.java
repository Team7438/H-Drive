/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.ElevatorWinch;

public class ElevatorDownResetCmd extends Command {
  boolean hitBottom;
  
  public ElevatorDownResetCmd() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.elevatorWinch);
    hitBottom=false;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    Robot.elevatorWinch.eleEncoderUpdate();

    if(!Robot.elevatorWinch.elevatorLimitPressed()){
      Robot.elevatorWinch.rollDown();
    }else {
      Robot.elevatorWinch.ElevatorEncoderReset();
      while (Robot.elevatorWinch.elevatorEncoder.getRaw() <= 25) {
        Robot.elevatorWinch.elevatorWinch.setSpeed(.3);
        }
      hitBottom=true;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(hitBottom){ 
      return true;
    } else {
      return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}