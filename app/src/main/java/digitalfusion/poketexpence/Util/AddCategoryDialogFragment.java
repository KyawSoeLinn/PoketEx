package digitalfusion.poketexpence.Util;

import android.app.Activity;
import android.app.DialogFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import digitalfusion.poketexpence.Activity.MainActivity;
import digitalfusion.poketexpence.Adapter.CategoryIconSelectAdapter;
import digitalfusion.poketexpence.Adapter.CategorySelectAdapter;
import digitalfusion.poketexpence.Data.DataBaseHelper;
import digitalfusion.poketexpence.Fragment.CategoriesFragment;
import digitalfusion.poketexpence.Model.IconList;
import digitalfusion.poketexpence.R;
import digitalfusion.poketexpence.ViewModel.AddCategoriesModel;

public class AddCategoryDialogFragment extends AppCompatDialogFragment {

    EditText CatEdittxt;
    Button btncatAdd, btncatCancel;
    ImageView iconview;
    private RecyclerView recyclerView;
    DataBaseHelper dbhelper;
    String Cattxt;
    private CategoryIconSelectAdapter iconadapter;
    private List<IconList> iconlist;
    private  Integer iconID;
    AddCategoriesModel viewModel;
    private PassDataToActivity passData;
   RecyclerViewClickListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.add_category, container,

                false);
        // getDialog().setTitle(R.string.addCategories);


          /*  @Override
            public void onSendData(String catName, Integer catId) {

                Toast.makeText(getActivity(), "Name:" + catName, Toast.LENGTH_SHORT).show();
                passData = (PassDataToActivity) getActivity();
                passData.passDataToActivity(catName, catId);
                dismiss();*/



        CatEdittxt = (EditText) rootView.findViewById(R.id.catNameedttxt);
        btncatAdd = (Button) rootView.findViewById(R.id.catAdd);
        btncatCancel = (Button) rootView.findViewById(R.id.catCancel);
        iconview = (ImageView) rootView.findViewById(R.id.catimgview);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.catIconRV);

        iconlist = new ArrayList<>();

        viewModel = ViewModelProviders.of(getActivity()).get(AddCategoriesModel.class);

        RecyclerView.LayoutManager iconLayoutmanger = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(iconLayoutmanger);

        mListener= new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int adapterPosition) {
                iconview.setImageResource(adapterPosition);
                iconID = adapterPosition;

            }

            @Override
            public void onSendData(String catName, Integer catId) {

            }


        };
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        iconadapter = new CategoryIconSelectAdapter(iconlist, mListener, getActivity());
        recyclerView.setAdapter(iconadapter);


        prepareIcon();

        dbhelper = new DataBaseHelper(getContext());

        btncatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Cattxt = CatEdittxt.getText().toString();

                viewModel.insertCategory(Cattxt, iconID);
                Toast.makeText(getContext(), Cattxt, Toast.LENGTH_SHORT).show();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                 CategoriesFragment parent = (CategoriesFragment) fm.findFragmentByTag("SampleFragment");
               parent.loadRecyclerView();

                dismiss();

                }
        });

        btncatCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(AddCategory.this, MainActivity.class);
                startActivity(intent);*/
                dismiss();
            }
        });
return rootView;

    }

    private void prepareIcon() {

        int[] icons = new int[]{
                R.drawable.ic_shopping_cart,
                R.drawable.ic_cloth,
                R.drawable.ic_food,
                R.drawable.ic_fun,
                R.drawable.ic_gift,
                R.drawable.ic_house,
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
        icon = new IconList(icons[6]);
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
