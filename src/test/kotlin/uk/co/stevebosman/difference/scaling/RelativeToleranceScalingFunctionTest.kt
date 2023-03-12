package uk.co.stevebosman.difference.scaling.uk.co.stevebosman.close.scaling

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RelativeToleranceScalingFunctionTest {
    @Test
    fun `ArithmeticMeanChange is linked to expected function`() {
        assertEquals(::mean, RelativeToleranceScalingFunction.ArithmeticMeanChange.function)
    }

    @Test
    fun `GeometricMeanChange is linked to expected function`() {
        assertEquals(::geometricMean, RelativeToleranceScalingFunction.GeometricMeanChange.function)
    }

    @Test
    fun `HarmonicMeanChange is linked to expected function`() {
        assertEquals(::harmonicMean, RelativeToleranceScalingFunction.HarmonicMeanChange.function)
    }

    @Test
    fun `LogarithmicChange is linked to expected function`() {
        assertEquals(::logarithmicChangeFunction, RelativeToleranceScalingFunction.LogarithmicChange.function)
    }

    @Test
    fun `MaximumMeanChange is linked to expected function`() {
        assertEquals(::maxAOrB, RelativeToleranceScalingFunction.MaximumMeanChange.function)
    }

    @Test
    fun `MinimumMeanChange is linked to expected function`() {
        assertEquals(::minAOrB, RelativeToleranceScalingFunction.MinimumMeanChange.function)
    }

    @Test
    fun `RelativeChange is linked to expected function`() {
        assertEquals(::absB, RelativeToleranceScalingFunction.RelativeChange.function)
    }

    @Test
    fun `ReversedRelativeChange is linked to expected function`() {
        assertEquals(::absA, RelativeToleranceScalingFunction.ReversedRelativeChange.function)
    }
}