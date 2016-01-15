package org.usablelabs.duedo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;

public class FormActivity extends BaseActivity {

    private static final int MenuItem_SaveID = 1;
    private boolean newNoteFlag = true;

    private EditText title;
    private EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        setDrawer(true);

        title = (EditText) findViewById(R.id.titleEdit);
        content = (EditText) findViewById(R.id.contentEdit);

        if (newNoteFlag)
            setTitle(R.string.new_task);
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
                if (newNoteFlag) {
                    if (title.getText().length() > 0 || content.getText().length() > 0) {
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
                }
                break;
            case MenuItem_SaveID:
                save();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void save() {
        if (title.getText().length() > 0) {
            Task task = new Task();
            task.title = title.getText().toString();
            task.content = content.getText().toString();
            task.save();
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
