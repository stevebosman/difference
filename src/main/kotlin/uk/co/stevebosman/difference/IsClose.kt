package uk.co.stevebosman.difference

import uk.co.stevebosman.difference.scaling.uk.co.stevebosman.close.scaling.RelativeToleranceScalingFunction
import uk.co.stevebosman.difference.scaling.uk.co.stevebosman.close.scaling.maxAbsAOrB
import java.lang.Double.isNaN
import kotlin.math.abs

/**
 * Check if [a] is approximately equal to [b],
 * that is if:
 *   abs([a] - [b]) <= max([relativeTolerance] * [scalingFunction].function([a],[b]), [absoluteTolerance])
 */
fun isClose(
    a: Double, b: Double,
    relativeTolerance: Double = 1e-09,
    absoluteTolerance: Double = 0.0,
    equalNaN: Boolean = false,
    scalingFunction: RelativeToleranceScalingFunction
): Boolean {
    return isClose(a,b,relativeTolerance, absoluteTolerance, equalNaN, scalingFunction.function)
}

/**
 * Check if [a] is approximately equal to [b],
 * that is if:
 *   abs([a] - [b]) <= max([relativeTolerance] * [scalingFunction] ([a],[b]), [absoluteTolerance])
 */
fun isClose(
    a: Double, b: Double,
    relativeTolerance: Double = 1e-09,
    absoluteTolerance: Double = 0.0,
    equalNaN: Boolean = false,
    scalingFunction: (Double, Double) -> Double = ::maxAbsAOrB
): Boolean {
    var result = false
    if (equalNaN && isNaN(a) && isNaN(b)) {
        // NaNs can, optionally, be close to other NaNs
        result = true
    } else if (a == b) {
        // Same values are always close
        result = true
    } else if (a == Double.POSITIVE_INFINITY || a == Double.NEGATIVE_INFINITY || b == Double.POSITIVE_INFINITY || b == Double.NEGATIVE_INFINITY) {
        // Infinities are never close to other values except themselves
        result = false
    } else {
        println("a,b: $a, $b")
        val absoluteDifference = absoluteDifference(a, b)
        println("absoluteDifference: $absoluteDifference")
        if (absoluteTolerance >= absoluteDifference) {
            result = true
        } else {
            val relativeDifference = absoluteDifference / scalingFunction(a, b)
            println("relativeDifference: $relativeDifference")
            if (relativeTolerance >= relativeDifference) {
                result = true
            }
        }
    }

    return result
}

/**
 * Determine absolute difference between two values [a] and [b],
 * i.e. |[a]-[b]|
 */
fun absoluteDifference(
    a: Double, b: Double
) = abs(a - b)

/**
 * Determine relative difference between two values [a] and [b],
 * i.e. |[a]-[b]| / [scalingFunction].function([a],[b])
 */
fun relativeDifference(
    a: Double, b: Double,
    scalingFunction: RelativeToleranceScalingFunction
) = relativeDifference(a, b, scalingFunction.function)

/**
 * Determine relative difference between two values [a] and [b],
 * i.e. |[a]-[b]| / [scalingFunction] ([a],[b])
 */
fun relativeDifference(
    a: Double, b: Double,
    scalingFunction: (Double, Double) -> Double = ::maxAbsAOrB
) = absoluteDifference(a, b) / scalingFunction(a, b)

