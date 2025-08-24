package oystudy.clonecoding.auth.service.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class LoginResponse(
    @JsonProperty("access_token")
    val accessToken: String
) {
}
