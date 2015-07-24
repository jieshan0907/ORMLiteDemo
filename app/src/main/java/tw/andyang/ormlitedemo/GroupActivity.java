package tw.andyang.ormlitedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import tw.andyang.ormlitedemo.Model.Group;
import tw.andyang.ormlitedemo.dao.GroupDao;

public class GroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        int id = getIntent().getIntExtra("id", 0);

        GroupDao dao = new GroupDao(this);
        Group group = dao.getById(id);

        TextView nameTextView = (TextView) findViewById(R.id.nameTextView);
        TextView descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);

        nameTextView.setText(group.getName());
        descriptionTextView.setText(group.getDescription());
    }
}
