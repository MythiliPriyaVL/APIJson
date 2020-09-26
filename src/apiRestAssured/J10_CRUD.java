package apiRestAssured;

import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class J10_CRUD {
	
	public static void main(String[] args) throws Exception{
					
		// Read XL ; Global Variable;
		String[][] xlAPIDemo;
		int apiDemo_r;
		String sPath ="C:\\Users\\parimaladevi\\API_Details\\api\\api\\api-keywords.xls";
		String sPath1 ="C:\\Users\\parimaladevi\\API_Details\\api\\api\\api-keywords_res.xls";

		xlAPIDemo = JReadWriteXls.readXL(sPath,"APIDemo");
		apiDemo_r = xlAPIDemo.length;
		System.out.println("xlAPIDemo length1" +apiDemo_r);	
	
		String vURI,vRequestType;		
		
		//go through each row and identify what to do
		for(int i=2;i<apiDemo_r;i++)			
			{
				//Go through each corresponding teststep
				vURI= xlAPIDemo[i][0];
				vRequestType=xlAPIDemo[i][1];
				try
				{
				 	if(vRequestType.equalsIgnoreCase("GET"))
				 	
				 	{
						int myResponse;
						System.out.println("My Input URI is " +vURI);
						RestAssured.baseURI = vURI;
						RequestSpecification myHTTPSpec = RestAssured.given();
						Response res1 = myHTTPSpec.request(Method.GET);
						myResponse = res1.getStatusCode();
					System.out.println("The response from this URI is " +myResponse);
				 	}
				 	else if(vRequestType.equalsIgnoreCase("POST"))
				 	{
				 		System.out.println("My Input URI is " +vURI);
					JSONObject uData = new JSONObject();
					uData.put("email","eve.holt@reqres.in");	
					//uData.put("password","cityslicka");
					RequestSpecification myHTTPSpec = RestAssured.given();
					
					myHTTPSpec.body(uData.toString());	//----Error myHTTPSpec.body(uData.toJSONString());
					
					Response res2 = myHTTPSpec.request(Method.POST);
					int myResponse2 = res2.getStatusCode();
					System.out.println("The response from this URI is " +myResponse2);
				 	}
				 	else if(vRequestType.equalsIgnoreCase("DELETE"))
				 	{
				 		System.out.println("My Input URI is " +vURI);
				 		RequestSpecification myHTTPSpec = RestAssured.given();
					Response res3 = myHTTPSpec.request(Method.DELETE);
					
					int myResponse3 = res3.getStatusCode();
					System.out.println("The response from this URI is " +myResponse3);
				 	}
				}
				catch(Exception e)
				{
					System.out.println("Caught an exception "+e);
				}
			}
			
		JReadWriteXls.writeXLSheets(sPath1, "apiDemoResult", 1, xlAPIDemo);
	}
}
