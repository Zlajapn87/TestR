package writerProject;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.AcroFields.Item;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class pdfWriter {


		    private static final String FILE_NAME = "C:\\Users\\nikola.zlatkovic\\Desktop\\Selenium\\Test\\itext.pdf";
		 
		    
		    public static final String SRC = "C:\\Users\\nikola.zlatkovic\\Desktop\\Selenium\\Test\\interactiveform_enabled.pdf";
		    public static final String DEST = "C:\\Users\\nikola.zlatkovic\\Desktop\\Selenium\\\\Test\\f8966_filled.pdf";

		    public static void main(String[] args) {
		        writeUsingIText();
		        try {
					new pdfWriter().manipulatePdf(SRC, DEST);
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }

		    private static void writeUsingIText() {

		        Document document = new Document();

		        try {
		            PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME)));
		            //open
		            document.open();
		            
		            Font f = new Font();
		            f.setStyle(Font.BOLD);
		            f.setSize(15);

		            Paragraph p = new Paragraph();
		            p.add("Zlaja Car, kreira pdfWriter!");
		            p.setAlignment(Element.ALIGN_CENTER);
		          /*  p.setSize(15); */
		            document.add(p);

		            Paragraph p2 = new Paragraph();
		            p2.add("ZVEZDA"); //no alignment
		            document.add(p2);
		            document.add(new Paragraph("Mare je veliki Srbin, rodjen u Mostaru", f));
		            //close
		            document.close();
		            System.out.println("Done, document has been created");
		         
		        } catch (FileNotFoundException | DocumentException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        };
		    
		    
		    PdfReader reader; 
	        
	        try {
	        	
	        	reader = new PdfReader(FILE_NAME);
	            // pageNumber = 1
	            String textFromPage = PdfTextExtractor.getTextFromPage(reader, 1);
	            

	            System.out.println(textFromPage);

		            reader.close();
		            
	        } catch (IOException e) {
	            e.printStackTrace(); 
	            
	        }
	        }
	        
		   
		    
		    public void manipulatePdf(String src, String dest) throws DocumentException, IOException {
		        PdfReader reader = new PdfReader(src);
		        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
		        AcroFields form = stamper.getAcroFields();
		        
		        System.out.println("Stamper list is: " +stamper);
		        
		        Map<String, Item> fields = form.getFields();
		        System.out.println("List of the fields is: " +fields);
		        
		   
		        
		        
		        form.removeXfa();
		        form.setField("Name_First", "Ratko");
		        form.setField("Name_Last", "Zekic");
		        form.setField("Name_Middle", "XXX");
		        stamper.close();
		        reader.close();
		    }
		}
		    



