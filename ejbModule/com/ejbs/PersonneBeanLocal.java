package com.ejbs;

import java.util.List;

import javax.ejb.Local;

import model.Personne;

@Local
public interface PersonneBeanLocal {

    public void addPersonne( Personne personne );

    public void deletePersonne( String nomPersone );

    public void updatePersonne( Personne personne, String nomPersonne );

    public void updatePersonneV3( String oldName, String nomPersonne );

    public void updatePersonneV2( Personne personne );

    public Personne getPersonne( String nomPersone );

    public List<Personne> getAllPersonne();

}
