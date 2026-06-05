package smart_home_scd;

public class DeviceFactory {
    public static SmartDevice createDeviceFromFile(String line) {
        try {
            String[] parts = line.split(",");
            String deviceType = parts[0];
            switch (deviceType) {
                case "Light":
                    return new Light(parts[1], Boolean.parseBoolean(parts[2]), Integer.parseInt(parts[3]));
                case "Thermostat":
                    return new Thermostat(parts[1], Boolean.parseBoolean(parts[2]), Integer.parseInt(parts[3]));
                case "SecurityCamera":
                    return new SecurityCamera(parts[1], Boolean.parseBoolean(parts[2]), Integer.parseInt(parts[3]));
                case "AirConditioner":
                    return new AirConditioner(parts[1], Boolean.parseBoolean(parts[2]), Integer.parseInt(parts[3]));
                default:
                    return null;
            }
        } catch (Exception e) {
            System.out.println("Error parsing device data: " + e.getMessage());
            return null;
        }
    }
}