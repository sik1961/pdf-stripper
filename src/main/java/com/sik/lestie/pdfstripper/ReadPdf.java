package com.sik.lestie.pdfstripper;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Set;

import org.apache.pdfbox.pdmodel.PDDocument;

public class ReadPdf {

	public static void main(String[] args) throws IOException {

		// Security.addProvider( new
		// org.bouncycastle.jce.provider.BouncyCastleProvider() );

		// String fileMask = "*.pdf";
		File folder = new File("/Volumes/MacData/sik/Documents/");
		if (!folder.isDirectory()) {
			throw new IllegalArgumentException("Not a folder: " + folder);
		}

		for (File pdf : folder.listFiles()) {

			if (pdf.getName().endsWith(".pdf")) {

				try {

					PDDocument document = PDDocument.load(new File(pdf.getAbsolutePath()));

					document.getClass();

					if (!document.isEncrypted()) {

						Set<String> keys = document.getDocumentInformation().getMetadataKeys();

						System.out.println(pdf.getName() + " - " + document.getDocumentInformation().getTitle() + ", "
								+ document.getDocumentInformation().getAuthor() + ", "
								+ document.getDocumentInformation().getProducer() + ", "
								+ document.getDocumentInformation().getCreator() + ", "
								+ document.getDocumentInformation().getCreationDate().get(Calendar.YEAR));

						// PDFTextStripperByArea stripper = new PDFTextStripperByArea();
						// stripper.setSortByPosition(true);

						// PDFTextStripper tStripper = new PDFTextStripper();

						// String pdfFileInText = tStripper.getText(document);
						// tStripper.
						// System.out.println("Text:" + st);

						// split by whitespace
						// String lines[] = pdfFileInText.split("\\r?\\n");
						// for (String line : lines) {
						// System.out.println(line);
						// }

					}
					document.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.err.println("Meh!");
				}

			}
		}

	}
}
