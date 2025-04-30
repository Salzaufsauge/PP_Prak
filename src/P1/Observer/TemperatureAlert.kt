package P1.Observer

class TemperatureAlert(val threshold: Double, val msg: String): TemperatureObserver {
    override fun update(temp: Double) {
        if(temp > threshold) println(msg)
    }
}