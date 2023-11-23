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

import com.example.tqlphone.DAO.DanhSachDAO;
import com.example.tqlphone.Model.DanhSach;
import com.example.tqlphone.R;

import java.util.ArrayList;

public class DanhSachAdapter extends RecyclerView.Adapter<DanhSachAdapter.viewholer>{
    private final Context context;
    private final ArrayList<DanhSach> list;
    DanhSachDAO dsDAO;

    public DanhSachAdapter(Context context, ArrayList<DanhSach> list) {
        this.context = context;
        this.list = list;
        dsDAO = new DanhSachDAO(context);
    }

    @NonNull
    @Override
    public DanhSachAdapter.viewholer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_ds, null);
        return new viewholer(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhSachAdapter.viewholer holder, int position) {
        holder.txttenDt.setText("Tên ĐT: "+ list.get(position).getTenDt());
        holder.txthangDt.setText("Hãng: "+ list.get(position).getHangDt());
        holder.txtgiaTien.setText("Giá Tiền: "+ list.get(position).getGiaTien());
        DanhSach ds =list.get(position);
        holder.btndeleteds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Cảnh báo");//set tiêu đề
                builder.setMessage("Bạn có chắc chắn muốn xóa không?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (dsDAO.delete(ds.getTenDt())) {
                            list.clear();
                            list.addAll(dsDAO.selectAllDanhSach());
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

    public class viewholer extends RecyclerView.ViewHolder {
        TextView txttenDt, txthangDt, txtgiaTien;
        ImageButton btnupdateds, btndeleteds;
        public viewholer(@NonNull View itemView) {
            super(itemView);
            txttenDt=itemView.findViewById(R.id.txttenDt);
            txthangDt=itemView.findViewById(R.id.txthangDt);
            txtgiaTien=itemView.findViewById(R.id.txtgiaTien);
            btnupdateds=itemView.findViewById(R.id.btnupdateds);
            btndeleteds=itemView.findViewById(R.id.btndeleteds);
        }
    }
}
