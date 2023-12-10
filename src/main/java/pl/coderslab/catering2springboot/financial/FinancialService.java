package pl.coderslab.catering2springboot.financial;

import pl.coderslab.catering2springboot.department.Department;

import java.math.BigDecimal;
import java.util.List;

public interface FinancialService {

    List<FinancialDepartmentSummary> getfinancialDepartmentSummaryList();
    BigDecimal getSumOfDepartmentDiscountPrice();
    BigDecimal getSumOfDepartmentFullPrice();
}
