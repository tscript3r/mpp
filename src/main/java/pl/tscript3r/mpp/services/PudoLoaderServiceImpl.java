package pl.tscript3r.mpp.services;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pl.tscript3r.mpp.domain.Pudo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
public class PudoLoaderServiceImpl implements PudoLoaderService {

    private String fillChar(String source, Integer times, String with) {
        StringBuilder builder = new StringBuilder(source);
        for (int i = 0; i < times; i++)
            builder.append(with);

        return builder.toString();
    }

    private Double multiply(Double number) {
        if(number >= 10 && number < 100)
            return number * 1000;
        if(number >= 100 && number < 1000)
            return number * 100;
        if(number >= 1000 && number < 9999)
            return number * 10;

        return number;
    }

    /**
     * Replaces "0" with "9" from end of the string until there is another char
     * @param from Integer value to be changed
     * @return Returns from value with changed "0" to "9" from the end of the integer.
     * if there is no "0" at the end of the string will return given from.
     */
    private Integer getRangeTo(Integer from) {
        String numberAsString = from.toString();
        int zerosCount = 0;

        for(int i = numberAsString.length() - 1; i > 0; i--)
            if(numberAsString.charAt(i) != 48)
                break;
            else
                zerosCount++;

        if(zerosCount == 0)
            return from;

        return Integer.valueOf(numberAsString.substring(0,
                numberAsString.length() - zerosCount) + fillChar("", zerosCount, "9"));
    }

    private Integer readStringCell(Cell cell) {
        String currentPlz = cell.getStringCellValue();
        if(currentPlz.length() == 0)
            return null;
        currentPlz = currentPlz.replace("%", "");
        if(currentPlz.length() < 5)
            currentPlz = fillChar(currentPlz, 5 - currentPlz.length(), "0");
        return Integer.valueOf(currentPlz);
    }

    private Integer readIntegerCell(Cell cell) {
        Double value = cell.getNumericCellValue();
        if(value == 0)
            return null;
        if(value <= 99)
            value *= 100;
        return Integer.valueOf(String.format("%.0f", multiply(value)));
    }

    private Pudo parseCell(Cell cell, String currentPudoCode) {
        String currentPlz = "";
        Integer result = 0;
        if (cell.getCellTypeEnum() == CellType.STRING)
            result = readStringCell(cell);
        else if (cell.getCellTypeEnum() == CellType.NUMERIC)
            result = readIntegerCell(cell);

        if(cell.getCellTypeEnum() != CellType.BLANK && result != 0)
            return new Pudo(result, getRangeTo(result), currentPudoCode);
        else
            return null;
    }

    public void loadFromExcel(String file, PudoMatcherService pudoMatcherService) {
        try {
            FileInputStream excelFile = new FileInputStream(new File(file));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            String currentPudoCode = "";
            iterator.next();
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                Cell pudoCodeCell = currentRow.getCell(0);
                if(!pudoCodeCell.getStringCellValue().isEmpty())
                    currentPudoCode = pudoCodeCell.getStringCellValue();
                Pudo pudo = parseCell(currentRow.getCell(1), currentPudoCode);

                if(pudo != null)
                    pudoMatcherService.addPudo(pudo);
                //else
                    // TODO: Log ignored cell

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void loadPudos(PudoMatcherService pudoMatcherService) {
        loadFromExcel("C:\\Users\\ADzierzon\\Desktop\\pudos.xlsx", pudoMatcherService);
    }

}
