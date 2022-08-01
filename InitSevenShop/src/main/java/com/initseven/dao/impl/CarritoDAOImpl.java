package com.initseven.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.initseven.dao.CarritoDAO;
import com.initseven.factory.HibernateFactory;
import com.initseven.model.Carrito;
import com.initseven.model.Producto;
import com.initseven.model.Usuario;


public class CarritoDAOImpl implements CarritoDAO {

	@Override
	public List<Carrito> getCarrito() {
		List<Carrito> reservas = new ArrayList<Carrito>();
		
		SessionFactory sf = HibernateFactory.getSessionFactory();
		Session session = sf.getCurrentSession();
		Transaction tr = null;
		
		try {
			
			tr = session.beginTransaction();
			
			reservas = session.createQuery("select r from Carrito r", Carrito.class).getResultList();
			
			
		} catch(Exception ex) {
			
			if(tr != null) {
				tr.rollback();
			}
			
			reservas = null;
			ex.printStackTrace();
			
		}finally {
			
			session.close();
			sf.close();
			
		}
		
		
		return reservas;
	}

	@Override
	public Carrito getCarritoById(int id) {
		Carrito carro = new Carrito();
		SessionFactory sf = HibernateFactory.getSessionFactory();
		Session session = sf.getCurrentSession();
		Transaction tr = null;
		
		try {
			
			tr = session.beginTransaction();
			carro = session.get(Carrito.class, id);
			
		}catch(Exception ex) {
			if(tr != null) {
				tr.rollback();
			}
			carro = null;
			ex.printStackTrace();
			
		}finally {
			session.close();
			sf.close();
		}
		
		return carro;
	}

	@Override
	public boolean insertCarrito(int id, Usuario usuario, Producto prod) {
		boolean status = false;
		SessionFactory sf = HibernateFactory.getSessionFactory();
		Session session = sf.getCurrentSession();
		Transaction tr = null;
		
		try {
			
			tr = session.beginTransaction();
			
			Carrito carro = session.get(Carrito.class, id);
			carro.setProducto(prod);
			carro.setUsuario(usuario);
			
			session.save(carro);
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
	public boolean updateCarrito(int id, Usuario usuario, Producto prod) {
		boolean status = false;
		SessionFactory sf = HibernateFactory.getSessionFactory();
		Session session = sf.getCurrentSession();
		Transaction tr = null;
		
		try {
			
			tr = session.beginTransaction();
			
			Carrito carro = session.get(Carrito.class, id);
			carro.setProducto(prod);
			carro.setUsuario(usuario);
			
			session.update(carro);
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
	public boolean deleteCarrito(int id) {
		boolean status = false;
		SessionFactory sf = HibernateFactory.getSessionFactory();
		Session session = sf.getCurrentSession();
		Transaction tr = null;
		
		try {
			
			tr = session.beginTransaction();
			
			Carrito carro = session.get(Carrito.class, id);
						
			session.delete(carro);
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
