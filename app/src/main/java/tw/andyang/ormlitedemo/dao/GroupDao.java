package tw.andyang.ormlitedemo.dao;

import android.content.Context;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import tw.andyang.ormlitedemo.Model.Group;

/**
 * Created by andyang on 15/7/23
 */
public class GroupDao extends BaseDao<Group, Integer>{

    public GroupDao(Context context) {
        super(context, Group.class);
    }

    public List<Group> getByUserId(int userId){
        try {
            return getDao().queryBuilder()
                    .orderBy("id", false)
                    .where().eq("user_id", userId).query();
        }catch (SQLException e){
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
