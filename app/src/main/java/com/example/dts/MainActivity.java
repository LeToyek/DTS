package com.example.dts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nameTXT,contactTXT,dobTXT;
    private Button insertData,deleteData,updateData,viewData;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameTXT = findViewById(R.id.edt_name);
        contactTXT = findViewById(R.id.edt_contact);
        dobTXT = findViewById(R.id.edt_dob);

        insertData = findViewById(R.id.btn_insert);
        deleteData = findViewById(R.id.btn_delete);
        updateData = findViewById(R.id.btn_update_exist);
        viewData = findViewById(R.id.btn_view_data);
        db = new DBHelper(this);

    }


    public void insert(View view) {
        String name = nameTXT.getText().toString();
        String contact = contactTXT.getText().toString();
        String dob = dobTXT.getText().toString();

        Boolean checkInsertData = db.insertUserData(name,contact,dob);
        if(checkInsertData == true){
            Toast.makeText(MainActivity.this, "Data baru berhasil ditambahkan", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this, "Data baru gagal ditambahkan", Toast.LENGTH_SHORT).show();
        }

    }
    public void update(View view){
        String name = nameTXT.getText().toString();
        String contact = contactTXT.getText().toString();
        String dob = dobTXT.getText().toString();
        Boolean checkUpdateData = db.updateUserData(name,contact,dob);
        if (checkUpdateData == true){
            Toast.makeText(MainActivity.this, "Data berhasil dirubah", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this, "Data gagal dirubah", Toast.LENGTH_SHORT).show();
        }
    }
    public void delete(View view){
        String name = nameTXT.getText().toString();
        Boolean checkDeleteData = db.deleteUserData(name);
        if (checkDeleteData == true){
            Toast.makeText(MainActivity.this, "Data terhapus", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
        }
    }
    public void view(View view){
        Cursor res = db.getData();
        if (res.getCount() == 0){
            Toast.makeText(MainActivity.this, "Tidak ada Data", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            buffer.append("Nama : " +res.getString(0)+"\n");
            buffer.append("No HP : " + res.getString(1)+"\n");
            buffer.append("Tanggal Lahir : " + res.getString(2)+"\n\n");

        }
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setTitle("Data pengguna");
        builder.setMessage(buffer.toString());
        builder.show();
    }
}