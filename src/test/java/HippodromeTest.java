import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    private List<Horse> horseList = new ArrayList<>();
    private List<Horse> horseListMocks = new ArrayList<>();
    {
        for (int i = 1; i <= 30; i++) {
            String name = "Horse_" + i;
            double speed = 10.0 + i;
            double distance = 100.0 - i;
            horseList.add(new Horse(name, speed, distance));
        }
    }

    {
        for (int i = 1; i <= 50; i++) {
            horseListMocks.add(Mockito.mock(Horse.class));
        }
    }

    @Test
    void hipoConstructorInitializationTest() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.",
                assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null)).getMessage());

        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.",
                assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>())).getMessage());
    }

    @Test
    void getHorsesTest() {
        Hippodrome hipo = new Hippodrome(horseList);
        List<Horse> horsesResultList = hipo.getHorses();
        assertEquals(horseList, horsesResultList);
    }

    @Test
    void moveOnHipoTest() {
        Hippodrome hipo = new Hippodrome(horseListMocks);
        hipo.move();

        for (Horse horseListMock : horseListMocks) {
            Mockito.verify(horseListMock , Mockito.only()).move();
        }
    }

    @Test
    void getWinnerOnHippodromeTest() {
        Hippodrome hipo = new Hippodrome(horseList);
        Horse horse = horseList.stream().max(Comparator.comparing(Horse::getDistance)).get();
        assertEquals(horse, hipo.getWinner());
    }
}