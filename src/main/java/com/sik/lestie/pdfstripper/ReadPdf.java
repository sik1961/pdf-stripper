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
		File folder = new File("/Volumes/SDC001/");
		if (!folder.isDirectory()) {
			throw new IllegalArgumentException("Not a folder: " + folder);
		}

		for (File pdf : folder.listFiles()) {

			if (pdf.getName().endsWith(".pdf")) {

				try {

					String name = pdf.getName();
					String title = "";
					String author = "";
					String producer = "";
					String creator = "";
					int year = -1;

					PDDocument document = PDDocument.load(new File(pdf.getAbsolutePath()));

					document.getClass();

					if (!document.isEncrypted()) {

						// Set<String> keys =
						// document.getDocumentInformation().getMetadataKeys();

						// System.out.println(pdf.getName() + " - "
						title = document.getDocumentInformation().getTitle();
						author = document.getDocumentInformation().getAuthor();
						producer = document.getDocumentInformation().getProducer();
						creator = document.getDocumentInformation().getCreator();
						if (document.getDocumentInformation().getCreationDate() != null) {
							year = document.getDocumentInformation().getCreationDate().get(Calendar.YEAR);
						}

						document.close();
					}
					if (title != null && author != null && year != -1) {
						System.out.println(
								name + " - " + title + ", " + author + ", " + producer + ", " + creator + ", " + year);
					} else {
						System.out.println(getDetailsFromName(pdf.getName()));
					}

				} catch (Exception e) {
					// throw new IllegalStateException("Meh!",e);
					// System.err.println("Meh! " + e.getCause());
				}

			}
		}

	}

	private static String getDetailsFromName(String name) {
		String extLess = name.replace(".pdf", "");
		int firstDash = extLess.indexOf("-");
		String title = extLess.substring(0, firstDash);
		String rest = extLess.substring(firstDash + 1);
		
		return title + ", " + rest;
	}
}
