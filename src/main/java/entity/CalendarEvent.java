
package entity;

import java.time.ZonedDateTime;

/**
 * Represents a calendar event in Study Nook.
 * This entity is independent of the Google Calendar API.
 */
public class CalendarEvent {

    private final String eventId;
    private final String calendarId;
    private final String title;
    private final String description;
    private final ZonedDateTime startTime;
    private final ZonedDateTime endTime;

    /**
     * Creates a calendar event.
     *
     * @param eventId the unique event ID (null for a new event)
     * @param calendarId the calendar containing the event
     * @param title the event title
     * @param description the event description
     * @param startTime the event start date and time
     * @param endTime the event end date and time
     */
    public CalendarEvent(String eventId, String calendarId,
                         String title, String description,
                         ZonedDateTime startTime, ZonedDateTime endTime) {

        if (eventId != null && eventId.isBlank()) {
            throw new IllegalArgumentException("Event ID cannot be blank");
        }

        if (calendarId == null || calendarId.isBlank()) {
            throw new IllegalArgumentException("Calendar ID cannot be empty");
        }

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Event title cannot be empty");
        }

        if (startTime == null || endTime == null) {
            throw new IllegalArgumentException("Event times cannot be null");
        }

        if (!endTime.isAfter(startTime)) {
            throw new IllegalArgumentException(
                    "End time must be after start time");
        }

        this.eventId = eventId;
        this.calendarId = calendarId;
        this.title = title;
        this.description = description == null ? "" : description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getEventId() {
        return eventId;
    }

    public String getCalendarId() {
        return calendarId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }
}
