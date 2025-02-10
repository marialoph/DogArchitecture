package com.pmdm.virgen.dogapi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.santi.pmdm.virgen.dogapicleanarchitecture.R
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.Repository
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.Dog


//MODIFICADO onDeleteDog
class DogAdapter(private val onDeleteDog: (Int) -> Unit)  : RecyclerView.Adapter<ViewHDog>() {
    var dogRepository: List<Dog> = Repository.dogs


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHDog {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutDogItem = R.layout.item_dog
        return ViewHDog(
            layoutInflater.inflate(layoutDogItem, parent, false), onDeleteDog)
    }

    override fun onBindViewHolder(holder: ViewHDog, position: Int) {
        holder.rendereize(dogRepository.get(position), position)
    }

    override fun getItemCount() = dogRepository.size
}