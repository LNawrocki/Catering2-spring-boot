package pl.coderslab.catering2springboot.backup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.catering2springboot.actualOrder.ActualOrder;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/backup")
public class BackupController {

    @Autowired
    private EntityManager entityManager;

    @GetMapping("/create")
    public String createBackup() {
        try {
            // Pobierz dane z bazy danych
            Query query = entityManager.createNativeQuery("SELECT * FROM actual_orders", ActualOrder.class);
            List<ActualOrder> data = query.getResultList();

            // Zapisz dane do pliku (możesz dostosować format zapisu)
            try (FileWriter writer = new FileWriter("backup.txt")) {
                for (ActualOrder entity : data) {
                    // Zapisz dane do pliku (możesz dostosować format zapisu)
                    writer.write(entity.toString() + "\n");
                }
            }

            return "Backup wykonany pomyślnie";
        } catch (IOException e) {
            e.printStackTrace();
            return "Błąd podczas tworzenia backupu: " + e.getMessage();
        }
    }
}