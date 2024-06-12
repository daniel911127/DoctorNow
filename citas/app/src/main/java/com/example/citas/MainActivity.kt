package com.example.citas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerViewCitas: RecyclerView
    private lateinit var citasAdapter: CitasAdapter

    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewCitas = findViewById(R.id.recyclerViewCitas)
        recyclerViewCitas.layoutManager = LinearLayoutManager(this)

        val citas = listOf(
            Cita("Doctor Miguel", "24/05/2024", "10:00 AM"),
            Cita("Doctor Miguel", "24/05/2024", "10:00 AM"),
            Cita("Doctor Miguel", "24/05/2024", "10:00 AM")
        )

        citasAdapter = CitasAdapter(citas)
        recyclerViewCitas.adapter = citasAdapter
    }
}

data class Cita(val doctor: String, val fecha: String, val hora: String)

class CitasAdapter(private val citas: List<Cita>) : RecyclerView.Adapter<CitasAdapter.CitaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cita, parent, false)
        return CitaViewHolder(view)
    }

    override fun onBindViewHolder(holder: CitaViewHolder, position: Int) {
        val cita = citas[position]
        holder.bind(cita)
    }

    override fun getItemCount(): Int = citas.size

    class CitaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewDoctor: TextView = itemView.findViewById(R.id.textViewDoctor)
        private val textViewFecha: TextView = itemView.findViewById(R.id.textViewFecha)
        private val textViewHora: TextView = itemView.findViewById(R.id.textViewHora)

        fun bind(cita: Cita) {
            textViewDoctor.text = cita.doctor
            textViewFecha.text = cita.fecha
            textViewHora.text = cita.hora
        }
    }
}
