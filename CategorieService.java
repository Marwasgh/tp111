package com.example.produits.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.produits.entities.Categorie;



public interface CategorieService {
	List<Categorie> getAllCategories();
	Categorie saveCategorie(Categorie c);
	Categorie updateCategorie(Categorie c);
	void deleteCategorie(Categorie c);
	void deleteCategorieById(Long id);
	Categorie getCategorie(Long id);
	Page<Categorie> getAllCategorieParPage(int i, int j);

}
