package com.example.crudlibrary

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.replace
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.crudlibrary.adapterLibro.adapterLibro
import com.example.crudlibrary.config.config
import com.example.crudlibrary.models.libro
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class lista_libro : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var rootView: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: adapterLibro
    private lateinit var editTextBuscar: EditText
    private  lateinit var buttonLupa: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_lista_libro, container, false)
        recyclerView = rootView.findViewById(R.id.listaLibro)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        editTextBuscar = rootView.findViewById(R.id.editTextBuscar)
        buttonLupa = rootView.findViewById(R.id.buttonLupa)

        adapter = adapterLibro(JSONArray(), requireContext())
        recyclerView.adapter = adapter

        val buttonInicio = rootView.findViewById<Button>(R.id.buttoninicio)
        buttonInicio.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }

        buttonLupa.setOnClickListener{
            val filtro = editTextBuscar.text.toString().trim()
            cargar_libro(filtro)
        }

        volveregistro()
        cargar_libro("")

        return rootView
    }


    private fun volveregistro() {
        val volverButton = rootView.findViewById<ImageView>(R.id.imageView2)
        volverButton.setOnClickListener {
            requireFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, guardarLibroFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun cargar_libro(filtro: String) {
        val url = if (filtro.isEmpty()) {
            config.urlLibros
        } else {
            "${config.urlLibros}busquedafiltro/$filtro"
        }
        val request = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                adapter = adapterLibro(response, requireContext())

                adapter.onclickEditar = { libro ->
                    val bundle = Bundle().apply {
                        putString("id_libro", libro.getString("id_libro"))
                    }
                    val fragment = detalleLibroFragment().apply {
                        arguments = bundle
                    }
                    requireFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit()
                }

                adapter.onclickEliminar = { libro ->
                    val builder = AlertDialog.Builder(requireContext())
                    builder.setMessage("Desea eliminar este Registro")
                        .setPositiveButton("Si") { _, _ ->
                            EliminarLibro(libro.getString("id_libro")) {
                                cargar_libro(filtro) // Actualiza la lista después de eliminar
                            }
                        }
                        .setNegativeButton("No", null)
                        .show()
                }
                recyclerView.adapter = adapter
            },
            { error ->
                Toast.makeText(context, "Error en la consulta", Toast.LENGTH_LONG).show()
            }
        )
        val queue = Volley.newRequestQueue(context)
        queue.add(request)
    }


    private fun EliminarLibro(id_libro: String, onSuccess: () -> Unit) {
        val request = StringRequest(
            Request.Method.DELETE,
            "${config.urlLibros}eliminar/$id_libro",
            { response ->
                Toast.makeText(context, "Libro eliminado correctamente", Toast.LENGTH_LONG).show()
                onSuccess() // Llama al callback para actualizar la lista
            },
            { error ->
                Toast.makeText(context, "Error al eliminar el libro", Toast.LENGTH_LONG).show()
            }
        )
        val queue = Volley.newRequestQueue(context)
        queue.add(request)
    }

    companion object {
        /**
        * Use this factory method to create a new instance of
        * this fragment using the provided parameters.
        *
        * @param param1 Parameter 1.
        * @param param2 Parameter 2.
        * @return A new instance of fragment lista_usuario.
        */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            lista_libro().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
