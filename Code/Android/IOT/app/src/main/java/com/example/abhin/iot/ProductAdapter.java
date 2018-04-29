package com.example.abhin.iot;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {


    private Context mCtx;
    private List<FTable> productList;

    public ProductAdapter(Context mCtx, List<FTable> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.ftable, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        FTable product = productList.get(position);

        holder.textViewCourse.setText(product.getCourse_id());
        holder.textViewCName.setText(product.getCourse_name());
        holder.textViewAttendance.setText(String.valueOf(product.getAttendance()));
        holder.textViewStudent.setText(String.valueOf(product.getStudent()));
        holder.textViewDate.setText(String.valueOf(product.getDate()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewCourse, textViewCName, textViewAttendance, textViewStudent, textViewDate;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewCourse = itemView.findViewById(R.id.textViewCourse);
            textViewCName = itemView.findViewById(R.id.textViewCName);
            textViewAttendance = itemView.findViewById(R.id.textViewAttendance);
            textViewStudent = itemView.findViewById(R.id.textViewStudent);
            textViewDate = itemView.findViewById(R.id.textViewDate);
        }
    }
}
