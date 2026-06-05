package smart_home_scd;

public class SecurityCamera extends SmartDevice {
    private int zoomLevel;

    public SecurityCamera(String name) {
        super(name);
        this.zoomLevel = 1;
    }

    public SecurityCamera(String name, boolean isOn, int zoomLevel) {
        super(name, isOn);
        this.zoomLevel = zoomLevel;
    }

    public int getZoomLevel() {
        return zoomLevel;
    }

    public void setZoomLevel(int zoomLevel) {
        if (zoomLevel >= 1 && zoomLevel <= 10) {
            this.zoomLevel = zoomLevel;
        } else {
            System.out.println("Invalid zoom level! Must be between 1x and 10x.");
        }
    }

    @Override
    public String toFileFormat() {
        return "SecurityCamera," + getName() + "," + isOn() + "," + zoomLevel;
    }
}
