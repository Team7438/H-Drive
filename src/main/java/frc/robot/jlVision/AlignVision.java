package frc.robot.jlVision;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

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

    private NetworkTableInstance ntinst;
    private NetworkTable outputTable;
    private NetworkTableEntry outputEntry;
    private NetworkTableEntry yawOutputEntry;
    private double[] motorPowerValues;
    private static double turnPower = 0;
    private double centerPower = 0;
    private double parallelAngle = 90;
    private double Yaw = 0;
    private double tempPower;

    public volatile double timestamp=0, lastLockTime=0;
    public volatile boolean isConnected;

    public void AugmentedDriving() {

        ntinst = NetworkTableInstance.getDefault();
        outputTable = ntinst.getTable("VisionCoProcessor");
        outputEntry = outputTable.getEntry("Parallel");
        yawOutputEntry = outputTable.getEntry("Yaw");
        outputEntry.addListener(event->{
            parallelAngle = outputEntry.getDouble(90);
            turnPower = turnRate(parallelAngle);
        }, EntryListenerFlags.kNew|EntryListenerFlags.kUpdate);
        yawOutputEntry.addListener(event->{
            Yaw = outputEntry.getDouble(0);
        }, EntryListenerFlags.kNew|EntryListenerFlags.kUpdate);

    }


    private double turnRate(double p) {


        tempPower = Math.abs(p / 55);
        if (tempPower < 0.1) {
            tempPower = 0.1;
        } else if (tempPower > 0.6) {
            tempPower = 0.6;
        }

        if (Yaw < 0) {
            return tempPower * -1;
        } else if (Yaw > 0) {
            return tempPower;
        } else {
            return 0;
        }

    }

    // private double centerMotor() {

    // }
    
    public static double AugmentedDriverInterface() {
        return turnPower;
    }

}