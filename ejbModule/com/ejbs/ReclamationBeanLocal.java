package com.ejbs;

import java.util.List;

import javax.ejb.Local;

import model.Personne;
import model.Reclamation;

@Local
public interface ReclamationBeanLocal {
    public void addReclamation( Reclamation reclamation );

    public void deleteReclamation( int id );

    // public void updateReclamation( Personne reclamation, String nomPersonne
    // );

    // public void updateReclamationV3( String oldName, String nomPersonne );

    public void updateReclamationV2( Reclamation reclamation );

    public Reclamation getReclamation( int id );

    public List<Reclamation> getAllReclamation();

    public List<Reclamation> getAllReclamationByPersonne( Personne personne );

}
