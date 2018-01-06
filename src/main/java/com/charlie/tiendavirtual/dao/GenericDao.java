package com.charlie.tiendavirtual.dao;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.UserTransaction;

public abstract class GenericDao<T> {
    
    @PersistenceContext(unitName = "TiendaVirtual")
    private EntityManager em;
    
    @Inject
    private UserTransaction ut;
    
    protected abstract Class<T> getClase();
    
    public T insertar(T entity) throws Exception{
        ut.begin();
        em.persist(entity);
        ut.commit();
        return entity;
    }
    
    public void insertarLista(List<T> entities){
        for(T entity : entities){
            em.persist(entity);
        }
    }
    
    public void actualizar(T entity){
        em.merge(entity);
    }
    
    public void eliminar(T entity){
        em.remove(entity);
    }
    
    public List<T> obtenerLista(){
        String query = "select t from " + getClase().getSimpleName()+" t";
        Query q = em.createQuery(query);
        List<T> entitys = q.getResultList();
        return entitys;
    }
    
    public T obtenerPorId(int id){
        return (T) em.find(getClase(), id);
    }
    
    public Query crearQuery(String query){
        Query queryCreada = em.createQuery(query);
        return queryCreada;
    }
    
    public Query crearQueryNativa(String query) {
        Query queryCreada = em.createNativeQuery(query);

        return queryCreada;
    }
    
    public StoredProcedureQuery crearProcedureQuery(String procedure){
        StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery(procedure);
        
        return storedProcedureQuery;
    }
    
}
