package se.uu.ub.cora.tikacontentanalyzer;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.tika.Tika;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RealTikaContentAnalyzerTest {

	private static final String TEST_RESOURCE_PATH = "./src/test/resources/";
	private TikaContentAnalyzer analyzer;
	private Tika tika;

	@BeforeMethod
	public void beforeMethod() {
		tika = new Tika();
		analyzer = new TikaContentAnalyzer(tika);
	}

	@Test(enabled = true)
	public void testImageJpeg() throws Exception {
		File initialFile = new File(TEST_RESOURCE_PATH + "manga.jpg");
		InputStream resouce = new FileInputStream(initialFile);

		String mimeType = analyzer.getMimeType(resouce);

		assertEquals(mimeType, "image/jpeg");
	}

	@Test(enabled = true)
	public void testDocWord97_2003() throws Exception {
		File initialFile = new File(TEST_RESOURCE_PATH + "testDocWord97-2003.doc");
		InputStream resouce = new FileInputStream(initialFile);

		String mimeType = analyzer.getMimeType(resouce);

		assertEquals(mimeType, "application/msword");
	}

	@Test(enabled = true)
	public void testDocWord2007_365() throws Exception {
		File initialFile = new File(TEST_RESOURCE_PATH + "testDocWord2007-365.docx");
		InputStream resource = new FileInputStream(initialFile);

		String mimeType = analyzer.getMimeType(resource);

		assertEquals(mimeType,
				"application/vnd.openxmlformats-officedocument.wordprocessingml.document");
	}

	@Test(enabled = true)
	public void testSheetODF() throws Exception {
		File initialFile = new File(TEST_RESOURCE_PATH + "testSheetODF.ods");
		InputStream resouce = new FileInputStream(initialFile);

		String mimeType = analyzer.getMimeType(resouce);

		assertEquals(mimeType, "application/vnd.oasis.opendocument.spreadsheet");
	}

	@Test(enabled = true)
	public void testSheetExcel97_2003() throws Exception {
		File initialFile = new File(TEST_RESOURCE_PATH + "testSheetExcel97-2003.xls");
		InputStream resouce = new FileInputStream(initialFile);

		String mimeType = analyzer.getMimeType(resouce);

		assertEquals(mimeType, "application/vnd.ms-excel");
	}

	@Test(enabled = true)
	public void testSheetExcel2007_365() throws Exception {
		File initialFile = new File(TEST_RESOURCE_PATH + "testSheetExcel2007-365.xlsx");
		InputStream resource = new FileInputStream(initialFile);

		String mimeType = analyzer.getMimeType(resource);

		assertEquals(mimeType, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	}

	@Test(enabled = true)
	public void testPresentationODF() throws Exception {
		File initialFile = new File(TEST_RESOURCE_PATH + "testPresentationODF.odp");
		InputStream resouce = new FileInputStream(initialFile);

		String mimeType = analyzer.getMimeType(resouce);

		assertEquals(mimeType, "application/vnd.oasis.opendocument.presentation");
	}

	@Test(enabled = true)
	public void testPresentationPowerPoint97_2003() throws Exception {
		File initialFile = new File(TEST_RESOURCE_PATH + "testPresentationPowerPoint97-2003.ppt");
		InputStream resouce = new FileInputStream(initialFile);

		String mimeType = analyzer.getMimeType(resouce);

		assertEquals(mimeType, "application/vnd.ms-powerpoint");
	}

	@Test(enabled = true)
	public void testPresentationPowerPoint2007_365() throws Exception {
		File initialFile = new File(TEST_RESOURCE_PATH + "testPresentationPowerPoint2007-365.pptx");
		InputStream resource = new FileInputStream(initialFile);

		String mimeType = analyzer.getMimeType(resource);

		assertEquals(mimeType,
				"application/vnd.openxmlformats-officedocument.presentationml.presentation");
	}

	@Test(enabled = true)
	public void testMp4() throws Exception {
		File initialFile = new File(TEST_RESOURCE_PATH + "tikaVideo.mp4");
		InputStream resource = new FileInputStream(initialFile);

		String mimeType = analyzer.getMimeType(resource);

		assertEquals(mimeType, "video/mp4");
	}

	@Test(enabled = true)
	public void testMp3() throws Exception {
		File initialFile = new File(TEST_RESOURCE_PATH + "tikaAudio.mp3");
		InputStream resource = new FileInputStream(initialFile);

		String mimeType = analyzer.getMimeType(resource);

		assertEquals(mimeType, "audio/mpeg");
	}

	@Test(enabled = true)
	public void testTarGz() throws Exception {
		File initialFile = new File(TEST_RESOURCE_PATH + "Archive.tar.gz");
		InputStream resource = new FileInputStream(initialFile);

		String mimeType = analyzer.getMimeType(resource);

		assertEquals(mimeType, "application/gzip");
	}

	@Test(enabled = true)
	public void testZip() throws Exception {
		File initialFile = new File(TEST_RESOURCE_PATH + "Archive.zip");
		InputStream resource = new FileInputStream(initialFile);

		String mimeType = analyzer.getMimeType(resource);

		assertEquals(mimeType, "application/zip");
	}

	@Test(enabled = true)
	public void testPDF() throws Exception {
		File initialFile = new File(TEST_RESOURCE_PATH + "testPDF.pdf");
		InputStream resource = new FileInputStream(initialFile);

		String mimeType = analyzer.getMimeType(resource);

		assertEquals(mimeType, "application/pdf");
	}

	@Test(enabled = true)
	public void testPDFA1b() throws Exception {
		File initialFile = new File(TEST_RESOURCE_PATH + "testPDF-A1b.pdf");
		InputStream resource = new FileInputStream(initialFile);

		String mimeType = analyzer.getMimeType(resource);

		assertEquals(mimeType, "application/pdf");
	}

	@Test(enabled = true)
	public void testPDFA2b() throws Exception {
		File initialFile = new File(TEST_RESOURCE_PATH + "testPDF-A2b.pdf");
		InputStream resource = new FileInputStream(initialFile);

		String mimeType = analyzer.getMimeType(resource);

		assertEquals(mimeType, "application/pdf");
	}

}
