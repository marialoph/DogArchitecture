package com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase

import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.database.entities.DogEntity
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.repository.DogRepository
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.mapper.toDogEntity
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.Dog
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.Repository
import javax.inject.Inject

class GetDogsUseCase @Inject constructor(
    private val dogRepositoryDao: DogRepository
) {
    suspend operator fun invoke(): List<Dog>?{
       Repository.dogs = dogRepositoryDao.getDogsEntity()

       if (Repository.dogs.isEmpty()){

           Repository.dogs = dogRepositoryDao.getDogs()
           val dataDogEntity : List<DogEntity> = Repository.dogs.map { it.toDogEntity() }
           dogRepositoryDao.insertBreedEntitytoDatabase(dataDogEntity)
       }
       return Repository.dogs

    }
}