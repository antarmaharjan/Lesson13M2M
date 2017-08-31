package me.ratna.springboot13.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import me.ratna.springboot13.models.Actor;
import me.ratna.springboot13.models.Movie;
import me.ratna.springboot13.repositories.ActorRepo;
import me.ratna.springboot13.repositories.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {
    @Autowired
    MovieRepo movieRepo;
    @Autowired
    ActorRepo actorRepo;
    @RequestMapping("/")
    public String showindex(Model model){
        model.addAttribute("actorlist",actorRepo.findAll());
        model.addAttribute("movielist",movieRepo.findAll());
        return "index";
    }
    @GetMapping("/addmovie")
    public String addMovie(Model model){
        Movie movie = new Movie();
        model.addAttribute("movie", movie);
        return "addmovie";
    }
    @PostMapping("/addmovie")
    public String saveMovie(@ModelAttribute("movie") Movie movie){
        movieRepo.save(movie);
        return "index";
    }
    @GetMapping("/addactor")
    public String addActor(Model model){
        model.addAttribute("actor",new Actor());
        return "addactor";
    }
    @PostMapping("/addactor")
    public String saveActor(@ModelAttribute("movie") Movie movie){
        movieRepo.save(movie);
        return "index";
    }
    @GetMapping("/addactorstomovie/{id}")
    public String addActor(@PathVariable("id") long movieID, Model model)
    {
        model.addAttribute("mov",movieRepo.findOne(new Long(movieID)));
        model.addAttribute("actorList",actorRepo.findAll());
        return "movieaddactor";
    }


    @PostMapping("/actorstomovie/{id}")
    public String addActor(@ModelAttribute("mov") String mov, HttpServletRequest servletRequest)
    {
        return "index";

    }

    @GetMapping("/addactorstomovie")
    public String showAddActorsToMovie(Model model){
        model.addAttribute("actorlist",actorRepo.findAll());
        return "movieaddactor";
    }

    @PostMapping("/addactorstomovie")
    public String addActorsToMovie(@ModelAttribute("anActor") Actor a, @ModelAttribute("mov") Movie m,Model model)
    {
        model.addAttribute("actorList",actorRepo.findAll());
        model.addAttribute("movieList",movieRepo.findAll());
        return "index";
    }


    @PostMapping("/addmoviestoactor/{movid}")
    public String addMoviesToActor(@RequestParam("actors") String actorID, @PathVariable("movid") long movieID, Model model) {
        System.out.println("Actor ID" + actorID);
        System.out.println("Movie ID" + movieID);
        Movie m = movieRepo.findOne(new Long(movieID));
        m.addActor(actorRepo.findOne(new Long(actorID)));
        movieRepo.save(m);
        model.addAttribute("actorList", actorRepo.findAll());
        model.addAttribute("movieList", movieRepo.findAll());
        return "index";
    }

}
