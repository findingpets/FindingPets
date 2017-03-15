package com.teamf.findingpets.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.ChangeEventListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.teamf.findingpets.R;
import com.teamf.findingpets.models.Animal;

public class AnimalFirebaseRecyclerAdapter extends FirebaseRecyclerAdapter<Animal, AnimalFirebaseRecyclerAdapter.AnimalHolder> {

    private static final String TAG = AnimalFirebaseRecyclerAdapter.class.getSimpleName();

    public AnimalFirebaseRecyclerAdapter(Query ref) {
        super(Animal.class, R.layout.item_animal, AnimalHolder.class, ref);

    }

    public AnimalFirebaseRecyclerAdapter(Class<Animal> modelClass, int modelLayout, Class<AnimalHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    @Override
    protected void onChildChanged(ChangeEventListener.EventType type, int index, int oldIndex) {
        super.onChildChanged(type, index, oldIndex);
    }

    @Override
    protected void populateViewHolder(AnimalHolder viewHolder, Animal animal, int position) {
        Log.d(TAG, "Holder = " + viewHolder.toString() + "  " + animal.getAlbum_update() + " position=" + position);
        viewHolder.setValue(animal);

    }

    static class AnimalHolder extends RecyclerView.ViewHolder {

        private static final String TAG = Animal.class.getSimpleName();
        ImageView imgAnimal;
        TextView textAnimalId;
        Animal animal;

        public AnimalHolder(View itemView) {
            super(itemView);
            imgAnimal = (ImageView) itemView.findViewById(R.id.img_animal);
            textAnimalId = (TextView) itemView.findViewById(R.id.text_animal_id);
        }

        public void setValue( Animal animal) {
            this.animal = animal;
            Log.d(TAG, animal.getAlbum_file());

            if  (!TextUtils.isEmpty(animal.getAlbum_file()))
                Glide.with(imgAnimal.getContext()).load(animal.getAlbum_file()).into(imgAnimal);

            textAnimalId.setText("animal id = " + animal.getAnimal_id());
        }


    }
}
