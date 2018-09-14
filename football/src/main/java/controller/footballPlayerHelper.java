package controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.footballPlayer;

public class footballPlayerHelper {

	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("football");

	public void cleanUp() {
		emfactory.close();
	}

	public void insertTeam(footballPlayer fp) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(fp);
		em.getTransaction().commit();
		em.close();
	}

	public void deleteTeam(footballPlayer toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();

		TypedQuery<footballPlayer> typedQuery = em.createQuery("select fb from footballPlayer fb where fb.name = :selectedName and fb.team = :selectedTeam",footballPlayer.class);
		typedQuery.setParameter("selectedName", toDelete.getName());
		typedQuery.setParameter("selectedTeam", toDelete.getTeam());
		typedQuery.setMaxResults(1);
		footballPlayer result = typedQuery.getSingleResult();
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	public List<footballPlayer> searchForTeamByTeam(String teamName) {  
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<footballPlayer> typedQuery = em.createQuery("select fb from footballPlayer fb where fb.team = :selectedTeam", footballPlayer.class);
		typedQuery.setParameter("selectedTeam", teamName);
		List<footballPlayer> foundName = typedQuery.getResultList();
		em.close();
		return foundName;
	}

	public List<footballPlayer> searchForTeamByName(String name) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<footballPlayer> typedQuery = em.createQuery("select fb from footballPlayer fb where fb.name = :selectedName", footballPlayer.class);
		typedQuery.setParameter("selectedName", name);
		List<footballPlayer> foundTeams = typedQuery.getResultList();
		em.close();
		return foundTeams;
	}

	public List<footballPlayer> searchForTeamByOwner(String owner) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<footballPlayer> typedQuery = em.createQuery("select fb from footballPlayer fb where fb.owner = :selectedOwner", footballPlayer.class);
		typedQuery.setParameter("selectedOwner", owner);
		List<footballPlayer> foundOwner = typedQuery.getResultList();
		em.close();
		return foundOwner;
	}
	
	public footballPlayer searchForTeamById(int id) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		footballPlayer found = em.find(footballPlayer.class, id);
		em.close();
		return found;
	}

	public List<footballPlayer> showAllTeams() {
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<footballPlayer> typedQuery = em.createQuery("select fb from footballPlayer fb", footballPlayer.class);
		List<footballPlayer> allTeams = typedQuery.getResultList();
		em.close();
		return allTeams;
	}

	public void updateTeam(footballPlayer toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
}