import com.jayway.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.AssertJUnit;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileWriter;
import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;

public class SecondTest {
    @Test
    public void getSoapMessage() throws SAXException {
        Response responseFromServer = given()
                .header("Bauth-token", "iYAqOoig9eMRIyXWYZlDYIxyGN04enzMRtjaqaNlNNEat7jSf8LFgzJ+kGFbWNx4QkUx9rhyGp6NZHD7416CLGAf6BM8JM7r0Sp65LaR2uD3PCkhqGnuYF7YTnKFAC1V8OTHQMqb8P70GBZFH8IFarrTAfYqnbFIRXI5INWOQrQXsMJB8u+KrALFiYyeOZ3kQHeCfItJ4HkYCIMLTSZfS+3Fo+p/DCn57IqTST4JuDNE8LEv+1GzaIfynWganuhj4gpSMz+H7Fcy59mau0W/C/5oPcYTcv6mwQJGGI3U6Jn5Lh+bCmnWFWYqBtEem0baqvaXm+uKxhxuKhLCSuKVEQ==")
                .body("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:at-consulting:integration:vimpelcom:tve-integration:types\">\n" +
                        "   <soapenv:Header/>\n" +
                        "   <soapenv:Body>\n" +
                        "      <urn:getInfoRequest ctn=\"9647070310\">\n" +
                        "         <!--1 or more repetitions:-->\n" +
                        "         <urn:requiredInfo>balance</urn:requiredInfo>\n" +
                        "      </urn:getInfoRequest>\n" +
                        "   </soapenv:Body>\n" +
                        "</soapenv:Envelope>")
                .when()
                .post("http://ms-glass017:8189/cxf/tveintegration");


        String response = responseFromServer.asString();
        writeInFile(response);

        try {
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse(responseFromServer.asInputStream());

            // Получаем корневой элемент
            Node root = document.getDocumentElement();
            Node soapBody = root.getFirstChild();
            Node infoResponse = soapBody.getFirstChild();
            Node balance = infoResponse.getFirstChild();
            System.out.println( balance.getNodeName() + ": " + balance.getChildNodes().item(0).getTextContent());

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void writeInFile(String response) {
        try(FileWriter writer = new FileWriter("C:\\Users\\DSGaliulin\\Desktop\\test-soap.log", false))
        {
            writer.write(response);
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
