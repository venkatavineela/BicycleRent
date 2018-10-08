public enum Model {
    Hero(3f),
    Hercules(2.5f),
    Atlas(4.5f),
    BSA(4f),
    Avon(5f),
    Montra(5.5f),
    Kross(6f);

    private float rentPerHour;

    Model(float rentPerHour) {
        this.rentPerHour = rentPerHour;
    }

    public float getRentPerHour() {
        return this.rentPerHour;
    }

}