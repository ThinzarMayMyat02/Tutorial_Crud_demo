package com.sip.TutoriaCRUDTest.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sip.TutoriaCRUDTest.entity.Tutorial;
import com.sip.TutoriaCRUDTest.services.TutorialService;

@Controller
public class TutorialController {

    @Autowired
    private TutorialService tutorialService;

    @GetMapping("/tutorials")
    public String tutorialList(Model model,@Param("keyword") String keyword) {
        try {
            List<Tutorial> tutorials = new ArrayList<Tutorial>();

            if(keyword == null){
                tutorialService.findAll().forEach(tutorials::add);
            }
            else{
                tutorialService.findByTitleContainingIgnoreCase(keyword).forEach(tutorials::add);
                model.addAttribute("keyword", keyword);
            }
            model.addAttribute("tutorials", tutorials);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "tutorials";
    }

    @GetMapping("tutorials/add")
    public String add(Model model) {
        Tutorial tutorial = new Tutorial();
        tutorial.setPublished(true);

        model.addAttribute("tutorial", tutorial);
        model.addAttribute("pageTitle", "Create new Tutorial");
        return "tutorial_form";
    }

    @PostMapping("tutorials/create/")
    public String CreateTutorial(Tutorial tutorial, RedirectAttributes redir) {
        try {
            tutorialService.saveTutorial(tutorial);
            redir.addFlashAttribute("message", "The tutorial has been added successfully.");
        } catch (Exception e) {
            redir.addAttribute("message", e.getMessage());
        }
        return "redirect:/tutorials";
    }

    @GetMapping("tutorials/delete/{id}")
    public String deleteTutorial(@PathVariable("id") Integer id, Model model, RedirectAttributes redir) {
        try {
            tutorialService.deleteById(id);
            redir.addFlashAttribute("message", "The Tutorial with id= " + id + " has been successfully deleted.");
        } catch (Exception e) {
            redir.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/tutorials";
    }

    @GetMapping("tutorials/{id}/published/{status}")
    public String updatePublishedStatus(@PathVariable("id") Integer id,
            @PathVariable("status") boolean published,
            Model model, RedirectAttributes redir) {
        try {
            tutorialService.updatePublishedStatus(id, published);
            String status = published ? "published" : "disabled";
            String message = "The tutorial id " + id + " has been Status: " + status;
            redir.addFlashAttribute("message", message);
        } catch (Exception e) {
            redir.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/tutorials";
    }

    @GetMapping("tutorials/edit/{id}")
    public String editTutorial(@PathVariable("id") Integer id, Model model, RedirectAttributes redir) {
        try {
            Tutorial tutorial = tutorialService.findById(id);
            model.addAttribute("tutorial", tutorial);
            model.addAttribute("pageTitle", "Edit tutorial (ID:" + id + ")");
            return "tutorial_form";
        } catch (Exception e) {
            redir.addFlashAttribute("message", e.getMessage());
            return "redirect:/tutorials";
        }
    }
    

    @GetMapping("/back")
    public String back(){
        return "redirect:/tutorials";
    }

}
