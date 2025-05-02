package P1.Tasks

import P1.Observer.HeatingSystemObserver
import P1.Observer.TemperatureAlert
import P1.Sensors.RandomSensor
import P1.Sensors.RoundValues
import P1.Sensors.SensorLogger
import P1.Thermometer.Thermometer

class A4 {
    fun output(){
        val sensor = SensorLogger ( RoundValues ( RandomSensor (-10.0 , 50.0)))
        val thermometer = Thermometer(sensor = sensor)
        val alertObserver = TemperatureAlert(
            30.0,
            "Ganz schön heiß"
        )
        val heatingSystemObserver = HeatingSystemObserver(
            19.0,
            23.0
        )
        thermometer . addObserver ( alertObserver )
        thermometer . addObserver ( heatingSystemObserver )
        thermometer . measure (20)
    }
}