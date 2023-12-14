package net.azarquiel.acb.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import net.azarquiel.acb.R
import net.azarquiel.acb.model.Equipo

class DetalleEquipoActivity : AppCompatActivity() {
    private lateinit var equipo: Equipo
    private lateinit var tvDetalleEquipoNombre: TextView
    private lateinit var ivDetalleEquipoEscudo: ImageView
    private lateinit var ivDetalleEquipoEstadio: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_equipo)

        equipo = intent.getSerializableExtra("equipo") as Equipo

        findViews()
        setDatosEquipo()
    }

    private fun findViews() {
        tvDetalleEquipoNombre =  findViewById(R.id.tvDetalleEquipoNombre)
        ivDetalleEquipoEscudo =  findViewById(R.id.ivDetalleEquipoEscudo)
        ivDetalleEquipoEstadio =  findViewById(R.id.ivDetalleEquipoEstadio)
    }

    private fun setDatosEquipo() {
        tvDetalleEquipoNombre.text = equipo.nombre
        // load images from url
        var url = equipo.imgescudo
        Picasso.get()
            .load(url)
            .into(ivDetalleEquipoEscudo)

        url = equipo.imgestadio
        Picasso.get()
            .load(url)
            .into(ivDetalleEquipoEstadio)
    }
}