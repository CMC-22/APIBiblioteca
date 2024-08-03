package com.example.crudlibrary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.crudlibrary.adapter.adapterUsuario
import com.example.crudlibrary.config.config
import com.example.crudlibrary.models.libro
import com.example.crudlibrary.models.usuario
import com.google.gson.Gson
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private var id_usuario: String? = null
/**
 * A simple [Fragment] subclass.
 * Use the [detalleUsuarioFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class detalleUsuarioFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id_usuario = it.getString("id_usuario").toString()
        }
    }

    //definir las variables
    private lateinit var lblNombre: TextView
    private lateinit var lblDireccion: TextView
    private lateinit var lblCorreo: TextView
    private lateinit var lblTipoUsuario: Spinner
    private lateinit var btnEditar: Button
    private lateinit var btnEliminar: Button

    private  var id_usuario:String=""

    private fun consultarUsuario(){
        /*
        solo se debe consultar, si el ID
        es diferente a vacio
         */
        id_usuario?.let {
            val request = JsonObjectRequest(
                Request.Method.GET,//metodo de la peticion
                config.urlUsuario + it, //url
                null,//parametros
                { response ->
                    //variable response contiene la respuesta de la API
                    //se convierte de json a un objecto tipo libro
                    //se genera un objecto de la libreria Gson
                    val gson = Gson()
                    //se conviertr
                    val usuario: usuario = gson.fromJson(response.toString(), usuario::class.java)
                    //se ejecuta el atributo text de los campos con el valor del objeto
                    lblNombre.setText(usuario.nombre)
                    lblDireccion.setText(usuario.direccion)
                    lblCorreo.setText(usuario.correo)
                    val opciones = arrayOf("Seleccionar...", "Lector", "Bibliotecario", "Administrador")
                    val tipoUsuarioIndex = opciones.indexOf(usuario.tipo_usuario)
                    if (tipoUsuarioIndex >= 0) {
                        lblTipoUsuario.setSelection(tipoUsuarioIndex)
                    }
                    Toast.makeText(context, "Se consulto correctamente", Toast.LENGTH_LONG).show()
                },
                { error ->
                    Toast.makeText(context, "Error al consultar", Toast.LENGTH_LONG).show()
                }//error en la peticon
            )
            var queue = Volley.newRequestQueue(context)
            queue.add(request)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detalle_usuario, container, false)
        lblNombre = view.findViewById(R.id.lblNombre)
        lblDireccion = view.findViewById(R.id. lblDireccion)
        lblCorreo = view.findViewById(R.id.lblCorreo)
        lblTipoUsuario = view.findViewById(R.id.spinnerTipoUsuario)
        btnEditar = view.findViewById(R.id.btnEditar)
        btnEditar.setOnClickListener{editarUsuario()}
        btnEliminar = view.findViewById(R.id.btnEliminar)
        btnEliminar.setOnClickListener{id_usuario?.let {eliminarUsuario(it)}}

        id_usuario?.let {
            consultarUsuario()
        }

        //Inicioalizar el Spinner
        val opciones = arrayOf("Seleccionar...", "Lector", "Bibliotecario", "Administrador")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, opciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        lblTipoUsuario.adapter = adapter

        return view
    }

    private fun editarUsuario() {
        id_usuario?.let {
            var parametros = JSONObject()
            parametros.put("nombre", lblNombre.text.toString())
            parametros.put("direccion", lblDireccion.text.toString())
            parametros.put("correo", lblCorreo.text.toString())
            parametros.put("tipo_usuario", lblTipoUsuario.selectedItem.toString())

            var request = JsonObjectRequest(
                Request.Method.PUT,//metodo de la peticion
                config.urlUsuario + it, //url
                parametros,//parametros
                { response ->
                    Toast.makeText(context, "Se actualizó correctamente", Toast.LENGTH_LONG).show()
                    parentFragmentManager.popBackStack()
                },
                { error ->
                    Toast.makeText(context, "Error al actualizar", Toast.LENGTH_LONG).show()
                }//error en la peticon
            )
            var queue = Volley.newRequestQueue(context)
            queue.add(request)
        }
    }




    //se cre el metodo eliminar Libro
    private fun eliminarUsuario(id_usuario: String) {
        val request = StringRequest(
            Request.Method.DELETE,
            config.urlUsuario + "eliminar/" + id_usuario,
            { response ->
                Toast.makeText(context, "Usuario eliminado correctamente", Toast.LENGTH_LONG).show()
                // Regresar al fragmento anterior si se desea
                parentFragmentManager.popBackStack()
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
         * @return A new instance of fragment detalle_usuario.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            detalleUsuarioFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}