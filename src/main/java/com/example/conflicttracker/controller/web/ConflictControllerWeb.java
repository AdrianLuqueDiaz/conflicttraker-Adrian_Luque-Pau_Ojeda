package com.example.conflicttracker.controller.web;

import com.example.conflicttracker.model.Conflict;
import com.example.conflicttracker.service.ConflictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(ConflictControllerWeb.CONFLICT)
public class ConflictControllerWeb {
    public static final String CONFLICT = "/web/conflict";

    @Autowired
    private ConflictService conflictService;

    @GetMapping
    public String llistaConflict(Model model) {
        List<Conflict> conflict = conflictService.obtenerTodos();

        model.addAttribute("llistaConflicts", conflict);
        model.addAttribute("prova", "Holaaaa text de prova");

        return "conflict-list";
    }

}
