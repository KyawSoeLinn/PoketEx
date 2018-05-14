package digitalfusion.poketexpence.Util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import digitalfusion.poketexpence.Activity.Category;
import digitalfusion.poketexpence.Adapter.CategoryIconSelectAdapter;
import digitalfusion.poketexpence.Data.DataBaseHelper;
import digitalfusion.poketexpence.Model.IconList;
import digitalfusion.poketexpence.R;

public class AddCategory extends Activity {

    EditText CatEdittxt;
    Button btncatAdd, btncatCancel;
    ImageView iconview;
    private RecyclerView recyclerView;
    DataBaseHelper dbhelper;
    String Cattxt;
    private CategoryIconSelectAdapter iconadapter;
    private List<IconList> iconlist;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_category);

        CatEdittxt = (EditText) findViewById(R.id.catNameedttxt);
        btncatAdd = (Button) findViewById(R.id.catAdd);
        btncatCancel = (Button) findViewById(R.id.catCancel);
        iconview = (ImageView) findViewById(R.id.catimgview);
        recyclerView = (RecyclerView) findViewById(R.id.catIconRV);

        iconlist = new ArrayList<>();
        iconadapter = new CategoryIconSelectAdapter(iconlist, this);

        RecyclerView.LayoutManager iconLayoutmanger = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(iconLayoutmanger);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setAdapter(iconadapter);

        prepareIcon();

        dbhelper = new DataBaseHelper(this);

        final Bundle bundle = getIntent().getExtras();




        btncatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bundle.getString("key")!= null){

                    int id;
                    id = Integer.parseInt(bundle.getString("key"));
                    Cattxt = CatEdittxt.getText().toString();
                    dbhelper.updateCategory(id, Cattxt, "grr");

                    Toast.makeText(AddCategory.this, Cattxt + "edited", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AddCategory.this, Category.class);
                    startActivity(intent);

                }
                else {


                    Cattxt = CatEdittxt.getText().toString();

                    Integer idddd = 0;
                    idddd= dbhelper.getAllCategories().size() + 1;


                    dbhelper.insertCategory((dbhelper.getAllCategories().size() + 1), Cattxt,R.drawable.ic_img_bus);


                    Toast.makeText(AddCategory.this, Cattxt, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AddCategory.this, Category.class);
                    startActivity(intent);
                }
            }
        });

        btncatCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCategory.this, Category.class);
                startActivity(intent);
            }
        });




    }

    private void prepareIcon() {

        int[] icons = new int[]{
                R.drawable.ic_shopping_cart,
                R.drawable.ic_menu_gallery,
                R.drawable.ic_menu_manage,
                R.drawable.ic_menu_send,
                R.drawable.ic_menu_share,
                R.drawable.ic_img_bus};

        IconList icon = new IconList(icons[0]);
        iconlist.add(icon);
        icon = new IconList(icons[1]);
        iconlist.add(icon);
        icon = new IconList(icons[2]);
        iconlist.add(icon);
        icon = new IconList(icons[3]);
        iconlist.add(icon);
        icon = new IconList(icons[4]);
        iconlist.add(icon);
        icon = new IconList(icons[5]);
        iconlist.add(icon);

        iconadapter.notifyDataSetChanged();

    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


}



    /*@Override
    public void onClick(View v) {

        final Bundle bundle = activity.getIntent().getExtras();

        switch (v.getId()) {
            case R.id.catAdd:
                if (bundle.getString("key")!= null){

                    int id;
                    id = Integer.parseInt(bundle.getString("key"));
                    Cattxt = CatEdittxt.getText().toString();
                    dbhelper.updateCategory(id, Cattxt, "grr");

                    Toast.makeText(activity, Cattxt + "edited", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(activity, Category.class);
                    activity.startActivity(intent);

                }
                else {


                    Cattxt = CatEdittxt.getText().toString();
                    dbhelper.insertCategory((dbhelper.getAllCategories().size() + 1), Cattxt, "grrr");

                    Toast.makeText(activity, Cattxt, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(activity, Category.class);
                    activity.startActivity(intent);
                }
                break;
            case R.id.catCancel:


        }

        btncatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bundle.getString("key")!= null){

                    int id;
                    id = Integer.parseInt(bundle.getString("key"));
                    Cattxt = CatEdittxt.getText().toString();
                    dbhelper.updateCategory(id, Cattxt, "grr");

                    Toast.makeText(activity, Cattxt + "edited", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(activity, Category.class);
                    activity.startActivity(intent);

                }
                else {


                    Cattxt = CatEdittxt.getText().toString();
                    dbhelper.insertCategory((dbhelper.getAllCategories().size() + 1), Cattxt, "grrr");

                    Toast.makeText(activity, Cattxt, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(activity, Category.class);
                    activity.startActivity(intent);
                }
            }
        });

        btncatCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, Category.class);
                activity.startActivity(intent);
            }
        });

    }*/

