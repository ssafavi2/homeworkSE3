package myweb.dto.weather

import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize
data class Coordination( val lon: String,
                         val lat: String)