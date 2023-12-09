package pl.coderslab.catering2springboot.csv;

import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;
import pl.coderslab.catering2springboot.actualOrder.ActualOrder;
import pl.coderslab.catering2springboot.actualOrder.ActualOrderRepository;
import pl.coderslab.catering2springboot.newOrder.NewOrder;
import pl.coderslab.catering2springboot.newOrder.NewOrderRepository;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Service
@AllArgsConstructor
public class CsvExportService {

//    private static final Logger log = getLogger(CsvExportService.class);

    private final ActualOrderRepository actualOrderRepository;
    private final NewOrderRepository newOrderRepository;

    public void writeActualOrdersToCsv(Writer writer) {

        List<ActualOrder> actualOrders = actualOrderRepository.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withDelimiter(';'))) {
            csvPrinter.printRecord(
                    "ID", "kw","Login", "zapłacono", "Kwota",
                    "Nr dania Pn", "nazwa dania Pn", "Cena dania Pn", "zmiana Pn",
                    "Nr dania Wt", "nazwa dania Wt", "Cena dania Wt", "zmiana Wt",
                    "Nr dania Śr", "nazwa dania Śr", "Cena dania Śr", "zmiana Śr",
                    "Nr dania Czw", "nazwa dania Czw", "Cena dania Czw", "zmiana Czw",
                    "Nr dania Pt", "nazwa dania Pt", "Cena dania Pt", "zmiana Pt");

            for (ActualOrder actualOrder : actualOrders) {
                csvPrinter.printRecord(actualOrder.getId(),
                        actualOrder.getKw(),
                        actualOrder.getUser().getLogin(),
                        actualOrder.getIsPaid(),
                        actualOrder.getToPay(),
                        actualOrder.getMealMon(),
                        actualOrder.getMealMonName(),
                        actualOrder.getPriceMon(),
                        actualOrder.getShiftMon(),
                        actualOrder.getMealTue(),
                        actualOrder.getMealTueName(),
                        actualOrder.getPriceTue(),
                        actualOrder.getShiftTue(),
                        actualOrder.getMealWed(),
                        actualOrder.getMealWedName(),
                        actualOrder.getPriceWed(),
                        actualOrder.getShiftWed(),
                        actualOrder.getMealThu(),
                        actualOrder.getMealThuName(),
                        actualOrder.getPriceThu(),
                        actualOrder.getShiftThu(),
                        actualOrder.getMealFri(),
                        actualOrder.getMealFriName(),
                        actualOrder.getPriceFri(),
                        actualOrder.getShiftFri());
            }
        } catch (IOException e) {
//            log.error("Error While writing CSV ", e);
        }
    }

    public void writeNewOrdersToCsv(Writer writer) {

        List<NewOrder> newOrders = newOrderRepository.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withDelimiter(';'))) {
            csvPrinter.printRecord(
                    "ID", "kw","Login", "zapłacono", "Kwota",
                    "Nr dania Pn", "nazwa dania Pn", "Cena dania Pn", "zmiana Pn",
                    "Nr dania Wt", "nazwa dania Wt", "Cena dania Wt", "zmiana Wt",
                    "Nr dania Śr", "nazwa dania Śr", "Cena dania Śr", "zmiana Śr",
                    "Nr dania Czw", "nazwa dania Czw", "Cena dania Czw", "zmiana Czw",
                    "Nr dania Pt", "nazwa dania Pt", "Cena dania Pt", "zmiana Pt");
            for (NewOrder newOrder : newOrders) {
                csvPrinter.printRecord(newOrder.getId(),
                        newOrder.getKw(),
                        newOrder.getUser().getLogin(),
                        newOrder.getIsPaid(),
                        newOrder.getToPay(),
                        newOrder.getMealMon(),
                        newOrder.getMealMonName(),
                        newOrder.getPriceMon(),
                        newOrder.getShiftMon(),
                        newOrder.getMealTue(),
                        newOrder.getMealTueName(),
                        newOrder.getPriceTue(),
                        newOrder.getShiftTue(),
                        newOrder.getMealWed(),
                        newOrder.getMealWedName(),
                        newOrder.getPriceWed(),
                        newOrder.getShiftWed(),
                        newOrder.getMealThu(),
                        newOrder.getMealThuName(),
                        newOrder.getPriceThu(),
                        newOrder.getShiftThu(),
                        newOrder.getMealFri(),
                        newOrder.getMealFriName(),
                        newOrder.getPriceFri(),
                        newOrder.getShiftFri());
            }
        } catch (IOException e) {
//            log.error("Error While writing CSV ", e);
        }
    }
}
