package digitalfusion.poketexpence.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import digitalfusion.poketexpence.Activity.AddTransactionActivity;
import digitalfusion.poketexpence.Data.DataBaseHelper;
import digitalfusion.poketexpence.Model.ExpenceCategories;
import digitalfusion.poketexpence.R;
import digitalfusion.poketexpence.Util.AddCategory;
import digitalfusion.poketexpence.Util.RecyclerViewHolders;

public class CategorySelectAdapter extends RecyclerView.Adapter<CategorySelectAdapter.CategorySelectViewHolder>{

    public List<ExpenceCategories> expenceCategories;
    private Context sContext;
    DataBaseHelper dataBaseHelper;
    public static Integer CatId ;
    public static String CatName = "";


    public class CategorySelectViewHolder extends RecyclerView.ViewHolder{

        public TextView selectname;
        public ImageView selecticon;

        public CategorySelectViewHolder (View view){
            super(view);
            selectname = view.findViewById(R.id.catSelecttxtview);
            selecticon = view.findViewById(R.id.imgCatSelect);
        }

    }

    public CategorySelectAdapter(List<ExpenceCategories> expenceCategories, Context context){
        this.expenceCategories = expenceCategories;
        this.sContext = context;
    }


    @Override
    public CategorySelectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.category_select_single_list, parent, false);
        CategorySelectViewHolder catSelectVH = new CategorySelectViewHolder(v);

        return catSelectVH;
    }

    @Override
    public void onBindViewHolder(CategorySelectAdapter.CategorySelectViewHolder holder, int position) {
        final ExpenceCategories expenceCategories1 = expenceCategories.get(position);
        holder.selectname.setText(expenceCategories1.getCategoriesName());
        holder.selecticon.setImageResource(expenceCategories1.getCategoriesIcon());
        holder.selectname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CatName = expenceCategories1.getCategoriesName();
                CatId = Integer.valueOf(expenceCategories1.getCategoriesID());

                Intent intent = new Intent(sContext, AddTransactionActivity.class);
                intent.putExtra("key", CatName);
                sContext.startActivity(intent);
                Toast.makeText(sContext, CatName +"edit edit" + CatId, Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return expenceCategories == null ? 0 : expenceCategories.size();
    }
}
