package com.aitec.amazon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;

import com.aitec.amazon.base.producto;


public class MainActivity extends AppCompatActivity {
    //objeto de tipo realm
    private Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();
    }

    //inflar el menu de la activity con el xml diseñado
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //acciones para cada uno de los items
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_producto:
                //mostrar alertDialog
                //construir alertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("CREAR PRODUCTO");
                View viewinflated = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialogo_crear_producto, null );
                builder.setView(viewinflated);
                //referecias a los elementos del xml
                EditText txtcodigo = viewinflated.findViewById(R.id.editTextNumber);
                EditText txtdescripcion = viewinflated.findViewById(R.id.editTextTextPersonName);
                EditText txtprecioCompra = viewinflated.findViewById(R.id.editTextNumberDecimal);
                EditText txtcantidad = viewinflated.findViewById(R.id.editTextNumber2);
                //definir acción para el botón positivo
                builder.setPositiveButton("REGISTRAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //obtener la información para guardar en la base
                        int codigo = Integer.valueOf(txtcodigo.getText().toString());
                        String descripcion = txtdescripcion.getText().toString();
                        double precioCompra = Double.valueOf(txtprecioCompra.getText().toString());
                        int cantidad = Integer.valueOf(txtcantidad.getText().toString());
                        //crear objeto de tipo producto para guardar en la tabla correspondiente
                        producto prod = new producto(codigo, descripcion, precioCompra, cantidad);
                        //obtener la referencia a la base
                        realm = Realm.getDefaultInstance();
                        realm.beginTransaction(); //comenzar la transacción
                        realm.copyToRealm(prod); //enviar el objeto
                        realm.commitTransaction(); //terminar la transacción

                        Toast.makeText(MainActivity.this, "PRODUCTO REGISTRADO", Toast.LENGTH_LONG).show();
                    }
                });
                //crear dialogo a partir del builder configurado
                AlertDialog dialog = builder.create();
                dialog.show(); //mostrar el dialogo

                break;

            case R.id.itemVerProductos:
                Intent intent = new Intent(MainActivity.this, SegundaActivity.class);
                startActivity(intent);
                break;

            case R.id.item_deuda:
                Toast.makeText(MainActivity.this, "VER DEUDA "+item.getItemId(), Toast.LENGTH_LONG).show();
                break;

            case R.id.item_usuario:
                Toast.makeText(MainActivity.this, "AGREGAR USUARIO "+item.getItemId(), Toast.LENGTH_LONG).show();
                break;

            case R.id.item_venta:
                Toast.makeText(MainActivity.this, "REGISTRAR VENTA "+item.getItemId(), Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}