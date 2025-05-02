package P1.Observer

class HeatingSystemObserver(val thresholdOn: Double, val thresholdOff: Double): TemperatureObserver {
    val temperatures = mutableListOf<Double>()
    override fun update(temp: Double) {
        temperatures.add(temp)
        if(temperatures.size >= 5) {
            var averageTemp = temperatures.average()
            println("Average temp der letzten ${temperatures.size}: $averageTemp")
            temperatures.clear()
            if(averageTemp > thresholdOff)
                println("Heizung aus")
            else if(averageTemp < thresholdOn)
                println("Heizung an")
        }
    }
}