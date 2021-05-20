package com.tictactoe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TictactoeAppTest {

    @Test
    public void shouldAnswerWithTrue() {
        TictactoeApp tictactoeApp = new TictactoeApp();
        assertNotNull( tictactoeApp );
    }
}
