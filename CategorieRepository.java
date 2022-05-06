package com.example.produits.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.produits.entities.Categorie;
@RepositoryRestResource(path = "rest")
public interface CategorieRepository extends JpaRepository<Categorie, Long> {

	

}
