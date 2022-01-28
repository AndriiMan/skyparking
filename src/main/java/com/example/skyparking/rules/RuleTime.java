package com.example.skyparking.rules;

import com.example.skyparking.entity.Talon;

public interface RuleTime {
    Talon count(Talon talon, double sum);
    boolean matches(Talon talon);
}
