package com.teamf.findingpets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.teamf.findingpets.adapters.AnimalRecyclerAdapter;
import com.teamf.findingpets.listener.EndlessRecyclerViewScrollListener;
import com.teamf.findingpets.models.Animal;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private DatabaseReference ref;
    ArrayList<Animal> animals;
    private AnimalRecyclerAdapter adapter;
    private Query query;
    private int visibleItemCount;
    private int totalItemCount;
    private int firstVisibleItemIndex;
    private boolean loading = false;
    private int previousTotal;
    private RecyclerView.OnScrollListener onScrollListener;
    private String lastAnimalId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        animals = new ArrayList<>();

        setViews();

    }

    private void setViews() {
        ref = FirebaseDatabase.getInstance().getReference().child("animals");
        setQuery(lastAnimalId);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        final GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);


        recyclerView.setLayoutManager(layoutManager);
        onScrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Log.d(TAG, "onLoadMore page=" + page + "totalItemsCount=" + totalItemsCount);
                setQuery(lastAnimalId);
//                setQuery(totalItemsCount+10);
            }
        };


        recyclerView.addOnScrollListener(onScrollListener);
        adapter = new AnimalRecyclerAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void setQuery(String endAt){
        Query query;
        if (TextUtils.isEmpty(endAt)){
            query = ref.orderByKey().limitToLast(25);
        }else{
            query = ref.orderByKey().endAt(endAt).limitToLast(25);
        }
        final int insertOffset = animals.size();

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String aid = null;
                for (DataSnapshot ds : dataSnapshot.getChildren()){

                    Animal animal = ds.getValue(Animal.class);
                    if (!TextUtils.isEmpty(lastAnimalId) && lastAnimalId.equals(animal.getAnimal_id())) continue;

                    animals.add(insertOffset, animal);
                    if (aid==null)  aid= animal.getAnimal_id();
//                    lastAnimalId  = animal.getAnimal_id();
                }
                lastAnimalId = aid;
                adapter.setAnimals(animals);

                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
//                        adapter.notifyDataSetChanged();
                    adapter.notifyItemRangeInserted(insertOffset, 25);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recyclerView.removeOnScrollListener(onScrollListener);

    }
}
