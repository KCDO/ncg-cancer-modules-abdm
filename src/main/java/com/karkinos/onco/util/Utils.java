package com.karkinos.onco.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import org.hl7.fhir.r4.model.Meta;

public class Utils {
	public static boolean isBlank(String value) {
		return value == null || "".equals(value.trim());
	}

	public static boolean randomBool() {
		Random random = new Random();
		return random.nextBoolean();
	}

	public static Meta getMeta(Date forDate) {
		Meta meta = new Meta();
		meta.setLastUpdated(forDate);
		return meta;
	}

	public static int randomInt(int min, int max) {
		Random random = new Random();
		return random.ints(min, max).findFirst().getAsInt();
	}

	public static Properties loadFromFile(String filename) throws IOException {
		Properties properties = new Properties();
		properties.load(Utils.class.getResourceAsStream(filename));
		return properties;
	}

	public static byte[] readFileContent(String filename) throws IOException {
		return Utils.class.getResourceAsStream(filename).readAllBytes();
	}

	public static void saveToFile(Path path, String content) throws Exception {
		try (BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"))) {
			writer.write(content);
		} catch (IOException ex) {
			throw new Exception(ex);
		}
	}

	public static String formatDate(Date date, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	public static Date getNextDate(LocalDateTime dateTime, int i) {
		if (i == 0) {
			return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
		}
		LocalDateTime newDateTime = dateTime.plusDays(i * 2);
		return Date.from(newDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date getPastDate(Date date, int days) {
		LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime newDateTime = localDateTime.minusDays(days);
		return Date.from(newDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date getFutureDate(Date date, int days) {
		LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime newDateTime = localDateTime.plusDays(days);
		return Date.from(newDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date getFutureDate(LocalDateTime date, int days) {
		LocalDateTime newDateTime = date.plusDays(days);
		return Date.from(newDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date getFutureTime(Date date, int minutes) {
		LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime newDateTime = localDateTime.plusMinutes(minutes);
		return Date.from(newDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date ageToDateConverter(int ageInYears) {

		// Get the current calendar instance
		Calendar calendar = Calendar.getInstance();

		// Subtract the age from the current year to calculate birth year
		int birthYear = calendar.get(Calendar.YEAR) - ageInYears;

		// Set the birthdate in the calendar
		calendar.set(Calendar.YEAR, birthYear);

		// The birthdate will be the first day of the birth year
		calendar.set(Calendar.MONTH, Calendar.JANUARY); // January (0-based)
		calendar.set(Calendar.DAY_OF_MONTH, 1); // 1st day of January

		// Get the Date object representing the birthdate
		Date birthdate = calendar.getTime();

		return birthdate;
	}

	public static String generateId() {
		return UUID.randomUUID().toString();
	}
}