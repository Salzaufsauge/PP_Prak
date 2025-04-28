package Sensors

class IncreasingSensor(var startTemp: Double): Sensor {
    override fun getTemperature(): Double{
        val temp = startTemp
        startTemp += 0.5
        return temp
    }
}