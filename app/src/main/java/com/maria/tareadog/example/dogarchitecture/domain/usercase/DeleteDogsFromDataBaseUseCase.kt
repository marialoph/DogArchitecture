package com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase

import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.database.dao.DogDao
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.Repository
import javax.inject.Inject

class DeleteDogsFromDataBaseUseCase @Inject constructor(
    private val dogRepositoryDao : DogDao
){
    suspend operator fun invoke(){
        dogRepositoryDao.deleteAll()
    }
}