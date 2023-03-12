package uk.co.stevebosman.difference.scaling.uk.co.stevebosman.close.scaling

enum class RelativeToleranceScalingFunction(val function: (Double, Double) -> Double) {
    RelativeChange(::absB),
    ReversedRelativeChange(::absA),
    ArithmeticMeanChange(::mean),
    GeometricMeanChange(::geometricMean),
    HarmonicMeanChange(::harmonicMean),
    MaximumMeanChange(::maxAOrB),
    MinimumMeanChange(::minAOrB),
    LogarithmicChange(::logarithmicChangeFunction)
}
