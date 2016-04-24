package by.bsuir.oop.model;

public abstract class CommunicationDevice {

    public enum AntennaType {FIXED, FOLDING, INTERNAL, REMOVABLE};

    public String brand;
    public String model;

    public boolean isHasVolumeControl;
    public AntennaType antenna;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isHasVolumeControl() {
        return isHasVolumeControl;
    }

    public void setHasVolumeControl(boolean hasVolumeControl) {
        isHasVolumeControl = hasVolumeControl;
    }

    public AntennaType getAntenna() {
        return antenna;
    }

    public void setAntenna(AntennaType antenna) {
        this.antenna = antenna;
    }

    public String toString() {
        return this.getClass().getSimpleName();
    }
}