package com.ejbs;

import java.util.List;

import javax.ejb.Remote;

import model.Personne;
import model.Reclamation;

@Remote
public interface ReclamationBeanRemote {

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
