package patientintake;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Date/Time Converter Should")
class DateTimeConverterShould {

    @Nested
    @DisplayName("Convert string with 'today' keyword")
    class TodayTests {
        @Test
        @DisplayName("correctly")
        void convertTodayStringToDateTime() {
            LocalDate today = LocalDate.of(2022, 03, 16);
            LocalDateTime result = DateTimeConverter.convertStringToDateTime("today 1:00 pm", today);
            assertEquals(result, LocalDateTime.of(2022, 3, 16, 13, 0),
                    () -> "Failed to convert 'today' string to expected date time, today passed was: " + today);
            // with the lambda expression, the expression evaluation only happens when there the assert fails. This helps with performances.
        }

        @Test
        @DisplayName("case insensitive")
        void convertTodayStringToDateTimeCaseInsensitive() {
            LocalDate today = LocalDate.of(2022, 03, 16);
            LocalDateTime result = DateTimeConverter.convertStringToDateTime("ToDay 1:00 pm", today);
            assertEquals(result, LocalDateTime.of(2022, 3, 16, 13, 0),
                    () -> "Failed to convert 'today' string to expected date time, today passed was: " + today);
            // with the lambda expression, the expression evaluation only happens when there the assert fails. This helps with performances.
        }

    }



    @Test
    @DisplayName("Convert expected date/time pattern in string correctly")
    void convertCorrectPatternToDateTime() {
        LocalDateTime result = DateTimeConverter.convertStringToDateTime("3/16/2022 1:00 pm", LocalDate.of(2022, 03, 16));
        assertEquals(result, LocalDateTime.of(2022, 3, 16, 13, 0));
    }
    @Test
    @Tag("dateTime")
    @DisplayName("Throw an exception if entered pattern of string is incorrect")
    void throwExceptionIfIncorrectPatternProvided() {

        Throwable error = assertThrows(RuntimeException.class, () ->
                DateTimeConverter.convertStringToDateTime("3/16/2022 133:00 pm", LocalDate.of(2022, 03, 16)));
        assertEquals(
                "Unable to create date time", error.getMessage().substring(0, 26));
    }

}

