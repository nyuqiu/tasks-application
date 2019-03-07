package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @GetMapping
    public void getTrelloBoards(){

        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        trelloBoards = trelloBoards.stream()
                .filter(n -> n.getId()!=null&&n.getName()!=null)
                .filter(n -> n.getName().contains("Kodilla"))
                .collect(Collectors.toList());

        trelloBoards.forEach(trelloBoardDto -> System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName()));

    }
}