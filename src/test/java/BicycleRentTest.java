import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BicycleRentTest {

    @Test
    public void shouldReturnCalculatedRent() {
        Bicycle bicycle = new Bicycle(1, Model.BSA);
        BicycleRent rent = new BicycleRent(bicycle, 3);
        assertEquals(12f, rent.calculate());
    }
}
