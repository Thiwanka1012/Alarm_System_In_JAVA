
import java.util.Random;

class Observer{
    public void update(int waterLevel){

    }
}
class Alarm extends Observer{
    String alarmID;

    public Alarm(String alarmID){
        this.alarmID=alarmID;
    }

    public void update(int waterLevel){
        if (waterLevel>=50){
            System.out.println("Alarm "+alarmID+" On");
        }else {
            System.out.println("Alarm "+alarmID+" Off");
        }
    }
}

class Display extends Observer{
    public void update(int waterLevel){
        System.out.println("Water level "+waterLevel);
    }
}

class SMSManager extends Observer{
    public void update(int waterLevel){
        System.out.println("Send SMS "+waterLevel);
    }
}

class ControlPanel extends Observer{
    private Alarm alarm;
    private Display display;
    private SMSManager smsManager;

    private int waterLevel;


    public ControlPanel() {
    }

    public ControlPanel(Alarm alarm, Display display, SMSManager smsManager, int waterLevel) {
        this.alarm = alarm;
        this.display = display;
        this.smsManager = smsManager;
        this.waterLevel = waterLevel;
    }

    public Alarm getAlarm() {
        return alarm;
    }

    public void setAlarm(Alarm alarm) {
        this.alarm = alarm;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public SMSManager getSmsManager() {
        return smsManager;
    }

    public void setSmsManager(SMSManager smsManager) {
        this.smsManager = smsManager;
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

    public void notifyObjects(){
        this.alarm.update(waterLevel);
        this.display.update(waterLevel);
        this.smsManager.update(waterLevel);

    }

    public void update(int waterLevel){
        if (this.waterLevel!=waterLevel){
            notifyObjects();
        }

    }
}


public class AlarmSystem {
    public static void main(String[] args) {

        ControlPanel controlPanel=new ControlPanel();
        controlPanel.setAlarm(new Alarm("Alarm 1"));
        controlPanel.setDisplay(new Display());
        controlPanel.setSmsManager(new SMSManager());


        while (true){
            Random random=new Random();
            int randomNumber= random.nextInt(101);
            controlPanel.update(randomNumber);

            try {
                Thread.sleep(1000);
            }catch (Exception e){
                System.out.println(e);
            }

            System.out.println("=============================");
        }



    }
}
