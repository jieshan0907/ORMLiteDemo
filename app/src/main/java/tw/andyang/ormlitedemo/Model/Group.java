package tw.andyang.ormlitedemo.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by andyang on 15/7/23
 */

@DatabaseTable(tableName = "Group")
public class Group {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String name;
    @DatabaseField
    private String description;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, canBeNull = false, columnName = "user_id")
    private User user;

    public Group() {
    }

    public Group(int id, String name, String description, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.user = user;
    }

    public Group(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public Group setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Group setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Group setDescription(String description) {
        this.description = description;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Group setUser(User user) {
        this.user = user;
        return this;
    }
}
