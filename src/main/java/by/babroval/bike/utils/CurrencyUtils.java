package by.babroval.bike.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyUtils {

	public static BigDecimal roundNumber(final BigDecimal number, final boolean isFloor) {
		return number.setScale(2, isFloor ? RoundingMode.FLOOR : RoundingMode.CEILING);
	}

}
