package com.aitec.amazon.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aitec.amazon.R;
import com.aitec.amazon.base.producto;
import java.util.List;


public class adaptadorProductos extends BaseAdapter {
    //elementos principales de la clase
    private Context context; //--> contexto para mostrar la informaciòn
    private int layout; //--> layout que vamos a inflar
    private List<producto> productoList; //--> lista de productos para mostrar

    //declarar constructor
    public adaptadorProductos(Context context, int layout, List<producto> productoList) {
        this.context = context;
        this.layout = layout;
        this.productoList = productoList;
    }

    @Override
    public int getCount() { //obtener la cantidad de elementos de la lista
        return productoList.size();
    }

    @Override
    public Object getItem(int position) { //obtener un elemento de la posición enviada
        return productoList.get(position);
    }

    @Override
    public long getItemId(int position) { //obtener el id de un elemento
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //declarar la vista e inflarla con el layout creado -->lista_productos
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.lista_productos, null);
        //acceder a los elementos de layout
        TextView txtDescripcion = view.findViewById(R.id.textViewDescripcion);
        TextView txtCodigo = view.findViewById(R.id.textViewCodigo);
        TextView txtPrecio = view.findViewById(R.id.textViewPrecio);
        TextView txtPVP = view.findViewById(R.id.textViewPVP);
        TextView txtCantidad = view.findViewById(R.id.textViewCantidad);
        //mostrar la información en cada elemento
        txtDescripcion.setText(productoList.get(position).getDescripcion());
        txtCodigo.setText(String.valueOf(productoList.get(position).getCodigo()));
        txtPrecio.setText(String.valueOf(productoList.get(position).getPrecioCompra()));
        txtPVP.setText(String.format("%.2f", productoList.get(position).getPvp()));
        txtCantidad.setText(String.valueOf(productoList.get(position).getCantidad()));

        return view;
    }
}
