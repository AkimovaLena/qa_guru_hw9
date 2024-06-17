import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


import static org.assertj.core.api.Assertions.assertThat;


public class ZipvalidatorTetst {
    private ClassLoader cl = ZipvalidatorTetst.class.getClassLoader();


    @Test
    @DisplayName("Чтение и проверка CSV")
    void zipValidatorCSVTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("test.zip")
        )) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {

                if (entry.getName().endsWith(".csv")){
                    System.out.println(entry.getName());
                    CSVReader reader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = reader.readAll();
                    Assertions.assertArrayEquals(content.get(1),new String[]{"1;user1;23;400"});
                }
            }
        }
    }

    @Test
    @DisplayName("Чтение и проверка PDF")
    void zipValidatorPDFTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("test.zip")
        )) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {

                if (entry.getName().endsWith(".pdf")){
                    System.out.println(entry.getName());
                    PDF reader = new PDF (zis);
                    assertThat(reader.text).contains("Тестовая таблица users");
                }
            }
        }
    }

    @Test
    @DisplayName("Чтение и проверка XLS")
    void zipValidatorXLSTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("test.zip")
        )) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {

                if (entry.getName().endsWith(".xls")){
                    System.out.println(entry.getName());
                    XLS reader = new XLS(zis);
                    String actualValue = reader.excel.getSheetAt(0).getRow(3).getCell(0).getStringCellValue();
                    Assertions.assertEquals(actualValue, "id");
                    String actualValue1 = reader.excel.getSheetAt(0).getRow(3).getCell(1).getStringCellValue();
                    Assertions.assertEquals(actualValue1, "name");
                    String actualValue2 = reader.excel.getSheetAt(0).getRow(3).getCell(2).getStringCellValue();
                    Assertions.assertEquals(actualValue2, "age");
                    String actualValue3 = reader.excel.getSheetAt(0).getRow(3).getCell(3).getStringCellValue();
                    Assertions.assertEquals(actualValue3, "salary");
                }
            }
        }
    }


}
