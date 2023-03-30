package in.sanjeev.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sanjeev.entity.Book;
import in.sanjeev.repository.BookRepository;

@Service
public class ReportService {
	@Autowired
	private BookRepository bookRepo;
	
	public void generateExcel(HttpServletResponse response) throws Exception {
		
		List<Book> books = bookRepo.findAll();
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Book Info");
		HSSFRow row = sheet.createRow(0);

		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("Name");
		row.createCell(2).setCellValue("Price");

		int dataRowIndex = 1;

		for (Book bk : books) {
			HSSFRow dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(bk.getBook_id());
			dataRow.createCell(1).setCellValue(bk.getBook_name());
			dataRow.createCell(2).setCellValue(bk.getBook_price());
			dataRowIndex++;
		}

		ServletOutputStream ops = response.getOutputStream();
		workbook.write(ops);
		workbook.close();
		ops.close();

		
	}

}
