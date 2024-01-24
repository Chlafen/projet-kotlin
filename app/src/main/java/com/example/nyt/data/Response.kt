package com.example.nyt.data

data class Response(
    val docs: List<Doc>,
    val meta: Meta
)