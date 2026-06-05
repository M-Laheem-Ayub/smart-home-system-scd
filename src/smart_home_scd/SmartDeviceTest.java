package smart_home_scd;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SmartDeviceTest {
    
    private Light testLight;
    private SecurityCamera testCamera;

    @Before
    public void setUp() {
        testLight = new Light("Living Room Light");
        testCamera = new SecurityCamera("Main Gate Camera");
    }

    // 1. Positive Test Case: Check Toggle Power Logic
    @Test
    public void testTogglePower_Positive() {
        assertFalse("Device should be OFF initially", testLight.isOn()); 
        testLight.togglePower();
        assertTrue("Device should be ON after toggling", testLight.isOn()); 
    }

    // 2. Positive Test Case: Factory Method Data Parsing
    @Test
    public void testDeviceFactory_Positive() {
        String fileLine = "Thermostat,Hallway Temp,true,24";
        SmartDevice device = DeviceFactory.createDeviceFromFile(fileLine);
        
        assertNotNull("Device object should not be null", device);
        assertTrue("Device should be an instance of Thermostat", device instanceof Thermostat);
        assertEquals("Device name should match", "Hallway Temp", device.getName());
        assertTrue("Device ON status should match", device.isOn());
    }

    // 3. Negative Test Case: Factory Method with Invalid Data
    @Test
    public void testDeviceFactory_Negative() {
        String invalidLine = "UnknownDevice,Test,true,10";
        SmartDevice device = DeviceFactory.createDeviceFromFile(invalidLine);
        
        assertNull("Should return null for invalid device type", device); 
    }

    // 4. Positive Test Case: Camera Zoom Limit
    @Test
    public void testCameraZoom_Positive() {
        testCamera.setZoomLevel(5); // Valid zoom
        assertEquals("Zoom level should be updated", 5, testCamera.getZoomLevel());
    }

    // 5. Negative Test Case: Camera Zoom Out of Bounds
    @Test
    public void testCameraZoom_Negative() {
        testCamera.setZoomLevel(15); // Invalid zoom
        
        assertEquals("Zoom level should not update for out of bounds value", 1, testCamera.getZoomLevel()); 
    }
}