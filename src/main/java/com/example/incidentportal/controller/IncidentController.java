package com.example.incidentportal.controller;

import com.example.incidentportal.model.Incident;
import com.example.incidentportal.service.IncidentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/incidents")
public class IncidentController {

    private final IncidentService incidentService;

    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping
    public String listIncidents(Model model) {
        model.addAttribute("incidents", incidentService.getAllIncidents());
        return "list-incidents";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("incident", new Incident());
        return "add-incident";
    }

    @PostMapping("/add")
    public String addIncident(@ModelAttribute Incident incident) {
        incidentService.saveIncident(incident);
        return "redirect:/incidents";
    }

    @GetMapping("/edit/{id}")
    public String editIncident(@PathVariable Long id, Model model) {
        model.addAttribute("incident", incidentService.getIncidentById(id));
        return "edit-incident";
    }

    @PostMapping("/update/{id}")
    public String updateIncident(@PathVariable Long id, @ModelAttribute Incident incident) {
        incident.setId(id);
        incidentService.saveIncident(incident);
        return "redirect:/incidents";
    }

    @GetMapping("/delete/{id}")
    public String deleteIncident(@PathVariable Long id) {
        incidentService.deleteIncident(id);
        return "redirect:/incidents";
    }

    @GetMapping("/view/{id}")
    public String viewIncident(@PathVariable Long id, Model model) {
        model.addAttribute("incident", incidentService.getIncidentById(id));
        return "view-incident";
    }
}