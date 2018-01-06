package my.edu.tarc.staff;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    private Button btn_add;
    private Button btn_remove_edit;
    private Button btn_setting;
    private Button btn_check;
    private Button btn_support;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = (Button)findViewById(R.id.btn_add);
        btn_remove_edit = (Button)findViewById(R.id.btn_remove_edit);
        btn_setting = (Button)findViewById(R.id.btn_remove_edit);
        btn_check = (Button)findViewById(R.id.btn_check);
        btn_support = (Button)findViewById(R.id.btn_support);

    }

    public void add_subject_menu(View view) {
        Intent intent = new Intent(this, SubjectActivity.class);
        startActivity(intent);
    }

    public void check_progression(View view) {
        Intent intent = new Intent(this, checkProgressActivity.class);
        startActivity(intent);
    }

    private boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();

    }

}
