package P1

import P1.Thermometer.Thermometer
import P1.Sensors.RandomSensor
import P1.Sensors.ConstantSensor
import P1.Sensors.SensorLogger


class A2 {
    fun output(){
        val constantSensor = ConstantSensor (temp = 21.5)
        val a: Thermometer = Thermometer(SensorLogger(constantSensor))
        a.measure(10)
        val randomSensor = RandomSensor (min = 2.0, max = 8.0)
        a.sensor = SensorLogger(randomSensor)
        a.measure(10)
    }
}