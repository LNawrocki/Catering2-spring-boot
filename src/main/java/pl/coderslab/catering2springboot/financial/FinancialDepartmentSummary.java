package pl.coderslab.catering2springboot.financial;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
@ToString
public class FinancialDepartmentSummary {

    private String departmentName ="";
    private BigDecimal departmentSummaryFullPrice = new BigDecimal(0);
    private BigDecimal departmentSummaryDiscountPrice = new BigDecimal(0);
}
