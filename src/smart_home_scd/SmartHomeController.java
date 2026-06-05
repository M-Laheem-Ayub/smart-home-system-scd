package smart_home_scd;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}

public class SmartHomeController extends JFrame {
    private JFrame frame;
    private JPanel devicePanel;
    private ArrayList<SmartDevice> devices;
    private String userName;
    private final int screenWidth = 350;
    private final int screenHeight = 700;

    public SmartHomeController() {
        devices = new ArrayList<>();
        loadDevicesFromFile();
        showUserInputScreen();
    }

    private void showUserInputScreen() {

        frame = new JFrame("Smart Home Controller");
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        handleWindowExit(frame);
        
        frame.setSize(screenWidth, screenHeight);
        frame.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        JLabel headerLabel = new JLabel("Smart Home Controller :)", SwingConstants.CENTER);
        headerPanel.add(headerLabel);
        frame.add(headerPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 0, 0));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(220, 20, 290, 20));

        JLabel nameFieldLabel = new JLabel("Enter your name:", JLabel.CENTER);
        JTextField nameField = new JTextField(15);
        JButton submitButton = new JButton("Submit");
        centerPanel.add(nameFieldLabel);

        JPanel nameFieldPanel = new JPanel();
        nameFieldPanel.add(nameField);
        centerPanel.add(nameFieldPanel);

        JPanel sBtnPanel = new JPanel();
        submitButton.setFocusPainted(false);
        sBtnPanel.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    userName = nameField.getText().trim();
                    if (userName.isEmpty()) {
                        throw new InvalidInputException("Name cannot be empty!");
                    }
                    if (!CheckCorrectInput(userName)) {
                        throw new InvalidInputException("Only alphabets are allowed!");
                    }
                    
                    userName = capitalizeWords(userName);
                    frame.dispose();
                    mainFrame();
                    
                } catch (InvalidInputException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Unexpected Error: " + ex.getMessage(), "System Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        centerPanel.add(sBtnPanel);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void mainFrame() {

        frame = new JFrame("Smart Home Controller");
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        handleWindowExit(frame);
        
        frame.setSize(screenWidth, screenHeight);
        frame.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 40, 0));

        JPanel headerPanelLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel helloLabel = new JLabel("   Hello,");
        JLabel nameLabel = new JLabel(userName);
        headerPanelLeft.add(helloLabel);
        headerPanelLeft.add(nameLabel);

        JPanel headerPanelRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addDeviceButton = new JButton("+");
        addDeviceButton.setFocusPainted(false);
        addDeviceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddDeviceDialog();
            }
        });

        JButton delButton = new JButton("-");
        delButton.setFocusPainted(false);
        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDelDeviceDialog();
            }
        });

        headerPanelRight.add(addDeviceButton);
        headerPanelRight.add(delButton);

        headerPanel.add(headerPanelLeft, BorderLayout.WEST);
        headerPanel.add(headerPanelRight, BorderLayout.EAST);

        JLabel welcomeLabel = new JLabel("Welcome to your smart home");
        welcomeLabel.setBounds(15, 40, 350, 80);

        JButton AllDevice = new JButton("All Device");
        AllDevice.setBounds(14, 100, 100, 20);
        AllDevice.setFocusPainted(false);
        AllDevice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allOrActiveDevicePanel("All Device", true);
            }
        });

        JButton ActiveDevice = new JButton("Active Device");
        ActiveDevice.setBounds(120, 100, 120, 20);
        ActiveDevice.setFocusPainted(false);
        ActiveDevice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allOrActiveDevicePanel("Active Device", false);
            }
        });
        
        JSeparator headerSeparator = new JSeparator(SwingConstants.HORIZONTAL);
        headerSeparator.setBounds(0, 134, screenWidth, 20);
        frame.add(welcomeLabel);
        frame.add(AllDevice);
        frame.add(ActiveDevice);
        frame.add(headerSeparator);
        frame.add(headerPanel, BorderLayout.NORTH);

        devicePanel = new JPanel();
        devicePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        allOrActiveDevicePanel("All Device", true);
        frame.add(devicePanel);
        frame.setVisible(true);

    }

    private void allOrActiveDevicePanel(String title, Boolean isAll) {
        devicePanel.removeAll();

        JLabel allDeviceLabel = new JLabel(title);

        JPanel allDeviceLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        allDeviceLabelPanel.setPreferredSize(new Dimension(320, 50));
        allDeviceLabelPanel.add(allDeviceLabel);
        devicePanel.add(allDeviceLabelPanel);

        for (SmartDevice device : devices) {
            if (isAll || device.isOn()) {
                JPanel panel = new JPanel();
                JButton deviceBtn = new JButton();
                deviceBtn.setPreferredSize(new Dimension(150, 100));
                deviceBtn.setLayout(new GridLayout(4, 1, 10, 5));
                JLabel onOffLabel = new JLabel(device.isOn() ? "ON" : "OFF");
                JLabel typeLabel;
                
                if (device instanceof Light) {
                    typeLabel = new JLabel("Light");
                } else if (device instanceof Thermostat) {
                    typeLabel = new JLabel("Thermostat");
                } else if (device instanceof SecurityCamera) {
                    typeLabel = new JLabel("Security Camera");
                } else {
                    typeLabel = new JLabel("Air Conditioner");
                }
                
                JLabel nameLabel = new JLabel(device.getName());

                deviceBtn.add(Box.createRigidArea(new Dimension(0, 0)));
                deviceBtn.add(onOffLabel);
                deviceBtn.add(typeLabel);
                deviceBtn.add(nameLabel);
                deviceBtn.setFocusPainted(false);
                
                deviceBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                        showControlScreen(device);
                    }
                });
                panel.add(deviceBtn);
                devicePanel.add(panel);
            }
        }
        devicePanel.revalidate();
        devicePanel.repaint();
    }

    private void showControlScreen(SmartDevice device) {
        JFrame controlFrame = new JFrame("Smart Home Controller");
        
        controlFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        handleWindowExit(controlFrame);
        
        controlFrame.setSize(screenWidth, screenHeight);
        controlFrame.setLayout(new GridLayout(15, 1, 20, 1));
        
        JPanel headerPanel = new JPanel(new BorderLayout());

        JPanel headerPanelLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backBtn = new JButton("<-");
        backBtn.setFocusPainted(false);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlFrame.dispose();
                saveDevicesToFile();
                loadDevicesFromFile();
                mainFrame();
                allOrActiveDevicePanel("All Device", true);
            }
        });
        
        JLabel titleLabel;
        if (device instanceof Light) {
            titleLabel = new JLabel("    Light");
        } else if (device instanceof Thermostat) {
            titleLabel = new JLabel("    Thermostat");
        } else if (device instanceof SecurityCamera) {
            titleLabel = new JLabel("    Security Camera");
        } else {
            titleLabel = new JLabel("    Air Conditioner");
        }
        headerPanelLeft.add(backBtn);
        headerPanelLeft.add(titleLabel);

        JLabel nameLabel = new JLabel("   " + device.getName());

        JLabel powerLabel = new JLabel("         POWER");

        JPanel powerPanel = new JPanel(new BorderLayout());

        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel statusLabel = new JLabel("    Status: ");
        JLabel onOrOffLabel = new JLabel(device.isOn() ? "ON" : "OFF");
        statusPanel.add(statusLabel);
        statusPanel.add(onOrOffLabel);

        JPanel toggleBtnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton toggleBtn = new JButton(device.isOn() ? "Turn OFF" : "Turn ON");
        toggleBtn.setFocusPainted(false);
        toggleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                device.togglePower();
                controlFrame.dispose();
                showControlScreen(device);
                onOrOffLabel.setText(device.isOn() ? "ON" : "OFF");
                toggleBtn.setText(device.isOn() ? "Turn OFF" : "Turn ON");
            }
        });

        JLabel space = new JLabel("     ");
        toggleBtnPanel.add(toggleBtn);
        toggleBtnPanel.add(space);

        powerPanel.add(statusPanel, BorderLayout.WEST);
        powerPanel.add(toggleBtnPanel, BorderLayout.EAST);

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        JSeparator headerSeparator = new JSeparator(SwingConstants.HORIZONTAL);

        JPanel separatorPanel = new JPanel(new BorderLayout());
        separatorPanel.setBorder(new EmptyBorder(15, 20, 0, 20));
        separatorPanel.add(separator, BorderLayout.CENTER);

        headerPanel.add(headerPanelLeft);
        headerPanel.add(headerSeparator,BorderLayout.SOUTH);
        controlFrame.add(headerPanel);
        controlFrame.add(Box.createRigidArea(new Dimension(0, 0)));
        controlFrame.add(nameLabel);
        controlFrame.add(Box.createRigidArea(new Dimension(0, 0)));
        controlFrame.add(Box.createRigidArea(new Dimension(0, 0)));
        controlFrame.add(powerLabel);
        controlFrame.add(powerPanel);
        controlFrame.add(separatorPanel);
        controlFrame.add(Box.createRigidArea(new Dimension(0, 0)));
        
        if (device.isOn()) {
            deviceFeatures(controlFrame, device);
        }
        controlFrame.setVisible(true);
    }

    private void deviceFeatures(JFrame controlFrame, SmartDevice device) {
        if (device instanceof Light) {
            Light light = (Light) device;
            JSlider brightnessSlider = new JSlider(0, 100, light.getBrightness());
            JLabel brightnessValueLabel = new JLabel(brightnessSlider.getValue() + "%");
            brightnessSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    light.setBrightness(brightnessSlider.getValue());
                    brightnessValueLabel.setText(brightnessSlider.getValue() + "%");
                }
            });

            JLabel brightnessLabel = new JLabel("         BRIGHTNESS");
            JPanel bVPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            bVPanel.add(brightnessValueLabel);

            controlFrame.add(brightnessLabel);
            controlFrame.add(bVPanel);
            controlFrame.add(brightnessSlider);
            
        } else if (device instanceof Thermostat) {
            Thermostat thermostat = (Thermostat) device;
            JSpinner temperatureSpinner = new JSpinner(new SpinnerNumberModel(thermostat.getTemperature(), 16, 30, 1));
            JLabel tempValueLabel = new JLabel(temperatureSpinner.getValue() + "C");
            temperatureSpinner.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    thermostat.setTemperature((int) temperatureSpinner.getValue());
                    tempValueLabel.setText(temperatureSpinner.getValue() + "C");
                }
            });
            JLabel tempLabel = new JLabel("         TEMPERATURE");

            JPanel tVPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            tVPanel.add(tempValueLabel);

            controlFrame.add(tempLabel);
            controlFrame.add(tVPanel);
            controlFrame.add(temperatureSpinner);
            
        } else if (device instanceof AirConditioner) {
            AirConditioner airConditioner = (AirConditioner) device;
            JSpinner temperatureSpinner = new JSpinner(new SpinnerNumberModel(airConditioner.getTemperature(), 16, 30, 1));
            JLabel tempValueLabel = new JLabel(temperatureSpinner.getValue() + "C");
            temperatureSpinner.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    airConditioner.setTemperature((int) temperatureSpinner.getValue());
                    tempValueLabel.setText(temperatureSpinner.getValue() + "C");
                }
            });
            JLabel tempLabel = new JLabel("         TEMPERATURE");

            JPanel tVPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            tVPanel.add(tempValueLabel);

            controlFrame.add(tempLabel);
            controlFrame.add(tVPanel);
            controlFrame.add(temperatureSpinner);
            
        } else if (device instanceof SecurityCamera) {
            SecurityCamera securityCamera = (SecurityCamera) device;
            JSlider zoomInOutSlider = new JSlider(1, 10, securityCamera.getZoomLevel());
            JLabel zoomLevelLabel = new JLabel(zoomInOutSlider.getValue() + "x");
            zoomInOutSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    securityCamera.setZoomLevel(zoomInOutSlider.getValue());
                    zoomLevelLabel.setText(zoomInOutSlider.getValue() + "x");
                }
            });

            JLabel zoomInOutLabel = new JLabel("         ZOOM LEVEL");
            JPanel zLPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            zLPanel.add(zoomLevelLabel);

            controlFrame.add(zoomInOutLabel);
            controlFrame.add(zLPanel);
            controlFrame.add(zoomInOutSlider);
        }
    }

    private String capitalizeWords(String input) {
        String[] words = input.split(" ");
        StringBuilder capitalized = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                capitalized.append((word.charAt(0) >= 'a' && word.charAt(0) <= 'z') ? (char) (word.charAt(0) - 32) : (char) (word.charAt(0))).append(word.substring(1).toLowerCase()).append(" ");
            }
        }
        return capitalized.toString().trim();
    }

    private Boolean CheckCorrectInput(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (!((input.charAt(i) >= 'A' && input.charAt(i) <= 'Z') || (input.charAt(i) >= 'a' && input.charAt(i) <= 'z') || (input.charAt(i) == ' '))) {
                return false;
            }
        }
        return true;
    }

    private void handleWindowExit(JFrame window) {
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(window, "Are you sure you want to exit?", 
                    "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (choice == JOptionPane.YES_OPTION) {
                    saveDevicesToFile();
                    System.exit(0);
                }
            }
        });
    }

    private void showDelDeviceDialog() {
        if (devices.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No devices available to delete.", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String[] deviceNames = new String[devices.size()];
        for (int i = 0; i < devices.size(); i++) {
            deviceNames[i] = devices.get(i).getName();
        }

        String selectedDevice = (String) JOptionPane.showInputDialog(frame, "Select a device to delete:", "Delete Device", JOptionPane.PLAIN_MESSAGE, null, deviceNames, deviceNames[0]);

        if (selectedDevice != null) {
            for (int i = 0; i < devices.size(); i++) {
                if (devices.get(i).getName().equals(selectedDevice)) {
                    devices.remove(i);
                    JOptionPane.showMessageDialog(frame, "Device '" + selectedDevice + "' deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    saveDevicesToFile();
                    loadDevicesFromFile();
                    allOrActiveDevicePanel("All Device", true);
                    break;
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "No device selected for deletion.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showAddDeviceDialog() {
        if (devices.size() < 8) {
            String[] options = {"Light", "Thermostat", "Security Camera", "Air Conditioner"};
            String choice = (String) JOptionPane.showInputDialog(frame, "Select Device Type:", "Add Device", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            if (choice != null) {
                String name = JOptionPane.showInputDialog(frame, "Enter Device Name:");
                try {
                    if (name == null || name.isBlank()) {
                        throw new InvalidInputException("Device name cannot be empty!");
                    }
                    name = capitalizeWords(name);
                    if (name.charAt(0) >= '0' && name.charAt(0) <= '9') {
                        throw new InvalidInputException("Device name cannot start with a number!");
                    }

                    SmartDevice device;
                    if (choice.equals("Light")) {
                        device = new Light(name);
                    } else if (choice.equals("Thermostat")) {
                        device = new Thermostat(name);
                    } else if (choice.equals("Security Camera")) {
                        device = new SecurityCamera(name);
                    } else {
                        device = new AirConditioner(name);
                    }
                    devices.add(device);
                    saveDevicesToFile();
                    loadDevicesFromFile();
                    allOrActiveDevicePanel("All Device", true);
                    
                } catch (InvalidInputException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Device limit exceeded! You can only add up to 8 devices.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveDevicesToFile() {
        try (FileWriter writer = new FileWriter("devices.txt")) {
            for (SmartDevice device : devices) {
                writer.write(device.toFileFormat() + "\n");
            }
            System.out.println("Device information saved to file successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error saving to file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadDevicesFromFile() {
        File file = new File("devices.txt");
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            devices.clear();
            while ((line = reader.readLine()) != null) {
                SmartDevice deviceFromFile = DeviceFactory.createDeviceFromFile(line);
                if (deviceFromFile != null) {
                    devices.add(deviceFromFile);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading from file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new SmartHomeController();
    }
}