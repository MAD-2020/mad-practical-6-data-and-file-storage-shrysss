package sg.edu.np.week_6_whackamole_3_0;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class CustomScoreAdaptor extends RecyclerView.Adapter<CustomScoreViewHolder> {
    /* Hint:
        1. This is the custom adaptor for the recyclerView list @ levels selection page
     */
    private static final String FILENAME = "CustomScoreAdaptor.java";
    private static final String TAG = "PAIn";
    ArrayList<Integer> levels;
    ArrayList<Integer> userScores;
    String username;
    CustomScoreAdaptorCallback listener;

    public CustomScoreAdaptor(UserData userdata, CustomScoreAdaptorCallback aListener){
        /* Hint:
        This method takes in the data and readies it for processing.
         */
        this.levels = userdata.getLevels();
        this.userScores = userdata.getScores();
        this.username = userdata.getMyUserName();
        this.listener = aListener;

    }

    public CustomScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        /* Hint:
        This method dictates how the viewholder layuout is to be once the viewholder is created.
         */
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.level_select, parent, false);

        return new CustomScoreViewHolder(v);
    }

    public void onBindViewHolder(CustomScoreViewHolder holder, final int position){

        /* Hint:
        This method passes the data to the viewholder upon bounded to the viewholder.
        It may also be used to do an onclick listener here to activate upon user level selections.
        Log.v(TAG, FILENAME + " Showing level " + levels.get(position) + " with highest score: " + userScores.get(position));
        Log.v(TAG, FILENAME+ ": Load level " + position +" for: " + list_members.getMyUserName());
         */
        int info1 = levels.get(position);
        holder.levelNumTextView.setText("Level " + String.valueOf(info1));

        int info2 = userScores.get(position);
        holder.highestScoreTextView.setText(String.valueOf(info2));

        Log.v(TAG, FILENAME + " Showing level " + levels.get(position) + " with highest score: " + userScores.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, FILENAME+ ": Load level " + levels.get(position) +" for: " + username);
                listener.startLevel(levels.get(position), username);
            }
        });
    }

    public int getItemCount(){
        /* Hint:
        This method returns the the size of the overall data.
         */
        return levels.size();
    }
}