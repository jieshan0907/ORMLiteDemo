package tw.andyang.ormlitedemo.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import tw.andyang.ormlitedemo.GroupsActivity;
import tw.andyang.ormlitedemo.Model.User;
import tw.andyang.ormlitedemo.R;

/**
 * Created by andyang on 15/7/23
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> users;
    private LayoutInflater inflater;
    private Activity activity;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public UserAdapter(Activity activity, List<User> users) {
        inflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.users = users;
    }

    public void swap(int sourcePosition, int targetPosition) {
        Collections.swap(users,sourcePosition, targetPosition);
        notifyItemMoved(sourcePosition, targetPosition);
    }

    public void delete(int position){
        users.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(inflater.inflate(R.layout.user_listitems, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.nameTextView.setText(users.get(i).getName());
        viewHolder.countTextView.setText(String.valueOf(users.get(i).getGroups().size()));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, GroupsActivity.class);
                intent.putExtra("id", users.get(i).getId());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView nameTextView;
        public TextView countTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            countTextView = (TextView) itemView.findViewById(R.id.countTextView);
        }
    }
}
