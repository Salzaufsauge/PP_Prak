package Sensors

import kotlin.math.sin

class SinusoidalSensor(val amplitude: Double, var frequency: Double, val phaseShift: Double): Sensor {
    override fun getTemperature(): Double {
        val temp = frequency
        frequency += phaseShift
        return amplitude * sin(temp)
    }
}