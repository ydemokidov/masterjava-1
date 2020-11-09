package ru.javaops.masterjava.xml.util;

import org.xml.sax.SAXException;

import javax.xml.bind.*;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.concurrent.Callable;


/**
 * Marshalling/Unmarshalling JAXB facade
 */
public class JaxbParser {

    private JAXBContext ctx;
    protected Schema schema;

    private ThreadLocal<Unmarshaller> unmarshaller;
    private ThreadLocal<Marshaller> marshaller;

    public JaxbParser(Class... classesToBeBound) {
        try {
            init(JAXBContext.newInstance(classesToBeBound));
        } catch (JAXBException e) {
            throw new IllegalStateException(e);
        }
    }

    public JaxbParser(JAXBContext ctx){
        try {
            threadSafeInit(ctx);
        }catch (JAXBException e){
            throw new IllegalArgumentException(e);
        }
    }

    private static <T> T safe(Callable<T> fn) {
        try {
            return fn.call();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //    http://stackoverflow.com/questions/30643802/what-is-jaxbcontext-newinstancestring-contextpath
    public JaxbParser(String context) {
        try {
            init(JAXBContext.newInstance(context));
        } catch (JAXBException e) {
            throw new IllegalStateException(e);
        }
    }

    private void init(JAXBContext ctx) {
        this.ctx = ctx;
    }

    private void threadSafeInit(JAXBContext ctx) throws JAXBException {
        this.unmarshaller = ThreadLocal.withInitial(() -> safe(ctx::createUnmarshaller));
        this.marshaller = ThreadLocal.withInitial(() -> safe(ctx::createMarshaller));
    }

    public <T> T threadSafeUnmarshal(XMLStreamReader reader, Class<T> elementClass) throws JAXBException {
        return unmarshaller.get().unmarshal(reader, elementClass).getValue();
    }


    // Marshaller
    public JaxbMarshaller createMarshaller() {
        try {
            JaxbMarshaller marshaller = new JaxbMarshaller(ctx);
            if (schema != null) {
                marshaller.setSchema(schema);
            }
            return marshaller;
        } catch (JAXBException e) {
            throw new IllegalStateException(e);
        }
    }

    //    https://stackoverflow.com/a/7400735/548473
        public JaxbUnmarshaller createUnmarshaller() {
            try {
                JaxbUnmarshaller unmarshaller = new JaxbUnmarshaller(ctx);
                if (schema != null) {
                    unmarshaller.setSchema(schema);
                }
                return unmarshaller;
            } catch (JAXBException e) {
                throw new IllegalStateException(e);
            }
        }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    public void validate(String str) throws IOException, SAXException {
        validate(new StringReader(str));
    }

    public void validate(Reader reader) throws IOException, SAXException {
        schema.newValidator().validate(new StreamSource(reader));
    }
}
