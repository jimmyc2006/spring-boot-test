package springstudy.springboothive.util;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.core.util.CloseUtil;

public class OutUtil {
  private static final Logger log = LoggerFactory.getLogger(OutUtil.class);
  
  public static final int LOG_BATCH = 5000;

  public static void write2CSV(ResultSet resultSet, String fileName) {
    try (final CSVPrinter printer =
        CSVFormat.DEFAULT.withHeader(resultSet).print(new FileWriter(fileName))) {
      printer.printRecords(resultSet);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void write2Excel(ResultSet rs, String fileName) throws IOException, SQLException {
    SXSSFWorkbook wb = new SXSSFWorkbook(LOG_BATCH); // keep 100 rows in memory, exceeding rows will be flushed to disk
    FileOutputStream out = null;
    try {
      Sheet sh = wb.createSheet();
      Row row = sh.createRow(0);
      Cell cell;
      
      for (int j = 0; j < rs.getMetaData().getColumnCount(); ++j) {
        String colName = rs.getMetaData().getColumnLabel(j + 1);
        cell = row.createCell(j);
        cell.setCellValue(colName);
      }
      int i = 0;
      while (rs.next()) {
        row = sh.createRow(i + 1);
        for (int j = 0; j < rs.getMetaData().getColumnCount(); ++j) {
          String c = rs.getString(j + 1);
          row.createCell(j).setCellValue(c);
        }
        if ((i + 1) % LOG_BATCH == 0) {
          log.info("数据写出到" + fileName + ", 第" + i + "行");
        }
        ++i;
      }
      
      out = new FileOutputStream(fileName);
      wb.write(out);
      
      // dispose of temporary files backing this workbook on disk
      wb.dispose();
    } finally {
      CloseUtil.closeQuietly(out);
      CloseUtil.closeQuietly(wb);
    }
  }
}
