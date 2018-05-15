package digitalfusion.poketexpence.Activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import digitalfusion.poketexpence.Adapter.CategoryListAdapter;
import digitalfusion.poketexpence.Data.DataBaseHelper;
import digitalfusion.poketexpence.Model.ExpenceCategories;
import digitalfusion.poketexpence.R;
import digitalfusion.poketexpence.Util.AddCategory;

public class Category extends AppCompatActivity {

    SQLiteDatabase databse;
    ListView CatListView;
    CategoryListAdapter adapter;
    DataBaseHelper dbHelper;
    List<ExpenceCategories> CatList;
    private CategoryListAdapter CatlistAdapter;
    private List<ExpenceCategories> expenceCategoriesList = new ArrayList<>();
    private RecyclerView recyclerView;
    Button btnAdd;
    List<ExpenceCategories> getCatdata;
    FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_fragment);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_show_category);
        dbHelper = new DataBaseHelper(this);
        getCatdata = dbHelper.getAllCategories();

        CatlistAdapter = new CategoryListAdapter(dbHelper.getAllCategories(), getBaseContext());
        RecyclerView.LayoutManager catLayoutManger = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(catLayoutManger);
        recyclerView.setAdapter(CatlistAdapter);




        fab = (FloatingActionButton) findViewById(R.id.add_category);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(Category.this, AddCategory.class);
                String key = null;
                intent.putExtra("key", key);
                startActivity(intent);



            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
