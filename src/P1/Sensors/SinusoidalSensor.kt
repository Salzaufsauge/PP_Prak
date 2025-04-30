package P1.Sensors

import kotlin.math.sin

class SinusoidalSensor(val amplitude: Double, val frequency: Double, val phaseShift: Double = 0.0) {
    fun getTemperature(): Double =
        amplitude * sin(2 * Math.PI * frequency * (System.currentTimeMillis()/1000.0) + phaseShift)
}
