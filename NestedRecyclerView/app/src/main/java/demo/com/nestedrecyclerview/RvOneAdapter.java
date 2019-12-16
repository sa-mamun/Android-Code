package demo.com.nestedrecyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RvOneAdapter extends RecyclerView.Adapter<RvOneAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<RvOneModel> rvOneAdapterArrayList;
    private ArrayList<RvTwoModel> rvTwoModelArrayList;
    DatabaseSource databaseSource;

    public RvOneAdapter(Context context, ArrayList<RvOneModel> rvOneAdapterArrayList) {

        this.context = context;
        this.rvOneAdapterArrayList = rvOneAdapterArrayList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        databaseSource = new DatabaseSource(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_one_item_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.bindHelper(position);

    }

    @Override
    public int getItemCount() {

        return rvOneAdapterArrayList.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView title;
        ImageButton addBtn;
        RecyclerView rv_two;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            rv_two = itemView.findViewById(R.id.rv_two);
            title = itemView.findViewById(R.id.tv_title);
            addBtn = itemView.findViewById(R.id.ib_add);

        }

        public void bindHelper(final int position)
        {

            rvTwoModelArrayList = new ArrayList<>();

            title.setText(rvOneAdapterArrayList.get(position).getTaskTime());

            rv_two.setHasFixedSize(true);

            rvTwoModelArrayList = databaseSource.getTaskName(rvOneAdapterArrayList.get(position).getId());

            if (rvTwoModelArrayList.size() > 0)
            {
                Log.e("Status", String.valueOf(rvTwoModelArrayList.get(0).isStatus()));

                LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                layoutManager.setOrientation(RecyclerView.VERTICAL);
                rv_two.setLayoutManager(layoutManager);

                RvTwoAdapter rvTwoAdapter = new RvTwoAdapter(context, rvTwoModelArrayList);
                rvTwoAdapter.notifyDataSetChanged();
                rv_two.setAdapter(rvTwoAdapter);
            }

            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    RvTwoModel rvTwoModel = new RvTwoModel("", 0, rvOneAdapterArrayList.get(position).getId());

                    boolean stat = databaseSource.addTaskName(rvTwoModel);

                    if (stat)
                    {
                        rvTwoModelArrayList = databaseSource.getTaskName(rvOneAdapterArrayList.get(position).getId());

                        //rvTwoModelArrayList.add(rvTwoModel);
                        Log.e("ArrayList Size", String.valueOf(rvTwoModelArrayList.size()));

                        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                        layoutManager.setOrientation(RecyclerView.VERTICAL);
                        rv_two.setLayoutManager(layoutManager);

                        RvTwoAdapter rvTwoAdapter = new RvTwoAdapter(context, rvTwoModelArrayList);
                        rvTwoAdapter.notifyDataSetChanged();
                        rv_two.setAdapter(rvTwoAdapter);
                    }
                    else {
                        Toast.makeText(context, "Failed to Insert", Toast.LENGTH_SHORT).show();
                    }



                }
            });

        }
    }

}
