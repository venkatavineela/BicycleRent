import java.util.Date;

class CustomerRecord {
    private Bicycle bicycle;
    private Date checkOutTime;
    private Date returnTime;
    private float totalRent;

    CustomerRecord(Bicycle bicycle, Date checkOutTime, Date returnTime, float totalRent) {
        this.bicycle = bicycle;
        this.checkOutTime = checkOutTime;
        this.returnTime = returnTime;
        this.totalRent = totalRent;
    }
}
