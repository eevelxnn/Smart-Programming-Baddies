package com.smartprogrammingbaddies;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.smartprogrammingbaddies.event.Event;
import com.smartprogrammingbaddies.organization.Organization;
import com.smartprogrammingbaddies.storagecenter.StorageCenter;
import com.smartprogrammingbaddies.volunteer.Volunteer;

/**
 * Unit tests for the Event class.
 */
@ActiveProfiles("test")
@SpringBootTest
@ContextConfiguration(classes = {Event.class, StorageCenter.class, Volunteer.class})
public class EventUnitTests {

    public static Event testEvent;
    public static StorageCenter testStorageCenter;
    public static Organization testOrganizer;
    public static Set<Volunteer> testVolunteers;

    /**
     * Sets up the Event instance and related objects before each test.
     */
    @BeforeEach
    public void setupEventForTesting() {
        testStorageCenter = new StorageCenter();
        testOrganizer = new Organization("Charity Org", "Non-Profit", new HashSet<>(), "12-01-2024");

        Volunteer volunteer1 = new Volunteer("John Doe", "Helper", "12-01-2024", null);
        Volunteer volunteer2 = new Volunteer("Jane Smith", "Cook", "12-01-2024", null);

        testVolunteers = new HashSet<>();
        testVolunteers.add(volunteer1);
        testVolunteers.add(volunteer2);

        testEvent = new Event(
                "Charity Drive",
                "A community charity event",
                new Date(2024 - 1900, 11, 25), // December 25, 2024
                new Date(2024 - 1900, 11, 25, 10, 0), // 10:00 AM
                "East Village",
                testStorageCenter,
                testOrganizer,
                testVolunteers
        );
    }

    @Test
    public void getNameTest() {
        String expectedName = "Charity Drive";
        assertEquals(expectedName, testEvent.getName());
    }

    @Test
    public void updateNameTest() {
        testEvent.updateName("Food Drive");
        String expectedName = "Food Drive";
        assertEquals(expectedName, testEvent.getName());
    }

    @Test
    public void getDescriptionTest() {
        String expectedDescription = "A community charity event";
        assertEquals(expectedDescription, testEvent.getDescription());
    }

    @Test
    public void updateDescriptionTest() {
        testEvent.updateDescription("A community food event");
        String expectedDescription = "A community food event";
        assertEquals(expectedDescription, testEvent.getDescription());
    }

    @Test
    public void getDateTest() {
        Date expectedDate = new Date(2024 - 1900, 11, 25); // December 25, 2024
        assertEquals(expectedDate, testEvent.getDate());
    }

    @Test
    public void updateDateTest() {
        Date newDate = new Date(2024 - 1900, 11, 26); // December 26, 2024
        testEvent.updateDate(newDate);
        assertEquals(newDate, testEvent.getDate());
    }

    @Test
    public void getTimeTest() {
        Date expectedTime = new Date(2024 - 1900, 11, 25, 10, 0); // 10:00 AM
        assertEquals(expectedTime, testEvent.getTime());
    }

    @Test
    public void updatedTimeTest() {
        Date newTime = new Date(2024 - 1900, 11, 25, 11, 0); // 11:00 AM
        testEvent.updateTime(newTime);
        assertEquals(newTime, testEvent.getTime());
    }

    @Test
    public void getLocationTest() {
        String expectedLocation = "East Village";
        assertEquals(expectedLocation, testEvent.getLocation());
    }

    @Test
    public void updateLocationTest() {
        testEvent.updateLocation("West Village");
        String expectedLocation = "West Village";
        assertEquals(expectedLocation, testEvent.getLocation());
    }

    @Test
    public void getOrganizerTest() {
        assertEquals(testOrganizer, testEvent.getOrganizer());
    }

    @Test
    public void getListOfVolunteersTest() {
        assertEquals(testVolunteers, testEvent.getListOfVolunteers());
    }

    @Test
    public void getVolunteerCountTest() {
        int expectedVolunteerCount = 2;
        assertEquals(expectedVolunteerCount, testEvent.getVolunteerCount());
    }

    @Test
    public void addVolunteerTest() {
        Volunteer newVolunteer = new Volunteer("Alice Doe", "Driver", "12-02-2024", null);
        testEvent.addVolunteer(newVolunteer);

        int expectedVolunteerCount = 3;
        assertEquals(expectedVolunteerCount, testEvent.getVolunteerCount());
    }

//    @Test
//    public void removeVolunteerTest() {
//        Volunteer volunteerToRemove = testVolunteers.iterator().next();
//        testEvent.removeVolunteer(volunteerToRemove.getDatabaseId());
//
//        int expectedVolunteerCount = 1;
//        assertEquals(expectedVolunteerCount, testEvent.getVolunteerCount());
//    }
//
//    @Test
//    public void toStringWithVolunteersTest() { - issue is with volunteers, they print random order
//        String expectedString = "Event Name: Charity Drive\n"
//                + "Description: A community charity event\n"
//                + "Date: Wed Dec 25 00:00:00 EST 2024\n"
//                + "Time: Wed Dec 25 10:00:00 EST 2024\n"
//                + "Location: East Village\n"
//                + "Storage Center: Food Pantry\n"
//                + "Organizer: Organization Name: Charity Org\n"
//                + "Organizaton Type: Non-Profit\n"
//                + "Date Added: 12-01-2024\n"
//                + "Schedule: \n\n"
//                + "Volunteer Names: \n"
//                + "- John Doe - 0\n"
//                + "- Jane Smith - 0\n";
//
//        assertEquals(expectedString, testEvent.toString());
//    }
//
//    @Test
//    public void toStringWithNoVolunteersTest() {
//        testEvent.getListOfVolunteers().clear();
//        String expectedString = "Event Name: Charity Drive\n"
//                + "Description: A community charity event\n"
//                + "Date: Wed Dec 25 00:00:00 EST 2024\n"
//                + "Time: Wed Dec 25 10:00:00 EST 2024\n"
//                + "Location: East Village\n"
//                + "Storage Center: Food Pantry\n"
//                + "Organizer: Organization Name: Charity Org\n"
//                + "Organizaton Type: Non-Profit\n"
//                + "Date Added: 12-01-2024\n"
//                + "Schedule: \n\n"
//                + "No volunteers have signed up yet.\n";
//
//        assertEquals(expectedString, testEvent.toString());
//    }

}