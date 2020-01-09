/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class ElevatorTilt extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.\
  public static DoubleSolenoid elevatorSole1 = new DoubleSolenoid(RobotMap.eleSole1, RobotMap.eleSole2);
  

  public void eleUp(){
    elevatorSole1.set(Value.kForward);
  }
  
  public void eleStop(){
    elevatorSole1.set(Value.kOff);
  }
  public void eleDown(){
    elevatorSole1.set(Value.kReverse);

  }
  public boolean isEleUp(){
    if(ElevatorTilt.elevatorSole1.get() == DoubleSolenoid.Value.kForward) {
      return true;
    }else{
      return false;
    }
}
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
