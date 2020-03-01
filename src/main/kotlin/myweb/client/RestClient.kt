package com.loyalty.transfer.api.client


import myweb.dto.location.WeatherRequest
import myweb.dto.location.WeatherResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.retry.annotation.Backoff
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.util.*

enum class TransferOperation(val path: String) {
    Transfer("transfer"),
    Merge("merge")
}

@Service
class RestClient @Autowired constructor(val restTemplate: RestTemplate) {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @Value("\${weather.api.url}")
    lateinit var weatherUri: String

    @Value("\${location.api.uri}")
    private lateinit var locationUri: String


    @Throws(HttpClientErrorException::class)
    @Retryable(include = [Exception::class], exclude = [HttpClientErrorException::class],
            maxAttemptsExpression = "\${weather.retry.attempts}",
            backoff = Backoff(delayExpression = "\${weather.retry.backoff.delay}",
                    multiplier = 2.0))
    fun callWeatherApi(request: WeatherRequest, respClass: Class<WeatherResponse>): WeatherResponse {

        val response: ResponseEntity<WeatherResponse>

        val httpHeaders = HttpHeaders()

        val httpEntity = HttpEntity(request, httpHeaders)

        val uri = UriComponentsBuilder.fromHttpUrl(weatherUri).build().toUri()

        log.debug("Sending request to Weather API. request={}, url={}", httpEntity, uri)
        response = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, respClass)
        log.debug("Got response from Weather API. response={}", response)

        return response.body!!
    }

    
}
