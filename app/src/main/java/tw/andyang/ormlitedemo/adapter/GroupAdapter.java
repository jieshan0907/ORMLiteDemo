package tw.andyang.ormlitedemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tw.andyang.ormlitedemo.Model.Group;
import tw.andyang.ormlitedemo.R;

/**
 * Created by andyang on 15/7/23
 */
public class GroupAdapter extends BaseAdapter{

    private List<Group> groups;
    private LayoutInflater inflater;

    public GroupAdapter(Context context, List<Group> users) {
        this.groups = users;
        inflater = LayoutInflater.from(context);
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<Group> getGroups() {
        return groups;
    }

    @Override
    public int getCount() {
        return groups.size();
    }

    @Override
    public Object getItem(int position) {
        return groups.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (null == view) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.group_listitems, parent, false);
            holder.nameTextView = (TextView) view.findViewById(R.id.nameTextView);
            holder.noTextView = (TextView) view.findViewById(R.id.noTextView);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.noTextView.setText("" + groups.get(position).getId());
        holder.nameTextView.setText(groups.get(position).getName());

        return view;
    }

    class ViewHolder {
        TextView nameTextView;
        TextView noTextView;
    }
}
