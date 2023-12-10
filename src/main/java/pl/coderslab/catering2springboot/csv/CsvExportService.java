package pl.coderslab.catering2springboot.csv;

import java.io.Writer;

public interface CsvExportService {

    void writeActualOrdersToCsv(Writer writer);
    void writeNewOrdersToCsv(Writer writer);
    void saveNewAndActualOrdersToCsv(Writer writer);
    void saveFinancialSummaryToCsv(Writer writer);
}
