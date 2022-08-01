package com.initseven.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.initseven.dao.UsuarioDAO;
import com.initseven.factory.HibernateFactory;
import com.initseven.model.Usuario;



public class UsuarioDAOImpl implements UsuarioDAO{

	@Override
	public List<Usuario> getUsuarios() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		SessionFactory sf = HibernateFactory.getSessionFactory();
		Session session = sf.getCurrentSession();
		Transaction tr = null;
		
		try {
			
			tr = session.beginTransaction();
			
			usuarios = session.createQuery("select u from Usuario u", Usuario.class).getResultList();
			
			
		} catch(Exception ex) {
			
			if(tr != null) {
				tr.rollback();
			}
			
			usuarios = null;
			ex.printStackTrace();
			
		}finally {
			
			session.close();
			sf.close();
			
		}
		
		
		return usuarios;
	}
	
	@Override
	public Usuario getUsuarioByUserAndPassword(String user, String pass) {
		Usuario usuario = new Usuario();
		
		SessionFactory sf = HibernateFactory.getSessionFactory();
		Session session = sf.getCurrentSession();
		Transaction tr = null;
		
		try {
			
			tr = session.beginTransaction();
			
			//List<Usuario> usuarios = session.createQuery("from Usuario u where u.email = '"+user+"'", Usuario.class).getResultList();
			
			Query query=session.createQuery("from Usuario u where u.email='"+user+"'");//here persistent class name is Emp  
			List<Usuario> usuarios=query.list(); 
			
			if(usuarios.size() > 0) {
				usuario = usuarios.get(0);
				System.out.println("----> "+usuario.getEmail()+" ----- "+usuario.getPassword());
				System.out.println("Total recuperado: "+usuarios.size());
			}
			else {
				usuario = null;
				System.out.println("No se han recuperado usuarios.");
			}
			
		} catch(Exception ex) {
			
			usuario = null;
			System.out.println(ex.getLocalizedMessage());
			
		}finally {
			
			session.close();
			sf.close();
		}
		
		return usuario;
	}
	

	@Override
	public Usuario getUsuarioById(int id) {
		Usuario usuario = new Usuario();
		SessionFactory sf = HibernateFactory.getSessionFactory();
		Session session = sf.getCurrentSession();
		Transaction tr = null;
		
		try {
			
			tr = session.beginTransaction();
			usuario = session.get(Usuario.class, id);
			
		}catch(Exception ex) {
			if(tr != null) {
				tr.rollback();
			}
			usuario = null;
			ex.printStackTrace();
			
		}finally {
			session.close();
			sf.close();
		}
		
		return usuario;
	}

	@Override
	public boolean insertUsuario(int id, String email, String nombre, String password, String perfil, String puesto,
			String username) {
boolean transactionStatus = false;
		
		SessionFactory sf = HibernateFactory.getSessionFactory();
		Session session = sf.getCurrentSession();
		Transaction tr = null;
		
		try {
			
			tr = session.beginTransaction();
			
			Usuario usuario = new Usuario();
			usuario.setEmail(email);
			usuario.setIdusuario(id);
			usuario.setNombre(nombre);
			usuario.setPassword(password);
			usuario.setPerfil(perfil);
			usuario.setPuesto(puesto);
			usuario.setUsername(username);
			
			
			session.save(usuario);
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
	public boolean updateUsuario(int id, String email, String nombre, String password, String perfil, String puesto,
			String username) {
		boolean status = false;
		SessionFactory sf = HibernateFactory.getSessionFactory();
		Session session = sf.getCurrentSession();
		Transaction tr = null;
		
		try {
			
			tr = session.beginTransaction();
			
			Usuario usuario = session.get(Usuario.class, id);
			usuario.setEmail(email);
			usuario.setNombre(nombre);
			usuario.setPassword(password);
			usuario.setPerfil(perfil);
			usuario.setPuesto(puesto);
			usuario.setUsername(username);
			
			
			session.update(usuario);
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
	public boolean deleteUsuario(int id) {
		boolean status = false;
		SessionFactory sf = HibernateFactory.getSessionFactory();
		Session session = sf.getCurrentSession();
		Transaction tr = null;
		
		try {
			
			tr = session.beginTransaction();
			
			Usuario usuario = session.get(Usuario.class, id);
						
			session.delete(usuario);
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
