import Sensors.*

fun main() {
    val randomSensor = RandomSensor (min = 2.0, max = 8.0)
    repeat (3) {
        println ("Random Sensor ${ randomSensor . getTemperature ()}")
    }
    val constantSensor = ConstantSensor (temp = 21.5)
            repeat (3) {
                println (" Constant Sensor : ${ constantSensor . getTemperature ()}")
            }
    val increasingSensor = IncreasingSensor ( startTemp = 15.0)
            repeat (3) {
                println (" Increasing Sensor ${ increasingSensor . getTemperature ()}")
            }
    val realWorldSenor = RealWorldSensor (51.023080 , 7.562183)
    println ("Real World Sensor ")
    println (" Gummersbach : ${ realWorldSenor . getTemperature ()}")
    realWorldSenor .lat = 50.941319
    realWorldSenor .long = 6.958210
    println ("Köln: ${ realWorldSenor . getTemperature ()}")
    val sinusoidalSensor = SinusoidalSensor(
        amplitude = 10.0,
        frequency = 0.1,
        phaseShift = 0.1
    )
    println("BONUS Sinusoidal Sensor")
    repeat(100) {
        println(sinusoidalSensor.getTemperature())
    }
}