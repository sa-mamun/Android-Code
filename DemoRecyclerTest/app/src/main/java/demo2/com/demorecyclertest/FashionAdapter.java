package demo2.com.demorecyclertest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FashionAdapter extends RecyclerView.Adapter<FashionAdapter.MyViewHolder> {

    Context context;
    ArrayList<FashionModel> arrayList;

    public FashionAdapter(Context context, ArrayList<FashionModel> arrayList)
    {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.food_row, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.fImage.setImageResource(arrayList.get(position).fashionImage);
        holder.fName.setText(arrayList.get(position).fashionName.toString());
        holder.fDesc.setText(arrayList.get(position).fashionDesc.toString());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView fName, fDesc;
        ImageView fImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            fImage = itemView.findViewById(R.id.foodImageV);
            fName = itemView.findViewById(R.id.foodNameTV);
            fDesc = itemView.findViewById(R.id.foodDescTV);

        }
    }

}
