package com.example.crudlibrary.models

data class libro(
    var id_libro: String,
    var titulo: String,
    var autor: String,
    var isbn: String,
    var genero: String,
    var numero_ejemplares_disponibles: Int,
    var numero_ejemplares_ocupados: Int
)
