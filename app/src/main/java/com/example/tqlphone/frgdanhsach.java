package com.example.tqlphone;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tqlphone.Adapter.DanhSachAdapter;
import com.example.tqlphone.DAO.DanhSachDAO;
import com.example.tqlphone.Model.DanhSach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class frgdanhsach extends Fragment {
    RecyclerView rcvdanhsach;
    FloatingActionButton fltaddds;
    DanhSachDAO dsDao;
    DanhSachAdapter adapter;
    private ArrayList<DanhSach> list=new ArrayList<>();


    public frgdanhsach() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_frgdanhsach, container, false);
        rcvdanhsach= view.findViewById(R.id.rcvdanhsach);
        fltaddds = view.findViewById(R.id.fltaddds);

        dsDao = new DanhSachDAO(getActivity());
        list = dsDao.selectAllDanhSach();
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        rcvdanhsach.setLayoutManager(layout);
        adapter = new DanhSachAdapter(getActivity(), list);
        rcvdanhsach.setAdapter(adapter);
        fltaddds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendialogthem();

            }
        });
        // Inflate the layout for this fragment
        return view;
    }
    public void opendialogthem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // tạo view, gán layout
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_addds, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show(); // show hộp thoại
        // ánh xạ các thành phàn widget
        EditText edttenDt = view.findViewById(R.id.edttenDt_add);
        EditText edthangDt = view.findViewById(R.id.edthangDt_add);
        EditText edtgiaTien = view.findViewById(R.id.edtgiaTien_add);
        Button btnthem = view.findViewById(R.id.btnAddds);
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tendt = edttenDt.getText().toString();
                String hangdt = edthangDt.getText().toString();
                String giatien = edtgiaTien.getText().toString();
                DanhSach ds = new DanhSach(tendt, hangdt, giatien);

                if (dsDao.insert(ds)) {
                    list.clear();
                    list.addAll(dsDao.selectAllDanhSach());
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                    Toast.makeText(getActivity(), "Add thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Add thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}