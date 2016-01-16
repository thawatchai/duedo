package org.usablelabs.duedo;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;

public class ShowActivity extends BaseActivity {

    private Task     task = null;
    private TextView titleView;
    private TextView contentView;

    private static final int MenuItem_EditID = 1;
    private static final int MenuItem_DeleteID = 2;
    private static final int EDIT_TASK = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        setDrawer(true);
        setTitle(R.string.task);

        titleView = (TextView) findViewById(R.id.titleView);
        contentView = (TextView) findViewById(R.id.contentView);

        long id = getIntent().getLongExtra("id", 0);
        setView(id);
    }

    private void setView(long id) {
        if (id > 0)
            task = Task.load(Task.class, id);
        if (task != null) {
            titleView.setText(task.title);
            contentView.setText(task.content);
        } else {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        addMenuItem(menu, MenuItem_EditID, R.string.edit, buildDrawable(MaterialDesignIconic.Icon.gmi_edit));
        addMenuItem(menu, MenuItem_DeleteID, R.string.delete, buildDrawable(MaterialDesignIconic.Icon.gmi_delete));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MenuItem_EditID:
                Intent intent = new Intent(this, FormActivity.class);
                intent.putExtra("id", task.getId());
                startActivityForResult(intent, EDIT_TASK);
                break;
            case MenuItem_DeleteID:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle(android.R.string.dialog_alert_title);
                alert.setMessage(R.string.are_you_sure);
                alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        task.delete();
                        setResult(Activity.RESULT_OK, new Intent().putExtra("refreshNeeded", true));
                        finish();
                    }
                });
                alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            switch (requestCode) {
                case EDIT_TASK:
                    if (extras != null && extras.getLong("id", 0) > 0) {
                        setView(task.getId());
                        setResult(Activity.RESULT_OK, new Intent().putExtra("refreshNeeded", true));
                    }
                    break;
            }
        }
    }
}
