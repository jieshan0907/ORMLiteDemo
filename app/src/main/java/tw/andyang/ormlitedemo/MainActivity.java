package tw.andyang.ormlitedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import tw.andyang.ormlitedemo.Model.User;
import tw.andyang.ormlitedemo.adapter.UserAdapter;
import tw.andyang.ormlitedemo.dao.UserDao;
import tw.andyang.ormlitedemo.utils.DividerItemDecoration;

public class MainActivity extends AppCompatActivity {

    private UserDao dao;
    private UserAdapter adapter;
    private int times;
    private boolean isMove = false;
    private ItemTouchHelper touchHelper = new ItemTouchHelper(
            new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder source, RecyclerView.ViewHolder target) {
                    int sourcePosition = source.getAdapterPosition();
                    int targetPosition = target.getAdapterPosition();
                    adapter.swap(sourcePosition, targetPosition);
                    dao.swap(adapter.getUsers().get(sourcePosition).getId(), adapter.getUsers().get(targetPosition).getId());
                    return true;
                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
                    int position = viewHolder.getAdapterPosition();
                    dao.deleteById(adapter.getUsers().get(position).getId());
                    adapter.delete(position);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dao = new UserDao(this);
        adapter = new UserAdapter(this, dao.getAll("sort", false));
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.userRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, null));
        touchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        isMove = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        if (isMove) {
                            adapter.notifyDataSetChanged();
                        }
                        isMove = false;
                        break;
                }
                return false;
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
            User user = createUser(times);
            dao.insert(user);
            adapter.setUsers(dao.getAll("sort", false));
            adapter.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private User createUser(int times) {
        return new User("Andy" + times);
    }


    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
