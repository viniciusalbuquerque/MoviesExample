package com.example.moviesexample.model.datasource

interface EntityMapper<Entity, DomainModel> {

    suspend fun mapFromEntity(entity: Entity) : DomainModel
    suspend fun mapToEntity(domainModel: DomainModel) : Entity
    suspend fun mapFromEntityList(entities: List<Entity>) : List<DomainModel>
    suspend fun mapToEntityList(domainModels: List<DomainModel>) : List<Entity>
}