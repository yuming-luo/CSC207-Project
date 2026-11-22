package use_case.google_calendar;

import entity.CalendarEvent;

import java.util.List;

/**
 * Defines the data access operations for Google Calendar events.
 * The actual Google Calendar API implementation will implement this interface.
 */
public interface CalendarEventDataAccessInterface {

    /**
     * Retrieves upcoming calendar events for a Study Nook user.
     *
     * @param userId the Study Nook user ID
     * @return the list of upcoming calendar events
     */
    List<CalendarEvent> getUpcomingEvents(String userId);

    /**
     * Creates a new Google Calendar event.
     *
     * @param userId the Study Nook user ID
     * @param event the event to create
     * @return the created event with its assigned event ID
     */
    CalendarEvent createEvent(String userId, CalendarEvent event);

    /**
     * Updates an existing Google Calendar event.
     *
     * @param userId the Study Nook user ID
     * @param event the event with updated information
     * @return the updated event
     */
    CalendarEvent updateEvent(String userId, CalendarEvent event);

    /**
     * Deletes an existing Google Calendar event.
     *
     * @param userId the Study Nook user ID
     * @param calendarId the calendar containing the event
     * @param eventId the event ID to delete
     */
    void deleteEvent(String userId, String calendarId, String eventId);
}
