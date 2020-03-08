package uk.co.stevebosman.close

import java.lang.Double.isNaN
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/**
 * Relative difference scaling: Max(|[a]|,|[b]|)
 */
fun maxAbsAOrB(a: Double, b: Double): Double {
    return max(abs(a), abs(b))
}

/**
 * Relative difference scaling: Max([a],[b])
 */
fun maxAOrB(a: Double, b: Double): Double {
    return max(a, b)
}

/**
 * Relative difference scaling: Min(|[a]|,|[b]|)
 */
fun minAbsAOrB(a: Double, b: Double): Double {
    return min(abs(a), abs(b))
}

/**
 * Relative difference scaling: Min([a],[b])
 */
fun minAOrB(a: Double, b: Double): Double {
    return min(a, b)
}

/**
 * Relative difference scaling: Mean: (|[a]|+|[b]|)/2, using Welford's method
 */
fun meanAbs(a: Double, b: Double): Double {
    val absA = abs(a)
    val absB = abs(b)
    return mean(absA, absB)
}

/**
 * Relative difference scaling: Mean: ([a]+[b])/2, using Welford's method
 */
fun mean(a: Double, b: Double): Double {
    return a + (b - a) / 2.0
}

/**
 * Relative error scaling: abs([a])
 */
fun absA(a: Double, b: Double): Double {
    return abs(a)
}

/**
 * Relative error scaling: abs([b])
 */
fun absB(a: Double, b: Double): Double {
    return abs(b)
}

/**
 * Check if [a] is approximately equal to [b],
 * that is if:
 *   abs([a] - [b]) <= max([relativeTolerance] * [relativeToleranceScalingFunction] ([a],[b]), [absoluteTolerance])
 */
fun isClose(
    a: Double, b: Double,
    relativeTolerance: Double = 1e-09,
    absoluteTolerance: Double = 0.0,
    equalNaN: Boolean = false,
    relativeToleranceScalingFunction: (Double, Double) -> Double = ::maxAbsAOrB
): Boolean {
    var result = false
    if (equalNaN && isNaN(a) && isNaN(b)) {
        // NaNs can, optionally, be close to other NaNs
        result = true
    } else if (a == b) {
        // Same values are always close
        result = true
    } else if (a == Double.POSITIVE_INFINITY || a == Double.NEGATIVE_INFINITY || b == Double.POSITIVE_INFINITY || b == -Double.NEGATIVE_INFINITY) {
        // Infinities are never close to other values except themselves
        result = false
    } else {
        val absoluteDifference = abs(a - b)
        if (absoluteTolerance >= absoluteDifference) {
            result = true
        } else {
            val relativeDifference = absoluteDifference / relativeToleranceScalingFunction(a, b)
            if (relativeTolerance >= relativeDifference) {
                result = true
            }
        }
    }

    return result
}

