package net.azarquiel.acb.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.azarquiel.acb.R
import net.azarquiel.acb.model.JugadorWithEquipo

class AdapterJugadores(val context: Context,
                     val layout: Int
) : RecyclerView.Adapter<AdapterJugadores.ViewHolder>() {

    private var dataList: List<JugadorWithEquipo> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setJugadores(Jugadores: List<JugadorWithEquipo>) {
        this.dataList = Jugadores
        notifyDataSetChanged()
    }

    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: JugadorWithEquipo){
            var ivRowJugadorImagen = itemView.findViewById(R.id.ivRowJugadorImagen) as ImageView
            var tvRowJugadorNombre = itemView.findViewById(R.id.tvRowJugadorNombre) as TextView
            var tvRowJugadorEquipo = itemView.findViewById(R.id.tvRowJugadorEquipo) as TextView
            var tvRowJugadorPais = itemView.findViewById(R.id.tvRowJugadorPais) as TextView
            var tvRowJugadorLikes = itemView.findViewById(R.id.tvRowJugadorLikes) as TextView

            tvRowJugadorNombre.text = dataItem.jugador.nombre
            tvRowJugadorEquipo.text = dataItem.equipo.nombre
            tvRowJugadorPais.text = dataItem.jugador.pais
            tvRowJugadorLikes.text = dataItem.jugador.likes.toString()

            // load image from url
            var url = dataItem.jugador.imagen
            Picasso.get()
                .load(url)
                .into(ivRowJugadorImagen)

            itemView.tag = dataItem
        }
    }


}