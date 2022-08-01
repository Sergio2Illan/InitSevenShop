package com.initseven.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.initseven.dao.ProductoDAO;
import com.initseven.factory.HibernateFactory;
import com.initseven.model.Producto;



public class ProductoDAOImpl implements ProductoDAO {

	@Override
	public List<Producto> getProductos() {
		
		List<Producto> aulas = new ArrayList<Producto>();
		
		SessionFactory sf = HibernateFactory.getSessionFactory();
		Session session = sf.getCurrentSession();
		Transaction tr = null;
		
		try {
			
			tr = session.beginTransaction();
			
			aulas = session.createQuery("select a from Aula a", Producto.class).getResultList();
			
			
		} catch(Exception ex) {
			
			if(tr != null) {
				tr.rollback();
			}
			
			aulas = null;
			ex.printStackTrace();
			
		}finally {
			
			session.close();
			sf.close();
			
		}
		
		
		return aulas;
	}

	@Override
	public Producto getProductoById(int id) {
		Producto aula = new Producto();
		SessionFactory sf = HibernateFactory.getSessionFactory();
		Session session = sf.getCurrentSession();
		Transaction tr = null;
		
		try {
			
			tr = session.beginTransaction();
			aula = session.get(Producto.class, id);
			
		}catch(Exception ex) {
			if(tr != null) {
				tr.rollback();
			}
			aula = null;
			ex.printStackTrace();
			
		}finally {
			session.close();
			sf.close();
		}
		
		return aula;
	}

	@Override
	public boolean insertProducto(int id, String nombre, int capacidad, String descripcion) {
		boolean transactionStatus = false;
		
		SessionFactory sf = HibernateFactory.getSessionFactory();
		Session session = sf.getCurrentSession();
		Transaction tr = null;
		
		try {
			
			tr = session.beginTransaction();
			
			Producto prod = new Producto();
			prod.setCapacidad(capacidad);
			prod.setDescripcion(descripcion);
			prod.setNombre(nombre);
			prod.setNumproducto(id);
			
			session.save(prod);
			tr.commit();
			
			
			transactionStatus = true;
			
			
		}catch(Exception ex) {
			if(tr != null) {
				tr.rollback();
			}
			
			ex.printStackTrace();
			
		}finally {
			session.close();
			sf.close();
		}
		
		
		return transactionStatus;
	}

	@Override
	public boolean updateProducto(int id, String nombre, int capacidad, String descripcion) {
		boolean status = false;
		SessionFactory sf = HibernateFactory.getSessionFactory();
		Session session = sf.getCurrentSession();
		Transaction tr = null;
		
		try {
			
			tr = session.beginTransaction();
			
			Producto aula = session.get(Producto.class, id);
			aula.setCapacidad(capacidad);
			aula.setDescripcion(descripcion);
			aula.setNombre(nombre);
			
			session.update(aula);
			tr.commit();
			
			
			status = true;
			
			
		}catch(Exception ex) {
			if(tr != null) {
				tr.rollback();
			}
			
			ex.printStackTrace();
			
		}finally {
			session.close();
			sf.close();
		}
		
		return status;
	}

	@Override
	public boolean deleteProducto(int id) {
		boolean status = false;
		SessionFactory sf = HibernateFactory.getSessionFactory();
		Session session = sf.getCurrentSession();
		Transaction tr = null;
		
		try {
			
			tr = session.beginTransaction();
			
			Producto aula = session.get(Producto.class, id);
						
			session.delete(aula);
			tr.commit();
			
			
			status = true;
			
			
		}catch(Exception ex) {
			if(tr != null) {
				tr.rollback();
			}
			
			ex.printStackTrace();
			
		}finally {
			session.close();
			sf.close();
		}
		
		return status;
	}
	
	
	
	
	
}
