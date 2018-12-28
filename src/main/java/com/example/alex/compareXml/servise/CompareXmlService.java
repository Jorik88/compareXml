package com.example.alex.compareXml.servise;

import com.example.alex.compareXml.configuration.CodeConfiguration;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.Diff;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.example.alex.compareXml.constant.Constant.*;

@Service
public class CompareXmlService {

    private Map<String, List<String>> codeAndExcludedNodesMap;

    public CompareXmlService(CodeConfiguration codeConfiguration) {
        this.codeAndExcludedNodesMap = new HashMap<>();
        codeAndExcludedNodesMap.put(CODE_260, codeConfiguration.getCode260());
        codeAndExcludedNodesMap.put(CODE_10615, codeConfiguration.getCode10615());
        codeAndExcludedNodesMap.put(CODE_77290, codeConfiguration.getCode77290());
    }

    public Diff process(String sourceFileName, String targetFileName, String code) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbf.newDocumentBuilder();
        Document sourceDoc = docBuilder.parse(sourceFileName);
        Document targetDoc = docBuilder.parse(targetFileName);

        Diff diff;
        switch (code) {
            case CODE_260:
                diff = compare(sourceDoc.getElementsByTagName(CODE_260_START_NODE).item(0),
                        targetDoc.getElementsByTagName(CODE_260_START_NODE).item(0),
                        codeAndExcludedNodesMap.get(code));
                break;

            case CODE_10615:
                diff = compare(sourceDoc.getElementsByTagName(CODE_10615_START_NODE).item(0),
                        targetDoc.getElementsByTagName(CODE_10615_START_NODE).item(0),
                        codeAndExcludedNodesMap.get(code));
                break;

            case CODE_77290:
                diff = compare(sourceDoc.getElementsByTagName(CODE_77290_START_NODE).item(0),
                        targetDoc.getElementsByTagName(CODE_77290_START_NODE).item(0),
                        codeAndExcludedNodesMap.get(code));
                break;

            default:
                throw new IllegalArgumentException("Wrong code!");
        }
        return diff;
    }

    private Diff compare(Node source, Node target, List<String> excludedNodes) {
        return DiffBuilder
                .compare(Input.fromNode(source))
                .withTest(Input.fromNode(target))
                .withNodeFilter(n -> !excludedNodes.contains(n.getNodeName()))
                .build();
    }
}
