package digitalfusion.poketexpence.Util;

import android.view.View;

public interface RecyclerViewClickListener {
    public void onClick(View view, int adapterPosition);

    void onSendData(String catName, Integer catId);


}
