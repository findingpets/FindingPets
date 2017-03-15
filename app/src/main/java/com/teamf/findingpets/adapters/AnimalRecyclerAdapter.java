package com.teamf.findingpets.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.teamf.findingpets.R;
import com.teamf.findingpets.models.Animal;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class AnimalRecyclerAdapter extends RecyclerView.Adapter<AnimalRecyclerAdapter.AnimalHolder> {
    ArrayList<Animal> animals;

    @Override
    public AnimalHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_animal, null);
        return new AnimalHolder(view);
    }

    public void setAnimals(ArrayList<Animal> animals){
        this.animals = animals;
        Log.d(TAG, "animals size = " + animals.size());
    }

    @Override
    public void onBindViewHolder(AnimalHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder position = " + position);
        holder.setValue(animals.get(position));
    }

    @Override
    public int getItemCount() {
        return animals==null ? 0 : animals.size();
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
