package org.usablelabs.duedo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;

public class FormActivity extends BaseActivity {

    private static final int MenuItem_SaveID = 1;

    private Task task = null;
    private EditText titleEdit;
    private EditText contentEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        setDrawer(true);

        titleEdit = (EditText) findViewById(R.id.titleEdit);
        contentEdit = (EditText) findViewById(R.id.contentEdit);

        long id = getIntent().getLongExtra("id", 0);
        if (id == 0) {
            setTitle(R.string.new_task);
        } else {
            setTitle(R.string.edit_task);
            task = Task.load(Task.class, id);
            if (task != null) {
                titleEdit.setText(task.title);
                contentEdit.setText(task.content);
            } else {
                finish();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        addMenuItem(menu, MenuItem_SaveID, R.string.save, buildDrawable(MaterialDesignIconic.Icon.gmi_save));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (isEdited()) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(this);
                    alert.setTitle(android.R.string.dialog_alert_title);
                    alert.setMessage(R.string.unsaved_exit_alert);
                    alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            onBackPressed();
                        }
                    });
                    alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alert.show();
                    return true;
                }
                break;
            case MenuItem_SaveID:
                save();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isEdited() {
        if (task == null)
            return titleEdit.getText().length() > 0 || contentEdit.getText().length() > 0;
        else
            return !task.title.equals(titleEdit.getText().toString()) || !task.content.equals(contentEdit.getText().toString());
    }

    private void save() {
        if (titleEdit.getText().length() > 0) {
            if (task == null)
                task = new Task();
            task.title = titleEdit.getText().toString();
            task.content = contentEdit.getText().toString();
            task.saveWithTimestamp();
            setResult(Activity.RESULT_OK, new Intent().putExtra("id", task.getId()));
            this.finish();
        } else {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle(android.R.string.dialog_alert_title);
            alert.setMessage(R.string.title_is_required);
            alert.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alert.show();
        }
    }
}
