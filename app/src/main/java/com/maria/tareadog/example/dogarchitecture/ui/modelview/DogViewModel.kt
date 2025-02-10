package com.santi.pmdm.virgen.dogapicleanarchitecture.ui.modelview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.Dog
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase.DeleteDogUseCase
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase.DeleteDogsFromDataBaseUseCase
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase.GetDogsBreedUseCase
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase.GetDogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
class DogViewModel @Inject constructor(
    private val useCaseList : GetDogsUseCase,
    private val getDogsBreedUseCase: GetDogsBreedUseCase,
    private val userCaseDeleteDatabase : DeleteDogsFromDataBaseUseCase,
    private val deleteDogUseCase: DeleteDogUseCase     //MODIFICADO

): ViewModel() {


    var dogListLiveData = MutableLiveData<List<Dog>>()
    var progressBarLiveData = MutableLiveData<Boolean> ()
    var breed = MutableLiveData<String>()

    fun list() {
        viewModelScope.launch {
            progressBarLiveData.value = true
            delay(500)

            var data : List<Dog> ?
            withContext(Dispatchers.IO){
                data  = useCaseList()
            }

            data.let {
                dogListLiveData.value = it
                progressBarLiveData.value = false
            }
        }
    }



    fun listForBreed(breed: String) {
        viewModelScope.launch {
            progressBarLiveData.value = true //notifico
            delay(500)
            var data : List<Dog> ?

            withContext(Dispatchers.IO){
                getDogsBreedUseCase.setBreed(breed)
                data  = getDogsBreedUseCase()
            }
            data.let {
                dogListLiveData.value = it
                progressBarLiveData.value = false
            }
        }
    }

    fun searchByBreed(breed: String){

        this.breed.value = breed
    }



    fun delete() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                userCaseDeleteDatabase()
            }
            list()
        }
    }

    //MODIFICADO
        fun deleteDog(dogId: Int) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    deleteDogUseCase(dogId)
                    val listaActualizada = useCaseList() // Obtengo la lista actualizada
                    if (listaActualizada?.isEmpty() == true) {
                        userCaseDeleteDatabase() // Si no hay perros, se cargan los datos nativos
                    }
                }
                list() // Vuelvo a cargar la lista
            }
        }



}