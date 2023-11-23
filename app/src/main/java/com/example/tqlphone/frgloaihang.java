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

import com.example.tqlphone.Adapter.LoaiHangAdapter;
import com.example.tqlphone.DAO.LoaiHangDAO;
import com.example.tqlphone.Model.LoaiHang;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class frgloaihang extends Fragment {
    RecyclerView rcvloaihang;
    FloatingActionButton fltaddlh;
    LoaiHangDAO lhDao;
    LoaiHangAdapter adapter;
    private ArrayList<LoaiHang> list=new ArrayList<>();


    public frgloaihang() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_frgloaihang, container, false);
        rcvloaihang= view.findViewById(R.id.rcvloaihang);
        fltaddlh = view.findViewById(R.id.fltaddlh);

        lhDao = new LoaiHangDAO(getActivity());
        list = lhDao.selectAllLoaiHang();
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        rcvloaihang.setLayoutManager(layout);
        adapter = new LoaiHangAdapter(getActivity(), list);
        rcvloaihang.setAdapter(adapter);
        fltaddlh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendialogthemlh();

            }
        });
        // Inflate the layout for this fragment
        return view;
    }
    public void opendialogthemlh() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // tạo view, gán layout
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_addlh, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show(); // show hộp thoại
        // ánh xạ các thành phàn widget
        EditText edtmaHang = view.findViewById(R.id.edtmaHang_add);
        EditText edttenHang = view.findViewById(R.id.edttenHang_add);
        Button btnthem = view.findViewById(R.id.btnAddlh);
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maHang = edtmaHang.getText().toString();
                String tenHang = edttenHang.getText().toString();
                LoaiHang lh = new LoaiHang(maHang, tenHang);

                if (lhDao.insert(lh)) {
                    list.clear();
                    list.addAll(lhDao.selectAllLoaiHang());
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