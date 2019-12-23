/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;


import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.HatchRelease;

public class PushoutCmd extends Command {
  int timer = 0; // 1 = 20ms

  public PushoutCmd() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    // requires(Robot.hatchRelease);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    // Robot.hatchRelease.hatchPusher.set(Value.kOff);
    timer  = 0;
    // Robot.hatchRelease.pushAway();

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    timer += 1;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (timer < 12){
      return false;
    } else {
      return  true; // Robot.hatchRelease.hatchout();
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    // Robot.hatchRelease.pushBack();
    // if (Robot.hatchRelease.hatchout() == false){
    //     Robot.hatchRelease.onehatchPusher.set(Value.kOff);
    //     }
      //STOPIT 
    // Robot.hatchRelease.hatchPusher.set(Value.kOff);


  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    //Robot.hatchRelease.pushBack();

  }
}
