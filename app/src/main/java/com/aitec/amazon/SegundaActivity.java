package com.aitec.amazon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;
import com.aitec.amazon.base.producto;
import com.aitec.amazon.adaptador.adaptadorProductos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SegundaActivity extends AppCompatActivity {
    private ListView listView;
    private Realm realm;
    private RealmResults <producto> productoRealmResults;
    private adaptadorProductos adaptador;
    private List<producto> aux;
    private Button button;
    private EditText buscar;
    private Button btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        //Referencias a los controles en el layout
        listView = findViewById(R.id.ListViewProductos);
        button = findViewById(R.id.button);
        buscar = findViewById(R.id.editTextTextPersonName2);
        btnBuscar = findViewById(R.id.button2);

        //Instancia de Realm
        realm = Realm.getDefaultInstance();
        productoRealmResults = realm.where(producto.class).findAll();//Select* from producto;
        aux = new ArrayList<>();
        //pasos intermedios
        if(productoRealmResults.size()>0){
            for (int i=0 ; i < productoRealmResults.size();i++){
                aux.add(new producto(productoRealmResults.get(i).getCodigo(),
                                     productoRealmResults.get(i).getDescripcion(),
                                     productoRealmResults.get(i).getPrecioCompra(),
                                     productoRealmResults.get(i).getCantidad()) );
            }
            //inicializar el adaptador
            adaptador = new adaptadorProductos(this, R.layout.lista_productos, aux);
            listView.setAdapter(adaptador);//asignar el adaptador al listView
        }else{
            Toast.makeText(this, "NO EXISTEN REGISTRO EN LA BASE DE DATOS", Toast.LENGTH_LONG).show();
        }
        //acción botón regresar
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SegundaActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //acción botón buscar
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtbuscar = buscar.getText().toString().trim();
                if(Character.isLetter(txtbuscar.charAt(0))){
                                            //Select* from producto where descripcion = ?txtbuscar;
                    productoRealmResults = realm.where(producto.class).contains("descripcion", txtbuscar).findAll();//Select* from producto;
                    aux = new ArrayList<>();
                    //pasos intermedios
                    if(productoRealmResults.size()>0){
                        for (int i=0 ; i < productoRealmResults.size();i++){
                            aux.add(new producto(productoRealmResults.get(i).getCodigo(),
                                    productoRealmResults.get(i).getDescripcion(),
                                    productoRealmResults.get(i).getPrecioCompra(),
                                    productoRealmResults.get(i).getCantidad()) );
                        }
                        //inicializar el adaptador
                        adaptador = new adaptadorProductos(SegundaActivity.this, R.layout.lista_productos, aux);
                        listView.setAdapter(adaptador);//asignar el adaptador al listView
                    }else{
                        Toast.makeText(SegundaActivity.this, "NO EXISTEN REGISTRO EN LA BASE DE DATOS", Toast.LENGTH_LONG).show();
                    }
                } else{
                    productoRealmResults = realm.where(producto.class).equalTo("codigo", Integer.valueOf(txtbuscar)).findAll();//Select* from producto;
                    aux = new ArrayList<>();
                    //pasos intermedios
                    if(productoRealmResults.size()>0){
                        for (int i=0 ; i < productoRealmResults.size();i++){
                            aux.add(new producto(productoRealmResults.get(i).getCodigo(),
                                    productoRealmResults.get(i).getDescripcion(),
                                    productoRealmResults.get(i).getPrecioCompra(),
                                    productoRealmResults.get(i).getCantidad()) );
                        }
                        //inicializar el adaptador
                        adaptador = new adaptadorProductos(SegundaActivity.this, R.layout.lista_productos, aux);
                        listView.setAdapter(adaptador);//asignar el adaptador al listView
                    }else{
                        Toast.makeText(SegundaActivity.this, "NO EXISTEN REGISTRO EN LA BASE DE DATOS", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

}