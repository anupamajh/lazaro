package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.R;

import java.util.ArrayList;

import Model.AttachFilesModel;

public class AttachFilesAdapter extends RecyclerView.Adapter<AttachFilesAdapter.ViewHolder> {
    ArrayList<AttachFilesModel> attachFilesModels;
    private Context context;
    public AttachFilesAdapter(Context context, ArrayList<AttachFilesModel> attachFilesModelArrayList) {
        this.context=context;
        this.attachFilesModels=attachFilesModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final  View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.attachment_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final AttachFilesModel attachFilesModel=this.attachFilesModels.get(position);
        holder.attachFilesName.setText(String.valueOf(attachFilesModel.getAttachFilesName()));
        holder.attachFilesSize.setText(String.valueOf(attachFilesModel.getAttachFilesSize()));
        holder.deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=attachFilesModels.indexOf(attachFilesModel);
                attachFilesModels.remove(position);
                notifyItemRemoved(position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return attachFilesModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView attachFilesName,attachFilesSize;
        ImageView deleteIcon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            attachFilesName=itemView.findViewById(R.id.attachFileName);
            attachFilesSize=itemView.findViewById(R.id.attachFileSize);
            deleteIcon=itemView.findViewById(R.id.deleteIcon);
            deleteIcon.setImageResource(R.drawable.delete_icon);
        }
    }
}
