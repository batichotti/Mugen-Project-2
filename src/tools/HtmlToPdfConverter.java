package tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.xhtmlrenderer.layout.SharedContext;

import org.xhtmlrenderer.pdf.ITextRenderer;

public class HtmlToPdfConverter {

    public static void main(String outputPath) {
        try {
            String htmlContent = new String(Files.readAllBytes(Paths.get("D:\\documentos\\GitHub\\Mugen-Project-2\\arquivo.html")));

            outputPath = "D:\\desktop\\ficha.pdf";

            convertHtmlToPdf(htmlContent, outputPath);

            System.out.println("Conversion successful. PDF saved to: " + outputPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void convertHtmlToPdf(String htmlContent, String outputPath) throws Exception {
        File outputFile = new File(outputPath);
        
        File htmlFile = new File(htmlContent);
        Document doc = Jsoup.parse(htmlContent, "UTF-8");
        doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        
        try (OutputStream os = new FileOutputStream(outputFile)) {
            ITextRenderer renderer = new ITextRenderer();
            SharedContext cntxt = renderer.getSharedContext();
            cntxt.setPrint(true);
            cntxt.setInteractive(false);
            String baseUrl = FileSystems.getDefault().getPath(outputPath).toUri().toURL().toString();
            renderer.setDocumentFromString(doc.html(), baseUrl);
            String fontPath = "D:\\desktop\\Geral\\Java\\Mugen_Project\\ficha\\Jujutsu_Kaisen.ttf";
            renderer.getFontResolver().addFont(fontPath, true);
            renderer.layout();
            renderer.createPDF(os);
            System.out.println("Done");
        }
    }
}
