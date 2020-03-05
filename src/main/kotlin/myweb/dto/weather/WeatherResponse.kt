package myweb.dto.weather


import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize
data class WeatherResponse(val coord: Coordination,
                           val main: Main
)
