package com.m4technology.busvans.domain.generic;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.Map;

public class GenericRelatorio {

    public byte[] gerarRelatorioPdf(String caminho, Map<String, Object> parametros,
                                    Connection connection) throws FileNotFoundException, JRException {

        JasperPrint relatorio = JasperFillManager.fillReport(
                        ResourceUtils.getFile(caminho).getAbsolutePath(),
                        parametros,
                        connection
                );

        return JasperExportManager.exportReportToPdf(relatorio);

    }
}
