package si.fri.rso.escooterental.escootercatalog.lib;

import java.time.Instant;

public class EscooterCatalog {
    private Integer scooterId;
    private String  scooterName;
    private String scooterStatus;
    private Float scooterPrice;
    private String scooterDescription;
    private String pickupLocation;
    private String leaveLocation;

    public String getScooterStatus() {
        return scooterStatus;
    }

    public void setScooterStatus(String scooterStatus) {
        this.scooterStatus = scooterStatus;
    }

    public Integer getScooterId(){ return scooterId;}
    public String getScooterName(){ return scooterName;}
    public void setScooterName(String scooterName) {
        this.scooterName = scooterName;
    }
    public Float getScooterPrice() {
        return scooterPrice;
    }
    public void setScooterPrice(Float scooterPrice) {
        this.scooterPrice = scooterPrice;
    }

    public String getScooterDescription() {
        return scooterDescription;
    }

    public void setScooterDescription(String scooterDescription) {
        this.scooterDescription = scooterDescription;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }
    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getLeaveLocation() {
        return leaveLocation;
    }

    public void setLeaveLocation(String leaveLocation) {
        this.leaveLocation = leaveLocation;
    }

    public void setScooterId(Integer scooterId) {
        this.scooterId = scooterId;
    }
}
