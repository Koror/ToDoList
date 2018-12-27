package com.koror.app.enumerated;

public enum Priority {

    LOW, MEDIUM, HIGH;

    public static Priority getPriority(final String input) {
        for (Priority priority : Priority.values()) {
            if (input.equals(priority.name()))
                return priority;
        }
        return null;
    }

}
