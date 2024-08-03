package com.example.crudlibrary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.crudlibrary.config.config
import com.example.crudlibrary.models.libro
import com.google.gson.Gson
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private var id_libro: String? =null
/**
 * A simple [Fragment] subclass.
 * Use the [detalleLibroFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class detalleLibroFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id_libro = it.getString("id_libro").toString()
        }
    }

    //definir las variables
    private lateinit var lblTitulo:TextView
    private lateinit var lblAutor:TextView
    private lateinit var lblIsbn:TextView
    private lateinit var lblGenero:TextView
    private lateinit var lblDisponible:TextView
    private lateinit var lblOcupado:TextView
    private lateinit var btnEditar: Button
    private lateinit var btnEliminar:Button


    //se asigna un id existente temporal
    private  var id_libro:String =""

    /*
    Request es la peticion que se le hace a la API
    StringRequest=responde un String
    JsonRequest=responde un Json
    JsonArrayRequest=responde un arreglo de Json
    */
    /*
    metodo encargado de traer la informacion del libro
     */
    private fun consultarLibro(){
        /*
        solo se debe consultar, si el ID
        es diferente a vacio
         */
        id_libro?.let {
            val request = JsonObjectRequest(
                Request.Method.GET,//metodo de la peticion
                config.urlLibros + it, //url
                null,//parametros
                { response ->
                    //variable response contiene la respuesta de la API
                    //se convierte de json a un objecto tipo libro
                    //se genera un objecto de la libreria Gson
                    val gson = Gson()
                    //se conviertr
                    val libro: libro = gson.fromJson(response.toString(), libro::class.java)
                    //se ejecuta el atributo text de los campos con el valor del objeto
                    lblAutor.setText(libro.autor)
                    lblTitulo.setText(libro.titulo)
                    lblIsbn.setText(libro.isbn)
                    lblDisponible.setText(libro.numero_ejemplares_disponibles.toString())
                    lblOcupado.setText(libro.numero_ejemplares_ocupados.toString())
                    lblGenero.setText(libro.genero)
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
        val view = inflater.inflate(R.layout.fragment_detalle_libro, container, false)
        lblAutor = view.findViewById(R.id.lblAutor)
        lblDisponible = view.findViewById(R.id.lblDisponible)
        lblOcupado = view.findViewById(R.id.lblOcupados)
        lblIsbn = view.findViewById(R.id.lblIsbn)
        lblGenero = view.findViewById(R.id.lblGenero)
        lblTitulo = view.findViewById(R.id.lblTitulo)
        btnEditar = view.findViewById(R.id.btnEditar)
        btnEditar.setOnClickListener{editarLibro()}
        btnEliminar = view.findViewById(R.id.btnEliminar)
        btnEliminar.setOnClickListener{id_libro?.let {eliminarLibro(it)}}

        id_libro?.let {
            consultarLibro()
        }
        return view
    }
    //se crea la metodo editar libro
    private fun editarLibro() {
        id_libro?.let {
            var parametros = JSONObject()
            parametros.put("titulo", lblTitulo.text.toString())
            parametros.put("autor", lblAutor.text.toString())
            parametros.put("genero", lblGenero.text.toString())
            parametros.put("isbn", lblIsbn.text.toString())
            parametros.put("numero_ejemplares_disponibles", lblDisponible.text.toString())
            parametros.put("numero_ejemplares_ocupados", lblOcupado.text.toString())

            var request = JsonObjectRequest(
                Request.Method.PUT,//metodo de la peticion
                config.urlLibros + it, //url
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
    private fun eliminarLibro(id_libro: String) {
        val request = StringRequest(
            Request.Method.DELETE,
            config.urlLibros + "eliminar/" + id_libro,
            { response ->
                Toast.makeText(context, "Libro eliminado correctamente", Toast.LENGTH_LONG).show()
                // Regresar al fragmento anterior si se desea
                parentFragmentManager.popBackStack()
            },
           { error ->
            Toast.makeText(context, "Error al eliminar el libro", Toast.LENGTH_LONG).show()
            }
        )
        val queue = Volley.newRequestQueue(context)
        queue.add(request)
    }


    companion object {
        /**vb
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment detalleLibroFragment.
         */
        // TODO: Rename and change types and number of parameters


        @JvmStatic
        fun newInstance(id_libro: String) =
            detalleLibroFragment().apply {
                arguments = Bundle().apply {
                    putString("id_libro", id_libro)
                }
            }
    }
}