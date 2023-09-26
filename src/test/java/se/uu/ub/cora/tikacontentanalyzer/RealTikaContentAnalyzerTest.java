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

	@Test(enabled = false)
	public void testImageJpeg() throws Exception {
		File initialFile = new File(TEST_RESOURCE_PATH + "manga.jpg");
		InputStream resouce = new FileInputStream(initialFile);

		String mimeType = analyzer.getMimeType(resouce);

		assertEquals(mimeType, "image/jpeg");
	}

	@Test(enabled = false)
	public void testDocWord97_2003() throws Exception {
		File initialFile = new File(TEST_RESOURCE_PATH + "testDocWord97-2003.doc");
		InputStream resouce = new FileInputStream(initialFile);

		String mimeType = analyzer.getMimeType(resouce);

		assertEquals(mimeType, "application/x-tika-msoffice");
	}

	@Test(enabled = false)
	public void testDocWord97_2003_Filename() throws Exception {
		File initialFile = new File(TEST_RESOURCE_PATH + "testDocWord97-2003.doc");
		InputStream resouce = new FileInputStream(initialFile);

		String mimeType = analyzer.getMimeTypeWithFileName(resouce, initialFile.getName());

		assertEquals(mimeType, "application/msword");
	}

	@Test(enabled = false)
	public void testDocWord2007_365() throws Exception {
		File initialFile = new File(TEST_RESOURCE_PATH + "testDocWord2007-365.docx");
		InputStream resource = new FileInputStream(initialFile);

		String mimeType = analyzer.getMimeType(resource);

		assertEquals(mimeType, "application/x-tika-ooxml");
	}

	@Test(enabled = false)
	public void testDocWord2007_365_FileName() throws Exception {
		File initialFile = new File(TEST_RESOURCE_PATH + "testDocWord2007-365.docx");
		InputStream resource = new FileInputStream(initialFile);

		String mimeType = analyzer.getMimeTypeWithFileName(resource, initialFile.getName());

		assertEquals(mimeType,
				"application/vnd.openxmlformats-officedocument.wordprocessingml.document");
	}

	@Test(enabled = false)
	public void testMp4_FileName() throws Exception {
		File initialFile = new File(TEST_RESOURCE_PATH + "tikaVideo.mp4");
		InputStream resource = new FileInputStream(initialFile);

		String mimeType = analyzer.getMimeTypeWithFileName(resource, initialFile.getName());

		assertEquals(mimeType, "video/mp4");
	}

}
