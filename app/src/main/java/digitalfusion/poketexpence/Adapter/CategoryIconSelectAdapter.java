package digitalfusion.poketexpence.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import digitalfusion.poketexpence.Model.IconList;
import digitalfusion.poketexpence.R;
import digitalfusion.poketexpence.Util.RecyclerViewClickListener;

public class CategoryIconSelectAdapter extends RecyclerView.Adapter<CategoryIconSelectAdapter.CategoriesIconViewHolder>{

    public List<IconList> IconList;
    private Context iContext;


 private RecyclerViewClickListener mListener;



    public class CategoriesIconViewHolder extends RecyclerView.ViewHolder {

        public ImageView selectIcon;

        public CategoriesIconViewHolder(View itemView) {
            super(itemView);
            selectIcon = itemView.findViewById(R.id.catIconSingle);
        }


    }

    public CategoryIconSelectAdapter(List<IconList> IconList, RecyclerViewClickListener listener, Context context){
        this.IconList = IconList;
        this.iContext = context;
        this.mListener=listener;
    }

    @Override
    public CategoryIconSelectAdapter.CategoriesIconViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View iconView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_icon_select_single, parent, false);

        return new CategoryIconSelectAdapter.CategoriesIconViewHolder(iconView);
    }

    @Override
    public void onBindViewHolder(CategoryIconSelectAdapter.CategoriesIconViewHolder holder, int position) {

        final IconList iconlist = IconList.get(position);
        /*Picasso.with(iContext)
                .load(iconlist.getIcon())
                .into(holder.selectIcon);*/



        holder.selectIcon.setImageResource(iconlist.getIcon());

        holder.selectIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(v,iconlist.getIcon());
                //Toast.makeText(iContext, "??" + iconlist.getIcon()  , Toast.LENGTH_SHORT).show();




            }
        });

    }

    @Override
    public int getItemCount() {
        return  IconList.size();
    }
}
