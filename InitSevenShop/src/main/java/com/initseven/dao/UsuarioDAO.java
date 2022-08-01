package com.initseven.dao;

import java.util.List;

import com.initseven.model.Usuario;


public interface UsuarioDAO {

	public List<Usuario> getUsuarios();
	
	public Usuario getUsuarioById(int id);
	
	public boolean insertUsuario(int id, String email, String nombre, String password, String perfil, String puesto, String username);
	
	public boolean updateUsuario(int id, String email, String nombre, String password, String perfil, String puesto, String username);
	
	public boolean deleteUsuario(int id);

	public Usuario getUsuarioByUserAndPassword(String user, String pass);
}
