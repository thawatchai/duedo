package org.usablelabs.duedo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.Date;
import java.util.List;

@Table(name = "Tasks")
public class Task extends Model {

    @Column(name = "title")
    public String title;

    @Column(name = "content")
    public String content;

    @Column(name = "due_at", index = true)
    public Date dueAt;

    @Column(name = "created_at", index = true)
    public Date createdAt;

    @Column(name = "updated_at", index = true)
    public Date updatedAt;

    public static List<Task> getAll() {
        return new Select().from(Task.class).orderBy("created_at ASC").execute();
    }
}
