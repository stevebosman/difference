import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import uk.co.stevebosman.close.*

class IsCloseTest {
    @Test
    fun maxAbsAOrB() {
        assertEquals(3.0, maxAbsAOrB(2.0, -3.0))
    }

    @Test
    fun maxAOrB() {
        assertEquals(2.0, maxAOrB(2.0, -3.0))
    }

    @Test
    fun minAbsAOrB() {
        assertEquals(2.0, minAbsAOrB(2.0, -3.0))
    }

    @Test
    fun minAOrB() {
        assertEquals(-3.0, minAOrB(2.0, -3.0))
    }

    @Test
    fun meanAbs() {
        assertEquals(2.5, meanAbs(2.0, -3.0))
    }

    @Test
    fun mean() {
        assertEquals(-0.5, mean(2.0, -3.0))
    }

    @Test
    fun absA() {
        assertEquals(2.0, absA(2.0, -3.0))
        assertEquals(2.0, absA(-2.0, -3.0))
    }

    @Test
    fun absB() {
        assertEquals(3.0, absB(2.0, -3.0))
        assertEquals(3.0, absB(2.0, 3.0))
    }

    @Test
    fun isClose() {
        assertTrue(isClose(100.0,100.01, 0.0, 0.0100001))
        assertTrue(isClose(100.0,100.01, 1e-4, 0.0))
    }

}