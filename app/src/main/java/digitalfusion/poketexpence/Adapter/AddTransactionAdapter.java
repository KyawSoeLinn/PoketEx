package digitalfusion.poketexpence.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import digitalfusion.poketexpence.Activity.AddTransactionActivity;
import digitalfusion.poketexpence.Activity.MainActivity;
import digitalfusion.poketexpence.Activity.UpdateRecordActivity;
import digitalfusion.poketexpence.Data.DataBaseHelper;
import digitalfusion.poketexpence.Fragment.HomeFragment;
import digitalfusion.poketexpence.Model.ExpenceTransation;
import digitalfusion.poketexpence.R;
import digitalfusion.poketexpence.Util.OnLoadMoreListener;
import digitalfusion.poketexpence.Util.ProgressViewHolder;
import digitalfusion.poketexpence.Util.RecyclerViewHolders;

public class AddTransactionAdapter extends RecyclerView.Adapter<AddTransactionAdapter.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private OnLoadMoreListener onLoadMoreListener;
    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private RecyclerView mRecyclerView;
    private Context mcontext;
    private List<ExpenceTransation> mTransactionList = new ArrayList<>();
    private DataBaseHelper DBHelper;


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgCategory;
        public TextView txtamount, txtPayee, txtDate, txtTransactionType;
        public View frontLayout;
        private SwipeRevealLayout swipeLayout;
        private View editlayout;
        private ImageView txtEdit, txtDelete;

        public ViewHolder(View v) {
            super(v);
            swipeLayout = (SwipeRevealLayout) v.findViewById(R.id.swipe_layout);
            frontLayout = v.findViewById(R.id.front_layout);
            editlayout = v.findViewById(R.id.edit_layout);
            imgCategory = (ImageView) v.findViewById(R.id.img_category);
            txtamount = (TextView) v.findViewById(R.id.txt_amount);
            txtPayee = (TextView) v.findViewById(R.id.txt_payee);
            txtDate = (TextView) v.findViewById(R.id.txt_date);
            txtTransactionType = (TextView) v.findViewById(R.id.txt_transactionType);

            txtEdit = (ImageView) v.findViewById(R.id.img_edit);
            txtDelete = (ImageView) v.findViewById(R.id.img_delete);
        }

    }

    public AddTransactionAdapter(List<ExpenceTransation> transaction, Context context) {
        this.mTransactionList = transaction;
        this.mcontext = context;
    }

    @Override
    public AddTransactionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.add_transaction_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AddTransactionAdapter.ViewHolder holder, final int position) {

        final ExpenceTransation transaction = mTransactionList.get(position);
        String transactionType;
        holder.txtamount.setText(transaction.getAmount().toString()+ " MMK");
        holder.txtDate.setText(transaction.getCreated_at().toString());
        holder.txtPayee.setText(transaction.getPayee().toString());
        transactionType = transaction.getExpencetype().toString();
        holder.txtTransactionType.setText(transactionType);
        if (transactionType.equals("Income")) {
          holder.txtTransactionType.setTextColor(Color.GREEN);
        }
        else{
            holder.txtTransactionType.setTextColor(Color.RED);
        }


        holder.txtEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUpdateActivity(transaction.getId());

            }
        });
        holder.txtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Delet item function
                DBHelper = new DataBaseHelper(mcontext);
                DBHelper.deleteExpenseRecord(transaction.getId(), mcontext);
                mTransactionList.remove(position);
                notifyDataSetChanged();

            }
        });
    }

    private void goToUpdateActivity(Long id) {
        Intent intent = new Intent(mcontext, UpdateRecordActivity.class);
        intent.putExtra("TRANSACTION_ID", id);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mcontext.startActivity(intent);


    }

    private void updateExpenseRecord() {

    }

    @Override
    public int getItemCount() {
        return mTransactionList == null ? 0 : mTransactionList.size();
    }



 /*   public AddTransactionAdapter(RecyclerView recyclerView, List<ExpenceTransation> transaction, Context context) {
        this.mTransactionList = transaction;
        this.mcontext = context;
        this.mRecyclerView=recyclerView;

       final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }
    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }
    // "Loading item" ViewHolder
    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
        }
    }

    // "Normal item" ViewHolder
    private class TransactionViewHolder extends RecyclerView.ViewHolder {
        public   ImageView imgCategory;
        public    TextView txtamount, txtPayee, txtDate, txtTransactionType;

        public TransactionViewHolder(View v) {
            super(v);
            imgCategory = (ImageView) v.findViewById(R.id.img_category);
            txtamount = (TextView) v.findViewById(R.id.txt_amount);
            txtPayee = (TextView) v.findViewById(R.id.txt_payee);
            txtDate = (TextView) v.findViewById(R.id.txt_date);
            txtTransactionType = (TextView) v.findViewById(R.id.txt_date);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(mcontext).inflate(R.layout.add_transaction_row, parent, false);
            return new TransactionViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(mcontext).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }


        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TransactionViewHolder) {
            ExpenceTransation transaction = mTransactionList.get(position);
            TransactionViewHolder userViewHolder = (TransactionViewHolder) holder;
            userViewHolder.txtamount.setText(transaction.getAmount().toString());
            userViewHolder.txtDate.setText(transaction.getCreated_at());
            userViewHolder.txtPayee.setText(transaction.getPayee().toString());
            userViewHolder.txtTransactionType.setText(transaction.getCategoriesID().toString());
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return mTransactionList == null ? 0 : mTransactionList.size();
    }


    @Override
    public int getItemViewType(int position) {
        return mTransactionList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }
    public void setLoaded() {
        isLoading = false;
    }
*/


}