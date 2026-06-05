package smart_home_scd;

public class Light extends SmartDevice {
    private int brightness;

    public Light(String name) {
        super(name);
        this.brightness = 50;
    }

    public Light(String name, boolean isOn, int brightness) {
        super(name, isOn);
        this.brightness = brightness;
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    @Override
    public String toFileFormat() {
        return "Light," + getName() + "," + isOn() + "," + brightness;
    }
}
