package digitalfusion.poketexpence.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import digitalfusion.poketexpence.Data.DataBaseHelper;
import digitalfusion.poketexpence.Model.ExpenceCategories;
import digitalfusion.poketexpence.R;
import digitalfusion.poketexpence.Util.AddCategoryDialogFragment;
import digitalfusion.poketexpence.Util.RecyclerViewClickListener;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoriesViewHolder> {


    public List<ExpenceCategories> expenceCategoriesList;
    private Context cContext;
    DataBaseHelper dbhelper;
   private  RecyclerViewClickListener mListener;
    public Activity activity;




    public class CategoriesViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public ImageView icon;
        public ImageView delete, edit;

        public CategoriesViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.catNameTextView);
            icon = view.findViewById(R.id.CatImg);
            delete = view.findViewById(R.id.CatDelete);
            edit = view.findViewById(R.id.CatEdit);
        }

    }

    public CategoryListAdapter(List<ExpenceCategories> expenceCategoriesList,  RecyclerViewClickListener mListener,Context context){
        this.expenceCategoriesList = expenceCategoriesList;
        this.cContext = context;
        this.mListener=mListener;
    }

    @Override
    public CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.category_list_item, parent, false);
        CategoriesViewHolder viewholder = new CategoriesViewHolder(v);


        return viewholder;
    }

    @Override
    public void onBindViewHolder(CategoriesViewHolder holder, final int position) {
        final ExpenceCategories expenceCategories = expenceCategoriesList.get(position);
        holder.name.setText(expenceCategories.getCategoriesName());
        /*Integer icon= R.drawable.ic_img_bus;
        Integer secIcon=expenceCategories.getCategoriesIcon();*/
        /*Picasso.with(cContext)
                .load(expenceCategories.getCategoriesIcon())  //name of the image to load.
                .into(holder.icon);*/
        holder.icon.setImageResource(expenceCategories.getCategoriesIcon());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbhelper = new DataBaseHelper(cContext);
                dbhelper.deleteCategory(expenceCategories.getCategoriesID(), cContext);
                expenceCategoriesList.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onSendCategoryData(Integer.parseInt(expenceCategories.getCategoriesID()),expenceCategories.getCategoriesIcon(),expenceCategories.getCategoriesName());

            }
        });

    }

    @Override
    public int getItemCount() {
        return expenceCategoriesList == null ? 0 : expenceCategoriesList.size();
    }






}
