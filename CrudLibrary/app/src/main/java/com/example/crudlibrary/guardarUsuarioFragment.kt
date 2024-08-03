package com.example.crudlibrary

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.crudlibrary.config.config
import com.example.crudlibrary.models.libro
import com.example.crudlibrary.models.usuario
import com.google.gson.Gson
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [guardarUsuarioFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class guardarUsuarioFragment : Fragment() {
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

    //definir lar varibles
    private lateinit var txtNombre: EditText
    private lateinit var txtDireccion: EditText
    private lateinit var txtCorreo: EditText
    private lateinit var txtTipoUsuario: Spinner
    private lateinit var btnGuardar: Button
    private lateinit var btnListaUsuario: Button
    //se le asigna un id existente temporal
    private var id_usuario: String = ""


    private fun guardarLibro() {
        try {
            val parametros = JSONObject().apply {
                put("nombre", txtNombre.text.toString())
                put("direccion", txtDireccion.text.toString())
                put("correo", txtCorreo.text.toString())
                put("tipo_usuario", txtTipoUsuario.selectedItem.toString())
            }

            val request = JsonObjectRequest(
                Request.Method.POST,
                config.urlUsuario,
                parametros,
                { response ->
                    Toast.makeText(context, "Usuario guardado correctamente", Toast.LENGTH_LONG).show()
                    // Limpiar los campos
                    txtNombre.text.clear()
                    txtDireccion.text.clear()
                    txtCorreo.text.clear()
                },
                { error ->
                    Toast.makeText(context, "Error al guardar el usuario", Toast.LENGTH_LONG).show()
                }
            )
            val queue = Volley.newRequestQueue(context)
            queue.add(request)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Error al guardar el usuario", Toast.LENGTH_LONG).show()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_guardar_usuario, container, false)

        txtNombre = view.findViewById(R.id.txtNombre)
        txtDireccion = view.findViewById(R.id.txtDireccion)
        txtCorreo = view.findViewById(R.id.txtCorreo)
        txtTipoUsuario = view.findViewById(R.id.txtTipoUsuario)
        btnGuardar = view.findViewById(R.id.btnGuardar)
        btnListaUsuario = view.findViewById(R.id.btnListaUsuario)

        btnGuardar.setOnClickListener { guardarLibro() }
        btnListaUsuario.setOnClickListener {
            val fragment = lista_usuario()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, fragment)
                ?.addToBackStack(null)
                ?.commit()
        }

        //Inicioalizar el Spinner
        val spinner: Spinner = view.findViewById(R.id.txtTipoUsuario)
        val opciones = arrayOf("Seleccionar...", "Lector", "Bibliotecario", "Administrador")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, opciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_guardar_usuario.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            guardarUsuarioFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}