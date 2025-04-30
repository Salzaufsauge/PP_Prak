package P1.Sensors

import kotlin.math.roundToInt

class RoundValues(val sensor: Sensor): Sensor {
    override fun getTemperature(): Double =
        sensor.getTemperature().roundToInt().toDouble()
}