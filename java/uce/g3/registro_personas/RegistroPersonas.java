package uce.g3.registro_personas;

import OpenHelper.BaseDeDatos;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class RegistroPersonas extends AppCompatActivity {

    //capturo las variables del formulario
    EditText nombre, apellido, telefono, email, usuario, passwd, fechaNacimiento;
    Button ingresarPersona;
    CheckBox c1,c2,c3,c4,c5;
    RadioButton masculino, femenino;
    Switch s;
    Spinner dia,mes,anio;

    BaseDeDatos base = new BaseDeDatos(this,"optativa3",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_personas);

        nombre = (EditText)findViewById(R.id.txtNombre);
        apellido = (EditText)findViewById(R.id.txtApellido);
        telefono = (EditText)findViewById(R.id.txtTelefono);
        email = (EditText)findViewById(R.id.txtEmail);
        usuario = (EditText)findViewById(R.id.txtUsuario);
        passwd = (EditText)findViewById(R.id.txtClave);
        //fechaNacimiento = (EditText)findViewById(R.id.txtFechaNacimiento);
        ingresarPersona = (Button)findViewById(R.id.btnInsertar);
        c1 = (CheckBox) findViewById(R.id.boxOptativa3);
        c2 = (CheckBox) findViewById(R.id.boxProgramacionDistribuida);
        c3 = (CheckBox) findViewById(R.id.boxGestionTics);
        c4 = (CheckBox) findViewById(R.id.boxGestionProyectos);
        c5 = (CheckBox) findViewById(R.id.boxMineria);
        masculino = (RadioButton)findViewById(R.id.radioBtnMasculino);
        femenino = (RadioButton)findViewById(R.id.radioBtnFemenino);
        s = (Switch) findViewById(R.id.switch1);
        dia = (Spinner)findViewById(R.id.spinnerDia);
        mes = (Spinner)findViewById(R.id.spinnerMes);
        anio = (Spinner)findViewById(R.id.spinnerAnio);

        ArrayAdapter<CharSequence> dias=ArrayAdapter.createFromResource(this, R.array.dia, android.R.layout.simple_spinner_item);
        dia.setAdapter(dias);
        ArrayAdapter<CharSequence> meses=ArrayAdapter.createFromResource(this, R.array.mes, android.R.layout.simple_spinner_item);
        mes.setAdapter(meses);
        ArrayAdapter<CharSequence> anios=ArrayAdapter.createFromResource(this, R.array.anio, android.R.layout.simple_spinner_item);
        anio.setAdapter(anios);

        ingresarPersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                base.abrirBase();
                validarCheckBox();

                base.insertarRegistro(String.valueOf(nombre.getText()),String.valueOf(apellido.getText()),String.valueOf(telefono.getText()),
                        String.valueOf(email.getText()),mostrarFecha(),String.valueOf(usuario.getText()),
                        String.valueOf(passwd.getText()),validarRadioButon(),validarCheckBox(),validarSwith());
                base.cerrarBase();

                Toast.makeText(getApplicationContext(),"Se ingresaron los datos con exito",Toast.LENGTH_LONG).show();

                Intent i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }
        });
    }
        private String validarCheckBox(){
            String cadena = "";
            if(c1.isChecked()){
                cadena += c1.getText();
            }
            if(c2.isChecked()){
                cadena += c2.getText();
            }
            if(c3.isChecked()){
                cadena += c3.getText();
            }
            if(c1.isChecked()){
                cadena += c4.getText();
            }
            if(c1.isChecked()){
                cadena += c5.getText();
            }
            return cadena;
    }
        private String validarRadioButon(){
        String cadena = "";
        if (masculino.isChecked()){
            cadena = masculino.getText().toString();
        }else if(femenino.isChecked()){
            cadena = femenino.getText().toString();
        }
        return cadena;
        }


    private String validarSwith() {
        String cadena = "";
        if (s.isChecked()){
            cadena="Becado";
        }else{
            cadena="No Becado";
        }

        return cadena;
    }

    private String mostrarFecha(){
        final String[] diaC = {""};
        final String[] mesC = {""};
        final String[] anioC = {""};
        dia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                diaC[0] =adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mesC[0] = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        anio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                anioC[0] = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return diaC[0] +"/"+ mesC[0] +"/"+ anioC[0];
    }
}
