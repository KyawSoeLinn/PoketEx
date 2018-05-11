package digitalfusion.poketexpence.Util;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import digitalfusion.poketexpence.Activity.AddTransactionActivity;
import digitalfusion.poketexpence.Adapter.CategoryListAdapter;
import digitalfusion.poketexpence.Adapter.CategorySelectAdapter;
import digitalfusion.poketexpence.Data.DataBaseHelper;
import digitalfusion.poketexpence.Model.ExpenceCategories;
import digitalfusion.poketexpence.R;

public class SelectCategory extends Activity{

    private RecyclerView recyclerView;
    public Activity activity;
    DataBaseHelper dataBaseHelper;
    List<ExpenceCategories> getCatdata;
    private CategorySelectAdapter CatSelectAdapter;
    Button yes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_category);

        recyclerView = (RecyclerView) findViewById(R.id.selectCatRV);
        yes = (Button) findViewById(R.id.selectOK);

        dataBaseHelper = new DataBaseHelper(activity);

        getCatdata = dataBaseHelper.getAllCategories();

        CatSelectAdapter = new CategorySelectAdapter(dataBaseHelper.getAllCategories(), activity.getBaseContext());
        RecyclerView.LayoutManager catLayoutManger = new LinearLayoutManager(activity.getApplicationContext());
        recyclerView.setLayoutManager(catLayoutManger);
        recyclerView.setAdapter(CatSelectAdapter);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


    }


}
