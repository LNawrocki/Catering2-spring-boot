package pl.coderslab.catering2springboot.csv;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@AllArgsConstructor
public class CsvExportController {

    private final CsvExportService csvExportService;

//    public EmployeeController(CsvExportService csvExportService) {
//        this.csvExportService = csvExportService;
//    }

    @PostMapping("/admin/financial/saveActualOrdersToCsv")
    public void getAllActualOrdersInCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv; charset=UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"aktualne_zamowienia.csv\"");
        csvExportService.writeActualOrdersToCsv(servletResponse.getWriter());
    }

    @PostMapping("/admin/financial/saveNewOrdersToCsv")
    public void getAllNewOrdersInCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv; charset=UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"nowe_zamowienia.csv\"");
        csvExportService.writeNewOrdersToCsv(servletResponse.getWriter());
    }

}
