package com.santi.pmdm.virgen.dogapicleanarchitecture.domain.mapper

import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.database.entities.DogEntity
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.Dog



//Funciones de extensión, para convertir objetos de Tipo DogEntity/Pair a Dog

//MODIFICADO ID
fun DogEntity.toDog(): Dog {
    return Dog(id = this.id, breed = this.breed, image = this.image)
}

//Mapeo un DogModel a DogEntity para persistir  IU --> base de datos
fun Dog.toDogEntity(): DogEntity{
    return DogEntity(breed = breed, image = image)
}

//MODIFICADO ID
//Mapeo un Pair<String,String> a Dog  Pair --> IU
fun Pair<String, String>.toDog(): Dog {
    return Dog(id = 0, breed = first, image = second)
}
