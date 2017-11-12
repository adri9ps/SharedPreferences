package com.example.adri9ps.preferencias_datos;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    private EditText nombre;
    private EditText dni;
    private EditText fecha;
    private RadioButton hombre;
    private RadioButton mujer;
    private Button btnEnviar;
    private Button btnMostrar;
    private Button btnEliminar;
    private SharedPreferences sharedPref;
    private TextView nombreUsuario;
    private TextView dniUsuario;
    private TextView fechaUsuario;
    private TextView sexoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = (EditText) findViewById(R.id.id_nombre);
        dni = (EditText) findViewById(R.id.id_dni);
        fecha = (EditText) findViewById(R.id.id_fecha);
        hombre = (RadioButton) findViewById(R.id.id_hombre);
        mujer = (RadioButton) findViewById(R.id.id_mujer);
        nombreUsuario = (TextView) findViewById(R.id.txt_nombre);
        dniUsuario = (TextView) findViewById(R.id.txt_dni);
        fechaUsuario = (TextView) findViewById(R.id.txt_fecha);
        sexoUsuario = (TextView) findViewById(R.id.txt_sexo);

        btnEnviar = (Button) findViewById(R.id.btn_enviar);
        btnMostrar = (Button) findViewById(R.id.btn_mostrar);
        btnEliminar = (Button) findViewById(R.id.btn_eliminar) ;
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveProfile();

            }
        });

        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getProfile();

            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clearProfile();

            }
        });

    }



    public void saveProfile() {
        //Guardar preferencias

         sharedPref = getSharedPreferences("Preferencias del Usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        //Cargamos valores de la vista
        String nombre_ = nombre.getText().toString();
        String dni_ = dni.getText().toString();
        String fecha_ = fecha.getText().toString();
        boolean hombre_ = hombre.isChecked();
        boolean mujer_ = mujer.isChecked();

        //Guardamos valores
        editor.putString("Nombre",nombre_);
        editor.putString("DNI",dni_);
        editor.putString("Fecha",fecha_);
        editor.putBoolean("Hombre",hombre_);
        editor.putBoolean("Mujer",mujer_);
        editor.commit();

        //Notificamos la usuario de que se han guardado los datos del perfil correctamente.
        Toast.makeText(getApplicationContext(),"Se han guardado las preferencias",Toast.LENGTH_SHORT).show();
    }


    private void getProfile() {
         sharedPref = getSharedPreferences("Preferencias del Usuario", Context.MODE_PRIVATE);

        String txtnombre = sharedPref.getString("Nombre","");
        nombreUsuario.setText("Nombre: "+txtnombre);

        String txtdni = sharedPref.getString("DNI","");
        dniUsuario.setText("DNI: "+txtdni);

        String txtfecha = sharedPref.getString("Fecha","");
        fechaUsuario.setText("Fecha de nacimiento: "+txtfecha);

        if(sharedPref.getBoolean("Hombre", true)){
            sexoUsuario.setText("Sexo: Hombre");
        }else{
            sexoUsuario.setText("Sexo: Mujer");
        }

        //Notificamos la usuario de que se han cargado los datos del perfil correctamente.
        Toast.makeText(getApplicationContext(),"Se han cargado las preferencias",Toast.LENGTH_SHORT).show();

    }

    public void clearProfile(){
         sharedPref = getSharedPreferences("Preferencias del Usuario", Context.MODE_PRIVATE);
         SharedPreferences.Editor editor = sharedPref.edit();

        editor.clear().commit();
        //Notificamos la usuario de que se han eliminado los datos del perfil correctamente.
        Toast.makeText(getApplicationContext(),"Se han eliminado las preferencias",Toast.LENGTH_SHORT).show();


    }







}

