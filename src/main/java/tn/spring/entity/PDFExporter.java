package tn.spring.entity;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;
 
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
 
public class PDFExporter {
    private List<Question> listquiz;
     
    public PDFExporter(List<Question> listquiz) {
        this.listquiz = listquiz;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("quiz", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("content", font));
        
        table.addCell(cell); cell.setPhrase(new Phrase("Points", font));
        table.addCell(cell);
         
      
    }
     
    private void writeTableData(PdfPTable table) {
        for (Question user : listquiz) {
            table.addCell(String.valueOf(user.getQuizs()));
            table.addCell(user.getContent());
            table.addCell(String.valueOf(user.getPoints()));
            
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("List of Users", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f,3.0f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close(); 
         
    }
}

