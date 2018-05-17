package digitalfusion.poketexpence.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import digitalfusion.poketexpence.Fragment.CategoriesFragment;
import digitalfusion.poketexpence.Fragment.HomeFragment;
import digitalfusion.poketexpence.Fragment.QuickSummaryFragment;
import digitalfusion.poketexpence.R;

import static digitalfusion.poketexpence.Fragment.SettingsFragment.FRAGMENTSTATUS;

public class SettingActivity extends AppCompatActivity {

    @SuppressLint("RestrictedApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_frame);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


      String fragmentStatus=getIntent().getStringExtra(FRAGMENTSTATUS);
      displaySelectedScreen(fragmentStatus);

    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }




    private void displaySelectedScreen(String SelectedFragment) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (SelectedFragment) {
            case "CategoryFragment":
                fragment = new CategoriesFragment();
                break;



        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.settings_frame, fragment);
            ft.commit();
        }



    }
}
