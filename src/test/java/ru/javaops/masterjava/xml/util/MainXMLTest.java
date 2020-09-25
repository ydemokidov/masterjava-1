package ru.javaops.masterjava.xml.util;

import com.google.common.io.Resources;
import org.junit.Test;

import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

public class MainXMLTest {
    @Test
    public void mainXMLTest() throws Exception{
        try(StaxStreamProcessor staxStreamProcessor =
                    new StaxStreamProcessor(Resources.getResource("payload.xml").openStream())){
            String email="";
            String userName="";
            XMLStreamReader reader = staxStreamProcessor.getReader();
            while(reader.hasNext()){
                int event = reader.next();
                //User begin tag
                if(event == XMLEvent.START_ELEMENT){

                    if("User".equals(reader.getLocalName())){
                        email = reader.getAttributeValue(null,"email");
                    }
                    if("fullName".equals(reader.getLocalName())){
                        userName = reader.getElementText();
                    }
                }

                //User end tag
                if(event == XMLEvent.END_ELEMENT){
                    if("User".equals(reader.getLocalName())){
                        System.out.println(userName+": "+email);
                    }
                }
            }

        }
    }
}
