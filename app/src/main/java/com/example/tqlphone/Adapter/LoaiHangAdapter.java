package com.example.tqlphone.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tqlphone.DAO.LoaiHangDAO;
import com.example.tqlphone.Model.DanhSach;
import com.example.tqlphone.Model.LoaiHang;
import com.example.tqlphone.R;

import java.util.ArrayList;

public class LoaiHangAdapter  extends RecyclerView.Adapter<LoaiHangAdapter.viewHolder>{
    private final Context context;
    private final ArrayList<LoaiHang> list;
    LoaiHangDAO lhDAO;

    public LoaiHangAdapter(Context context, ArrayList<LoaiHang> list) {
        this.context = context;
        this.list = list;
        lhDAO = new LoaiHangDAO(context);
    }

    @NonNull
    @Override
    public LoaiHangAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_lh, null);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiHangAdapter.viewHolder holder, int position) {
        holder.txtmaHang.setText("Mã Hãng: "+ list.get(position).getMaHang());
        holder.txttenHang.setText("Tên Hãng: "+ list.get(position).getTenHang());
        LoaiHang lh =list.get(position);
        holder.btndeletelh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
                builder.setTitle("Cảnh báo");//set tiêu đề
                builder.setMessage("Bạn có chắc chắn muốn xóa không?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (lhDAO.delete(lh.getMaHang())) {
                            list.clear();
                            list.addAll(lhDAO.selectAllLoaiHang());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView txtmaHang, txttenHang;
        ImageButton btnupdatelh, btndeletelh;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtmaHang=itemView.findViewById(R.id.txtmaHang);
            txttenHang=itemView.findViewById(R.id.txttenHang);
            btnupdatelh=itemView.findViewById(R.id.btnupdatelh);
            btndeletelh=itemView.findViewById(R.id.btndeletelh);
        }
    }
}
