package com.ejbs;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Personne;

/**
 * Session Bean implementation class PersonneBean
 */
@Stateless
public class PersonneBean implements PersonneBeanRemote, PersonneBeanLocal {

    @PersistenceContext
    private EntityManager em;

    // private static final String SQL_GET_ALL_PERSONNES = "SELECT p FROM
    // personne p";

    public PersonneBean() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void addPersonne( Personne personne ) {
        em.persist( personne );

    }

    @Override
    public void deletePersonne( String nomPersone ) {
        // Personne p = getPersonne( nomPersone );
        em.remove( this.getPersonne( nomPersone ) );

    }

    @Override
    public void updatePersonne( Personne personne, String nomPersonne ) {
        Personne p = getPersonne( personne.getNom() );
        p.setNom( nomPersonne );
        em.merge( p );
    }

    @Override
    public void updatePersonneV2( Personne personne ) {
        em.merge( personne );
    }

    @Override
    public void updatePersonneV3( String oldName, String nomPersonne ) {
        Query q = em.createQuery( "UPDATE Personne p SET nom=:t where nom=:s" );
        q.setParameter( "t", nomPersonne );
        q.setParameter( "s", oldName );
        int t = q.executeUpdate();
    }

    @Override
    public Personne getPersonne( String nomPersone ) {
        Personne p = em.find( Personne.class, nomPersone );
        if ( p == null )
            throw new RuntimeException( "Personne Introuvable" );
        return p;
    }

    @Override
    public List<Personne> getAllPersonne() {
        List<Personne> personnes = null;
        Query req = em.createNamedQuery( "Personne.findAll" );
        personnes = req.getResultList();
        return personnes;
    }

}
