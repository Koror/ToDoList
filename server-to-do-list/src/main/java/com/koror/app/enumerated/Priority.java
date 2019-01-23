package com.koror.app.enumerated;

public enum Priority {

    LOW_PRIORITY, MEDIUM_PRIORITY, HIGH_PRIORITY;

    public static Priority getPriority(final String input) {
        for (Priority priority : Priority.values()) {
            if (input.equals(priority.name()))
                return priority;
        }
        return null;
    }

}
