import java.util.Objects;

class Bicycle {
    int id;
    Model model;

    Bicycle(int id, Model model) {
        this.id = id;
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bicycle bicycle = (Bicycle) o;
        return id == bicycle.id &&
                model == bicycle.model;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model);
    }
}
