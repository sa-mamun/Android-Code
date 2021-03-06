package demo.com.easylist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TodayFragmentAdapter extends RecyclerView.Adapter<TodayFragmentAdapter.MyViewHolder>{

    private Context context;
    private ArrayList<String> arrayList;
    ArrayList<RvTwoModel> list;
    DatabaseSource databaseSource;

    public TodayFragmentAdapter(Context context, ArrayList<String> arrayList)
    {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        databaseSource = new DatabaseSource(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.type_item_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.getData(position);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView typeNameTv, noOfTypeTv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            typeNameTv = itemView.findViewById(R.id.typeNameTV);
            noOfTypeTv = itemView.findViewById(R.id.noOfTypeTV);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, AddTaskActivity.class);
                    intent.putExtra("type", arrayList.get(getAdapterPosition()));
                    intent.putExtra("time", "Today");
                    context.startActivity(intent);

                }
            });

        }

        public void getData(int position) {

            typeNameTv.setText(arrayList.get(position));

            String noOfTask = databaseSource.getTodayTaskNameByType(arrayList.get(position), "Today");

            noOfTypeTv.setText(noOfTask + " Tasks");

        }
    }
}
