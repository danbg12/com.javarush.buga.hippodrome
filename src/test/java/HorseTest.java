import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    private Horse horse = new Horse("Vasiok" , 2.0 , 5.5);

    @ParameterizedTest
    @CsvSource({", 2.0 , 5.0",
            "'', 2.0 , 5.0",
            "Jorik , -3.0 , 5.0",
            "Valera , 5.0 , -3.0"})
    void horseConstructorInitializationTest(String name, double speed, double distance) {
        String message = "";
        if (name == null) {
            message = "Name cannot be null.";
        } else if (name.isBlank()) {
            message = "Name cannot be blank.";
        } else if (speed < 0) {
            message = "Speed cannot be negative.";
        } else if (distance < 0) {
            message = "Distance cannot be negative.";
        }

        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
        assertEquals(message, assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance)).getMessage());
    }

    @Test
    void getNameTest() {
        assertEquals("Vasiok", horse.getName());
    }

    @Test
    void getSpeedTest() {
        assertEquals(2.0 , horse.getSpeed());
    }

    @Test
    void getDistanceTest() {
        Horse firstHorse = new Horse("Vasiok" , 2.0 , 5.5);
        Horse secondHorse = new Horse("Vasiok" , 2.0 );

        assertEquals(5.5, horse.getDistance());
        assertEquals(0, secondHorse.getDistance());
    }

    @Test
    void moveAndRandomTest() {
        double randomNum = 0.5;
        double expectedDistance = 105.0;
        Horse horse = new Horse("Vasiok" , 10.0 , 100.0);
        MockedStatic<Horse> mockedHorseStatic = Mockito.mockStatic(Horse.class);

        mockedHorseStatic.when(() -> Horse.getRandomDouble(0.2 , 0.9)).thenReturn(randomNum);
        horse.move();

        mockedHorseStatic.verify(() -> Horse.getRandomDouble(0.2 , 0.9));
        assertEquals(expectedDistance, horse.getDistance());
    }
}