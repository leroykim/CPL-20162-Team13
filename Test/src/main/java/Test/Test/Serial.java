package Test.Test;

/**
 * Hello world!
 *
 */
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.OutputStream;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Scanner;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.IDatatype;
import ca.uhn.fhir.model.dstu2.composite.CodingDt;
import ca.uhn.fhir.model.dstu2.composite.HumanNameDt;
import ca.uhn.fhir.model.dstu2.composite.IdentifierDt;
import ca.uhn.fhir.model.dstu2.composite.QuantityDt;
import ca.uhn.fhir.model.dstu2.resource.Observation;
import ca.uhn.fhir.model.dstu2.resource.Observation.Component;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.dstu2.valueset.AdministrativeGenderEnum;
import ca.uhn.fhir.model.dstu2.valueset.NameUseEnum;
import ca.uhn.fhir.model.dstu2.valueset.ObservationStatusEnum;
import ca.uhn.fhir.model.primitive.TimeDt;
import ca.uhn.fhir.parser.IParser;


public class Serial
{
    public Serial()
    {
        super();
    }
    
    void connect ( String portName ) throws Exception
    {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if ( portIdentifier.isCurrentlyOwned() )
        {
            System.out.println("Error: Port is currently in use");
        }
        else
        {
            
            CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);
            
            if ( commPort instanceof SerialPort )
            {
               
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(115200,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
                
                InputStream in = serialPort.getInputStream();
                
                (new Thread(new SerialReader(in))).start();

            }
            else
            {
                System.out.println("Error: Only serial ports are handled by this example.");
            }
        }     
    }
    
  
    public static class SerialReader implements Runnable 
    {
        InputStream in;
        sendToserver sd = new sendToserver();
        		
        public SerialReader ( InputStream in )
        {
            this.in = in;
        }
       
        public int ByteArrayToInt(byte[] bytes) 
        {

            ByteBuffer byte_buf = ByteBuffer.allocate(4);

            final byte[] change = new byte[4];


            for (int i = 0; i < 4; i++) {

                    change[i] = (byte) 0x00;

            }

            for (int i = 0; i < bytes.length; i++) {

                    change[3 - i] = bytes[bytes.length - 1 - i];

            }


            byte_buf = ByteBuffer.wrap(change);

            byte_buf.order(ByteOrder.BIG_ENDIAN);


            return byte_buf.getInt();

        }
        
        public void run ()
        {
            byte[] buffer = new byte[4];
            int glen = -1;
            try
            {
                while ( ( glen = this.in.read(buffer, 0, 4)) > -1 )
                {
                	sd.getBPM(ByteArrayToInt(buffer));
                	sd.urlSet();
                	sd.wri();
                    //System.out.print(new String(buffer,0,glen));
                }
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }            
        }
    }
    
    public static class sendToserver
    {
    	int bpm;
    	URL url;
    	HttpURLConnection conn;
    	
    	void getBPM(int b)
    	{
    		bpm = b;
    	}
    	
    	void urlSet() throws IOException
    	{
    		url =  new URL("https://learning-machine-fhir.herokuapp.com/repository/save/patients");
    		conn = (HttpURLConnection) url.openConnection();
       	
    		conn.setDoInput(true);
    		conn.setDoOutput(true);
    		conn.setUseCaches(false);
    		conn.setRequestProperty("Content-Type", "application/fhir+json");
    		conn.setRequestProperty("Accept", "*/*");
    		conn.setRequestProperty("X-Requested-with", "XMLHttpRequest");
    		conn.setRequestMethod("POST");
    		InputStream is = conn.getInputStream();
    		Scanner scan = new Scanner(is);
    	}
    	
    	void wri() throws IOException
    	{
    	   FhirContext ctx = FhirContext.forDstu2();    
       	   Patient patient = new Patient();
       	   patient.addIdentifier().setSystem("http://example.com/fictitious-mrns").setValue("MRN001");
       	   patient.addName().setUse(NameUseEnum.OFFICIAL).addFamily("Anderson").addGiven("rollingstone").addGiven("K");
       	   patient.addTelecom();
        
           
       	   patient.setGender(AdministrativeGenderEnum.MALE);
           
       	   Observation observer = new Observation();
       	   observer.setId("Heart-rate-beat");
       	   observer.addIdentifier().setSystem("http://example.com/fictitious-mrns").setValue("MRN001");
       	   observer.setStatus(ObservationStatusEnum.FINAL);
           
       	   CodingDt coding = observer.getCode().addCoding();
       	   coding.setCode("76282-3").setSystem("http://loinc.org").setDisplay("Heart rate.beat-to-beat");
            
       	   QuantityDt value = new QuantityDt();
       	   value.setValue(bpm).setSystem("http://unitsofmeasure.org").setCode("bpm");
       	   observer.setValue(value);
            
       	   TimeDt time = new TimeDt();
       	   time.setValue("2014-01-30T22:35:23+11:00");
       	   observer.setValue(time);
            
      	   
       	   String encoded = ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(patient);
       	   OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
       	   wr.write(encoded);
       	   wr.flush();
       	  
       	   System.out.println(encoded);
       	   encoded = ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(observer);
       	   wr.write(encoded);
     	   wr.flush();
    	}
    }

   
    public static void main ( String[] args )
    {
        
   	   try
        {
            (new Serial()).connect("COM3");
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        
    }
}