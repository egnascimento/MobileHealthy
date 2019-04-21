package br.com.eduardo.healthy.model

/*
data class Record(
    val blood_pressure: Int,
    val weight: Int,
    val more: String,
    val timestamp: String
)*/


data class Record(
    var timestamp: String,
    val blood_pressure: String,
    val weight: String
)