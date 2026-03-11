package com.example.conflicttracker.controller.web;

import com.example.conflicttracker.model.Event;
import com.example.conflicttracker.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(EventControllerWeb.EVENT)
public class EventControllerWeb {
    public static final String EVENT = "/web/event";

    @Autowired
    private EventService eventService;

    @GetMapping
    public String llistaConflict(Model model) {
        List<Event> events = eventService.obtenerTodos();

        model.addAttribute("llistaEvents", events);
        model.addAttribute("prova", "Holaaaa text de prova");

        return "event-list";
    }

}
