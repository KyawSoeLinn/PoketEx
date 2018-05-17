package digitalfusion.poketexpence.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import digitalfusion.poketexpence.Data.DataBaseHelper;
import digitalfusion.poketexpence.Model.ExpenceCategories;
import digitalfusion.poketexpence.R;
import digitalfusion.poketexpence.Util.RecyclerViewClickListener;

public class CategorySelectAdapter extends RecyclerView.Adapter<CategorySelectAdapter.CategorySelectViewHolder> {

    public List<ExpenceCategories> expenceCategories;
    private Context sContext;
    DataBaseHelper dataBaseHelper;
    private Integer CatId;
    private String CatName ="Select Category";
    private RecyclerViewClickListener mListener;

    public class CategorySelectViewHolder extends RecyclerView.ViewHolder {

        private TextView selectname;
        private ImageView selecticon;
        private LinearLayoutCompat linearLayoutCompat;

        public CategorySelectViewHolder(View view) {
            super(view);
            selectname = view.findViewById(R.id.catSelecttxtview);
            selecticon = view.findViewById(R.id.imgCatSelect);
            linearLayoutCompat = view.findViewById(R.id.linear_layout);
        }

    }

    public CategorySelectAdapter(List<ExpenceCategories> expenceCategories, Context context, RecyclerViewClickListener listener) {
        this.expenceCategories = expenceCategories;
        this.sContext = context;
        this.mListener = listener;
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
        holder.linearLayoutCompat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CatName = expenceCategories1.getCategoriesName();
                CatId = Integer.valueOf(expenceCategories1.getCategoriesID());
                mListener.onSendData(CatName, CatId);
            }
        });


    }

    @Override
    public int getItemCount() {
        return expenceCategories == null ? 0 : expenceCategories.size();
    }
}
