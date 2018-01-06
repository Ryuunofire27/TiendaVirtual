package com.charlie.tiendavirtual.dao;

import com.charlie.tiendavirtual.entity.Producto;
import javax.persistence.Query;

public class ProductoDao extends GenericDao<Producto>{

    @Override
    protected Class<Producto> getClase() {
        return Producto.class;
    }
    
    public int obtenerId(){
        Query query = crearQueryNativa("Select * from productoid where ");
    }
    
}
