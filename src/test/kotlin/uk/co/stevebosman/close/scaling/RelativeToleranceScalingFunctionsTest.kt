package uk.co.stevebosman.close.scaling.uk.co.stevebosman.close.scaling

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class RelativeToleranceScalingFunctionsTest {
    @ParameterizedTest(name = "logarithmicChangeFunction should return {2} for {0}, {1}")
    @CsvSource(
        value = [
            "3.0, 3.0, 3.0, 0.0",
            "2.71828, 1.0, 1.71828, 0.00001"
        ]
    )
    fun `logarithmicChangeFunction should return expected result`(a: Double, b: Double, expected: Double, epsilon: Double) {
        assertEquals(expected, logarithmicChangeFunction(a, b), epsilon)
    }

    @ParameterizedTest(name = "harmonicMean should return {2} for {0}, {1}")
    @CsvSource(
        value = [
            "1.0, 1.0, 1.0",
            "5.0, 15.0, 7.5"
        ]
    )
    fun `harmonicMean should return expected result`(a: Double, b: Double, expected: Double) {
        assertEquals(expected, harmonicMean(a, b))
    }

    @ParameterizedTest(name = "geometricMean should return {2} for {0}, {1}")
    @CsvSource(
        value = [
            "1.0, 1.0, 1.0",
            "4.0, 9.0, 6.0"
        ]
    )
    fun `geometricMean should return expected result`(a: Double, b: Double, expected: Double) {
        assertEquals(expected, geometricMean(a, b))
    }

    @ParameterizedTest(name = "maxAbsAOrB should return {2} for {0}, {1}")
    @CsvSource(
        value = [
            "2.0, -3.0, 3.0"
        ]
    )
    fun `maxAbsAOrB should return expected result`(a: Double, b: Double, expected: Double) {
        assertEquals(expected, maxAbsAOrB(a, b))
    }


    @ParameterizedTest(name = "maxAOrB should return {2} for {0}, {1}")
    @CsvSource(
        value = [
            "2.0, -3.0, 2.0"
        ]
    )
    fun `maxAOrB should return expected result`(a: Double, b: Double, expected: Double) {
        assertEquals(expected, maxAOrB(a, b))
    }

    @ParameterizedTest(name = "minAbsAOrB should return {2} for {0}, {1}")
    @CsvSource(
        value = [
            "2.0, -3.0, 2.0"
        ]
    )
    fun `minAbsAOrB should return expected result`(a: Double, b: Double, expected: Double) {
        assertEquals(expected, minAbsAOrB(a, b))
    }

    @ParameterizedTest(name = "minAOrB should return {2} for {0}, {1}")
    @CsvSource(
        value = [
            "2.0, -3.0, -3.0"
        ]
    )
    fun `minAOrB should return expected result`(a: Double, b: Double, expected: Double) {
        assertEquals(expected, minAOrB(a, b))
    }

    @ParameterizedTest(name = "meanAbs should return {2} for {0}, {1}")
    @CsvSource(
        value = [
            "2.0, -3.0, 2.5"
        ]
    )
    fun `meanAbs should return expected result`(a: Double, b: Double, expected: Double) {
        assertEquals(expected, meanAbs(a, b))
    }

    @ParameterizedTest(name = "mean should return {2} for {0}, {1}")
    @CsvSource(
        value = [
            "2.0, -3.0, -0.5"
        ]
    )
    fun `mean should return expected result`(a: Double, b: Double, expected: Double) {
        assertEquals(expected, mean(a, b))
    }

    @ParameterizedTest(name = "absA should return {2} for {0}, {1}")
    @CsvSource(
        value = [
            "2.0, -3.0, 2.0",
            "-2.0, -3.0, 2.0"
        ]
    )
    fun `absA should return expected result`(a: Double, b: Double, expected: Double) {
        assertEquals(expected, absA(a, b))
    }

    @ParameterizedTest(name = "absB should return {2} for {0}, {1}")
    @CsvSource(
        value = [
            "2.0, -3.0, 3.0",
            "2.0, 3.0, 3.0"
        ]
    )
    fun `absB should return expected result`(a: Double, b: Double, expected: Double) {
        assertEquals(expected, absB(a, b))
    }
}