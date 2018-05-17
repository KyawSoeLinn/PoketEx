package digitalfusion.poketexpence.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import digitalfusion.poketexpence.Adapter.CategorySelectAdapter;
import digitalfusion.poketexpence.Data.DataBaseHelper;
import digitalfusion.poketexpence.R;
import digitalfusion.poketexpence.Util.PassDataToActivity;
import digitalfusion.poketexpence.Util.RecyclerViewClickListener;

public class SelectCategoryFragment extends AppCompatDialogFragment {

    private GridLayoutManager categoryLayout;
    private PassDataToActivity passData;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.select_category, container,

                false);
        getDialog().setTitle(R.string.selectCategories);

        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int adapterPosition) {



            }

            @Override
            public void onSendData(String catName, Integer catId) {

                Toast.makeText(getActivity(),"Name:" + catName ,Toast.LENGTH_SHORT).show();
                passData = (PassDataToActivity) getActivity();
                passData.passDataToActivity(catName,catId);
                dismiss();

            }

            @Override
            public void onSendCategoryData(Integer categoriesID, Integer categoriesIcon, String categoriesName) {


            }

        };

        CategorySelectAdapter CatSelectAdapter;
        DataBaseHelper dataBaseHelper=new DataBaseHelper(getContext());
        CatSelectAdapter = new CategorySelectAdapter(dataBaseHelper.getAllCategory(), getActivity(),listener);

          categoryLayout=new GridLayoutManager(getContext(),3);
          RecyclerView mRecylcerView=(RecyclerView) rootView.findViewById(R.id.selectCatRV);
          mRecylcerView.setHasFixedSize(true);
          mRecylcerView.setLayoutManager(categoryLayout);
          mRecylcerView.setAdapter(CatSelectAdapter);

        return rootView;
    }


}
