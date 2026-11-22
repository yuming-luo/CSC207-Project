package entity;

import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the CalendarEvent entity.
 */
public class CalendarEventTest {

    private final ZonedDateTime startTime = ZonedDateTime.of(
            2026, 11, 23, 14, 0, 0, 0,
            ZoneId.of("America/Toronto"));

    private final ZonedDateTime endTime = startTime.plusHours(2);

    @Test
    public void testValidCalendarEvent() {
        CalendarEvent event = new CalendarEvent(
                "event123", "primary", "Study CSC207",
                "Review Clean Architecture", startTime, endTime);

        assertEquals("event123", event.getEventId());
        assertEquals("primary", event.getCalendarId());
        assertEquals("Study CSC207", event.getTitle());
        assertEquals("Review Clean Architecture", event.getDescription());
        assertEquals(startTime, event.getStartTime());
        assertEquals(endTime, event.getEndTime());
    }

    @Test
    public void testNewEventWithoutId() {
        CalendarEvent event = new CalendarEvent(
                null, "primary", "Study CSC207",
                "Review notes", startTime, endTime);

        assertNull(event.getEventId());
    }

    @Test
    public void testNullDescription() {
        CalendarEvent event = new CalendarEvent(
                null, "primary", "Study CSC207",
                null, startTime, endTime);

        assertEquals("", event.getDescription());
    }

    @Test
    public void testBlankEventId() {
        assertThrows(IllegalArgumentException.class, () ->
                new CalendarEvent(
                        " ", "primary", "Study CSC207",
                        "Review notes", startTime, endTime));
    }

    @Test
    public void testInvalidCalendarId() {
        assertThrows(IllegalArgumentException.class, () ->
                new CalendarEvent(
                        null, "", "Study CSC207",
                        "Review notes", startTime, endTime));
    }

    @Test
    public void testBlankTitle() {
        assertThrows(IllegalArgumentException.class, () ->
                new CalendarEvent(
                        null, "primary", " ",
                        "Review notes", startTime, endTime));
    }

    @Test
    public void testNullStartTime() {
        assertThrows(IllegalArgumentException.class, () ->
                new CalendarEvent(
                        null, "primary", "Study CSC207",
                        "Review notes", null, endTime));
    }

    @Test
    public void testNullEndTime() {
        assertThrows(IllegalArgumentException.class, () ->
                new CalendarEvent(
                        null, "primary", "Study CSC207",
                        "Review notes", startTime, null));
    }

    @Test
    public void testEndTimeBeforeStartTime() {
        assertThrows(IllegalArgumentException.class, () ->
                new CalendarEvent(
                        null, "primary", "Study CSC207",
                        "Review notes", startTime,
                        startTime.minusHours(1)));
    }

    @Test
    public void testEndTimeEqualsStartTime() {
        assertThrows(IllegalArgumentException.class, () ->
                new CalendarEvent(
                        null, "primary", "Study CSC207",
                        "Review notes", startTime, startTime));
    }
}
