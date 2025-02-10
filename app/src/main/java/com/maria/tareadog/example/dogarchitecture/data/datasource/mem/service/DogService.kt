package com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.mem.service

import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.mem.models.Dogs
import javax.inject.Inject


//Esta clase, para tener acceso a los datos nativos.
class DogService @Inject constructor(): DogServiceInterface {
    override fun getDogs(): List<Pair<String, String>> {
        return Dogs.dogs
    }


    //Filtrado por raza.
    override fun getBreedDogs(breed: String): List<Pair<String,String>> {
        val newDogs = Dogs.dogs.filter {
            it.first.equals(breed)
        }
        return newDogs
    }

}