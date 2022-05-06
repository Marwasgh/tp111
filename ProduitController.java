package com.example.produits.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.produits.entities.Categorie;
import com.example.produits.entities.Produit;
import com.example.produits.service.CategorieService;
import com.example.produits.service.ProduitService;

@Controller
public class ProduitController {
	@Autowired
	ProduitService produitService;
	@Autowired
	CategorieService categorieService;
	
	@RequestMapping("/showCreate")
	public String showCreate(ModelMap modelMap)
	{
	modelMap.addAttribute("produit", new Produit());
	modelMap.addAttribute("mode", "new");
	List<Categorie> cats = categorieService.getAllCategories();

	modelMap.addAttribute("categories", cats);
	return "formProduit";
	}
	@RequestMapping("/saveProduit")
	public String saveProduit(@Valid Produit produit,BindingResult bindingResult)
	{
	if (bindingResult.hasErrors()) return "formProduit";
	
	produitService.saveProduit(produit);
	return "redirect:/ListeProduits";
	}
	@RequestMapping("/ListeProduits")
	public String listeProduits(ModelMap modelMap,

	@RequestParam (name="page",defaultValue = "0") int page,
	@RequestParam (name="size", defaultValue = "2") int size)

	{
	Page<Produit> prods = produitService.getAllProduitsParPage(page, size);
	modelMap.addAttribute("produits", prods);

	modelMap.addAttribute("pages", new int[prods.getTotalPages()]);

	modelMap.addAttribute("currentPage", page);
	
	
	
	return "listeProduits";
	}
	
	@RequestMapping("/supprimerProduit")
	public String supprimerProduit(@RequestParam("id") Long id,

	ModelMap modelMap,
	@RequestParam (name="page",defaultValue = "0") int page,
	@RequestParam (name="size", defaultValue = "2") int size)

	{
	produitService.deleteProduitById(id);
	Page<Produit> prods = produitService.getAllProduitsParPage(page,

	size);

	modelMap.addAttribute("produits", prods);
	modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
	modelMap.addAttribute("currentPage", page);
	modelMap.addAttribute("size", size);
	return "listeProduits";
	}
	
	@RequestMapping("/modifierProduit")
	public String editerProduit(@RequestParam("id") Long id,ModelMap modelMap)
	{
		Produit p= produitService.getProduit(id);
		modelMap.addAttribute("produit", p);
		modelMap.addAttribute("mode", "edit");
		
		List<Categorie> cats = categorieService.getAllCategories();

		modelMap.addAttribute("categories", cats);
		return "formProduit";
	}
	@RequestMapping("/updateProduit")
	public String updateProduit(@ModelAttribute("produit") Produit produit,
	@RequestParam("date") String date,ModelMap modelMap) throws ParseException

{
//conversion de la date

SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
Date dateCreation = dateformat.parse(String.valueOf(date));
produit.setDateCreation(dateCreation);
produitService.updateProduit(produit);
List<Produit> prods = produitService.getAllProduits();
modelMap.addAttribute("produits", prods);
return "listeProduits";

}

	
	@RequestMapping("/showCreat")
	public String showCreat(ModelMap modelMap)
	{
		modelMap.addAttribute("categorie", new Categorie());
		modelMap.addAttribute("mode", "new");
		return "formCategorie";
	}
	
	
	@RequestMapping("/saveCategorie")
	public String saveCategorie(@Valid Categorie categorie,
	BindingResult bindingResult)
	{
		if (bindingResult.hasErrors()) return "formCategorie";
		 
		categorieService.saveCategorie(categorie);
		return "redirect:/listeCategories";
	}
	
	
	@RequestMapping("/listeCategories")
	public String listeCategorie(ModelMap modelMap,
	@RequestParam (name="page",defaultValue = "0") int page,
	@RequestParam (name="size", defaultValue = "5") int size)
	{
	Page<Categorie> cat = categorieService.getAllCategorieParPage(page, size);
	modelMap.addAttribute("categories", cat);
	 modelMap.addAttribute("pages", new int[cat.getTotalPages()]);
	modelMap.addAttribute("currentPage", page);
	modelMap.addAttribute("size", size);
	return "listeCategories"; 
	}
	
	
	@RequestMapping("/supprimerCategorie")
	public String supprimerCategories(@RequestParam("id") Long id,
	ModelMap modelMap,
	@RequestParam (name="page",defaultValue = "0") int page,
	@RequestParam (name="size", defaultValue = "3") int size)
	{
		categorieService.deleteCategorieById(id);
	Page<Categorie> cat = categorieService.getAllCategorieParPage(page, size);
	modelMap.addAttribute("categories", cat);
	modelMap.addAttribute("pages", new int[cat.getTotalPages()]);
	modelMap.addAttribute("currentPage", page);
	modelMap.addAttribute("size", size);
	return "redirect:/listeCategories";
	}
	
	@RequestMapping("/modifierCategorie")
	public String editerCategorie(@RequestParam("id") Long id,ModelMap modelMap)
	{
	Categorie c= categorieService.getCategorie(id);
	modelMap.addAttribute("categorie", c);
	modelMap.addAttribute("mode", "edit");
	return "formCategorie";
	}

	@RequestMapping("/updateCategorie")
	public String updateCategorie(@ModelAttribute("categorie") Categorie categorie,
	
	ModelMap modelMap) throws ParseException 
	{
	 
		categorieService.updateCategorie(categorie);
	 List<Categorie> cat = categorieService.getAllCategories();
	 modelMap.addAttribute("categories", cat);
	 
	    
	return "listeCategories";
	
}

}