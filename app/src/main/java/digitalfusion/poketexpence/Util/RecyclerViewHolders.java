package digitalfusion.poketexpence.Util;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import digitalfusion.poketexpence.R;

public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{
  public   ImageView imgCategory;
 public    TextView txtamount, txtPayee, txtDate, txtTransactionType;
    public RecyclerViewHolders(View v) {
        super(v);
        v.setOnClickListener(this);
        imgCategory = (ImageView) v.findViewById(R.id.img_category);
        txtamount = (TextView) v.findViewById(R.id.txt_amount);
        txtPayee = (TextView) v.findViewById(R.id.txt_payee);
        txtDate = (TextView) v.findViewById(R.id.txt_date);
        txtTransactionType = (TextView) v.findViewById(R.id.txt_date);
    }
    @Override
    public void onClick(View view) {


    }
}