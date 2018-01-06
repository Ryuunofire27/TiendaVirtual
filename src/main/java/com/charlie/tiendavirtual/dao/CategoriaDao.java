package com.charlie.tiendavirtual.dao;

import com.charlie.tiendavirtual.entity.Categoria;

public class CategoriaDao extends GenericDao<Categoria>{

    @Override
    protected Class<Categoria> getClase() {
        return Categoria.class;
    }
    
}
