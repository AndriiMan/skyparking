package com.example.skyparking;

import com.example.skyparking.entity.Terminal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TerminalTest {
    Terminal terminal = new Terminal();

    @Test
    public void getMachine() {
        Assertions.assertNotNull(terminal);
    }
}