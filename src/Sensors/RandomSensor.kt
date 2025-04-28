package Sensors

class RandomSensor(val min: Double, val max: Double) : Sensor {
    override fun getTemperature(): Double = Math.random() * (max - min) + min
}