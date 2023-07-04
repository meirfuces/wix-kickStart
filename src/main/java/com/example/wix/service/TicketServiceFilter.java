package com.example.wix.service;

import com.example.wix.entities.TicketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class TicketServiceFilter {
@Autowired
    TicketServiceLoader ticketServiceLoader;
    public List<TicketEntity> filterTickets(HashMap<String, Object> filterMap){
        List<TicketEntity> ticketEntityList = ticketServiceLoader.loadEntities();
        if (filterMap.get("title")!=null){
            ticketEntityList = filterTicketByTitle(ticketEntityList,(String) filterMap.get("title"));
        }
            ticketEntityList =filterTicketsByTime(ticketEntityList, (Long)filterMap.get("toTime"), (Long)filterMap.get("fromTime"));
        if (filterMap.get("content")!=null) {
            ticketEntityList = filterTicketByContent(ticketEntityList,(String) filterMap.get("content"));
        }
        if (filterMap.get("email")!=null) {
            ticketEntityList = filterTicketByEmail(ticketEntityList,(String) filterMap.get("email"));
        }
        return ticketEntityList;
        }

    private List<TicketEntity> filterTicketByTitle(List<TicketEntity> ticketEntityList, String title) {
        Predicate <TicketEntity> contentPredicate = ticketEntity -> ticketEntity.getTitle().equals(title);
        return filterTicketByField(ticketEntityList,title,contentPredicate);
    }

    private List<TicketEntity> filterTicketByContent(List<TicketEntity> ticketEntityList, String content) {
        Predicate <TicketEntity> contentPredicate = ticketEntity -> ticketEntity.getContent().equals(content);
        return filterTicketByField(ticketEntityList,content,contentPredicate);
    }
    public List<TicketEntity> filterTicketByField(List<TicketEntity> ticketEntityList, String title, Predicate<TicketEntity> predicate){
        return ticketEntityList.stream().filter(predicate).collect(Collectors.toList());

    }


    public List<TicketEntity> filterTicketsByTime( List<TicketEntity> ticketEntityList, Long to , Long from){
        if (from==null && to ==null ) {
            return ticketEntityList;
        }
        return ticketEntityList.stream().filter(ticketEntity -> isTicketWithinTime(ticketEntity,from,to)).collect(Collectors.toList());

    }
    public List<TicketEntity> filterTicketByEmail(List<TicketEntity> ticketEntityList, String email){
        Predicate <TicketEntity> emailPredicate = ticketEntity -> ticketEntity.getUserEmail().equals(email);
        return filterTicketByField(ticketEntityList,email,emailPredicate);
    }

    private boolean isTicketWithinTime(TicketEntity ticketEntity, Long from, Long to) {

        Long ticketTime =ticketEntity.getCreationTime();
        return isAfterFrom(ticketTime, from)&& isBeforeTo(ticketTime, to);
    }

    private boolean isAfterFrom(Long ticketTime, Long from) {
        if (from==null ){
            return true;
        }
        return from<=ticketTime;
    }

    private boolean isBeforeTo(long ticketTime, Long to){
        if (to==null ) {
            return true;
        }

        return ticketTime<=to;
    }
    private List<TicketEntity> isBeforeTo(List<TicketEntity> ticketList, long to) {
        return ticketList.stream().filter(ticket-> to>=ticket.getCreationTime()).collect(Collectors.toList());
    }
    }


