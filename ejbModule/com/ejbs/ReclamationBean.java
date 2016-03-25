package com.ejbs;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Personne;
import model.Reclamation;

/**
 * Session Bean implementation class ReclamationBean
 */
@Stateless
public class ReclamationBean implements ReclamationBeanRemote, ReclamationBeanLocal {

    @PersistenceContext
    private EntityManager     em;
    private PersonneBeanLocal pb;

    public ReclamationBean() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void addReclamation( Reclamation reclamation ) {
        Personne per = pb.getPersonne( reclamation.getPersonne().getNom() );
        if ( per != null ) {
            em.persist( reclamation );
        } else {
            throw new RuntimeException( "Personne non trouver" );
        }
    }

    @Override
    public void deleteReclamation( int id ) {
        em.remove( this.getReclamation( id ) );
    }

    @Override
    public void updateReclamationV2( Reclamation reclamation ) {
        Reclamation rec = getReclamation( reclamation.getId() );
        rec.setCommentaire( reclamation.getCommentaire() );
        em.merge( rec );

    }

    @Override
    public Reclamation getReclamation( int id ) {
        Reclamation rec = em.find( Reclamation.class, id );
        if ( rec == null )
            throw new RuntimeException( "reclamation non touver" );
        return rec;
    }

    @Override
    public List<Reclamation> getAllReclamation() {
        List<Reclamation> reclamations = null;
        Query q = em.createNamedQuery( "Reclamation.findAll" );

        reclamations = q.getResultList();
        return reclamations;
    }

    @Override
    public List<Reclamation> getAllReclamationByPersonne( Personne personne ) {
        String sql = "FROM Reclamation where personne.nom = :nom";
        List<Reclamation> reclamations = null;
        Query q = em.createQuery( sql ).setParameter( "nom", personne.getNom() );
        reclamations = q.getResultList();
        return reclamations;
    }

}
