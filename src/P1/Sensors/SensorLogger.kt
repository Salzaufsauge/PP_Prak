package P1.Sensors

class SensorLogger(val sensor: Sensor): Sensor {
    override fun getTemperature(): Double =
        sensor.getTemperature().also{println("Sensor Value: $it")}
}