package com.novo.model.entry.enums;

/**
 * Describes the type of log entry
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
public enum EntryType {
    Telephony ("Телефония"),
    NetWork ("Сеть"),
    Asupog ("АСУПОГ"),
    Other ("Прочее");

    private final String displayValue;

    private EntryType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
