package myweb.client


import com.fasterxml.jackson.databind.DeserializationFeature
import myweb.dto.weather.WeatherResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.retry.annotation.Backoff
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
    import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper



@Service
class RestClient @Autowired constructor(val restTemplate: RestTemplate) {

    private val log = LoggerFactory.getLogger(this.javaClass)

    val API: String = "8118ed6ee68db2debfaaa5a44c832918"


    @Throws(HttpClientErrorException::class)
    @Retryable(include = [Exception::class], exclude = [HttpClientErrorException::class],
            maxAttemptsExpression = "\${weather.retry.attempts}",
            backoff = Backoff(delayExpression = "\${weather.retry.backoff.delay}",
                    multiplier = 2.0))
    fun callWeatherApi(city: String): WeatherResponse {



        var weatherUri: String = "https://samples.openweathermap.org/data/2.5/weather?q=$city&appid=b6907d289e10d714a6e88b30761fae22"


        val uri = UriComponentsBuilder.fromHttpUrl(weatherUri).build().toUri()

        log.debug("Sending request to Weather API. request={}, url={}", uri)
        val mapper = jacksonObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        val response = restTemplate.getForObject(weatherUri, String::class.java)
        val weatherResponse = mapper.readValue(response, WeatherResponse::class.java)
        log.debug("Got response from Weather API. response={}", response)

        return weatherResponse!!
    }


}


