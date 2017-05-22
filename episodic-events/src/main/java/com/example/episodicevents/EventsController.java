package com.example.episodicevents;

import com.example.episodicevents.documents.Event;
import com.example.episodicevents.documents.EventsRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by trainer20 on 5/21/17.
 */
@RestController
public class EventsController {

    private final EventsRepository eventsRepository;


    public EventsController(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @PostMapping("/")
    public Event storeEvents(@RequestBody Event event){
        eventsRepository.save(event);
        return event;
    }

    @GetMapping("/recent")
    public List<Event> getRecentEvents(){
        Pageable page = new PageRequest(0,20, new Sort(new Sort.Order(Sort.Direction.DESC,"createdAt")));
        return eventsRepository.findRecent(page);
    }
}
