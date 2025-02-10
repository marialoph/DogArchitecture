package com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase

import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.database.entities.DogEntity
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.repository.DogRepository
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.mapper.toDogEntity
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.Dog
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.Repository
import javax.inject.Inject


class GetDogsBreedUseCase @Inject constructor(
    private val dogRepositoryDao: DogRepository
){
    private var breed: String = ""
    fun setBreed(breed: String){
        this.breed = breed
    }


    suspend operator fun invoke() : List<Dog>{
        Repository.dogs = dogRepositoryDao.getBreedDogsEntity(breed)

        if (Repository.dogs.isEmpty()){
            Repository.dogs  = dogRepositoryDao.getBreedDogs(breed)
            val dataDogEntity : List<DogEntity> = Repository.dogs.map { it.toDogEntity() }
            dogRepositoryDao.insertBreedEntitytoDatabase(dataDogEntity)
        }
        return  return Repository.dogs

    }

}