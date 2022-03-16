package patientintake;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class ClinicCalendarShould {

   private ClinicCalendar calendar;

   @BeforeAll
   static void testClassSetup() {
      System.out.println("Before All...");
   }

   @BeforeEach
   void init() {
      System.out.println("Before Each...");
      calendar = new ClinicCalendar(LocalDate.of(2022, 3, 15));
   }

   @Test
   void allowEntryOfAnAppointment() {

      calendar.addAppointment("Jim", "Weaver", "avery",
         "03/15/2022 2:00 pm");
      List<PatientAppointment> appointments = calendar.getAppointments();
      assertNotNull(appointments);
      assertEquals(1, appointments.size());
      PatientAppointment enteredAppt = appointments.get(0);

      assertAll (
         () -> assertEquals("Jim", enteredAppt.getPatientFirstName()),
         () -> assertEquals("Weaver", enteredAppt.getPatientLastName()),
         () -> assertSame(Doctor.avery, enteredAppt.getDoctor()),
         () -> assertEquals("3/15/2022 02:00 PM",
                 enteredAppt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a", Locale.US)))
      );
   }

   @Test
   void returnTrueForHasAppointmentsIfThereAreAppointments() {

      calendar.addAppointment("Jim", "Weaver", "avery", "03/15/2022 3:00 pm");
      assertTrue(calendar.hasAppointment(LocalDate.of(2022, 03, 15)));
   }

   @Test
   void returnFalseForHasAppointmentsIfThereAreNoAppointments() {

      assertFalse(calendar.hasAppointment(LocalDate.of(2022, 03, 15)));
   }

   @AfterEach
   void tearDownEachTest() {
      System.out.println("After each...");
   }

   @AfterAll
   static void tearDownTestClass() {
      System.out.println("After All... ");
   }
}