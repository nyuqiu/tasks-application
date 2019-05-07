package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {
    @Autowired
    private TrelloMapper trelloMapper;

    private List<TrelloBoard> trelloBoards;
    private List<TrelloBoardDto> trelloBoardDtos;
    private List<TrelloList> trelloLists;
    private List<TrelloListDto> trelloListDtos;
    private TrelloCard trelloCard;
    private TrelloCardDto trelloCardDto;

    @Before
    public void setUp() {
        TrelloBoard trelloBoard1 = new TrelloBoard("test1", "test1", new ArrayList<>());
        TrelloBoard trelloBoard2 = new TrelloBoard("test2", "test2", new ArrayList<>());
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("test3", "test3", new ArrayList<>());
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("test5", "test5", new ArrayList<>());
        TrelloList trelloList1 = new TrelloList("test5", "test5", false);
        TrelloList trelloList2 = new TrelloList("test6", "test6", false);
        TrelloListDto trelloListDto1 = new TrelloListDto("test7", "test7", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("test7", "test7", false);
        trelloCard = new TrelloCard("test8", "test8", "test8", "test8");
        trelloCardDto = new TrelloCardDto("test9", "test9", "test9", "test9");
        trelloBoards = new ArrayList<>(Arrays.asList(trelloBoard1, trelloBoard2));
        trelloBoardDtos = new ArrayList<>(Arrays.asList(trelloBoardDto1, trelloBoardDto2));
        trelloLists = new ArrayList<>(Arrays.asList(trelloList1, trelloList2));
        trelloListDtos = new ArrayList<>(Arrays.asList(trelloListDto1, trelloListDto2));
    }

    @Test
    public void mapToBoards() {
        //When
        List<TrelloBoard> mappedBoard = trelloMapper.mapToBoards(trelloBoardDtos);
        //Then
        assertEquals(mappedBoard.size(), trelloBoardDtos.size());
        assertEquals(mappedBoard.get(0).getName(), trelloBoardDtos.get(0).getName());
        assertEquals(mappedBoard.get(1).getName(), trelloBoardDtos.get(1).getName());
    }

    @Test
    public void mapToBoardsDto() {
        //When
        List<TrelloBoardDto> mappedBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        assertEquals(mappedBoardDtos.size(), trelloBoards.size());
        assertEquals(mappedBoardDtos.get(0).getName(), trelloBoards.get(0).getName());
        assertEquals(mappedBoardDtos.get(1).getName(), trelloBoards.get(1).getName());
    }

    @Test
    public void mapToList() {
        //When
        List<TrelloList> mappedLists = trelloMapper.mapToList(trelloListDtos);
        //Then
        assertEquals(mappedLists.size(), trelloListDtos.size());
        assertEquals(mappedLists.get(0).getName(), trelloListDtos.get(0).getName());
        assertEquals(mappedLists.get(1).getName(), trelloListDtos.get(1).getName());
    }

    @Test
    public void mapToListDto() {
        //When
        List<TrelloListDto> mappedListDtos = trelloMapper.mapToListDto(trelloLists);
        //Then
        assertEquals(mappedListDtos.size(), trelloLists.size());
        assertEquals(mappedListDtos.get(0).getName(), trelloLists.get(0).getName());
        assertEquals(mappedListDtos.get(1).getName(), trelloLists.get(1).getName());
    }

    @Test
    public void mapToCardDto() {
        //When
        TrelloCardDto mappedCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then


    }

    @Test
    public void mapToCard() {
        //When
        TrelloCard mappedCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals(mappedCard.getName(), trelloCardDto.getName());
    }
}