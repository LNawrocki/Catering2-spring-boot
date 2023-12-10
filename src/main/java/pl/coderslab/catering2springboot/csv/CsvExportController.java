package pl.coderslab.catering2springboot.csv;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Controller
@AllArgsConstructor
public class CsvExportController {

    private final CsvExportService csvExportService;


//    public EmployeeController(CsvExportService csvExportService) {
//        this.csvExportService = csvExportService;
//    }

    @PostMapping("/admin/financial/saveActualOrdersToCsv")
    public void getAllActualOrdersToCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv; charset=UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"aktualne_zamowienia" + LocalDateTime.now() + ".csv\"");
        csvExportService.writeActualOrdersToCsv(servletResponse.getWriter());

    }

    @PostMapping("/admin/financial/saveNewOrdersToCsv")
    public void getAllNewOrdersToCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv; charset=UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"nowe_zamowienia" + LocalDateTime.now() + ".csv\"");
        csvExportService.writeNewOrdersToCsv(servletResponse.getWriter());
    }

    @GetMapping("/admin/financial/saveNewAndActualOrdersToCsv")
    public void getAllNewAndActualOrdersToCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv; charset=UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"kopia_zamowien" + LocalDateTime.now() + ".csv\"");
        csvExportService.saveNewAndActualOrdersToCsv(servletResponse.getWriter());
    }

    @GetMapping("/admin/financial/saveFinancialSummaryToCsv")
    public void getFinancialSummaryToCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv; charset=UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"podsumowanie_finansowe" + LocalDateTime.now() + ".csv\"");
        csvExportService.saveFinancialSummaryToCsv(servletResponse.getWriter());
    }
}
