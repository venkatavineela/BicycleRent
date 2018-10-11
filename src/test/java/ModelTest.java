import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelTest {

    @Test
    public void getRentPerHourShouldReturn5ForAvon() {
        assertEquals(5f, Model.Avon.getRentPerHour());
    }
}