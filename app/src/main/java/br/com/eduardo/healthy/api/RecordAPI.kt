package br.com.eduardo.healthy.api


import br.com.eduardo.healthy.model.Record
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RecordAPI {

    @GET("/api/record")
    fun getRecords() : Call<List<Record>>

    @POST("/api/record")
    fun salvar(@Body record: Record): Call<Record>

    @POST("/api/delete")
    fun excluir(@Body timestamp: Record): Call<Record>

}