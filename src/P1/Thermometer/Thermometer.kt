package P1.Thermometer

import P1.Observer.TemperatureObserver
import P1.Sensors.Sensor

class Thermometer(var sensor: Sensor): TemperatureSubject {
    fun measure(times: Int) {
        repeat(times) {
            //println(sensor.getTemperature())
            val temp = sensor.getTemperature()
            observer.forEach{it.update(temp)}
        }
    }

    override val observer: MutableList<TemperatureObserver> = mutableListOf()

    override fun addObserver(o: TemperatureObserver) {
        observer.add(o)
    }

    override fun removeObserver(o: TemperatureObserver) {
        observer.remove(o)
    }
}