package com.bedel.app.kamerbiz.Activity.Filter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;


import com.bedel.app.kamerbiz.Activity.Post.DetailsEntrepriseActivity;
import com.bedel.app.kamerbiz.Adapter.EnterpriseAdapter;
import com.bedel.app.kamerbiz.Adapter.ItemClickListener;
import com.bedel.app.kamerbiz.Entity.Enterprise;
import com.bedel.app.kamerbiz.Entity.Category;
import com.bedel.app.kamerbiz.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;
import com.yalantis.filter.adapter.FilterAdapter;
import com.yalantis.filter.listener.FilterListener;
import com.yalantis.filter.widget.Filter;
import com.yalantis.filter.widget.FilterItem;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;




public class FilterActivity extends AppCompatActivity implements FilterListener<Category>,ItemClickListener<Enterprise> {

    private RecyclerView mRecyclerView;
    private int[] mColors;
    private String[] mTitles;
    private List<Enterprise> mAllEnterprises;
    private  Filter<Category> mFilter;
    private EnterpriseAdapter mAdapter;
    private SearchBox search;
    private ItemClickListener<Enterprise> itemClickListener;


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        buildpersistantsearch();

        ImagePipelineConfig config = ImagePipelineConfig
                .newBuilder(this)
                .setDownsampleEnabled(true)
                .build();
        Fresco.initialize(this, config);

        mColors = getResources().getIntArray(R.array.colors);
        mTitles = getResources().getStringArray(R.array.job_titles);

        mFilter = (Filter<Category>) findViewById(R.id.filter);
        mFilter.setAdapter(new Adapter(getTags()));
        mFilter.setListener(this);

        //the text to show when there's no selected items
        mFilter.setNoSelectedItemText(getString(R.string.str_all_selected));
        mFilter.build();



        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter = new EnterpriseAdapter(FilterActivity.this, mAllEnterprises = getEnterprises(),new ItemClickListener<Enterprise>() {
            @Override
            public void onClick(Enterprise item, View sharedView) {
                View textView = findViewById(R.id.text_question);
                Intent intent = new Intent(FilterActivity.this, DetailsEntrepriseActivity.class);
                Bundle data = new Bundle();
                data.putSerializable("Post", item);
                intent.putExtras(data);
                Pair<View, String> pair = Pair.create(textView, "post_desc");
                ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(FilterActivity.this, sharedView, "post_desc");
                startActivity(intent, activityOptions.toBundle());

            }
        }));
        //mRecyclerView.setItemAnimator(new FiltersListItemAnimator());

    }

    private void buildpersistantsearch() {
        search = (SearchBox) findViewById(R.id.searchbox);
        search.enableVoiceRecognition(this);
        search.setHint("Search your enterprise");
        search.setSearchListener(new SearchBox.SearchListener(){

            @Override
            public void onSearchOpened() {
                //Use this to tint the screen
            }

            @Override
            public void onSearchClosed() {
                //Use this to un-tint the screen
            }

            @Override
            public void onSearchTermChanged(String term) {
                //React to the search term changing
                //Called after it has updated results
                mAdapter.getFilter().filter(term);
            }

            @Override
            public void onSearch(String searchTerm) {
                mAdapter.getFilter().filter(searchTerm);
            }

            @Override
            public void onResultClick(SearchResult result) {
                //React to a result being clicked
            }

            @Override
            public void onSearchCleared() {
                //Called when the clear button is clicked
            }

        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1234 && resultCode == RESULT_OK) {
            ArrayList<String> matches = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            search.populateEditText(matches.get(0));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



    private void calculateDiff(final List<Enterprise> oldList, final List<Enterprise> newList) {
        DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return oldList.size();
            }

            @Override
            public int getNewListSize() {
                return newList.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
            }
        }).dispatchUpdatesTo(mAdapter);
    }

    private List<Category> getTags() {
        List<Category> categories = new ArrayList<>();

        for (int i = 0; i < mTitles.length; ++i) {
            categories.add(new Category(mTitles[i], mColors[i]));
        }

        return categories;
    }

    @Override
    public void onNothingSelected() {
        if (mRecyclerView != null) {
            mAdapter.setEntreprise(mAllEnterprises);
            mAdapter.notifyDataSetChanged();
        }
    }

    private List<Enterprise> getEnterprises() {
        return new ArrayList<Enterprise>() {{
            add(new Enterprise("HOSPITAL LAQUINTINIE", "Graphic Designer",
                    "https://www.google.cm/url?sa=i&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwjT3aOYq4jfAhXMYlAKHTbHDj4QjRx6BAgBEAU&url=https%3A%2F%2Fwww.canva.com%2Ftemplates%2Flogos%2Fband%2F&psig=AOvVaw3SuxtJtOoOfK6CXewnDGCP&ust=1544087174069814", "Nov 20, 6:12 PM",
                    "What is the first step to transform an idea into an actual project?", new ArrayList<Category>() {{
                add(new Category(mTitles[2], mColors[2]));
                add(new Category(mTitles[4], mColors[4]));
            }}));
            add(new Enterprise("HOTEL LAFALAIRE", "Project Manager",
                    "http://weknowyourdreams.com/images/girl/girl-03.jpg", "Nov 20",
                    "What is your biggest frustration with taking your business/career (in a corporate) to the next level?", new ArrayList<Category>() {{
                add(new Category(mTitles[1], mColors[1]));
                add(new Category(mTitles[5], mColors[5]));
            }}));
            add(new Enterprise("VOLCAN GOLDY", "iOS Developer",
                    "https://www.popsci.com/sites/popsci.com/files/import/2014/NASA_LOGO.gif", "Nov 20",
                    "What is the first step to transform an idea into an actual project?", new ArrayList<Category>() {{
                add(new Category(mTitles[7], mColors[7]));
                add(new Category(mTitles[8], mColors[8]));
            }}));
            add(new Enterprise("IPME LOGBESSOU", "QA Engineer",
                    "http://kingofwallpapers.com/girl/girl-019.jpg", "Nov 20",
                    "What is the first step to transform an idea into an actual project?", new ArrayList<Category>() {{
                add(new Category(mTitles[3], mColors[3]));
                add(new Category(mTitles[9], mColors[9]));
            }}));
            add(new Enterprise("ELEVATION COMPAGNY", "Pharmer",
                    "http://tribzap2it.files.wordpress.com/2014/09/hannah-simone-new-girl-season-4-cece.jpg", "Nov 20",
                    "What is the first step to transform an idea into an actual project?", new ArrayList<Category>() {{
                add(new Category(mTitles[1], mColors[1]));
                add(new Category(mTitles[6], mColors[6]));
            }}));

            add(new Enterprise("TYFHANIE COMPAGNY", "Pharmer",
                    "http://tribzap2it.files.wordpress.com/2014/09/hannah-simone-new-girl-season-4-cece.jpg", "Nov 20",
                    "What is the first step to transform an idea into an actual project?", new ArrayList<Category>() {{
                add(new Category(mTitles[1], mColors[1]));
                add(new Category(mTitles[6], mColors[6]));
            }}));
        }};
    }

    private List<Enterprise> findByTags(List<Category> categories) {
        List<Enterprise> enterprises = new ArrayList<>();

        for (Enterprise enterprise : mAllEnterprises) {
            for (Category category : categories) {
                if (enterprise.hasTag(category.getText()) && !enterprises.contains(enterprise)) {
                    enterprises.add(enterprise);
                }
            }
        }

        return enterprises;
    }

    @Override
    public void onFiltersSelected(@NotNull ArrayList<Category> filters) {
        List<Enterprise> newEnterprises = findByTags(filters);
        List<Enterprise> oldEnterprises = mAdapter.getEnterprises();
        mAdapter.setEntreprise(newEnterprises);
        calculateDiff(oldEnterprises, newEnterprises);
    }

    @Override
    public void onFilterSelected(Category item) {
        if (item.getText().equals(mTitles[0])) {
            mFilter.deselectAll();
            mFilter.collapse();
        }
    }

    @Override
    public void onFilterDeselected(Category item) {

    }

    @Override
    public void onClick(Enterprise item, View sharedView) {
        Intent intent = new Intent(this, DetailsEntrepriseActivity.class);
        Bundle data = new Bundle();
        data.putSerializable("Enterprise", item);
        intent.putExtras(data);
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(FilterActivity.this, sharedView, "shared_img");
        startActivity(intent, activityOptionsCompat.toBundle());

    }

    class Adapter extends FilterAdapter<Category> {

        Adapter(@NotNull List<? extends Category> items) {
            super(items);
        }

        @NotNull
        @Override
        public FilterItem createView(int position, Category item) {
            FilterItem filterItem = new FilterItem(FilterActivity.this);

            filterItem.setStrokeColor(mColors[0]);
            filterItem.setTextColor(mColors[0]);
            filterItem.setCornerRadius(50);
            filterItem.setCheckedTextColor(ContextCompat.getColor(FilterActivity.this, android.R.color.white));
            filterItem.setColor(ContextCompat.getColor(FilterActivity.this, android.R.color.white));
            filterItem.setCheckedColor(mColors[position]);
            filterItem.setText(item.getText());
            filterItem.deselect();

            return filterItem;
        }
    }

}
