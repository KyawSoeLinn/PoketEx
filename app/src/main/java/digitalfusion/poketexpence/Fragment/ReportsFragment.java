package digitalfusion.poketexpence.Fragment;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import digitalfusion.poketexpence.Data.DataBaseHelper;
import digitalfusion.poketexpence.R;

/**
 * Created by MD003 on 5/7/18.
 */

public class ReportsFragment  extends Fragment{

    BarChart chart;

    DataBaseHelper dataBaseHelper;
    Context context;
    String Income = "Income";
    String Expense = "Expense";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.reports_fragement, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(R.string.nav_report);

        BarChart chart=(BarChart) view.findViewById(R.id.chart);

        context = getActivity();
        dataBaseHelper = new DataBaseHelper(context);

        SQLiteDatabase collect = dataBaseHelper.getReadableDatabase();

        BarData data = new BarData(getXAxisValues(), getDataSet());
        chart.setData(data);
        chart.setDescription("");
        chart.animateXY(2000, 2000);
        chart.invalidate();
    }

    private ArrayList<BarDataSet> getDataSet()
    {
        ArrayList<BarDataSet> dataSets=null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry vle1= new BarEntry(dataBaseHelper.getselectmonth(Income , "01") ,0); //Jan
        valueSet1.add(vle1);
        BarEntry v1e2 = new BarEntry(dataBaseHelper.getselectmonth(Income , "02"), 1); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(dataBaseHelper.getselectmonth(Income , "03"), 2); // Mar
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(dataBaseHelper.getselectmonth(Income , "04"), 3); // Apr
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(dataBaseHelper.getselectmonth(Income , "05"), 4); // May
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(dataBaseHelper.getselectmonth(Income , "06"), 5); // Jun
        valueSet1.add(v1e6);
        BarEntry v1e7 = new BarEntry(dataBaseHelper.getselectmonth(Income , "07"), 6); // Jun
        valueSet1.add(v1e7);
        BarEntry v1e8 = new BarEntry(dataBaseHelper.getselectmonth(Income , "08"), 7); // Jun
        valueSet1.add(v1e8);
        BarEntry v1e9 = new BarEntry(dataBaseHelper.getselectmonth(Income , "09"), 8); // Jun
        valueSet1.add(v1e9);
        BarEntry v1e10 = new BarEntry(dataBaseHelper.getselectmonth(Income , "10"), 9); // Jun
        valueSet1.add(v1e10);
        BarEntry v1e11 = new BarEntry(dataBaseHelper.getselectmonth(Income , "11"), 10); // Jun
        valueSet1.add(v1e11);
        BarEntry v1e12 = new BarEntry(dataBaseHelper.getselectmonth(Income , "12"), 11); // Jun
        valueSet1.add(v1e12);


        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        BarEntry v2e1 = new BarEntry(dataBaseHelper.getselectmonth(Expense , "01"), 0); // Jan
        valueSet2.add(v2e1);
        BarEntry v2e2 = new BarEntry(dataBaseHelper.getselectmonth(Expense , "02"), 1); // Feb
        valueSet2.add(v2e2);
        BarEntry v2e3 = new BarEntry(dataBaseHelper.getselectmonth(Expense , "03"), 2); // Mar
        valueSet2.add(v2e3);
        BarEntry v2e4 = new BarEntry(dataBaseHelper.getselectmonth(Expense , "04"), 3); // Apr
        valueSet2.add(v2e4);
        BarEntry v2e5 = new BarEntry(dataBaseHelper.getselectmonth(Expense , "05"), 4); // May
        valueSet2.add(v2e5);
        BarEntry v2e6 = new BarEntry(dataBaseHelper.getselectmonth(Expense , "06"), 5); // Jun
        valueSet2.add(v2e6);
        BarEntry v2e7 = new BarEntry(dataBaseHelper.getselectmonth(Income , "07"), 6); // Jun
        valueSet2.add(v2e7);
        BarEntry v2e8 = new BarEntry(dataBaseHelper.getselectmonth(Expense , "08"), 7); // Jun
        valueSet2.add(v2e8);
        BarEntry v2e9 = new BarEntry(dataBaseHelper.getselectmonth(Expense , "09"), 8); // Jun
        valueSet2.add(v2e9);
        BarEntry v2e10 = new BarEntry(dataBaseHelper.getselectmonth(Expense , "10"), 9); // Jun
        valueSet2.add(v2e10);
        BarEntry v2e11 = new BarEntry(dataBaseHelper.getselectmonth(Expense , "11"), 10); // Jun
        valueSet2.add(v2e11);
        BarEntry v2e12 = new BarEntry(dataBaseHelper.getselectmonth(Expense , "12"), 11); // Jun
        valueSet2.add(v2e12);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Income");
        barDataSet1.setColor(Color.rgb(0, 155, 0));
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Expense");
        //barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet2.setColor(Color.rgb(150, 0, 0));
        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        return dataSets;
    }
    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("JAN");
        xAxis.add("FEB");
        xAxis.add("MAR");
        xAxis.add("APR");
        xAxis.add("MAY");
        xAxis.add("JUN");
        xAxis.add("JUL");
        xAxis.add("AUG");
        xAxis.add("SEP");
        xAxis.add("OCT");
        xAxis.add("NOV");
        xAxis.add("DEC");
        return xAxis;
    }
}
