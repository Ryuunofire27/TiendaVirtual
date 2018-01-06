package com.charlie.tiendavirtual.dao;

import com.charlie.tiendavirtual.entity.Marca;

public class MarcaDao extends GenericDao<Marca>{

    @Override
    protected Class<Marca> getClase() {
        return Marca.class;
    }
    
}
