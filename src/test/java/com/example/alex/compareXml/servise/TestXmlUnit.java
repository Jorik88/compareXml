package com.example.alex.compareXml.servise;

import com.example.alex.compareXml.servise.CompareXmlService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xml.sax.SAXException;
import org.xmlunit.diff.Diff;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestXmlUnit {

    private String code260 = "260";
    private String code10615 = "10615";
    private String code77290 = "77290";
    private String sourceFilePath260 = "src/test/resources/test260.xml";
    private String targetFilePath260 = "src/test/resources/test260!.xml";
    private String sourceFilePath10615 = "src/test/resources/test10615.xml";
    private String targetFilePath10615 = "src/test/resources/test10615!.xml";
    private String sourceFilePath77290 = "src/test/resources/test77290.xml";
    private String targetFilePath77290 = "src/test/resources/test77290!.xml";

    @Autowired
    private CompareXmlService service;

    @Test
    public void testProcess() throws IOException, SAXException, ParserConfigurationException {
        Diff process = service.process(sourceFilePath260, targetFilePath260, code260);
        System.out.println(process.hasDifferences());
        System.out.println(process.getDifferences());
    }

}
