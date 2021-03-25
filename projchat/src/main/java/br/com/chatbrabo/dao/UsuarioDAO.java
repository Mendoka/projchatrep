package br.com.chatbrabo.dao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import br.com.chatbrabo.model.Usuario;

@Named
@RequestScoped
public class UsuarioDAO {
	
	public UsuarioDAO() {
		
	}

	public Usuario buscarDadosUS (String usuario, String senha) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projchat");
		EntityManager em = emf.createEntityManager();
		try{
			Usuario usr = (Usuario)em.createQuery("SELECT u FROM usuarios u "
					+ "WHERE u.usuario = :usuario AND u.senha = :senha")
					.setParameter("usuario", usuario.trim())
					.setParameter("senha", senha.trim())
					.getSingleResult();
			em.clear();
			em.close();
			emf.close();
			return usr;			
		}catch(NoResultException nre){
			Usuario user = new Usuario (null, null);
			return user;
			
		}
	}
	
	public Usuario buscarUsuario (String usuario) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projchat");
		EntityManager em = emf.createEntityManager();
		try{
			Usuario usr = (Usuario)em.createQuery("SELECT u FROM usuarios u "
					+ "WHERE u.usuario = :usuario")
					.setParameter("usuario", usuario.trim())
					.getSingleResult();
			em.clear();
			em.close();
			emf.close();
			return usr;			
		}catch(NoResultException nre){
			Usuario usr = new Usuario (null,null);
			return usr;
		}
	}
	
	public void gravarUsuario(Usuario usr) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projchat");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(usr);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
}
