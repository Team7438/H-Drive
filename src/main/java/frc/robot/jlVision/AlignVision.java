package frc.robot.jlVision;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AlignVision {

    private static AlignVision instance;
    public static AlignVision getInstance() {
        if(instance==null)
            synchronized(AlignVision.class){
                if(instance==null)
                    instance=new AlignVision();
            }
        return instance;
    }

    private NetworkTableInstance sd;
    private NetworkTable outputTable;
    private NetworkTableEntry outputEntry;
    private NetworkTableEntry yawOutputEntry;
    private double[] motorPowerValues;
    private static double turnPower = 0.1;
    private double centerPower = 0;
    private double ballAngle = 0;
    private static double tempPower = 0.2;
    public static double tempVar;

    public volatile double timestamp=0, lastLockTime=0;
    public volatile boolean isConnected;

    public void AugmentedDriving() {
        //Filler
    }


    private static double turnRate(double p) {

        tempPower = Math.abs(p / 55);
        if (p > -5 && p < 5) {
            return 0;
        }
        if (tempPower < 0.2) {
            tempPower = 0.2;
        } else if (tempPower > 0.4) {
            tempPower = 0.4;
        }

        if (p < 0) {
            return tempPower * -1;
        } else if (p > 0) {
            return tempPower;
        } else {
            return 0;
        }

    }

    // private double centerMotor() {

    // }

    public static double AugmentedDriverInterface() {
        tempVar = SmartDashboard.getNumber("ballAngle", 0);
        turnPower = turnRate(tempVar);
        return turnPower;
    }

}