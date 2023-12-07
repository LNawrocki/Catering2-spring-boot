package pl.coderslab.catering2springboot.price;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class PriceController {

    private final PriceService priceService;

    @GetMapping("/price")
    public String pricesView(Model model) {
        model.addAttribute("price", new Price());
        model.addAttribute("prices", priceService.findAll());
        return "/admin/price-list";
    }

    @PostMapping("/price/delete")
    public String dishDelete(@RequestParam Integer deletePriceId){
        priceService.deleteByPriceId(deletePriceId);
        return "redirect:/admin/price";
    }
}
