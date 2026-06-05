package smart_home_scd;

public abstract class SmartDevice {
    private String name;
    private boolean isOn;

    public SmartDevice(String name) {
        this.name = name;
        this.isOn = false;
    }

    public SmartDevice(String name, boolean isOn) {
        this.name = name;
        this.isOn = isOn;
    }

    public String getName() {
        return name;
    }

    public boolean isOn() {
        return isOn;
    }

    public void togglePower() {
        isOn = !isOn;
    }

    public abstract String toFileFormat();
}