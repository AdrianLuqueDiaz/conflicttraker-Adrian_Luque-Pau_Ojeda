package com.example.conflicttracker.controller.web;

import com.example.conflicttracker.model.Country;
import com.example.conflicttracker.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(CountryControllerWeb.COUNTRY)
public class CountryControllerWeb {
    public static final String COUNTRY = "/web/country";

    @Autowired
    private CountryService countryService;

    @GetMapping
    public String llistaConflict(Model model) {
        List<Country> countries = countryService.obtenerTodos();

        model.addAttribute("llistaCountries", countries);
        model.addAttribute("prova", "Holaaaa text de prova");

        return "country-list";
    }

}
