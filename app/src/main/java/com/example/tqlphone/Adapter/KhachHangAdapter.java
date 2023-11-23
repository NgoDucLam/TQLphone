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

import com.example.tqlphone.DAO.KhachHangDAO;
import com.example.tqlphone.DAO.LoaiHangDAO;
import com.example.tqlphone.Model.KhachHang;
import com.example.tqlphone.Model.LoaiHang;
import com.example.tqlphone.R;

import java.util.ArrayList;

public class KhachHangAdapter  extends RecyclerView.Adapter<KhachHangAdapter.viewHolder>{
    private final Context context;
    private final ArrayList<KhachHang> list;
    KhachHangDAO khDAO;

    public KhachHangAdapter(Context context, ArrayList<KhachHang> list) {
        this.context = context;
        this.list = list;
        khDAO = new KhachHangDAO(context);
    }

    @NonNull
    @Override
    public KhachHangAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_kh, null);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhachHangAdapter.viewHolder holder, int position) {
        holder.txtmaKh.setText("Mã Khách Hàng: "+ list.get(position).getMaKh());
        holder.txttenKh.setText("Tên Khách Hàng: "+ list.get(position).getTenKh());
        holder.txtdiaChi.setText("Đại Chỉ: "+ list.get(position).getDiaChi());
        holder.txtsoDienthoai.setText("Sdt: "+ list.get(position).getSoDienthoai());
        KhachHang kh =list.get(position);
        holder.btndeletekh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
                builder.setTitle("Cảnh báo");//set tiêu đề
                builder.setMessage("Bạn có chắc chắn muốn xóa không?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (khDAO.delete(kh.getMaKh())) {
                            list.clear();
                            list.addAll(khDAO.selectAllKhachHang());
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
        TextView txtmaKh, txttenKh, txtdiaChi, txtsoDienthoai;
        ImageButton btnupdatekh, btndeletekh;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtmaKh=itemView.findViewById(R.id.txtmaKh);
            txttenKh=itemView.findViewById(R.id.txttenKh);
            txtdiaChi=itemView.findViewById(R.id.txtdiaChi);
            txtsoDienthoai=itemView.findViewById(R.id.txtsoDienthoai);
            btnupdatekh=itemView.findViewById(R.id.btnupdatekh);
            btndeletekh=itemView.findViewById(R.id.btndeletekh);
        }
    }
}
