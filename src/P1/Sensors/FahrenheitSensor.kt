package P1.Sensors

class FahrenheitSensor(val sensor: Sensor): Sensor {
    override fun getTemperature(): Double =
        sensor.getTemperature() * 1.8 + 32
}