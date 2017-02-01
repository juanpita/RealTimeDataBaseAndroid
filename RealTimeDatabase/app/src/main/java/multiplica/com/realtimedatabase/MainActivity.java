package multiplica.com.realtimedatabase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    TextView mconditionTextview;
    Button mButtonSunny;
    Button mButtonFoggy;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference(); //json tree conexion
    DatabaseReference mconditionRef = mRootRef.child("condition"); //listener

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get UI Elements
        mconditionTextview = (TextView)findViewById(R.id.textViewCondition);
        mButtonSunny = (Button) findViewById(R.id.buttonSunny);
        mButtonFoggy = (Button) findViewById(R.id.buttonFoggy);
    }
    @Override
    protected void onStart(){ //funcion para carga de enventos
        super.onStart();

        //evento de la eqtiqueta Textview
        mconditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                mconditionTextview.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //evento del boton llamado Sunny
        mButtonSunny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //al dar onclick se esta enviando la palabra Sunny directo a la DB
                mconditionRef.setValue("Sunny");
            }
        });

        //evento del boton llamado Foggy
        mButtonFoggy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mconditionRef.setValue("Foggy");
            }
        });
    }
}
