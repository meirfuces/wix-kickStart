package com.example.wix.service;

import com.example.wix.entities.TicketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
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
            ticketEntityList = filterTicketByTitle(filterMap,ticketEntityList);
            ticketEntityList =filterTicketsByTime(filterMap, ticketEntityList);
            ticketEntityList = filterTicketByContent(filterMap, ticketEntityList);
            ticketEntityList = filterTicketByEmail(filterMap, ticketEntityList);
        return ticketEntityList;
        }

    private List<TicketEntity> filterTicketByTitle(HashMap<String, Object> filterMap, List<TicketEntity> ticketEntityList ) {
        if (filterMap.get("title")!=null) {
            String title= (String) filterMap.get("title");
            Predicate<TicketEntity> contentPredicate = ticketEntity -> ticketEntity.getTitle().contains(title);
            return filterTicketByField(ticketEntityList, contentPredicate);
        }
        return ticketEntityList;
    }

    private List<TicketEntity> filterTicketByContent(HashMap<String, Object> filterMap, List<TicketEntity> ticketEntityList) {
        if (filterMap.get("content")!=null) {
            String content = (String) filterMap.get("content");
            Predicate<TicketEntity> contentPredicate = ticketEntity -> ticketEntity.getContent().contains(content);
            return filterTicketByField(ticketEntityList, contentPredicate);
        }
        return ticketEntityList;
        }
    public List<TicketEntity> filterTicketByField(List<TicketEntity> ticketEntityList, Predicate<TicketEntity> predicate){
        return ticketEntityList.stream().filter(predicate).collect(Collectors.toList());

    }

    public List<TicketEntity> filterTicketsByTime(HashMap<String, Object> filterMap, List<TicketEntity> ticketEntityList){
        Long to= (Long)filterMap.get("toTime");
        Long from = (Long)filterMap.get("fromTime");
        if (from==null && to ==null ) {
            return ticketEntityList;
        }
        return ticketEntityList.stream().filter(ticketEntity -> isTicketWithinTime(ticketEntity,from,to)).collect(Collectors.toList());

    }
    public List<TicketEntity> filterTicketByEmail(HashMap<String, Object> filterMap, List<TicketEntity> ticketEntityList){
        if (filterMap.get("email")!=null) {
            String email = (String) filterMap.get("email");
            Predicate<TicketEntity> emailPredicate = ticketEntity -> ticketEntity.getUserEmail().contains(email);
            return filterTicketByField(ticketEntityList, emailPredicate);
        }
        return ticketEntityList;
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
    }


