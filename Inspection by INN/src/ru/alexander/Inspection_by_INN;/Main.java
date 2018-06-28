package ru.alexander.Inspection_by_INN;

import ru.nalog.npchk.FNSNDSCAWS2;
import ru.nalog.npchk.FNSNDSCAWS2Port;
import ru.nalog.npchk.NdsRequest2;
import ru.nalog.npchk.NdsResponse2;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        System.out.println("\u001B[30m\u001B[43m");

        System.out.print("Введите ИНН: ");
        String inn = System.console().readLine();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String date = simpleDateFormat.format(new Date());

        FNSNDSCAWS2 fnsndscaws2 = new FNSNDSCAWS2();
        FNSNDSCAWS2Port fnsndscaws2Port = fnsndscaws2.getFNSNDSCAWS2Port();

        NdsRequest2.NP np = new NdsRequest2.NP();
        np.setINN(inn);
        np.setDT(date);

        NdsRequest2 ndsRequest2 = new NdsRequest2();
        ndsRequest2.getNP().add(np);

        NdsResponse2 ndsResponse2 = fnsndscaws2Port.ndsRequest2(ndsRequest2);
        String state = ndsResponse2.getNP().get(0).getState();

        System.out.print("Результат: ");
        switch (state){
            case "0":  System.out.println("Налогоплательщик зарегистрирован в ЕГРН и имел статус действующего в указанную дату"); break;
            case "1":  System.out.println("Налогоплательщик зарегистрирован в ЕГРН, но не имел статус действующего в указанную дату"); break;
            case "2":  System.out.println("Налогоплательщик зарегистрирован в ЕГРН"); break;
            case "3":  System.out.println("Налогоплательщик с указанным ИНН зарегистрирован в ЕГРН"); break;
            case "4":  System.out.println("Налогоплательщик с указанным ИНН не зарегистрирован в ЕГРН"); break;
            case "5":  System.out.println("Некорректный ИНН"); break;
            case "6":  System.out.println("Недопустимое количество символов ИНН"); break;
            case "7":  System.out.println("Недопустимое количество символов КПП"); break;
            case "8":  System.out.println("Недопустимые символы в ИНН"); break;
            case "9":  System.out.println("Недопустимые символы в КПП"); break;
            case "10": System.out.println("Некорректный формат даты"); break;
            case "11": System.out.println("Некорректная дата (ранее 01.01.1991 или позднее текущей даты)"); break;
            case "12": System.out.println("Неизвестная ошибка"); break;
            default: break;
        }

        System.out.println("\u001B[0m");
    }
}
