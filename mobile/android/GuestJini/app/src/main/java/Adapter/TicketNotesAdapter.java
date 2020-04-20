package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Models.Ticket.TaskNote;
import com.carmel.guestjini.R;

import java.util.ArrayList;

public class TicketNotesAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<TaskNote> taskNotes;

    public TicketNotesAdapter(Context context, ArrayList<TaskNote> taskNotes) {
        this.context = context;
        this.taskNotes = taskNotes;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_notes_cell,parent,false);
        return new TicketNotesAdapter.TaskNotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return taskNotes.size();
    }

    public void updateData(ArrayList<TaskNote> taskNotes) {
        this.taskNotes = taskNotes;
        notifyDataSetChanged();
    }

    class TaskNotesViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{


        public TaskNotesViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
