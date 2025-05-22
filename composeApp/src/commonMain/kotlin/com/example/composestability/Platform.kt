package com.example.composestability

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform