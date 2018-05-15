package digitalfusion.poketexpence.Fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import digitalfusion.poketexpence.Activity.AddTransactionActivity;
import digitalfusion.poketexpence.Adapter.AddTransactionAdapter;
import digitalfusion.poketexpence.Data.DataBaseHelper;
import digitalfusion.poketexpence.Model.ExpenceTransation;
import digitalfusion.poketexpence.R;
import digitalfusion.poketexpence.Util.OnLoadMoreListener;
import digitalfusion.poketexpence.ViewModel.AddTransactionModel;

public class HomeFragment extends Fragment {
    private FloatingActionButton fab;
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;

    DataBaseHelper dbHelper;
    AddTransactionAdapter addTransactionAdapter;
    List<ExpenceTransation> transactionList=new ArrayList<>();
    MaterialSpinner transactionSpinner, dateFilterSpinner;
    String transactionFilter, dateFilter;
    AddTransactionModel viewModel;


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


        viewModel = ViewModelProviders.of(this).get(AddTransactionModel.class);


        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_show_transaction);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);


        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx,int dy){
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                    fab.hide();
                } else if (dy < 0 && fab.getVisibility() !=View.VISIBLE) {
                    fab.show();
                }
            }


        });
        dateFilterSpinner = (MaterialSpinner) view.findViewById(R.id.transaction_date_spinner);
        dateFilterSpinner.setItems("Today", "Yesterday", "This Week", "Last Week","Last Month");
        dateFilter="Today";
        transactionSpinner = (MaterialSpinner) view.findViewById(R.id.transaction_spinner);
        transactionSpinner.setItems("All", "Income", "Expense");
       transactionFilter="All";

        loadRecyclerView(transactionFilter, dateFilter);

        dateFilterSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                dateFilter = item.toString();
                loadRecyclerView(transactionFilter,dateFilter);

            }
        });

        transactionSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

                transactionFilter = item.toString();
                viewModel.getTransactionByType(transactionFilter,dateFilter);
                //loadRecyclerView(transactionFilter,dateFilter);
                // Update the list when the data changes

                if(viewModel.getTransactionListObservable().getValue() == null)
                {
                    addTransactionAdapter = new AddTransactionAdapter(transactionList, getContext());
                    mRecyclerView.setAdapter(addTransactionAdapter);
                    addTransactionAdapter.notifyDataSetChanged();
                }
                viewModel.getTransactionListObservable().observe(getActivity(), new Observer<List<ExpenceTransation>>() {
                    @Override
                    public void onChanged(@Nullable List<ExpenceTransation> expenceTransations) {
                        if(expenceTransations == null || expenceTransations.size() ==0)
                        {

                        }
                        else
                        {
                            addTransactionAdapter = new AddTransactionAdapter(expenceTransations, getContext());
                            mRecyclerView.setAdapter(addTransactionAdapter);
                            addTransactionAdapter.notifyDataSetChanged();


                        }

                    }
                });

            }
        });





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


    private void loadRecyclerView(String transactionFilter, String dateFilter) {

        viewModel.getAllTransaction(transactionFilter,dateFilter);

        // Update the list when the data changes
        viewModel.getTransactionListObservable().observe(this, new Observer<List<ExpenceTransation>>() {
            @Override
            public void onChanged(@Nullable List<ExpenceTransation> expenceTransations) {
                if(expenceTransations == null || expenceTransations.size() ==0)
                {

                }
                else
                {
                    addTransactionAdapter = new AddTransactionAdapter(expenceTransations, getContext());
                    mRecyclerView.setAdapter(addTransactionAdapter);
                    addTransactionAdapter.notifyDataSetChanged();


                }


            }
        });


    }

}


