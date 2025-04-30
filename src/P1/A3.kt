package P1

import P1.Sensors.*
import P1.Thermometer.Thermometer

class A3 {
    fun output(){
        val sensor: Sensor =
            SensorLogger(
                RoundValues(
                    RandomSensor(2.0, 5.0)
                )
            )
        val thermometer = Thermometer(sensor)
        thermometer.measure(10)
        println()
        val sensor2: Sensor =
            SensorLogger(RoundValues(
                FahrenheitSensor(IncreasingSensor(20.0))
            ))
        thermometer.sensor = sensor2
        thermometer.measure(10)
        println()
        val sensor3: Sensor =
            SensorLogger(RoundValues(
                FahrenheitSensor(SensorLogger(
                    IncreasingSensor(20.0)))))
        thermometer.sensor = sensor3
        thermometer.measure(10)
    }
}