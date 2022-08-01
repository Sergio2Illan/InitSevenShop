package com.initseven.dao;

import java.util.List;

import com.initseven.model.Carrito;
import com.initseven.model.Producto;
import com.initseven.model.Usuario;



public interface CarritoDAO {

		public List<Carrito> getCarrito();
		
		public Carrito getCarritoById(int id);
		
		public boolean insertCarrito(int id, Usuario usuario, Producto prod);
		
		public boolean updateCarrito(int id, Usuario usuario, Producto prod);
		
		public boolean deleteCarrito(int id);
	
}
