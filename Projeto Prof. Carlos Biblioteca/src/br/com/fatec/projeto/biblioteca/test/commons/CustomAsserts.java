package br.com.fatec.projeto.biblioteca.test.commons;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;

/**
 * @author Carlos
 *
 * @version
 */
public class CustomAsserts {

	public static void assertDatas(Date expected, Date actual) {
		Calendar calendarExpec = Calendar.getInstance();
		Calendar calendarActual = Calendar.getInstance();
		calendarExpec.setTimeInMillis(expected.getTime());
		calendarActual.setTimeInMillis(actual.getTime());

		Assert.assertEquals(calendarExpec.get(Calendar.YEAR), calendarActual.get(Calendar.YEAR));
		Assert.assertEquals(calendarExpec.get(Calendar.MONTH), calendarActual.get(Calendar.MONTH));
		Assert.assertEquals(calendarExpec.get(Calendar.DAY_OF_MONTH), calendarActual.get(Calendar.DAY_OF_MONTH));

	}

}
