import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class Braille {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String POST_URL = "http://localhost:5000/translate/brailletotext/java";
    public String arr;
    public Braille(int []array, int chars){
        this.arr = ArrayToString(array,chars);
    }
    
    public String ArrayToString(int[] array, int n){
        String s = "";
        for(int i=0; i<n; i++){
            s = s.concat(Integer.toString(array[i]) + ",");
        }
        s = s.substring(0,s.length()-1);
        return s;
    }

    public String sendPOST(String text) throws IOException {
        URL obj = new URL(POST_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Content-Type", "text/plain");
        con.setRequestProperty("Accept", "text/plain");
        // For POST only - START
        String body = text;
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(body.getBytes());
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            System.out.println("POST request not worked");
            return "";
        }
    }

}