package com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase

import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.database.dao.DogDao
import javax.inject.Inject

//MODIFICADO
class DeleteDogUseCase @Inject constructor(
    private val dogDao: DogDao
){
    suspend operator fun invoke(dogId: Int) {
        dogDao.deleteDogById(dogId)
    }
}