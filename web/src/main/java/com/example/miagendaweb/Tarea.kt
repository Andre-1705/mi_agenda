package com.example.miagendaweb

import jakarta.persistence.*

@Entity
data class Tarea(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var dia: String = "",

    var hora: String = "",

    var texto: String = ""
) {
    // Constructor secundario para que Java pueda hacer new Tarea(dia, hora, texto)
    constructor(dia: String, hora: String, texto: String) : this(null, dia, hora, texto)

    override fun toString(): String = "$hora - $texto"
}