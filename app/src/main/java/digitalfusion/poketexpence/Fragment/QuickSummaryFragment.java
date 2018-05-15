package digitalfusion.poketexpence.Fragment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import digitalfusion.poketexpence.Data.DataBaseHelper;
import digitalfusion.poketexpence.R;
import digitalfusion.poketexpence.ViewModel.AddTransactionModel;

/**
 * Created by MD003 on 5/7/18.
 */

public class QuickSummaryFragment extends Fragment {


    AddTransactionModel viewmodel;
    DataBaseHelper dataBaseHelper;
    Integer expence ;
    Integer income ;

    TextView expencetxtview;
    TextView incometxtview;
    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.quick_summary, container, false);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        expencetxtview = (TextView) view.findViewById(R.id.totalExpence);
        incometxtview = (TextView) view.findViewById(R.id.totalIncome);

        context = getActivity();
        dataBaseHelper = new DataBaseHelper(context);

        SQLiteDatabase collect = dataBaseHelper.getReadableDatabase();

        expence =  dataBaseHelper.gettodayexpence("Expence") ;
        income = dataBaseHelper.gettodayexpence("Inocme");

        expencetxtview.setText(expence + " MMK");
        incometxtview.setText(income + " MMK");

        getActivity().setTitle(R.string.quicksummary_fragment);
    }
}