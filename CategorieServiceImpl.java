package com.example.produits.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.produits.entities.Categorie;
import com.example.produits.entities.Produit;
import com.example.produits.repos.CategorieRepository;



@Service
public class CategorieServiceImpl implements CategorieService {
	@Autowired
	CategorieRepository categorieRepository;

	@Override
	public List<Categorie> getAllCategories() {
		return categorieRepository.findAll();
	}

@Override
public Categorie saveCategorie(Categorie p) {
	return categorieRepository.save(p);
}

@Override
public Categorie updateCategorie(Categorie p) {
	return categorieRepository.save(p);

}

@Override
public void deleteCategorie(Categorie p) {
	categorieRepository.delete(p);

	
}

@Override
public void deleteCategorieById(Long id) {
	categorieRepository.deleteById(id);

	
}

@Override
public Categorie getCategorie(Long id) {
	return categorieRepository.findById(id).get();

}



@Override
public Page<Categorie> getAllCategorieParPage(int page, int size) {
	
	return categorieRepository.findAll(PageRequest.of(page, size));
}



	
}