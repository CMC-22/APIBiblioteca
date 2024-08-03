package com.example.crudlibrary

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.crudlibrary.config.config
import com.example.crudlibrary.models.libro
import com.google.gson.Gson
import org.json.JSONObject

class guardarLibroFragment : Fragment() {

    //Definir las variables
    private lateinit var txtTitulo: EditText
    private lateinit var txtAutor: EditText
    private lateinit var txtGenero: EditText
    private lateinit var txtIsbn: EditText
    private lateinit var txtDisponible: EditText
    private lateinit var txtOcupado: EditText
    private lateinit var btnGuardar: Button
    private lateinit var btnListaLibro: Button
    //se le asigna un id existente temporal
    private var id_libro: String = ""

    private fun consultarLibro(){

        if (id_libro.isNotEmpty()){
            val request= JsonObjectRequest(
                Request.Method.GET,//metodo de la peticion
                config.urlLibros+id_libro, //url
                null,//parametros
                {response->
                    val gson = Gson()
                    val libro = gson.fromJson(response.toString(), libro::class.java)
                    //se ejecuta el atributo text de los campos con el valor del objeto
                    txtAutor.setText(libro.autor)
                    txtTitulo.setText(libro.titulo)
                    txtIsbn.setText(libro.isbn)
                    txtDisponible.setText(libro.numero_ejemplares_disponibles.toString())
                    txtOcupado.setText(libro.numero_ejemplares_ocupados.toString())
                    txtGenero.setText(libro.genero)
                    Toast.makeText(context, "Se consulto correctamente", Toast.LENGTH_LONG).show()
                },
                {error->
                    Toast.makeText(context,"Error al consultar",Toast.LENGTH_LONG).show()
                }//error en la peticon
            )
            var queue=Volley.newRequestQueue(context)
            queue.add(request)
        }
    }

    private fun guardarLibro() {
        try {
            val parametros = JSONObject().apply {
                put("titulo", txtTitulo.text.toString())
                put("autor", txtAutor.text.toString())
                put("genero", txtGenero.text.toString())
                put("isbn", txtIsbn.text.toString())
                put("numero_ejemplares_disponibles", txtDisponible.text.toString())
                put("numero_ejemplares_ocupados", txtOcupado.text.toString())
            }

            val request = JsonObjectRequest(
                Request.Method.POST,
                config.urlLibros,
                parametros,
                { response ->
                    Toast.makeText(context, "Libro guardado correctamente", Toast.LENGTH_LONG).show()
                    // Limpiar los campos
                    txtTitulo.text.clear()
                    txtAutor.text.clear()
                    txtGenero.text.clear()
                    txtIsbn.text.clear()
                    txtDisponible.text.clear()
                    txtOcupado.text.clear()
                },
                { error ->
                    Toast.makeText(context, "Error al guardar el libro", Toast.LENGTH_LONG).show()
                }
            )
            val queue = Volley.newRequestQueue(context)
            queue.add(request)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Error al guardar el libro", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_guardar_libro, container, false)

        txtTitulo = view.findViewById(R.id.txtTitulo)
        txtAutor = view.findViewById(R.id.txtAutor)
        txtGenero = view.findViewById(R.id.txtGenero)
        txtIsbn = view.findViewById(R.id.txtIsbn)
        txtDisponible = view.findViewById(R.id.txtDisponible)
        txtOcupado = view.findViewById(R.id.txtOcupado)
        btnGuardar = view.findViewById(R.id.btnGuardar)
        btnListaLibro = view.findViewById(R.id.btnListaLibro)

        btnGuardar.setOnClickListener { guardarLibro() }
        btnListaLibro.setOnClickListener {
            val fragment = lista_libro()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, fragment)
                ?.addToBackStack(null)
                ?.commit()
        }

        return view
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            guardarLibroFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
