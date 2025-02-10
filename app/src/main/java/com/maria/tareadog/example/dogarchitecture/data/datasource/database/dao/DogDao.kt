package com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.database.entities.DogEntity

@Dao
interface DogDao {

    //Listado de todos los Dogs
    @Query ("SELECT * FROM dogentity")
    suspend fun getAll(): List<DogEntity>

    //Listado de todos los Dogs dada la raza
    @Query ("SELECT * FROM dogentity WHERE breed = :breed")
    suspend fun getDogsByBreed(breed: String): List<DogEntity>

    /*Insertamos uno o varios Dogs. Utilizaremos corrutinas.
    Ejemplo de utilización del método sería  dao.insertDog(dog1, dog2, dog3)*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDog(vararg dogs : DogEntity)


    /*Insertamos una lista de Dogs. Utilizaremos corrutinas.
    Ejemplo de utilización del método sería  dao.insertDog(dogs), siendo dogs :List<DogEntity>*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDog(dogs: List<DogEntity>)

    @Query ("DELETE FROM dogentity")
    suspend fun deleteAll()

    //MODIFICADO
    @Query("DELETE FROM dogentity WHERE id = :dogId")
    suspend fun deleteDogById(dogId: Int)


}