package br.com.eduardo.healthy.view.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.eduardo.healthy.model.Record
import br.com.eduardo.healthy.repository.RecordRepository

class MainViewModel : ViewModel() {

    val recordRepository = RecordRepository()

    val records : MutableLiveData<List<Record>> = MutableLiveData()
    val mensagemErro : MutableLiveData<String> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()


    fun buscarTodos () {
        isLoading.value = true
        recordRepository.buscarTodos(
            onComplete = {
                isLoading.value = false
                records.value = it

            },
            onError = {
                isLoading.value = false
                mensagemErro.value = it?.message
            }
        )
    }

}