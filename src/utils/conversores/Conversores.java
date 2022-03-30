package utils.conversores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Conversores {

    public static String converterFormatoDataBr(LocalDate data){
       return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static void converterFormatoDataEng(String data){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(data, formato);
        System.out.println(date);
    }

}
