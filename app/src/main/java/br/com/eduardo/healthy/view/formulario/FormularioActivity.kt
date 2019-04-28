package br.com.eduardo.healthy.view.formulario

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.eduardo.healthy.R
import android.view.View
import android.widget.EditText
import android.widget.Toast
import br.com.eduardo.healthy.model.ResponseStatus
import kotlinx.android.synthetic.main.activity_formulario.*
import kotlinx.android.synthetic.main.loading.*

class FormularioActivity : AppCompatActivity() {

    private lateinit var formularioViewModel: FormularioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        formularioViewModel = ViewModelProviders.of(this)
            .get(FormularioViewModel::class.java)

        btSalvar.setOnClickListener {
            formularioViewModel.salvar(
                inputBloodPressure.editText?.text.toString(),
                inputWeight.editText?.text.toString(),
                inputMore.editText?.text.toString()
            )
        }

        val bp = findViewById<EditText>(R.id.etBloodPressure)
        bp.setText(intent.getStringExtra("bp"))

        val wg = findViewById<EditText>(R.id.etWeigth)
        wg.setText(intent.getStringExtra("wg"))

        val mr = findViewById<EditText>(R.id.etMore)
        mr.setText(intent.getStringExtra("mr"))

        registerObserver()
    }

    private fun registerObserver() {
        formularioViewModel.responseStatus.observe(this, responseObserver)
        formularioViewModel.isLoading.observe(this, loadingObserver)
    }

    private var loadingObserver = Observer<Boolean> {
        if (it == true) {
            containerLoading.visibility = View.VISIBLE
        } else {
            containerLoading.visibility = View.GONE
        }
    }

    private var responseObserver = Observer<ResponseStatus> {
        Toast.makeText(this, it?.mensagem, Toast.LENGTH_SHORT).show()
        if (it?.sucesso == true) {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}