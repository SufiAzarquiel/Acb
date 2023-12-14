package net.azarquiel.acb.database

import android.app.Application
import androidx.lifecycle.LiveData
import net.azarquiel.acb.model.Equipo
import net.azarquiel.acb.model.JugadorWithEquipo

class JugadorRepository(application: Application) {

    val jugadorDao = EquiposDB.getDatabase(application).jugadorDao()
    // select
    fun getJugadoresWithEquipo(): LiveData<List<JugadorWithEquipo>> {
        return jugadorDao.getJugadoresWithEquipo()
    }

    // update
    fun addLike(id: Int) {
        jugadorDao.addLike(id)
    }
}

class EquipoRepository(application: Application) {

    val equipoDao = EquiposDB.getDatabase(application).equipoDao()
    // select
    fun getEquipoById(id : Int): LiveData<Equipo> {
        return equipoDao.getEquipoById(id)
    }
}