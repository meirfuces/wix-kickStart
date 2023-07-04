package com.example.wix.controller;

import com.example.wix.entities.TicketEntity;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest

class TicketsControllerTest {
@Autowired
TicketsController ticketsController;
    @Test
    void getTicketByToTitle(){
        String title = "setting up my login page profile";
        List<TicketEntity> ticketEntityList =ticketsController.getAllTicketsByFilter(title, null, null,null,null);
        ticketEntityList.stream().forEach(ticketEntity -> {
            Assert.assertTrue(ticketEntity.getTitle().contains(title));
        });
    }
    @Test
    void getTicketByToTime(){
        Long fromTime =1542111235545L;
        List<TicketEntity> ticketEntityList =ticketsController.getAllTicketsByFilter(null, fromTime, null,null,null);
        Optional<TicketEntity> ticketEntity1= ticketEntityList.stream().filter(ticketEntity -> ticketEntity.getCreationTime()<=fromTime).findAny();
        Assertions.assertFalse(ticketEntity1.isPresent());
    }
    @Test
    void getTicketByFromTime(){
        Long toTime =1542111235545L;
        List<TicketEntity> ticketEntityList =ticketsController.getAllTicketsByFilter(null, null, toTime,null,null);
        Optional<TicketEntity> ticketEntity1= ticketEntityList.stream().filter(ticketEntity -> ticketEntity.getCreationTime()>=toTime).findAny();
        Assert.assertEquals(ticketEntity1.isPresent(),false);
    }
    @Test
    void getTicketByFromTimeTo(){
        Long fromTime =1542111235545L;
        Long toTime =1542111235545L;
        List<TicketEntity> ticketEntityList =ticketsController.getAllTicketsByFilter(null, fromTime, toTime,null,null);
        Optional<TicketEntity> ticketEntity1= ticketEntityList.stream().filter(ticketEntity -> ticketEntity.getCreationTime()==toTime).findAny();
        Assert.assertEquals(ticketEntity1.isPresent(),false);
    }

    @Test
    void getTicketByToContent(){
        String content = "want to ask";
        List<TicketEntity> ticketEntityList =ticketsController.getAllTicketsByFilter(null, null, null,content,null);
        ticketEntityList.forEach(ticketEntity -> Assert.assertTrue(ticketEntity.getContent().contains(content)));
    }

    @Test
    void getTicketByEmail(){
        String email = "cube@zo.lv";
        List<TicketEntity> ticketEntityList =ticketsController.getAllTicketsByFilter(null, null, null,null,"cube@zo.lv");
        ticketEntityList.forEach(ticketEntity -> Assert.assertEquals(ticketEntity.getUserEmail(),email));
    }
    @Test
    void getTicketByFields(){
        String email = "cube@zo.lv";
        String content = "want to ask";

        List<TicketEntity> ticketEntityList =ticketsController.getAllTicketsByFilter("setting up my login page profile", null, null,         content ,"cube@zo.lv");
        ticketEntityList.forEach(ticketEntity -> {
            Assert.assertEquals(ticketEntity.getUserEmail(),email);
            Assert.assertEquals(ticketEntity.getTitle(),"setting up my login page profile");
            Assert.assertTrue(ticketEntity.getContent().contains(content));
        });
    }
}