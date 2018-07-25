package co.com.softhouse.permisos;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Verificamos si tenemos el permiso para usar la camara
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == getPackageManager().PERMISSION_GRANTED){
            Toast.makeText(getApplicationContext(), "Permiso de usar la camara aceptado", Toast.LENGTH_LONG).show();
        }else{
            //Para ejecutar el metodo onRequestPermissionResult...
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);

            //Mostramos al usuario que no tenemos el permiso de usar la camara
            Toast.makeText(getApplicationContext(), "Permiso de usar la camara no aceptado", Toast.LENGTH_LONG).show();
        }
    }

    //Si no se tiene permiso, hacemos saber al usuario que no tenemos permiso y lo solicitamos
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //Aqui recibimos el requestCode del else del ActivityCompat...
        switch (requestCode){
            //Si el resultaod es 1, tenemos el permiso
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getApplicationContext(), "Permiso de usar la camara aceptado", Toast.LENGTH_LONG).show();
                }else{
                    //Si es importante y obligatorio usar el permiso que se ha solicitado, lo volvemos a solicitar si no omitimos esta linea.
                    //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);

                    Toast.makeText(getApplicationContext(), "Permiso de usar la camara no aceptado", Toast.LENGTH_LONG).show();
                }
        }
    }
}
