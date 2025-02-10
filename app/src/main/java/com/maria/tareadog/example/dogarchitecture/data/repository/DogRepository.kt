package com.santi.pmdm.virgen.dogapicleanarchitecture.data.repository

import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.database.dao.DogDao
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.database.entities.DogEntity
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.mem.service.DogService
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.mapper.toDog
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.Dog
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.Repository
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.repository.DogRepositoryInterface
import javax.inject.Inject


class DogRepository @Inject constructor(
    private val service : DogService,
    private val dogDao : DogDao
) : DogRepositoryInterface {

    override fun getDogs(): List<Dog> {
        val dataSource = service.getDogs()

        Repository.dogs = dataSource.map { it.toDog() }
        return Repository.dogs
    }

    override fun getBreedDogs(breed: String): List<Dog> {
        val dataSource = service.getBreedDogs(breed)

        Repository.dogs = dataSource.map { it.toDog() }
        return Repository.dogs
    }

    override suspend fun getDogsEntity(): List<Dog> {
        val listEntity : List<DogEntity> = dogDao.getAll()
        Repository.dogs = listEntity.map { it.toDog()}
       return Repository.dogs

    }

    override suspend fun getBreedDogsEntity(breed: String): List<Dog> {
        val listEntity : List<DogEntity> = dogDao.getDogsByBreed(breed)
        Repository.dogs = listEntity.map { it.toDog()}
        return Repository.dogs

    }


    //Método que inserte una lista de Entity en la BBDD.
    override suspend fun insertBreedEntitytoDatabase(listEntity : List<DogEntity>) {
        dogDao.insertAllDog(listEntity)
    }

    override suspend fun deleteDatabase() {
        dogDao.deleteAll()
    }

    //MODIFICADO
    override suspend fun deleteDog(dogId: Int) {
        dogDao.deleteDogById(dogId)
    }

}