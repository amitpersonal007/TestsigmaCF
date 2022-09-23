
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;
import org.openqa.selenium.interactions.Actions;
import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class GmailFetch extends TestsigmaCustomFunctions{
StringBuffer sb = new StringBuffer();
    @CustomTestStep
    public TestStepResult read(String out) {
        Properties props = new Properties();
        TestStepResult result = new TestStepResult();
        String word="";
System.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");

        try {
props.put("mail.smtp.starttls.required", "true");
props.put("mail.smtp.ssl.protocols", "TLSv1.2");
            props.put("mail.smtp.auth","true");
            props.put("mail.smtp.starttls.enable","true");
            props.put("mail.smtp.host","smtp.gmail.com");
            props.put("mail.smtp.port","465");
            Session session = Session.getDefaultInstance(props, null);
            Store store = session.getStore("imaps");
            store.connect("smtp.gmail.com","lifeaihdfc@gmail.com","kkmwlifnbgxhvjdf");
            Folder inbox = store.getFolder("inbox");
            inbox.open(Folder.READ_ONLY);
            int messageCount = inbox.getMessageCount();
            Message[] messages = inbox.getMessages();
            String subject = getTextFromMessage(messages[messageCount-1]);
          sb.append("Message is:"+subject+"<br>");
            int cnt = 0;
            String[] cont =subject.split(" ");
            String check = "is";
            for(int i=0; i<cont.length; i++){

                if (cont[i].contains(check)){
                  sb.append("String Before: "+cont[cnt]+"<br>");
                    cnt= i+1;
                    word = cont[cnt];
                  sb.append("Inside loop word: "+word+"<br>");

                try{
                       int temp =  Integer.parseInt(word.trim());
                        break;
                    }catch(Exception e){
                     sb.append("<br> Not a string::"+word);
                    }
                }
            }

			sb.append("OTP:"+word+"<br>");
            setRuntimeData(out,word);

            inbox.close(true);
            store.close();
            result.setStatus(ResultConstants.SUCCESS);
            sb.append("<br>Value in runtime variable is: " +word+"<br>");
                      result.setMessage(sb.toString());

            return result;}
        	catch (Exception e) {
            e.printStackTrace();
            result.setStatus(ResultConstants.FAILURE);

            result.setMessage("Failed"+e);

            return result;
        }

    }
    private String getTextFromMessage(Message message) throws Exception {
        String result = "";
        if (message.isMimeType("text/plain")) {
            result = message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            result = getTextFromMimeMultipart(mimeMultipart);
        }
        return result;
    }
    private String getTextFromMimeMultipart(
            MimeMultipart mimeMultipart)  throws Exception{
        String result = "";
        int count = mimeMultipart.getCount();
      sb.append("Multipart count:"+count+"<br>");
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result = result + "\n" + bodyPart.getContent();
                break; // without break same text appears twice in my tests
            } 
          else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                result = result + "\n" + html; 
          }
             else if (bodyPart.getContent() instanceof MimeMultipart){
                result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
            }
        }
        return result;
    }
}