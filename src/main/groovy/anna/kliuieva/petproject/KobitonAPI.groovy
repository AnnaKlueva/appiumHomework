package anna.kliuieva.petproject;

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder

public class KobitonAPI {

    public static KobitonDevice getAvailableDevice(String platformName) {
        String response = getAllDevices();
        ObjectMapper objectMapper = new ObjectMapper();
        KobitonListDevices containerList = null;
        try {
            containerList = objectMapper.readValue(response, KobitonListDevices.class);
        } catch (IOException e) {
            throw new RuntimeException("JSON can't be mapped to object: " + e.toString());
        }
        //List<KobitonDevices>
        KobitonDevice device = containerList.getDevices().find {item -> item.getIsBooked() == "false" && item.getIsHidden() == "false" && item.getIsOnline() == "true" && item.getPlatformName() == platformName };
        return device;
    }

    private static String getAllDevices() {
        def url = 'https://api.kobiton.com/v1/devices'
        def get = new HttpGet(url)
        get.addHeader("Authorization", "Basic Y2FtZWxlb25hODoxNjdjNWQ2Zi04N2IwLTRkMjUtYWU4ZS05MTgzMmE5NTc5ODA=")
        def client = HttpClientBuilder.create().build()
        def response = client.execute(get)
        def bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))
        def jsonResponse = bufferedReader.getText()

        return jsonResponse
    }
}
