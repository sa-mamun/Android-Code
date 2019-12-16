package demo.com.easylist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
    private String type, size;
    private TextView textView;

    public RvOneAdapter(Context context, ArrayList<RvOneModel> rvOneAdapterArrayList, String type, TextView textView) {

        this.context = context;
        this.rvOneAdapterArrayList = rvOneAdapterArrayList;
        this.type = type;
        this.textView = textView;

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
        ImageButton addBtn, deleteBtn;
        RecyclerView rv_two;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            rv_two = itemView.findViewById(R.id.rv_two);
            title = itemView.findViewById(R.id.tv_title);
            addBtn = itemView.findViewById(R.id.ib_add);
            deleteBtn = itemView.findViewById(R.id.ib_delete);

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

                RvTwoAdapter rvTwoAdapter = new RvTwoAdapter(context, rvTwoModelArrayList, type, textView);
                rvTwoAdapter.notifyDataSetChanged();
                rv_two.setAdapter(rvTwoAdapter);
            }

            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    RvTwoModel rvTwoModel = new RvTwoModel("", 0, rvOneAdapterArrayList.get(position).getTaskType(),
                            rvOneAdapterArrayList.get(position).getId());

                    boolean stat = databaseSource.addTaskName(rvTwoModel);

                    if (stat)
                    {
                        rvTwoModelArrayList = databaseSource.getTaskName(rvOneAdapterArrayList.get(position).getId());

                        //rvTwoModelArrayList.add(rvTwoModel);
                        Log.e("Two RV ArrayList Size", String.valueOf(rvTwoModelArrayList.size()));

                        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                        layoutManager.setOrientation(RecyclerView.VERTICAL);
                        rv_two.setLayoutManager(layoutManager);

                        RvTwoAdapter rvTwoAdapter = new RvTwoAdapter(context, rvTwoModelArrayList, type, textView);
                        rvTwoAdapter.notifyDataSetChanged();
                        rv_two.setAdapter(rvTwoAdapter);

                        size = databaseSource.getTaskNameByType(type);
                        textView.setText(size + " Tasks");

                    }
                    else {
                        Toast.makeText(context, "Failed to Insert", Toast.LENGTH_SHORT).show();
                    }



                }
            });

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog diaBox = AskOption();
                    diaBox.show();

                }
            });



        }

        private AlertDialog AskOption()
        {
            AlertDialog myQuittingDialogBox = new AlertDialog.Builder(context)
                    // set message, title, and icon
                    .setTitle("Delete")
                    .setMessage("Do you want to delete " + rvOneAdapterArrayList.get(getAdapterPosition()).getTaskTime() +" ?")
//                .setIcon(R.drawable.delete_image_icon)

                    .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            //your deleting code
                            boolean status = databaseSource.deleteUpAllById(rvOneAdapterArrayList.get(getAdapterPosition()).getId(),
                                    rvOneAdapterArrayList.get(getAdapterPosition()).getTaskTime());
                            rvOneAdapterArrayList.remove(getAdapterPosition());
                            size = databaseSource.getTaskNameByType(type);
                            notifyDataSetChanged();
                            textView.setText(size + " Tasks");
                            if (status)
                            {
                                Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                            }

                        }

                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();

                        }
                    })
                    .create();

            return myQuittingDialogBox;
        }
    }

}
