package net.azarquiel.acb.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import net.azarquiel.acb.R
import net.azarquiel.acb.model.JugadorWithEquipo
import net.azarquiel.acb.viewmodel.JugadorViewModel

class DetalleJugadorActivity : AppCompatActivity() {
    private lateinit var ivRowDetalleInfoEquipo: ImageView
    private lateinit var tvRowDetalleEdad: TextView
    private lateinit var jugadorViewModel: JugadorViewModel
    private lateinit var tvRowDetalleNombre: TextView
    private lateinit var tvRowDetalleEquipo: TextView
    private lateinit var tvRowDetallePais: TextView
    private lateinit var tvRowDetalleAltura: TextView
    private lateinit var tvRowDetalleLikes: TextView
    private lateinit var ivRowDetalleLike: ImageView
    private lateinit var ivRowDetalleImagen: ImageView
    private lateinit var ivRowDetalleInfoJugador: ImageView
    private lateinit var jugadorWithEquipo: JugadorWithEquipo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_jugador)

        jugadorWithEquipo = intent.getSerializableExtra("jugadorWithEquipo") as JugadorWithEquipo

        findViews()
        setDatosJugador()
        jugadorViewModel = ViewModelProvider(this).get(JugadorViewModel::class.java)

        ivRowDetalleLike.setOnClickListener {
            jugadorWithEquipo.jugador.likes++
            tvRowDetalleLikes.text = jugadorWithEquipo.jugador.likes.toString()
            jugadorViewModel.addLike(jugadorWithEquipo.jugador.id)
        }

        ivRowDetalleInfoEquipo.setOnClickListener {
            val intent = Intent(this, DetalleEquipoActivity::class.java)
            intent.putExtra("equipo", jugadorWithEquipo.equipo)
            startActivity(intent)
        }

        ivRowDetalleInfoJugador.setOnClickListener {
            val intent = Intent(this, DetalleJugadorWebView::class.java)
            intent.putExtra("jugador", jugadorWithEquipo.jugador)
            startActivity(intent)
        }
    }

    private fun findViews() {
        ivRowDetalleImagen =  findViewById(R.id.ivRowDetalleImagen)
        tvRowDetalleNombre =  findViewById(R.id.tvRowDetalleNombre)
        tvRowDetalleEquipo =  findViewById(R.id.tvRowDetalleEquipo)
        tvRowDetallePais =  findViewById(R.id.tvRowDetallePais)
        tvRowDetalleEdad =  findViewById(R.id.tvRowDetalleEdad)
        tvRowDetalleAltura =  findViewById(R.id.tvRowDetalleAltura)
        tvRowDetalleLikes =  findViewById(R.id.tvRowDetalleLikes)
        ivRowDetalleLike =  findViewById(R.id.ivRowDetalleLike)
        ivRowDetalleInfoEquipo =  findViewById(R.id.ivRowDetalleInfoEquipo)
        ivRowDetalleInfoJugador =  findViewById(R.id.ivRowDetalleInfoJugador)
    }

    private fun setDatosJugador() {
        tvRowDetalleNombre.text = jugadorWithEquipo.jugador.nombre
        tvRowDetalleEquipo.text = jugadorWithEquipo.equipo.nombre
        tvRowDetallePais.text = jugadorWithEquipo.jugador.pais
        tvRowDetalleAltura.text = jugadorWithEquipo.jugador.estatura.toString()
        tvRowDetalleLikes.text = jugadorWithEquipo.jugador.likes.toString()
        tvRowDetalleEdad.text = jugadorWithEquipo.jugador.edad.toString()

        // load image from url
        var url = jugadorWithEquipo.jugador.imagen
        Picasso.get()
            .load(url)
            .into(ivRowDetalleImagen)
    }


}