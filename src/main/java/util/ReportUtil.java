package util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.poi.ss.usermodel.*;

import com.lowagie.text.Row;

import model.ModelLogin;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReportUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public byte[] geraRelatorioPDF(List listaDados, String nomeRelatorio, ServletContext servletContext)
			throws Exception {

		JRBeanCollectionDataSource jrbCollectionDataSource = new JRBeanCollectionDataSource(listaDados);

		String caminhoJasper = servletContext.getRealPath("relatorio") + File.separator + nomeRelatorio + ".jasper";

		JasperPrint impressoaJasper = JasperFillManager.fillReport(caminhoJasper, new HashMap(),
				jrbCollectionDataSource);

		return JasperExportManager.exportReportToPdf(impressoaJasper);

	}

	public byte[] geraRelatorioExcelSemSubLista(List listaDados, String nomeRelatorio, ServletContext servletContext)
			throws Exception {

		JRBeanCollectionDataSource jrbCollectionDataSource = new JRBeanCollectionDataSource(listaDados);

		String caminhoJasper = servletContext.getRealPath("relatorio") + File.separator + nomeRelatorio + ".jasper";

		JasperPrint impressoaJasper = JasperFillManager.fillReport(caminhoJasper, new HashMap(),
				jrbCollectionDataSource);

		JRExporter exporter = new JRXlsExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressoaJasper);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

		exporter.exportReport();

		return baos.toByteArray();

	}

	public byte[] geraRelatorioExcelComSubLista(List listaDados, String nomeRelatorio, HashMap<String, Object> params,
			ServletContext servletContext) throws Exception {

		JRBeanCollectionDataSource jrbCollectionDataSource = new JRBeanCollectionDataSource(listaDados);

		String caminhoJasper = servletContext.getRealPath("relatorio") + File.separator + nomeRelatorio + ".jasper";

		JasperPrint impressoaJasper = JasperFillManager.fillReport(caminhoJasper, params, jrbCollectionDataSource);

		JRExporter exporter = new JRXlsExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressoaJasper);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

		exporter.exportReport();

		return baos.toByteArray();

	}

	public byte[] geraRelatorioPDF(List listaDados, String nomeRelatorio, HashMap<String, Object> params,
			ServletContext servletContext) throws Exception {

		JRBeanCollectionDataSource jrbCollectionDataSource = new JRBeanCollectionDataSource(listaDados);

		String caminhoJasper = servletContext.getRealPath("relatorio") + File.separator + nomeRelatorio + ".jasper";

		JasperPrint impressoaJasper = JasperFillManager.fillReport(caminhoJasper, params, jrbCollectionDataSource);

		return JasperExportManager.exportReportToPdf(impressoaJasper);

	}

	public byte[] geraRelatorioExcel(List listaDados, String nomeRelatorio, HashMap<String, Object> params,
			ServletContext servletContext) throws Exception {

		JRBeanCollectionDataSource jrbCollectionDataSource = new JRBeanCollectionDataSource(listaDados);

		String caminhoJasper = servletContext.getRealPath("relatorio") + File.separator + nomeRelatorio + ".jasper";

		JasperPrint impressoaJasper = JasperFillManager.fillReport(caminhoJasper, params, jrbCollectionDataSource);
		impressoaJasper.setProperty("net.sf.jasperreports.export.xls.ignore.page.margins", "true");
		impressoaJasper.setPageHeight(Integer.MAX_VALUE);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		JRXlsExporter exporter = new JRXlsExporter();// excel;

		exporter.setExporterInput(new SimpleExporterInput(impressoaJasper));
		// exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressoaJasper);

		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));

		SimpleXlsxReportConfiguration config = new SimpleXlsxReportConfiguration();
		config.setDetectCellType(true);
		config.setCollapseRowSpan(false);
		config.setOnePagePerSheet(false);
		exporter.setConfiguration(config);

		exporter.exportReport();

		return outputStream.toByteArray();

	}

}
