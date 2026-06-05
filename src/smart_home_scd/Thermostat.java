package smart_home_scd;

public class Thermostat extends SmartDevice {
    private int temperature;

    public Thermostat(String name) {
        super(name);
        this.temperature = 22;
    }

    public Thermostat(String name, boolean isOn, int temperature) {
        super(name, isOn);
        this.temperature = temperature;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toFileFormat() {
        return "Thermostat," + getName() + "," + isOn() + "," + temperature;
    }
}