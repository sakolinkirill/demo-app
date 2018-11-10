package com.issoft.meetup.app1.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class User(
        val user_name: String? = null,
        val authorities: List<String>? = null)