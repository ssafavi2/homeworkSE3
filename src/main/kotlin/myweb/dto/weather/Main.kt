package myweb.dto.weather

import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize
data class Main( val temp: String,
                 val pressure: String,
                 val humidity: String,
                 val temp_min: String,
                  val temp_max: String)
