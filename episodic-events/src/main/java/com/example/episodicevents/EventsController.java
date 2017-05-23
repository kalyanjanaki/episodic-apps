package com.example.episodicevents;

import com.example.episodicevents.documents.Event;
import com.example.episodicevents.documents.EventMessage;
import com.example.episodicevents.documents.EventsRepository;
import com.example.episodicevents.documents.Progress;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
    private final RabbitTemplate rabbitTemplate;


    public EventsController(EventsRepository eventsRepository, RabbitTemplate rabbitTemplate) {
        this.eventsRepository = eventsRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/")
    public Event storeEvents(@RequestBody Event event){
        eventsRepository.save(event);
        if(event.getType().equals("progress")){
            EventMessage eventMessage = new EventMessage();
            eventMessage.setUserId(event.getUserId());
            eventMessage.setEpisodeId(event.getEpisodeId());
            eventMessage.setOffset(((Progress) event).getData().getOffset());
            eventMessage.setCreatedAt(event.getCreatedAt());
            rabbitTemplate.convertAndSend("my-exchange", "my-routing-key",eventMessage);
        }
        return event;
    }

    @GetMapping("/recent")
    public List<Event> getRecentEvents(){
        Pageable page = new PageRequest(0,20, new Sort(new Sort.Order(Sort.Direction.DESC,"createdAt")));
        return eventsRepository.findRecent(page);
    }
}
