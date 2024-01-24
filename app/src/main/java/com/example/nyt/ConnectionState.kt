package com.example.nyt

sealed class ConnectionState {
    object Available : ConnectionState()
    object Unavailable : ConnectionState()
}