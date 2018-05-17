package digitalfusion.poketexpence.Fragment;

import android.app.Fragment;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;


import java.util.ArrayList;
import java.util.List;

import digitalfusion.poketexpence.Data.DataBaseHelper;
import digitalfusion.poketexpence.Model.ExpenceCategories;
import digitalfusion.poketexpence.R;
import digitalfusion.poketexpence.ViewModel.AddCategoriesModel;

public class ReportCategoryFragment extends android.support.v4.app.Fragment {

    BarChart chart;

    DataBaseHelper dataBaseHelper;
    Context context;
    List<ExpenceCategories> expenceCategoriesList;
    int catSize = 0;
    int total;
    AddCategoriesModel viewmodel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.report_category_fragment, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(R.string.nav_report);

        BarChart chart=(BarChart) view.findViewById(R.id.chart);

        context = getActivity();
        dataBaseHelper = new DataBaseHelper(context);
        viewmodel = ViewModelProviders.of(getActivity()).get(AddCategoriesModel.class);
        /*expenceCategoriesList = dataBaseHelper.getAllCategories();*/
        catSize = expenceCategoriesList.size();
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
        if (catSize == 0) {
            BarEntry vle1= new BarEntry(0.00f,0); //Jan
            valueSet1.add(vle1);
        }
        else if (catSize >= 1) {
            for (int i = 1; i <= catSize; i++) {
                BarEntry vle1= new BarEntry(dataBaseHelper.getTotalCategory(Integer.parseInt(expenceCategoriesList.get(i-1).getCategoriesID())) ,i-1);
                valueSet1.add(vle1);
            }
        }

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "");
        barDataSet1.setColor(Color.rgb(0, 155, 0));
        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);

        return dataSets;
    }
    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        for (int i = 1; i <= catSize; i++) {
                xAxis.add(expenceCategoriesList.get(i-1).getCategoriesName());

        }
        return xAxis;
    }


}