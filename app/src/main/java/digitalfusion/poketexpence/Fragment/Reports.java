package digitalfusion.poketexpence.Fragment;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import digitalfusion.poketexpence.R;

/**
 * Created by MD003 on 5/7/18.
 */

public class Reports extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        Intent intent = getIntent();

        String type = intent.getStringExtra("reportType");
        Fragment fragment = null;
        Integer id = Integer.parseInt(type);

        if (id == 1){
            fragment = new ReportsFragment();
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);

            ft.commit();
        }else {
            fragment = new ReportCategoryFragment();
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);

            ft.commit();
        }

    }
}
