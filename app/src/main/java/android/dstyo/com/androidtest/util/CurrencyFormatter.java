package android.dstyo.com.androidtest.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Currency Formatter to return readable format of Decimal to currency
 *
 * @author Dwi Setiyono <dwi@urbanindo.com>
 * @since 2016.05.19
 */

public class CurrencyFormatter {


    private static String symbolFormat = "Rp. %s";
    private static String separatorFormat = "#,###";

    /**
     * Return Rupiah currency format.
     *
     * @param amount the double contain currency value
     * @return currency format string
     */
    public static String format(double amount) {
        return String.format(symbolFormat, formatToNonDecimal(amount));
    }

    /**
     * Return Rupiah currency format.
     *
     * @param amount the double contain currency value
     * @return currency format string from decimal format
     */
    public static String formatToNonDecimal(double amount) {
        DecimalFormat decimalFormat = (DecimalFormat)
                NumberFormat.getNumberInstance();
        return decimalFormat.format(amount);
    }

}
