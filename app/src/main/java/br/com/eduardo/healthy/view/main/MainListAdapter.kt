package br.com.eduardo.healthy.view.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.eduardo.healthy.R
import br.com.eduardo.healthy.model.Record
import kotlinx.android.synthetic.main.record_item.view.*
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainListAdapter(
    val context:Context,
    val records: List<Record>,
    val clickLista: (Record) -> Unit
) :
    RecyclerView.Adapter<MainListAdapter.RecordViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecordViewHolder {

        val itemView = LayoutInflater.from(context)
            .inflate(R.layout.record_item, p0, false)

        return RecordViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return records.size
    }

    override fun onBindViewHolder(p0: RecordViewHolder, position: Int) {
        val record = records[position]
        p0.bindView(record, clickLista)

    }

    class RecordViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bindView(
            record: Record,
            clickLista: (Record) -> Unit
        ) = with(itemView) {

            val stamp = Timestamp(record.timestamp.toLong())
            val date = Date(stamp.getTime()).date.toString() + "/" +
                    (Date(stamp.getTime()).month + 1).toString() + "/" +
                    (Date(stamp.getTime()).year+1900).toString() + " " +
            Date(stamp.getTime()).hours.toString() + ":" +
            Date(stamp.getTime()).minutes.toString() + ":" +
            Date(stamp.getTime()).seconds.toString()




            tvTitulo.text = date.toString()
            tvDescricao.text = record.weight + "Kg "  + record.blood_pressure + "mmHg (" + record.more + ")"

            setOnClickListener { clickLista(record) }
        }

    }


}