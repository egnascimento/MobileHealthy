package br.com.eduardo.healthy.model

data class Record(
    var timestamp: String,
    val blood_pressure: String,
    val weight: String,
    val more: String
)