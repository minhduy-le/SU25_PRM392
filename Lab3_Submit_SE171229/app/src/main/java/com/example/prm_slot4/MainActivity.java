package com.example.prm_slot4;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvMonHoc;
    EditText edtMonHoc;
    Button btnThem, btnCapNhat, btnXoa;
    ArrayList<String> arrayCourse;
    ArrayAdapter<String> adapter;
    int vitri = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        // Ánh xạ các thành phần giao diện
        lvMonHoc = findViewById(R.id.listViewMonHoc);
        edtMonHoc = findViewById(R.id.edtMonHoc);
        btnThem = findViewById(R.id.btnThem);
        btnCapNhat = findViewById(R.id.btnCapNhat);
        btnXoa = findViewById(R.id.btnXoa); // Ánh xạ nút Xóa

        // Khởi tạo danh sách và adapter
        arrayCourse = new ArrayList<>();
        arrayCourse.add("Android");
        arrayCourse.add("PHP");
        arrayCourse.add("IOS");
        arrayCourse.add("Unity");
        arrayCourse.add("ASP.net");

        adapter = new ArrayAdapter<>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                arrayCourse
        );
        lvMonHoc.setAdapter(adapter);

        // Sự kiện nhấn nút "Thêm"
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String monHoc = edtMonHoc.getText().toString();
                if (!monHoc.isEmpty()) {
                    arrayCourse.add(monHoc);
                    adapter.notifyDataSetChanged();
                    edtMonHoc.setText("");
                    Toast.makeText(MainActivity.this, "Đã thêm môn học!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập tên môn học!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Sự kiện nhấn vào một mục trong ListView
        lvMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                edtMonHoc.setText(arrayCourse.get(position));
                vitri = position;
            }
        });

        // Sự kiện nhấn nút "Cập nhật"
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vitri != -1) {
                    String monHoc = edtMonHoc.getText().toString();
                    if (!monHoc.isEmpty()) {
                        arrayCourse.set(vitri, monHoc);
                        adapter.notifyDataSetChanged();
                        edtMonHoc.setText("");
                        vitri = -1;
                        Toast.makeText(MainActivity.this, "Đã cập nhật môn học!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Vui lòng nhập tên môn học!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng chọn một môn học để cập nhật!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Sự kiện nhấn nút "Xóa"
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vitri != -1) {
                    arrayCourse.remove(vitri); // Xóa mục tại vị trí vitri
                    adapter.notifyDataSetChanged();
                    edtMonHoc.setText(""); // Xóa nội dung EditText
                    vitri = -1; // Đặt lại vị trí
                    Toast.makeText(MainActivity.this, "Đã xóa môn học!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng chọn một môn học để xóa!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}