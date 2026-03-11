package com.example.conflicttracker.controller.web;


import com.example.conflicttracker.model.Faction;
import com.example.conflicttracker.service.FactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(FactionControllerWeb.FACTION)
public class FactionControllerWeb {
    public static final String FACTION = "/web/faction";

    @Autowired
    private FactionService factionService;

    @GetMapping
    public String llistaConflict(Model model) {
        List<Faction> factions = factionService.obtenerTodos();

        model.addAttribute("llistaFactions", factions);
        model.addAttribute("prova", "Holaaaa text de prova");

        return "faction-list";
    }

}
