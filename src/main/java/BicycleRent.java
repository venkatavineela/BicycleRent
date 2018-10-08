class BicycleRent {
    private Bicycle bicycle;
    private int hours;

    BicycleRent(Bicycle bicycle, int hours) {
        this.bicycle = bicycle;
        this.hours = hours;
    }


    float calculate() {
        return bicycle.model.getRentPerHour() * hours;
    }
}
