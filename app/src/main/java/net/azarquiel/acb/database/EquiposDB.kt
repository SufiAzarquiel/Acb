package net.azarquiel.acb.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.azarquiel.acb.model.Equipo
import net.azarquiel.acb.model.Jugador
import net.azarquiel.acb.model.JugadorWithEquipo

@Database(entities = [Jugador::class, Equipo::class], version = 1)
abstract class EquiposDB: RoomDatabase() {
    abstract fun jugadorDao(): JugadorDao
    abstract fun equipoDao(): EquipoDao
    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE:  EquiposDB? = null

        fun getDatabase(context: Context): EquiposDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EquiposDB::class.java,   "acb.sqlite"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}