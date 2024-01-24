package com.example.nyt.data

data class Person(
    val firstname: String,
    val lastname: String,
    val middlename: String,
    val organization: String,
    val qualifier: Any,
    val rank: Int,
    val role: String,
    val title: Any
)