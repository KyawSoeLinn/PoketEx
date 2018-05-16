package digitalfusion.poketexpence.Fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import digitalfusion.poketexpence.Adapter.AddTransactionAdapter;
import digitalfusion.poketexpence.Adapter.CategoryListAdapter;
import digitalfusion.poketexpence.Data.DataBaseHelper;
import digitalfusion.poketexpence.Model.ExpenceCategories;
import digitalfusion.poketexpence.Model.ExpenceTransation;
import digitalfusion.poketexpence.R;
import digitalfusion.poketexpence.Util.AddCategory;
import digitalfusion.poketexpence.ViewModel.AddCategoriesModel;
import digitalfusion.poketexpence.ViewModel.AddTransactionModel;

public class CategoriesFragment extends Fragment {
    private FloatingActionButton fab;
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    CategoryListAdapter categoriesAdapter;
    List<ExpenceTransation> transactionList=new ArrayList<>();
    AddCategoriesModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(R.string.categories_fragment);


        viewModel = ViewModelProviders.of(this).get(AddCategoriesModel.class);


        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_show_category);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);


        loadRecyclerView();

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




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  AddCategoriesFragment fragment = null;
               Intent intent = new Intent(getActivity(), AddCategory.class);
                String key = null;
                intent.putExtra("key", key);
                startActivity(intent);*/




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


    private void loadRecyclerView() {

        viewModel.getAllCategories();

        // Update the list when the data changes
        viewModel.getCategoryListObservable().observe(this, new Observer<List<ExpenceCategories>>() {
            @Override
            public void onChanged(@Nullable List<ExpenceCategories> categories) {
                categoriesAdapter = new CategoryListAdapter(categories, getContext());
                mRecyclerView.setAdapter(categoriesAdapter);
                categoriesAdapter.notifyDataSetChanged();

            }


        });


    }
}