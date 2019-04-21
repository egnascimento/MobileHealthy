package br.com.eduardo.healthy.api


import br.com.eduardo.healthy.model.Record
import retrofit2.Call
import retrofit2.http.GET

interface RecordAPI {

    @GET("/api/record")
    fun getRecords() : Call<List<Record>>

    /*@GET("/nota/{id}")
    fun getNotas(@Path("id") id:String)*/
}