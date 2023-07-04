package com.example.wix.controller;

import java.util.HashMap;
import java.util.List;

import com.example.wix.entities.TicketEntity;
import com.example.wix.service.TicketServiceFilter;
import com.example.wix.service.TicketServiceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketsController {
    @Autowired
    TicketServiceLoader ticketServiceLoader;
    @Autowired
    TicketServiceFilter ticketServiceFilter;

//    @GetMapping("/tickets")
//    public List<TicketEntity> getAllTickets() {
//        return ticketServiceLoader.loadEntities();
//    }

    @GetMapping("/tickets")
    public List<TicketEntity> getAllTicketsByFilter(@RequestParam(required = false) String title,
                                                    @RequestParam(required = false) Long fromTime,
                                                    @RequestParam(required = false) Long toTime,
                                                    @RequestParam(required = false) String content,
                                                    @RequestParam(required = false) String email) {
        HashMap<String, Object> filterMap = new HashMap<>();
        filterMap.put("toTime", toTime);
        filterMap.put("fromTime", fromTime);
        filterMap.put("title", title);
        filterMap.put("content", content);
        filterMap.put("email", email);
        return ticketServiceFilter.filterTickets(filterMap);
    }

}