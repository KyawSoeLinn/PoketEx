package digitalfusion.poketexpence.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import digitalfusion.poketexpence.Activity.AddTransactionActivity;
import digitalfusion.poketexpence.Adapter.AddTransactionAdapter;
import digitalfusion.poketexpence.Data.DataBaseHelper;
import digitalfusion.poketexpence.Model.ExpenceTransation;
import digitalfusion.poketexpence.R;
import digitalfusion.poketexpence.Util.OnLoadMoreListener;

public class HomeFragment extends Fragment {
    private FloatingActionButton fab;
    RecyclerView mRecyclerView;
  LinearLayoutManager mLayoutManager;
  DataBaseHelper dbHelper;
  AddTransactionAdapter addTransactionAdapter;
 List<ExpenceTransation> transactionList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.home_fragment, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(R.string.home_fragment);

        dbHelper = new DataBaseHelper(getContext());
        transactionList = dbHelper.getAllData();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_show_transaction);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        addTransactionAdapter = new AddTransactionAdapter(dbHelper.getAllData(), getContext());
        mRecyclerView.setAdapter(addTransactionAdapter);

        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddTransactionActivity.class);
                getActivity().startActivity(intent);
            }
        });

       /* //set load more listener for the RecyclerView adapter
        addTransactionAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (transactionList.size() <= 20) {
                    transactionList.add(null);
                    addTransactionAdapter.notifyItemInserted(transactionList.size() - 1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            transactionList.remove(transactionList.size() - 1);
                            addTransactionAdapter.notifyItemRemoved(transactionList.size());

                            //Generating more data
                            int index = transactionList.size();
                            int end = index + 10;
                            for (int i = index; i < end; i++) {
                                *//*Contact contact = new Contact();
                                contact.setPhone(phoneNumberGenerating());
                                contact.setEmail("DevExchanges" + i + "@gmail.com");
                                contacts.add(contact);*//*
                                transactionList=dbHelper.getAllData();
                            }
                            addTransactionAdapter.notifyDataSetChanged();
                            addTransactionAdapter.setLoaded();
                        }
                    }, 5000);
                } else {
                    Toast.makeText(getContext(), "Loading data completed", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }

    }


