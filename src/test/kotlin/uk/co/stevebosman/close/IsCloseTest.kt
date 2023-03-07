package uk.co.stevebosman.close

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import uk.co.stevebosman.close.scaling.uk.co.stevebosman.close.scaling.RelativeToleranceScalingFunction
import uk.co.stevebosman.close.scaling.uk.co.stevebosman.close.scaling.absA

class IsCloseTest {
    @ParameterizedTest(name = "isClose returns true with {0}, {1} using {2}")
    @CsvSource(
        value = [
            "100.0, 100.0000001, ArithmeticMeanChange",
            "100.0, 100.0000001, RelativeChange",
            "100.0, 100.0000001, ReversedRelativeChange"
        ]
    )
    fun `isClose with defaults gives true`(
        a: Double,
        b: Double,
        function: RelativeToleranceScalingFunction
    ) {
        assertTrue(isClose(a, b, scalingFunction = function))
    }

    @ParameterizedTest(name = "isClose returns true with {0}, {1} using {5}")
    @CsvSource(
        value = [
            "100.0, 100.01, 0.0, 0.0100001, false, ArithmeticMeanChange",
            "100.0, 100.01, 1e-4, 0.0, false, RelativeChange",
            "100.0, 100.0, 0.0, 0.0, true, ReversedRelativeChange"
        ]
    )
    fun `isClose gives true`(
        a: Double,
        b: Double,
        relativeTolerance: Double,
        absoluteTolerance: Double,
        equalNaN: Boolean,
        function: RelativeToleranceScalingFunction
    ) {
        assertTrue(isClose(a, b, relativeTolerance, absoluteTolerance, equalNaN, function))
    }

    @ParameterizedTest(name = "isClose returns true with {0}, {1}")
    @CsvSource(
        value = [
            "100.0, 100.01, 0.0, 0.0100001, false",
            "100.0, 100.01, 1e-4, 0.0, false",
            "100.0, 100.0, 0.0, 0.0, true"
        ]
    )
    fun `isClose gives true`(
        a: Double,
        b: Double,
        relativeTolerance: Double,
        absoluteTolerance: Double,
        equalNaN: Boolean
    ) {
        assertTrue(isClose(a, b, relativeTolerance, absoluteTolerance, equalNaN))
    }

    @Test
    fun `isClose gives false if left positive infinite`() {
        assertFalse(isClose(Double.POSITIVE_INFINITY, 0.0))
    }

    @Test
    fun `isClose gives false if left negative infinite`() {
        assertFalse(isClose(Double.NEGATIVE_INFINITY, 1.0))
    }

    @Test
    fun `isClose gives false if right positive infinite`() {
        assertFalse(isClose(2.0, Double.POSITIVE_INFINITY))
    }

    @Test
    fun `isClose gives false if right negative infinite`() {
        assertFalse(isClose(3.0, Double.NEGATIVE_INFINITY))
    }

    @Test
    fun `isClose gives true if both positive infinite`() {
        assertTrue(isClose(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY))
    }

    @Test
    fun `isClose gives true if both negative infinite`() {
        assertTrue(isClose(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY))
    }

    @Test
    fun `isClose gives false if both NaNs`() {
        assertFalse(isClose(Double.NaN, Double.NaN, equalNaN = false))
    }

    @Test
    fun `isClose gives true if both NaNs and equalNaN allowed`() {
        assertTrue(isClose(Double.NaN, Double.NaN, equalNaN = true))
    }

    @Test
    fun absoluteDifference() {
        assertEquals(4.0, absoluteDifference(1.5, -2.5))
        assertEquals(Double.NaN, absoluteDifference(Double.NaN, -2.5))
        assertEquals(Double.POSITIVE_INFINITY, absoluteDifference(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY))
        assertEquals(Double.POSITIVE_INFINITY, absoluteDifference(1.3, Double.NEGATIVE_INFINITY))
        assertEquals(Double.POSITIVE_INFINITY, absoluteDifference(Double.POSITIVE_INFINITY, 2.5))
        assertEquals(Double.POSITIVE_INFINITY, absoluteDifference(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY))
        assertEquals(Double.NaN, absoluteDifference(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY))
        assertEquals(Double.NaN, absoluteDifference(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY))
    }

    @Test
    fun relativeDifferenceWithEnum() {
        assertEquals(-1.8, relativeDifference(2.0, -2.5, RelativeToleranceScalingFunction.MinimumMeanChange))
        assertEquals(
            Double.NaN,
            relativeDifference(2.0, Double.NEGATIVE_INFINITY, RelativeToleranceScalingFunction.MinimumMeanChange)
        )
        assertEquals(
            Double.NEGATIVE_INFINITY,
            relativeDifference(Double.POSITIVE_INFINITY, -2.5, RelativeToleranceScalingFunction.MinimumMeanChange)
        )
        assertEquals(
            Double.POSITIVE_INFINITY,
            relativeDifference(2.0, Double.NEGATIVE_INFINITY, RelativeToleranceScalingFunction.MaximumMeanChange)
        )
        assertEquals(
            Double.NaN,
            relativeDifference(Double.POSITIVE_INFINITY, -2.5, RelativeToleranceScalingFunction.MaximumMeanChange)
        )
    }

    @Test
    fun relativeDifference() {
        assertEquals(2.25, relativeDifference(2.0, -2.5, ::absA))
        assertEquals(Double.POSITIVE_INFINITY, relativeDifference(2.0, Double.NEGATIVE_INFINITY, ::absA))
        assertEquals(Double.NaN, relativeDifference(Double.POSITIVE_INFINITY, -2.5, ::absA))
        assertEquals(1.8, relativeDifference(2.0, -2.5))
        assertEquals(Double.NaN, relativeDifference(2.0, Double.NEGATIVE_INFINITY))
        assertEquals(Double.NaN, relativeDifference(Double.POSITIVE_INFINITY, -2.5))
    }
}