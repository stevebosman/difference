package uk.co.stevebosman.close
import org.junit.jupiter.api.Assertions
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

    @Test
    fun absoluteDifference() {
        Assertions.assertEquals(4.0, absoluteDifference(1.5, -2.5))
        Assertions.assertEquals(Double.NaN, absoluteDifference(Double.NaN, -2.5))
        Assertions.assertEquals(Double.POSITIVE_INFINITY, absoluteDifference(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY))
        Assertions.assertEquals(Double.POSITIVE_INFINITY, absoluteDifference(1.3, Double.NEGATIVE_INFINITY))
        Assertions.assertEquals(Double.POSITIVE_INFINITY, absoluteDifference(Double.POSITIVE_INFINITY, 2.5))
        Assertions.assertEquals(Double.POSITIVE_INFINITY, absoluteDifference(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY))
        Assertions.assertEquals(Double.NaN, absoluteDifference(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY))
        Assertions.assertEquals(Double.NaN, absoluteDifference(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY))
    }

    @Test
    fun relativeDifference() {
        Assertions.assertEquals(2.25, relativeDifference(2.0, -2.5, ::absA))
        Assertions.assertEquals(Double.POSITIVE_INFINITY, relativeDifference(2.0, Double.NEGATIVE_INFINITY, ::absA))
        Assertions.assertEquals(Double.NaN, relativeDifference(Double.POSITIVE_INFINITY, -2.5, ::absA))
        Assertions.assertEquals(Double.NaN, relativeDifference(2.0, Double.NEGATIVE_INFINITY, ::maxAbsAOrB))
        Assertions.assertEquals(Double.NaN, relativeDifference(Double.POSITIVE_INFINITY, -2.5, ::maxAbsAOrB))
    }
}