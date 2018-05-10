package digitalfusion.poketexpence.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import digitalfusion.poketexpence.Activity.Category;
import digitalfusion.poketexpence.Data.DataBaseHelper;
import digitalfusion.poketexpence.Model.ExpenceCategories;
import digitalfusion.poketexpence.R;
import digitalfusion.poketexpence.Util.AddCategory;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoriesViewHolder> {


    public List<ExpenceCategories> expenceCategoriesList;
    private Context cContext;
    DataBaseHelper dbhelper;

    public class CategoriesViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public ImageView icon;
        public Button delete, edit;

        public CategoriesViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.catNameTextView);
            icon = view.findViewById(R.id.CatImg);
            delete = view.findViewById(R.id.CatDelete);
            edit = view.findViewById(R.id.CatEdit);
        }

    }

    public CategoryListAdapter(List<ExpenceCategories> expenceCategoriesList,  Context context){
        this.expenceCategoriesList = expenceCategoriesList;
        this.cContext = context;
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
        /*holder.icon.setImageURI(expenceCategories.getCategoriesIcon());*/
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
                Intent intent = new Intent(cContext, AddCategory.class);
                String key = expenceCategories.getCategoriesID();
                intent.putExtra("key", key);
                cContext.startActivity(intent);
                Toast.makeText(cContext, key +"edit edit", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return expenceCategoriesList == null ? 0 : expenceCategoriesList.size();
    }






}
