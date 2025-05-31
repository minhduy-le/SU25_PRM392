package com.example.prm_slot4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity1 extends AppCompatActivity {
    ListView lvtraiCay;
    EditText edtTenTraiCay, edtMoTaTraiCay;
    Spinner spinnerHinh;
    Button btnThem, btnCapNhat, btnXoa;
    ArrayList<TraiCay> arrayTraiCay;
    TraiCayAdapter adapter;
    int vitri = -1;
    ArrayAdapter<String> spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các thành phần giao diện
        lvtraiCay = findViewById(R.id.listViewTraiCay);
        edtTenTraiCay = findViewById(R.id.edtTenTraiCay);
        edtMoTaTraiCay = findViewById(R.id.edtMoTaTraiCay);
        spinnerHinh = findViewById(R.id.spinnerHinh);
        btnThem = findViewById(R.id.btnThem);
        btnCapNhat = findViewById(R.id.btnCapNhat);
        btnXoa = findViewById(R.id.btnXoa);

        // Khởi tạo danh sách trái cây
        arrayTraiCay = new ArrayList<>();
        arrayTraiCay.add(new TraiCay("Chuối tiêu", "Chuối tiêu Long An", R.drawable.chuoi));
        arrayTraiCay.add(new TraiCay("Thanh Long", "Thanh long ruột đỏ", R.drawable.thanhlong));
        arrayTraiCay.add(new TraiCay("Dâu tây", "Dâu tây Đà Lạt", R.drawable.dautay));
        arrayTraiCay.add(new TraiCay("Dưa hấu", "Dưa hấu Tiền Giang", R.drawable.duahau));
        arrayTraiCay.add(new TraiCay("Cam vàng", "Cam vàng nhập khẩu", R.drawable.camvang));

        // Khởi tạo adapter cho ListView
        adapter = new TraiCayAdapter(this, R.layout.dong_trai_cay, arrayTraiCay);
        lvtraiCay.setAdapter(adapter);

        // Khởi tạo adapter cho Spinner
        spinnerAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.hinh_anh_trai_cay)
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHinh.setAdapter(spinnerAdapter);

        // Sự kiện nhấn nút "Thêm"
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edtTenTraiCay.getText().toString();
                String moTa = edtMoTaTraiCay.getText().toString();
                if (!ten.isEmpty() && !moTa.isEmpty()) {
                    int hinh = getHinhFromSpinner(spinnerHinh.getSelectedItemPosition());
                    arrayTraiCay.add(new TraiCay(ten, moTa, hinh));
                    adapter.notifyDataSetChanged();
                    edtTenTraiCay.setText("");
                    edtMoTaTraiCay.setText("");
                    Toast.makeText(MainActivity1.this, "Đã thêm trái cây!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity1.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Sự kiện nhấn vào một mục trong ListView
        lvtraiCay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TraiCay traiCay = arrayTraiCay.get(position);
                edtTenTraiCay.setText(traiCay.getTen());
                edtMoTaTraiCay.setText(traiCay.getMota());
                vitri = position;
            }
        });

        // Sự kiện nhấn nút "Sửa"
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vitri != -1) {
                    String ten = edtTenTraiCay.getText().toString();
                    String moTa = edtMoTaTraiCay.getText().toString();
                    if (!ten.isEmpty() && !moTa.isEmpty()) {
                        int hinh = getHinhFromSpinner(spinnerHinh.getSelectedItemPosition());
                        arrayTraiCay.set(vitri, new TraiCay(ten, moTa, hinh));
                        adapter.notifyDataSetChanged();
                        edtTenTraiCay.setText("");
                        edtMoTaTraiCay.setText("");
                        vitri = -1;
                        Toast.makeText(MainActivity1.this, "Đã cập nhật trái cây!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity1.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity1.this, "Vui lòng chọn một trái cây để sửa!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Sự kiện nhấn nút "Xóa"
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vitri != -1) {
                    // Hiển thị hộp thoại xác nhận
                    new AlertDialog.Builder(MainActivity1.this)
                            .setTitle("Xác nhận xóa")
                            .setMessage("Bạn có chắc chắn muốn xóa trái cây không?")
                            .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Thực hiện xóa
                                    arrayTraiCay.remove(vitri);
                                    adapter.notifyDataSetChanged();
                                    edtTenTraiCay.setText("");
                                    edtMoTaTraiCay.setText("");
                                    vitri = -1;
                                    Toast.makeText(MainActivity1.this, "Đã xóa trái cây!", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                } else {
                    Toast.makeText(MainActivity1.this, "Vui lòng chọn một trái cây để xóa!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Phương thức ánh xạ vị trí Spinner với id hình ảnh trong drawable
    private int getHinhFromSpinner(int position) {
        switch (position) {
            case 0: return R.drawable.chuoi;
            case 1: return R.drawable.thanhlong;
            case 2: return R.drawable.dautay;
            case 3: return R.drawable.duahau;
            case 4: return R.drawable.camvang;
            default: return R.drawable.chuoi;
        }
    }

}