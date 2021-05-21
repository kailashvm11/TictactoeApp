package com.tictactoe;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TictactoeAppTest {
    @Nested
    @DisplayName("Test cases without conflicting input")
    class InputMockTestWithoutConflict {
        Logger gameLogger = (Logger) LoggerFactory.getLogger(TictactoeGame.class);
        TictactoeGame game;
        ListAppender<ILoggingEvent> listAppender;

        @BeforeEach
        void setUp() {

            listAppender = new ListAppender<>();
            listAppender.start();
            gameLogger.addAppender(listAppender);
            InputHandler mockInputHandler = mock(InputHandler.class);
            when(mockInputHandler.readInput('X'))
                    .thenReturn("2,3")
                    .thenReturn("1,1")
                    .thenReturn("2,2")
                    .thenReturn("3,3")
                    .thenReturn("1,3");
            when(mockInputHandler.readInput('O'))
                    .thenReturn("2,1")
                    .thenReturn("3,1")
                    .thenReturn("3,2")
                    .thenReturn("1,2");
            game = new TictactoeGame(mockInputHandler);
        }

        @Test
        public void shouldAnswerWithTrue() {
            TictactoeApp tictactoeApp = new TictactoeApp();
            assertNotNull(tictactoeApp);
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

        @Test
        void shouldUpdateGridAfterO() {
            game.start();
            char[][] grid = game.getGrid();
            assertEquals('O', grid[1][0]);
        }

        @Test
        void shouldDisplayGridAfterOUpdate() {
            game.start();
            List<ILoggingEvent> logsList = listAppender.list;
            Assertions.assertThat(listAppender.list)
                    .extracting(ILoggingEvent::getFormattedMessage)
                    .contains("| O   X |");
        }

        @AfterEach
        void tearDown() {
            listAppender.stop();
            listAppender.list.clear();
        }
    }

    @Nested
    @DisplayName("Test cases with conflicting input")
    class InputMockTestWithConflict {
        Logger gameLogger = (Logger) LoggerFactory.getLogger(TictactoeGame.class);
        TictactoeGame game;
        ListAppender<ILoggingEvent> listAppender;

        @BeforeEach
        void setUp() {

            listAppender = new ListAppender<>();
            listAppender.start();
            gameLogger.addAppender(listAppender);
            InputHandler mockInputHandler = mock(InputHandler.class);
            when(mockInputHandler.readInput('X'))
                    .thenReturn("2,3")
                    .thenReturn("1,1")
                    .thenReturn("2,2")
                    .thenReturn("3,3")
                    .thenReturn("1,3");

            when(mockInputHandler.readInput('O'))
                    .thenReturn("2,3")
                    .thenReturn("2,1")
                    .thenReturn("3,1")
                    .thenReturn("3,2")
                    .thenReturn("1,2");
            game = new TictactoeGame(mockInputHandler);
        }

        @Test
        void shouldDisplayCellAlreadyFilled() {
            game.start();
            List<ILoggingEvent> logsList = listAppender.list;
            Assertions.assertThat(listAppender.list)
                    .extracting(ILoggingEvent::getFormattedMessage)
                    .contains("This cell is already filled. Please select another cell");
        }

        @AfterEach
        void tearDown() {
            listAppender.stop();
            listAppender.list.clear();
        }
    }

    @Nested
    @DisplayName("Test cases with horizontal row wins")
    class InputMockTestWithHorizontalRowWins {
        Logger gameLogger = (Logger) LoggerFactory.getLogger(TictactoeGame.class);
        TictactoeGame game;
        ListAppender<ILoggingEvent> listAppender;

        @BeforeEach
        void setUp() {

            listAppender = new ListAppender<>();
            listAppender.start();
            gameLogger.addAppender(listAppender);
            InputHandler mockInputHandler = mock(InputHandler.class);
            when(mockInputHandler.readInput('X'))
                    .thenReturn("2,3")
                    .thenReturn("2,1")
                    .thenReturn("2,2");
            when(mockInputHandler.readInput('O'))
                    .thenReturn("1,1")
                    .thenReturn("3,1");
            game = new TictactoeGame(mockInputHandler);
        }

        @Test
        void shouldUpdateGridAfterHorizontalRows() {
            game.start();
            char[][] grid = game.getGrid();
            assertEquals('X', grid[1][0]);
            assertEquals('X', grid[1][1]);
            assertEquals('X', grid[1][2]);
        }

        @Test
        void shouldBeWinForXWithHorizontalRows() {
            game.start();
            List<ILoggingEvent> logsList = listAppender.list;
            Assertions.assertThat(listAppender.list)
                    .extracting(ILoggingEvent::getFormattedMessage)
                    .contains("Player with X mark wins!");
        }

        @AfterEach
        void tearDown() {
            listAppender.stop();
            listAppender.list.clear();
        }
    }

    @Nested
    @DisplayName("Test cases with vertical row wins")
    class InputMockTestWithVerticalRowWins {
        Logger gameLogger = (Logger) LoggerFactory.getLogger(TictactoeGame.class);
        TictactoeGame game;
        ListAppender<ILoggingEvent> listAppender;

        @BeforeEach
        void setUp() {

            listAppender = new ListAppender<>();
            listAppender.start();
            gameLogger.addAppender(listAppender);
            InputHandler mockInputHandler = mock(InputHandler.class);
            when(mockInputHandler.readInput('X'))
                    .thenReturn("3,1")
                    .thenReturn("2,1")
                    .thenReturn("1,2")
                    .thenReturn("2,2");
            when(mockInputHandler.readInput('O'))
                    .thenReturn("2,3")
                    .thenReturn("1,3")
                    .thenReturn("3,3");
            game = new TictactoeGame(mockInputHandler);
        }

        @Test
        void shouldUpdateGridAfterVerticalRows() {
            game.start();
            char[][] grid = game.getGrid();
            assertEquals('O', grid[0][2]);
            assertEquals('O', grid[1][2]);
            assertEquals('O', grid[2][2]);
        }

        @Test
        void shouldBeWinForXWithHorizontalRows() {
            game.start();
            List<ILoggingEvent> logsList = listAppender.list;
            Assertions.assertThat(listAppender.list)
                    .extracting(ILoggingEvent::getFormattedMessage)
                    .contains("Player with O mark wins!");
        }

        @AfterEach
        void tearDown() {
            listAppender.stop();
            listAppender.list.clear();
        }
    }

    @Nested
    @DisplayName("Test cases with diaganol row wins")
    class InputMockTestWithDiaganolRowWins {
        Logger gameLogger = (Logger) LoggerFactory.getLogger(TictactoeGame.class);
        TictactoeGame game;
        ListAppender<ILoggingEvent> listAppender;

        @BeforeEach
        void setUp() {

            listAppender = new ListAppender<>();
            listAppender.start();
            gameLogger.addAppender(listAppender);
            InputHandler mockInputHandler = mock(InputHandler.class);
            when(mockInputHandler.readInput('X'))
                    .thenReturn("1,1")
                    .thenReturn("2,2")
                    .thenReturn("1,2")
                    .thenReturn("3,3");
            when(mockInputHandler.readInput('O'))
                    .thenReturn("1,3")
                    .thenReturn("2,3")
                    .thenReturn("3,2");
            game = new TictactoeGame(mockInputHandler);
        }

        @Test
        void shouldUpdateGridAfterDiagonalRows() {
            game.start();
            char[][] grid = game.getGrid();
            assertEquals('X', grid[0][0]);
            assertEquals('X', grid[1][1]);
            assertEquals('X', grid[2][2]);
        }

        @Test
        void shouldBeWinForXWithDiagonalRows() {
            game.start();
            List<ILoggingEvent> logsList = listAppender.list;
            Assertions.assertThat(listAppender.list)
                    .extracting(ILoggingEvent::getFormattedMessage)
                    .contains("Player with X mark wins!");
        }

        @AfterEach
        void tearDown() {
            listAppender.stop();
            listAppender.list.clear();
        }
    }

    @Nested
    @DisplayName("Test cases with back diaganol row wins")
    class InputMockTestWithBackDiaganolRowWins {
        Logger gameLogger = (Logger) LoggerFactory.getLogger(TictactoeGame.class);
        TictactoeGame game;
        ListAppender<ILoggingEvent> listAppender;

        @BeforeEach
        void setUp() {

            listAppender = new ListAppender<>();
            listAppender.start();
            gameLogger.addAppender(listAppender);
            InputHandler mockInputHandler = mock(InputHandler.class);
            when(mockInputHandler.readInput('X'))
                    .thenReturn("1,1")
                    .thenReturn("3,2")
                    .thenReturn("1,2")
                    .thenReturn("3,3");
            when(mockInputHandler.readInput('O'))
                    .thenReturn("1,3")
                    .thenReturn("2,2")
                    .thenReturn("2,1")
                    .thenReturn("3,1");
            game = new TictactoeGame(mockInputHandler);
        }

        @Test
        void shouldUpdateGridAfterBackDiagonalRows() {
            game.start();
            char[][] grid = game.getGrid();
            assertEquals('O', grid[0][2]);
            assertEquals('O', grid[1][1]);
            assertEquals('O', grid[2][0]);
        }

        @Test
        void shouldBeWinForOWithBackDiagonalRows() {
            game.start();
            List<ILoggingEvent> logsList = listAppender.list;
            Assertions.assertThat(listAppender.list)
                    .extracting(ILoggingEvent::getFormattedMessage)
                    .contains("Player with O mark wins!");
        }

        @AfterEach
        void tearDown() {
            listAppender.stop();
            listAppender.list.clear();
        }
    }
}
