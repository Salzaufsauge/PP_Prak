package Sensors

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class RealWorldSensor(var lat: Double, var long: Double) : Sensor {
    private val client = HttpClient.newBuilder().build()
    private val requestBuilder = HttpRequest.newBuilder().GET()

    override fun getTemperature(): Double {
        val uri = "https://api.brightsky.dev/current_weather?lat=$lat&lon=$long"
        val request = requestBuilder.uri(URI.create(uri)).build()
        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        val temp = parseBody(response.body())
        return temp
    }

    private fun parseBody(body: String): Double {
        val startKey = "\"temperature\":"
        return body.indexOf(startKey)
            .takeIf { it != -1 }
            ?.let { startIndex ->
                body.drop(startIndex + startKey.length)
                    .takeWhile { it != ',' }
                    .toDoubleOrNull()
            }
            ?: 0.0
    }
}