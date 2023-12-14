package net.azarquiel.acb.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import net.azarquiel.acb.model.Equipo
import net.azarquiel.acb.model.JugadorWithEquipo

@Dao
interface JugadorDao {
    @Transaction
    @Query("SELECT * FROM jugador")
    fun getJugadoresWithEquipo(): LiveData<List<JugadorWithEquipo>>

    @Transaction
    @Query("UPDATE jugador SET likes = likes + 1 WHERE id = :id")
    fun addLike(id: Int)
}
@Dao
interface EquipoDao {
    @Query("SELECT * FROM equipo where id=:id")
    fun getEquipoById(id:Int): LiveData<Equipo>
}
