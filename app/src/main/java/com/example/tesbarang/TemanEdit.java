package com.example.tesbarang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tesbarang.database.Teman;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TemanEdit extends AppCompatActivity {
    TextView tv_Key;
    EditText ed_Nama, ed_Telpon;
    Button btnEdit;
    DatabaseReference databaseReference;
    String kode, nama, telpon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teman_edit);

        tv_Key = findViewById(R.id.tv_key);
        ed_Nama = findViewById(R.id.edNama);
        ed_Telpon = findViewById(R.id.edTelpon);
        btnEdit = findViewById(R.id.btEdit);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        Bundle bundle = this.getIntent().getExtras();
        kode = bundle.getString("Kunci1");
        nama = bundle.getString("Kunci2");
        telpon = bundle.getString("Kunci3");

        tv_Key.setText(kode);
        ed_Nama.setText(nama);
        ed_Telpon.setText(telpon);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTeman(new Teman(ed_Nama.getText().toString(), ed_Telpon.getText().toString()));
            }
        });
    }
    public void editTeman(Teman teman)
    {
        databaseReference.child("Teman") //Akses parent index, ibaratnya seperti nama tabel
                .child(kode) //select teman berdasarkan key
                .setValue(teman) //set value teman yang baru
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(TemanEdit.this, "Data Berhasil Diupdate", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });

    }
}