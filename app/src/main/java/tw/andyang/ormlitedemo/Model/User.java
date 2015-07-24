package tw.andyang.ormlitedemo.Model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by andyang on 15/7/23
 */

@DatabaseTable(tableName = "User")
public class User {
    @DatabaseField(generatedId = true) private int id;
    @DatabaseField private String name;
    @DatabaseField private int sort;
    @ForeignCollectionField private ForeignCollection<Group> groups;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public int getSort() {
        return sort;
    }

    public User setSort(int sort) {
        this.sort = sort;
        return this;
    }

    public void setGroups(ForeignCollection<Group> groups) {
        this.groups = groups;
    }

    public ForeignCollection<Group> getGroups() {
        return groups;
    }
}
