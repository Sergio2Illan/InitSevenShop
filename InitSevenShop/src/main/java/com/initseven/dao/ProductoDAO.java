package com.initseven.dao;

import java.util.List;

import com.initseven.model.Producto;



public interface ProductoDAO {
	
	public List<Producto> getProductos();
	
	public Producto getProductoById(int id);
	
	public boolean insertProducto(int id, String nombre, int capacidad, String descripcion);
	
	public boolean updateProducto(int id, String nombre, int capacidad, String descripcion);
	
	public boolean deleteProducto(int id);

}
