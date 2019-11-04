package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

import androidx.annotation.Nullable;

public class BaseDeDatos extends SQLiteOpenHelper {

    public BaseDeDatos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
/*crear estructura de las tablas*/
    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "create table usuario(_ID integer primary key autoincrement, Nombre text, " +
                "Apellido text, Telefono text, Email text,Fecha_Nacimiento date, Usuario text, Clave text, " +
                "Genero text, Asignatura text, Becado text);";

        db.execSQL(query);

    }
/*modificar la estructura de la base de datos*/
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
// metodo para abrir la base de datos
    public void abrirBase(){
        this.getWritableDatabase();
    }

// metodo para cerrar la base de datos
    public void cerrarBase(){
        this.close();
    }
// metodo para ingresar datos a la tabla
    public void insertarRegistro(String nombre, String apellido, String telefono, String email, String fechaNacimiento, String usuario, String clave, String genero, String asignatura, String becado){

        ContentValues datos = new ContentValues();
        datos.put("Nombre",nombre);
        datos.put("Apellido",apellido);
        datos.put("Telefono",telefono);
        datos.put("Email",email);
        datos.put("Fecha_Nacimiento", String.valueOf(fechaNacimiento));
        datos.put("Usuario",usuario);
        datos.put("Clave",clave);
        datos.put("Genero",genero);
        datos.put("Asignatura",asignatura);
        datos.put("Becado",becado);

        this.getWritableDatabase().insert("usuario",null,datos);
    }

// metodo para validar el usuario
    public Cursor login(String usuario, String clave) throws SQLException {
        Cursor cursorC = null;
        cursorC = this.getReadableDatabase().query("usuario", new String[]{"Usuario","Clave"},
                "Usuario='"+usuario+"' and Clave='"+clave+"'",null,
                null,null,null);

        return cursorC;
    }

}
