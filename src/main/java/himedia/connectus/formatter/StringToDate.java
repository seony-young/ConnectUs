package himedia.connectus.formatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringToDate {

	//String
    String dateStr = "2021-06-19";

    //formatting
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    //String -> LocalDateTime
    LocalDateTime date = LocalDateTime.parse(dateStr, formatter);
}
