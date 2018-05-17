package digitalfusion.poketexpence.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import digitalfusion.poketexpence.Activity.AddTransactionActivity;
import digitalfusion.poketexpence.Activity.SettingActivity;
import digitalfusion.poketexpence.R;

public class SettingsFragment extends Fragment {

    String settingStatus;
   public static final String  FRAGMENTSTATUS ="Status";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.nav_setting, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.txt_settings);
        //you can set the title for your toolbar here for different fragments different titles
        AppCompatTextView selectCategory=(AppCompatTextView) view.findViewById(R.id.show_category);
        RelativeLayout categoryLayout=(RelativeLayout)view.findViewById(R.id.Category_layout);
        categoryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               settingStatus="CategoryFragment";
               onLoadActivity(settingStatus);
            }
        });

    }

    private void onLoadActivity(String settingStatus) {
        Intent i = new Intent(getActivity(),SettingActivity.class);
        i.putExtra(FRAGMENTSTATUS,settingStatus);
        getActivity().startActivity(i);
    }


}
