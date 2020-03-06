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

    @Throws(HttpClientErrorException::class)
    @Retryable(include = [Exception::class], exclude = [HttpClientErrorException::class],
            maxAttemptsExpression = "\${weather.retry.attempts}",
            backoff = Backoff(delayExpression = "\${weather.retry.backoff.delay}",
                    multiplier = 2.0))
    fun callWeatherApi(city: String): WeatherResponse {



        var weatherUri= "https://api.openweathermap.org/data/2.5/weather?q=$city&units=metric&appid=10ee74f194eb0f57190a4f7759fc8323"


        val uri = UriComponentsBuilder.fromHttpUrl(weatherUri).build().toUri()

        log.debug("Sending request to Weather API. request={}, url={}", uri)
        val mapper = jacksonObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        val response = restTemplate.getForObject(weatherUri, String::class.java)
        val weatherResponse = mapper.readValue(response, WeatherResponse::class.java)
        log.debug("Got response from Weather API. response={}", response)

        return weatherResponse!!
    }


}


