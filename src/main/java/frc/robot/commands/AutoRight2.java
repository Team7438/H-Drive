/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class AutoRight2 extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutoRight2() {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.
    
     
    addSequential(new DriveForwardCmd(205,.6)); //This was good in Walpole
    //addSequential(new DriveForwardCmd(2,.5)); //Use this for testing
    addSequential(new ResetElevatorEncoderCommand());
    addSequential(new ElevatorWinchCmd(450));
    addSequential(new RotateCmd(-90));

    addSequential(new YeetCargoCmd());
    addSequential(new DriveBackwardCmd(12,.6));
    addSequential(new ElevatorTiltCmd(Value.kForward));
    addSequential(new WaitCommand(2));
    addSequential(new ElevatorDownResetCmd());
    addSequential(new ElevatorWinchCmd(150));
    
   
    // addSequential(new ElevatorWinchCmd(5100));
    
   
   // addSequential(new DriveForwardCmd(6,.6));
   
    
    
    //addSequential(new YeetCargoCmd());
    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
