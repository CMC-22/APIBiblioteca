package com.example.crudlibrary.adapterLibro

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.crudlibrary.R
import com.example.crudlibrary.models.libro
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject


class adapterLibro (
    var listaLibro: JSONArray,
    var context: Context
):RecyclerView.Adapter<adapterLibro.MyHolder>()
{
    /*
    se crea la clase MyHolder
     */
    inner class  MyHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        /*
        Dentro de la clase de MyHolder se crea las varibales y se asocian los objetos de la vista item
         */
        val lblTitulo: TextView = itemView.findViewById(R.id.lblTitulo)
        val lblAutor: TextView = itemView.findViewById(R.id.lblAutor)
        val btnEditar: ImageView = itemView.findViewById(R.id.btnEditar)
        val btnEliminar: ImageView = itemView.findViewById(R.id.btnEliminar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapterLibro.MyHolder {
        var itemView=LayoutInflater.from(context).inflate(R.layout.item_libro,parent,false)
        return MyHolder(itemView)
    }

    override fun onBindViewHolder(holder: adapterLibro.MyHolder, position: Int) {
        //Obtener el registro
        val libro=listaLibro.getJSONObject(position)
        //asignar valores
        holder.lblTitulo.text=libro.getString("titulo")
        holder.lblAutor.text=libro.getString("autor")

        //se crea la función del onclick nuevo que puse
         holder.btnEditar.setOnClickListener{
         onclickEditar?.invoke(libro)
         }

        holder.btnEliminar.setOnClickListener{
            onclickEliminar?.invoke(libro)
        }
    }

/*
getItemCount: retorna el numero de elementos de la lista
 */

    override fun getItemCount(): Int {
        return listaLibro.length()
    }


    var onclickEditar: ((JSONObject) -> Unit)? = null
    var onclickEliminar: ((JSONObject) -> Unit)? = null

}