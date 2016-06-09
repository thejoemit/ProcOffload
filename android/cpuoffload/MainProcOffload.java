package net.notoriousbit.cpuoffload;
// MainProcOffload.java
// by: Joseph Mitchell licensed under CREATIVE COMMONS Attribution-NonCommercial-ShareAlike 4.0 International
import android.annotation.TargetApi;
import android.app.LoaderManager;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class MainProcOffload extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Object> {
    private static final int REQUEST_READ_EXTERNAL = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cpuoffloader);
        final Button button = (Button) findViewById(R.id.send_file);
        final TextView server = (TextView) findViewById(R.id.editText);
        assert button != null;
        checkStorage(button);
        button.setOnClickListener(new View.OnClickListener() {
            String m_chosen;
            @Override
            public void onClick(final View v) {
                /////////////////////////////////////////////////////////////////////////////////////////////////
                //Create FileOpenDialog and register a callback
                /////////////////////////////////////////////////////////////////////////////////////////////////
                SimpleFileDialog FileOpenDialog =  new SimpleFileDialog(MainProcOffload.this, "FileOpen",
                        new SimpleFileDialog.SimpleFileDialogListener()
                        {
                            @Override
                            public void onChosenDir(String chosenDir)
                            {
                                // The code in this function will be executed when the dialog OK button is pushed
                                m_chosen = chosenDir;
                                Snackbar.make(v, "Chosen File:  " +
                                        m_chosen, Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                                button.setText(m_chosen);
                            }
                        });
                //You can change the default filename using the public variable "Default_File_Name"
                FileOpenDialog.Default_File_Name = "";
                FileOpenDialog.chooseFile_or_Dir();
                /////////////////////////////////////////////////////////////////////////////////////////////////
            }
        });
        FloatingActionButton fab_send = (FloatingActionButton) findViewById(R.id.fab_send);
        assert fab_send != null;
        fab_send.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {
                Snackbar.make(view, "Sending file: " + button.getText() + "\nTo: " + server.getText() +"\n", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        FloatingActionButton fab_check = (FloatingActionButton) findViewById(R.id.fab_check);
        assert fab_check != null;
        fab_check.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {
                Snackbar.make(view, "Fetching user related tasks!\nFrom: " + server.getText() +"\n", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_main_exit) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private void checkStorage(Button button) {
        if (!mayRequestRead(button)) {
            return;
        }
        getLoaderManager().initLoader(0, null, this);
    }
    private boolean mayRequestRead(Button button) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_EXTERNAL_STORAGE)) {
            Snackbar.make(button, R.string.permission_rationale_storage, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_EXTERNAL_STORAGE}, REQUEST_READ_EXTERNAL);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_EXTERNAL_STORAGE}, REQUEST_READ_EXTERNAL);
        }
        return false;
    }
    @Override
    public Loader<Object> onCreateLoader(int id, Bundle args) {
        return null;
    }
    @Override
    public void onLoadFinished(Loader<Object> loader, Object data) {

    }
    @Override
    public void onLoaderReset(Loader<Object> loader) {

    }
}
