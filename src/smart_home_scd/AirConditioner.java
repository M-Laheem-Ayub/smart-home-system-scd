package smart_home_scd;

public class AirConditioner extends SmartDevice {
    private int temperatur;

    public AirConditioner(String name) {
        super(name);
        this.temperatur = 22;
    }

    public AirConditioner(String name, boolean isOn, int temperatur) {
        super(name, isOn);
        this.temperatur = temperatur;
    }

    public int getTemperature() {
        return temperatur;
    }

    public void setTemperature(int temperatur) {
        this.temperatur = temperatur;
    }

    @Override
    public String toFileFormat() {
        return "AirConditioner," + getName() + "," + isOn() + "," + temperatur;
    }
}
