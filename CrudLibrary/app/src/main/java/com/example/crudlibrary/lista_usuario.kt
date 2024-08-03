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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.crudlibrary.adapter.adapterUsuario
import com.example.crudlibrary.adapterLibro.adapterLibro
import com.example.crudlibrary.config.config
import com.example.crudlibrary.models.usuario
import org.json.JSONArray

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [lista_usuario.newInstance] factory method to
 * create an instance of this fragment.
 */
class lista_usuario : Fragment() {
    // TODO: Rename and change types of parameters
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
    private lateinit var adapter: adapterUsuario
    private lateinit var editTextBuscar: EditText
    private  lateinit var buttonLupa: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_lista_usuario, container, false)
        recyclerView = rootView.findViewById(R.id.listaUsuario)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        editTextBuscar = rootView.findViewById(R.id.editTextBuscar)
        buttonLupa = rootView.findViewById(R.id.buttonLupa)

        adapter = adapterUsuario(JSONArray(), requireContext())
        recyclerView.adapter = adapter

        val buttonInicio = rootView.findViewById<Button>(R.id.buttoninicio)
        buttonInicio.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }

        buttonLupa.setOnClickListener{
            val filtro = editTextBuscar.text.toString().trim()
            cargar_usuario(filtro)
        }

        volveregistro()
        cargar_usuario("")

        return rootView

    }

    private fun volveregistro() {
        val volverButton = rootView.findViewById<ImageView>(R.id.imageView2)
        volverButton.setOnClickListener {
            requireFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, guardarUsuarioFragment())
                .addToBackStack(null)
                .commit()
        }
    }


    private fun cargar_usuario(filtro: String) {
        val url = if (filtro.isEmpty()) {
            config.urlUsuario
        } else {
            "${config.urlUsuario}busquedafiltro/$filtro"
        }
        val request = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                adapter = adapterUsuario(response, requireContext())

                adapter.onclickEditar = { usuario ->
                    val bundle = Bundle().apply {
                        putString("id_usuario", usuario.getString("id_usuario"))
                    }
                    val fragment = detalleUsuarioFragment().apply {
                        arguments = bundle
                    }
                    requireFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit()
                }

                adapter.onclickEliminar = { usuario ->
                    val builder = AlertDialog.Builder(requireContext())
                    builder.setMessage("Desea eliminar este Registro")
                        .setPositiveButton("Si") { _, _ ->
                            EliminarUsuario(usuario.getString("id_usuario")) {
                                cargar_usuario(filtro) // Actualiza la lista después de eliminar
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


    private fun EliminarUsuario(id_usuario: String, onSuccess: () -> Unit) {
        val request = StringRequest(
            Request.Method.DELETE,
            "${config.urlUsuario}eliminar/$id_usuario",
            { response ->
                Toast.makeText(context, "Usuario eliminado correctamente", Toast.LENGTH_LONG).show()
                onSuccess() // Llama al callback para actualizar la lista
            },
            { error ->
                Toast.makeText(context, "Error al eliminar el usuario", Toast.LENGTH_LONG).show()
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
            lista_usuario().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}