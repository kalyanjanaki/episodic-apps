package com.example.shows;

import org.springframework.web.bind.annotation.*;

/**
 * Created by trainer20 on 5/18/17.
 */
@RestController
@RequestMapping("/shows")
public class ShowsController {

    private final ShowsRepository showsRepository;

    public ShowsController(ShowsRepository showsRepository) {
        this.showsRepository = showsRepository;
    }

    @PostMapping
    public Show createShow(@RequestBody Show show){
        return showsRepository.save(show);
    }

    @GetMapping
    public Iterable<Show> getShows(){
        return showsRepository.findAll();
    }
}
