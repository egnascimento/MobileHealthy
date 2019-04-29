package br.com.eduardo.healthy.view.formulario

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.eduardo.healthy.model.Record
import br.com.eduardo.healthy.model.ResponseStatus
import br.com.eduardo.healthy.repository.RecordRepository

class FormularioViewModel : ViewModel() {

    val recordRepository = RecordRepository()
    val responseStatus: MutableLiveData<ResponseStatus> = MutableLiveData()

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun salvar(
        blood_pressure: String,
        weight: String,
        more: String,
        timestamp: String
    ) {
        isLoading.value = true
        val record = Record(timestamp=timestamp, blood_pressure = blood_pressure, weight = weight, more = more )
        recordRepository.salvar(record,
            onComplete = {
                isLoading.value = false
                responseStatus.value = ResponseStatus(
                    true,
                    "Dados enviados com sucesso"
                )
            }, onError = {
                isLoading.value = false
                responseStatus.value = ResponseStatus(
                    false,
                    it?.message!!
                )
            })

    }

    fun excluir(timestamp: String) {
        recordRepository.excluir(timestamp,  onComplete = {
            isLoading.value = false
            responseStatus.value = ResponseStatus(
                true,
                "Dado excluido com sucesso"
            )
        }, onError = {
            isLoading.value = false
            responseStatus.value = ResponseStatus(
                false,
                it?.message!!
            )
        })
    }

}