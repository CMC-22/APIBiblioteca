package com.example.crudlibrary.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.crudlibrary.R
import org.json.JSONArray
import org.json.JSONObject

class adapterUsuario (
    var listaUsuario: JSONArray,
    var context: Context
):RecyclerView.Adapter<adapterUsuario.MyHolder>()
{
    /*
    se crea la clase MyHolder
     */
    inner class  MyHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        /*
        Dentro de la clase de MyHolder se crea las varibales y se asocian los objetos de la vista item
         */
        val lblNombre: TextView = itemView.findViewById(R.id.lblNombre)
        val lblTipoUsuario: TextView = itemView.findViewById(R.id.lblTipoUsuario)
        val btnEditar: ImageView = itemView.findViewById(R.id.btnEditar)
        val btnEliminar: ImageView = itemView.findViewById(R.id.btnEliminar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapterUsuario.MyHolder {
        var itemView=LayoutInflater.from(context).inflate(R.layout.item_usuario,parent,false)
        return MyHolder(itemView)
    }

    override fun onBindViewHolder(holder: adapterUsuario.MyHolder, position: Int) {
        //Obtener el registro
        val usuario=listaUsuario.getJSONObject(position)
        //asignar valores
        holder.lblNombre.text=usuario.getString("nombre")
        holder.lblTipoUsuario.text=usuario.getString("tipo_usuario")

        //se crea la función del onclick nuevo que puse
        holder.btnEditar.setOnClickListener{
            onclickEditar?.invoke(usuario)
        }

        holder.btnEliminar.setOnClickListener{
            onclickEliminar?.invoke(usuario)
        }
    }

    /*
    getItemCount: retorna el numero de elementos de la lista
     */

    override fun getItemCount(): Int {
        return listaUsuario.length()
    }


    var onclickEditar: ((JSONObject) -> Unit)? = null
    var onclickEliminar: ((JSONObject) -> Unit)? = null

}
