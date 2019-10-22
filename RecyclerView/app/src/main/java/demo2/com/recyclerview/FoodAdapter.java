package demo2.com.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<FoodModel> arrayList;

    FoodAdapter(Context context, ArrayList<FoodModel> arrayList)
    {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.food_item_row, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.foodImage.setImageResource(arrayList.get(position).foodImage);
        holder.foodName.setText(arrayList.get(position).foodName.toString());
        holder.foodDesc.setText(arrayList.get(position).foodDesc.toString());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView foodName, foodDesc;
        ImageView foodImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.foodImageVW);
            foodName = itemView.findViewById(R.id.foodNameTV);
            foodDesc = itemView.findViewById(R.id.foodDescTV);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,arrayList.get(getAdapterPosition()).foodDesc.toString(),Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

}
