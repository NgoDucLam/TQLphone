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

import com.example.tqlphone.Adapter.KhachHangAdapter;
import com.example.tqlphone.Adapter.LoaiHangAdapter;
import com.example.tqlphone.DAO.KhachHangDAO;
import com.example.tqlphone.DAO.LoaiHangDAO;
import com.example.tqlphone.Model.KhachHang;
import com.example.tqlphone.Model.LoaiHang;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class frgkhachhang extends Fragment {
    RecyclerView rcvkhachhang;
    FloatingActionButton fltaddkh;
    KhachHangDAO khDao;
    KhachHangAdapter adapter;
    private ArrayList<KhachHang> list=new ArrayList<>();


    public frgkhachhang() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_frgkhachhang, container, false);
        rcvkhachhang = view.findViewById(R.id.rcvkhachhang);
        fltaddkh = view.findViewById(R.id.btnAddkh);

        khDao = new KhachHangDAO(getActivity());
        list= khDao.selectAllKhachHang();
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        rcvkhachhang.setLayoutManager(layout);
        adapter = new KhachHangAdapter(getActivity(), list);
        rcvkhachhang.setAdapter(adapter);
//
//        khDao = new KhachHangDAO(getActivity());
//        list = khDao.selectAllKhachHang();
//        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
//        rcvkhachhang.setLayoutManager(layout);
//        adapter = new KhachHangAdapter(getActivity(), list);
//        rcvkhachhang.setAdapter(adapter);
        fltaddkh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendialogthemkh();

            }
        });
        // Inflate the layout for this fragment
        return view;
    }
    public void opendialogthemkh() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // tạo view, gán layout
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_addkh, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show(); // show hộp thoại
        // ánh xạ các thành phàn widget
        EditText edtmaKh = view.findViewById(R.id.edtmaKh_add);
        EditText edttenKh = view.findViewById(R.id.edttenKh_add);
        EditText edtdiaChi = view.findViewById(R.id.edtdiaChi_add);
        EditText edtsoDienthoai = view.findViewById(R.id.edtsoDienthoai_add);
        Button btnthem = view.findViewById(R.id.btnAddkh);
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maKh = edtmaKh.getText().toString();
                String tenKh = edttenKh.getText().toString();
                String diaChi = edtdiaChi.getText().toString();
                String soDienthoai= edtsoDienthoai.getText().toString();
                KhachHang kh= new KhachHang(maKh, tenKh, diaChi, soDienthoai);

                if (khDao.insert(kh)) {
                    list.clear();
                    list.addAll(khDao.selectAllKhachHang());
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