package org.usablelabs.duedo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import java.util.List;

public class ListActivity extends BaseActivity {

    private static final int NEW_TASK = 1;

    private List<Task> tasks;
    private TextView emptyLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setDrawer(false);
        setTitle(R.string.tasks);

        emptyLabel = (TextView) findViewById(R.id.emptyLabel);

        FloatingActionButton newFab = (FloatingActionButton) findViewById(R.id.newFab);
        newFab.setImageDrawable(buildDrawable(MaterialDesignIconic.Icon.gmi_plus));
        newFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, FormActivity.class);
                startActivityForResult(intent, NEW_TASK);
            }
        });

        tasks = Task.getAll();
        if (tasks.isEmpty())
            emptyLabel.setVisibility(View.VISIBLE);
    }
}
