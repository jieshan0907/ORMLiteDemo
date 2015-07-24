package tw.andyang.ormlitedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import tw.andyang.ormlitedemo.Model.Group;
import tw.andyang.ormlitedemo.Model.User;
import tw.andyang.ormlitedemo.adapter.GroupAdapter;
import tw.andyang.ormlitedemo.dao.GroupDao;
import tw.andyang.ormlitedemo.dao.UserDao;

public class GroupsActivity extends AppCompatActivity {

    private GroupDao dao;
    private GroupAdapter adapter;
    private User user;
    private int times;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        int id = getIntent().getIntExtra("id", 0);
        UserDao userDao = new UserDao(this);
        user = userDao.getById(id);
        dao = new GroupDao(this);
        adapter = new GroupAdapter(this, dao.getByUserId(id));
        ListView listView = (ListView) findViewById(R.id.groupListView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GroupsActivity.this, GroupActivity.class);
                intent.putExtra("id", adapter.getGroups().get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        times++;
        int id = item.getItemId();
        if (id == R.id.user_add) {
            dao.insert(createGroup(times));
            adapter.setGroups(dao.getByUserId(user.getId()));
            adapter.notifyDataSetChanged();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public Group createGroup(int times) {
        Group group = new Group("Group" + times, "Description" + times);
        group.setUser(user);
        return group;
    }
}
