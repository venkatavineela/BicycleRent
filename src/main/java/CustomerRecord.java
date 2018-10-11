import java.util.Date;

public class CustomerRecord {
    private int bicycleId;
    private int totalHours;
    private float rentPerHour;
    private float totalRent;

    public CustomerRecord(int bicycleId, int totalHours, float rentPerHour, float totalRent) {
        this.bicycleId = bicycleId;
        this.totalHours = totalHours;
        this.rentPerHour = rentPerHour;
        this.totalRent = totalRent;
    }

    @Override
    public String toString() {
        return "\nbicycleId= " + bicycleId +
                " totalHours= " + totalHours +
                " rentPerHour= " + rentPerHour +
                " totalRent= " + totalRent;
    }
}
