package P1.Thermometer

import P1.Observer.TemperatureObserver

interface TemperatureSubject {
    val observer: MutableList<TemperatureObserver>
    fun addObserver(o: TemperatureObserver)
    fun removeObserver(o: TemperatureObserver)
}