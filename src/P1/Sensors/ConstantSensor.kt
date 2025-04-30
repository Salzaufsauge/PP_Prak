package P1.Sensors

class ConstantSensor(val temp: Double): Sensor {
    override fun getTemperature(): Double = temp
}