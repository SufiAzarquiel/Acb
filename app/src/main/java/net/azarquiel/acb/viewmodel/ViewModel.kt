package net.azarquiel.acb.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.acb.database.EquiposDB
import net.azarquiel.acb.database.JugadorRepository
import net.azarquiel.acb.model.Equipo
import net.azarquiel.acb.model.JugadorWithEquipo

class JugadorViewModel (application: Application) : AndroidViewModel(application) {

    private var repository: JugadorRepository = JugadorRepository(application)

    fun getJugadoresWithEquipo(): LiveData<List<JugadorWithEquipo>> {
        return repository.getJugadoresWithEquipo()
    }

    fun addLike(id: Int) {
        GlobalScope.launch {
            repository.addLike(id)
            launch(Dispatchers.Main) {
            }
        }
    }
}

class EquipoRepository(application: Application) {

    val equipoDao = EquiposDB.getDatabase(application)!!.equipoDao()

    // select
    fun getEquipoById(id: Int): LiveData<Equipo> {
        return equipoDao.getEquipoById(id)
    }
}