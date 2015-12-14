package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.Date;

import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class ITextPdf {

	public static boolean writePdfByTxt(String readFile, String outFile){
		boolean flag = false;
		OutputStream os = null;
		FileInputStream fis = null;
		BufferedReader br = null;
		Document document = null;
		try{
			os = new FileOutputStream(new File(outFile));
			fis = new FileInputStream(new File(readFile));
			br = new BufferedReader(new InputStreamReader(fis, "gbk"));
			
			document = new Document();
			PdfWriter.getInstance(document, os);
			document.open();

			// BaseFont baseFontChinese = BaseFont.createFont("STSong-Light",
			// "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			// Font fontChinese = new Font(baseFontChinese , 12 , Font.NORMAL);

			// 方法一：使用Windows系统字体(TrueType)
			BaseFont baseFont = BaseFont.createFont("C:/Windows/Fonts/SIMYOU.TTF",
					BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			// 方法二：使用iTextAsian.jar中的字体
			// BaseFont baseFont =
			// BaseFont.createFont("STSong-Light",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
			// 方法三：使用资源字体(ClassPath)
			// //BaseFont baseFont =
			// BaseFont.createFont("/SIMYOU.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);

			Font font = new Font(baseFont);
			String tempStr = null;
			while ((tempStr = br.readLine()) != null) {
				document.add(new Paragraph(tempStr, font));
			}
			document.add(new Paragraph(new Date().toString()));
			flag = true;
		}catch(IOException e){
			e.printStackTrace();
		}catch(DocumentException d){
			d.printStackTrace();
		}
		finally{
		
			document.close();
			try {
				fis.close();
				br.close();
				os.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		return flag;
	}

	

	// HTML to PDF 
	public void htmlTransPdf1(String htmlpath, String pdfPath)
			throws IOException, Exception {
		// step 1
		String url = new File(htmlpath).toURI().toURL().toString();
		System.out.println(url);
		// step 2
		OutputStream os = new FileOutputStream(pdfPath);
		org.xhtmlrenderer.pdf.ITextRenderer renderer = new ITextRenderer();
		renderer.setDocument(url);

		// step 3 解决中文支持
		ITextFontResolver fontResolver = renderer.getFontResolver();
		// com.itextpdf.text.pdf.BaseFont
		fontResolver.addFont("C:/Windows/Fonts/SIMSUN.TTC",
				com.itextpdf.text.pdf.BaseFont.IDENTITY_H,
				BaseFont.NOT_EMBEDDED);

		renderer.layout();
		renderer.createPDF(os);
		os.close();

		System.out.println("create pdf done!!");
	}

	//对html不规范进行预处理
	public void test(String inputStream, String outStream){
		Tidy tidy = new Tidy();
		tidy.setXmlOut(true);
		tidy.setXmlPi(true); // 添加 <?xml?> 标签 为输出的 XML 文件， 这些参数是可选的。
		tidy.setXmlSpace(true);
		/*tidy.setInputEncoding("utf-8");
		tidy.setOutputEncoding("utf-8");*/
		try {
		// 文件转换
//			tidy.parse(inputStream, outStream);
		}catch (Exception e) {
		 e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
//			ITextPdf it = new ITextPdf();
//			it.htmlTransPdf1("f:/txt/all-post.html", "f:/txt/testdelete.pdf");
//			htmlToPdf3("f:/txt/HTTPS是如何保证连接安全.html","f:/txt/HTTPS是如何保证连接安全.pdf");
			System.out.println(writePdfByTxt("f:/txt/ (2) - 副本.txt","f:/txt/ (2) - 副本.pdf"));
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	// Barcode QRCode
	/*
	 * public static void barcodePdf(){ Document doc = new
	 * Document(PageSize.LETTER); String myString = "http://www.google.com";
	 * 
	 * Barcode128 code128 = new Barcode128(); code128.setCode(myString.trim());
	 * code128.setCodeType(Barcode128.CODE128); Image code128Image =
	 * code128.createImageWithBarcode(cb, null, null);
	 * code128Image.setAbsolutePosition(10,700); code128Image.scalePercent(125);
	 * doc.add(code128Image);
	 * 
	 * BarcodeQRCode qrcode = new BarcodeQRCode(myString.trim(), 1, 1, null);
	 * Image qrcodeImage = qrcode.getImage();
	 * qrcodeImage.setAbsolutePosition(10,600); qrcodeImage.scalePercent(200);
	 * doc.add(qrcodeImage);
	 * 
	 * }
	 */
	
	/*
	 * 暂时不使用 public void writePdf2() throws DocumentException, IOException {
	 * 
	 * 
	 * OutputStream file = new FileOutputStream(new File("f:\\Test.pdf"));
	 * 
	 * Document document = new Document(); PdfWriter.getInstance(document,
	 * file); document.open();
	 * 
	 * AsianFontMapper mapper = new AsianFontMapper ("STSong-Light",
	 * "UniGB-UCS2-H"); BaseFont baseFontChinese =
	 * BaseFont.createFont("STSong-Light",
	 * "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED); Font fontChinese = new
	 * Font(baseFontChinese , 12 , Font.NORMAL); // Paragraph graph = new
	 * Paragraph(content , fontChinese); document.add(new
	 * Paragraph("Hello Kiran")); document.add(new Paragraph(new
	 * Date().toString()));
	 * 
	 * document.close(); file.close(); }
	 */
}
