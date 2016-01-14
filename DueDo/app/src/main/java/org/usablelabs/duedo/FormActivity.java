package org.usablelabs.duedo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;

public class FormActivity extends BaseActivity {

    private static final int MenuItem_SaveID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        setDrawer(true);
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
            case MenuItem_SaveID: save();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void save() {

    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            MenuItem_SaveID -> { save() }
//        }
//        return super.onOptionsItemSelected(item)
//    }
}
