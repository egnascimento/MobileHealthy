package br.com.eduardo.healthy.repository

import br.com.eduardo.healthy.api.getRecordAPI
import br.com.eduardo.healthy.model.Record
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecordRepository {

    fun buscarTodos(
        onComplete:(List<Record>?) -> Unit,
        onError: (Throwable?) -> Unit
    ) {

        getRecordAPI()
            .getRecords()
            .enqueue(object : Callback<List<Record>>{
                override fun onFailure(call: Call<List<Record>>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<List<Record>>, response: Response<List<Record>>) {
                    if(response.isSuccessful) {
                        onComplete(response.body())
                    } else {
                        onError(Throwable("Erro ao buscar os dados"))
                    }
                }
            })

    }

    fun salvar(record: Record,
               onComplete: (Record) -> Unit,
               onError: (Throwable?) -> Unit) {
        getRecordAPI()
            .salvar(record)
            .enqueue(object : Callback<Record>{
                override fun onFailure(call: Call<Record>, t: Throwable) {
                    onError(t)
                }
                override fun onResponse(call: Call<Record>, response: Response<Record>) {
                    onComplete(response.body()!!)
                }
            })
    }

    fun excluir(timestamp: Record,
               onComplete: (Record) -> Unit,
               onError: (Throwable?) -> Unit) {

        getRecordAPI()
            .excluir(timestamp)
            .enqueue(object : Callback<Record>{
                override fun onFailure(call: Call<Record>, t: Throwable) {
                    onError(t)
                }
                override fun onResponse(call: Call<Record>, response: Response<Record>) {
                    onComplete(response.body()!!)
                }
            })
    }
}