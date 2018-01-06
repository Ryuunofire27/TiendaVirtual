package com.charlie.tiendavirtual.dao;

import com.charlie.tiendavirtual.entity.Imagen;

public class ImagenDao extends GenericDao<Imagen>{

    @Override
    protected Class<Imagen> getClase() {
        return Imagen.class;
    }
    
}
