package edu.wgu.d387_sample_code.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class TimeZoneService {

    public List<String> getConvertedTimes() {

        ZoneId zEastern = ZoneId.of("America/New_York");
        ZoneId zMountain = ZoneId.of("America/Denver");
        ZoneId zUTC = ZoneId.of("UTC");

        LocalDateTime localDateTime = LocalDateTime.now().plusHours(1);
        ZonedDateTime zonedDateTimeEastern = localDateTime.atZone(zEastern);

        // Convert to Mountain Time
        ZonedDateTime zonedDateTimeMountain = zonedDateTimeEastern.withZoneSameInstant(zMountain);
        LocalDateTime localDateTimeMountain = zonedDateTimeMountain.toLocalDateTime();

        // Convert to UTC
        ZonedDateTime zonedDateTimeUTC = zonedDateTimeEastern.withZoneSameInstant(zUTC);
        LocalDateTime localDateTimeUTC = zonedDateTimeUTC.toLocalDateTime();

        // Format times to be in hours and minutes
        String timeEastern = "ET: " + localDateTime.format(java.time.format.DateTimeFormatter.ofPattern("hh:mm a"));
        String timeMountain = "MT: " + localDateTimeMountain.format(java.time.format.DateTimeFormatter.ofPattern("hh:mm a"));
        String timeUTC = "UTC: " + localDateTimeUTC.format(java.time.format.DateTimeFormatter.ofPattern("hh:mm a"));

        return List.of(timeEastern, timeMountain, timeUTC);
    }
}