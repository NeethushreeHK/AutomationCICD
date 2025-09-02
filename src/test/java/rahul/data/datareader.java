package rahul.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class datareader {
public static List<HashMap<String, String>> getjsondatatomap(String filepath) throws IOException {
	//read json to string
	String jsoncontent = FileUtils.readFileToString(
	        new File(filepath), 
	        StandardCharsets.UTF_8);
		
		//convert data to hashmap, instal new dependency Jackson databind
		ObjectMapper mapper=new ObjectMapper();
		//we are aking in which way it should return the data
		  List<HashMap<String, String>> data =  mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String, String>>>(){});
		  return data;
}
}
