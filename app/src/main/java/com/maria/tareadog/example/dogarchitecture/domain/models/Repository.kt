package com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models



//Repositorio en memoria.
class Repository {
    companion object{
        var dogs:List<Dog> = emptyList()
    }
}