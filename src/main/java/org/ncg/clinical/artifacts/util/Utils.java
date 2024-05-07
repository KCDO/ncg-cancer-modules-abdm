package org.ncg.clinical.artifacts.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.hl7.fhir.r4.model.Meta;
import org.hl7.fhir.r4.model.UriType;

public class Utils {
	public static boolean isBlank(String value) {
		return value == null || "".equals(value.trim());
	}

	public static boolean randomBool() {
		Random random = new Random();
		return random.nextBoolean();
	}

	public static Meta getMeta(Date forDate, String resourceType) {
		Meta meta = new Meta();
		meta.setLastUpdated(forDate);
		meta.setVersionId(Constants.INITIAL_VERSION_NUMBER);
		meta.addProfile(resourceType);
		return meta;
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