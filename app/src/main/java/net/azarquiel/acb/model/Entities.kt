package net.azarquiel.acb.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.io.Serializable

@Entity(tableName = "jugador",
    foreignKeys = [ForeignKey(entity = Equipo::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("equipo"))])
data class Jugador(
    @PrimaryKey
    var id: Int,
    var equipo: Int,
    var nombre: String,
    var imagen: String,
    var link: String,
    var pais: String,
    var estatura: Float,
    var edad: Int,
    var likes: Int): Serializable


@Entity(tableName = "equipo")
data class Equipo(
    @PrimaryKey
    var id: Int,
    var nombre:String,
    var imgestadio: String,
    var imgescudo: String): Serializable

// Relación uno a uno (un jugador => un equipo)
data class JugadorWithEquipo(
    @Embedded val jugador: Jugador,
    @Relation(
        parentColumn = "equipo",
        entityColumn = "id"
    )
    val equipo: Equipo
): Serializable