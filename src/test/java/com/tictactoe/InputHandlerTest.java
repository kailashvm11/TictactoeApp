package com.tictactoe;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputHandlerTest {

    Logger inputLogger = (Logger) LoggerFactory.getLogger(InputHandler.class);
    ListAppender<ILoggingEvent> listAppender;

    @BeforeEach
    void setUp() {

        listAppender = new ListAppender<>();
        listAppender.start();
        inputLogger.addAppender(listAppender);
    }

    @AfterEach
    public void restoreSystemInput() {
        System.setIn(System.in);
    }

    @Test
    public void shouldTakeUserInput() {
        setInput("2,3");
        InputHandler inputHandler= new InputHandler();
        List<ILoggingEvent> logsList = listAppender.list;
        assertEquals("2,3", inputHandler.readInput());
        assertEquals("Enter the coordinates for X", logsList.get(0).getMessage());

    }

    private void setInput(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

}
