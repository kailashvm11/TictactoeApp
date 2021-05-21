package com.tictactoe;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TictactoeAppTest {

    Logger gameLogger = (Logger) LoggerFactory.getLogger(TictactoeGame.class);
    TictactoeGame game;
    ListAppender<ILoggingEvent> listAppender;

    @BeforeEach
    void setUp() {

        listAppender = new ListAppender<>();
        listAppender.start();
        gameLogger.addAppender(listAppender);
        InputHandler mockInputHandler = mock(InputHandler.class);
        when(mockInputHandler.readInput())
                .thenReturn("2,3")
                .thenReturn("1,1")
                .thenReturn("2,2");
        game = new TictactoeGame(mockInputHandler);
    }

    @Test
    public void shouldAnswerWithTrue() {
        TictactoeApp tictactoeApp = new TictactoeApp();
        assertNotNull( tictactoeApp );
    }

    @Test
    void shouldDisplayStartMessage() {
        game.start();
        List<ILoggingEvent> logsList = listAppender.list;
        assertEquals("Starting a new Tic-tac-toe Game", logsList.get(0)
                .getMessage());

    }

    @Test
    void shouldDisplayEmptyGrid() {
        game.start();
        List<ILoggingEvent> logsList = listAppender.list;
        assertEquals("---------", logsList.get(1).getMessage());
        assertEquals("|       |", logsList.get(2).getMessage());
        assertEquals("|       |", logsList.get(3).getMessage());
        assertEquals("|       |", logsList.get(4).getMessage());
        assertEquals("---------", logsList.get(5).getMessage());
    }

    @Test
    void shouldUpdateGrid() {
        game.start();
        char[][] grid = game.getGrid();
        assertEquals('X', grid[1][2]);
    }

    @Test
    void shouldDisplayGridAfterUpdate() {
        game.start();
        List<ILoggingEvent> logsList = listAppender.list;
        Assertions.assertThat(listAppender.list)
                .extracting(ILoggingEvent::getFormattedMessage)
                .contains("|     X |");
    }

    @AfterEach
    void tearDown() {
        listAppender.stop();
        listAppender.list.clear();
    }
}
