

import com.jayway.restassured.response.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.AssertJUnit;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;
import static org.codehaus.jackson.map.SerializationConfig.Feature.*;

public class FirstTest {
    @Test
    public void getRequestFindCapital() throws JSONException {

        Response response = given()
                .header("Bauth-token", "iYAqOoig9eMRIyXWYZlDYIxyGN04enzMRtjaqaNlNNEat7jSf8LFgzJ+kGFbWNx4QkUx9rhyGp6NZHD7416CLGAf6BM8JM7r0Sp65LaR2uD3PCkhqGnuYF7YTnKFAC1V8OTHQMqb8P70GBZFH8IFarrTAfYqnbFIRXI5INWOQrQXsMJB8u+KrALFiYyeOZ3kQHeCfItJ4HkYCIMLTSZfS+3Fo+p/DCn57IqTST4JuDNE8LEv+1GzaIfynWganuhj4gpSMz+H7Fcy59mau0W/C/5oPcYTcv6mwQJGGI3U6Jn5Lh+bCmnWFWYqBtEem0baqvaXm+uKxhxuKhLCSuKVEQ==")
                .when()
                .get("http://tst-ucpappl001.vimpelcom.ru:80/ucp/0001/subscriber/services/features/subscriber_no/9683500003/soc/RAG");


        JSONObject jsonObject = new JSONObject(response.asString());
        writeInFile(jsonObject);

        int code = jsonObject.getJSONObject("status").getInt("code");
        AssertJUnit.assertEquals(200, code);


         /*
                        parameters("ctn", "9647070310", "requiredInfo", "balance").
                        when().post("http://ms-glass017:8189/cxf/tveintegration");


        try {
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();

            Document doc = dBuilder.parse(response.asString());

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
        } catch (Exception e) {
            System.out.println("ERROR :" + e);

        }
 */
    }

    public void writeInFile(JSONObject jsonObject) {
        try(FileWriter writer = new FileWriter("C:\\Users\\DSGaliulin\\Desktop\\test.log", false))
        {
            jsonObject.write(writer, 1, 1);
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
