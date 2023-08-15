package uz.pdp;


    import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.math.BigDecimal;

    public class TwilioExample {
        // Find your Account Sid and Token at twilio.com/console
        //ACcb4e8bad285be9e156937e85d0e8e223
        //b3a8658c01bc0fba4433403f58f41f62
        public static final String ACCOUNT_SID = "ACcb4e8bad285be9e156937e85d0e8e223";
        public static final String AUTH_TOKEN = "b3a8658c01bc0fba4433403f58f41f62";

        public static void main(String[] args) {
            twillioApi();
        }
        public static int twillioApi() {

            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            int code = codeGenerate();

            Message message = Message.creator(new PhoneNumber("+998993885321"), new PhoneNumber("+14094027971"), "Confirmation code " + code).create();

            System.out.println(message.getSid());

            return code;
        }

        public static int codeGenerate() {
            int min = 1000;
            int max = 9999;
            int code = (int) (Math.random() * (max - min + 1) + min);

            return code;
        }

//        public static void main(String[] args) {
//            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//            Message message = Message.creator(
//                            new com.twilio.type.PhoneNumber("+998993885321"),
//                            "Your message")
//                    .create();
//
//            System.out.println(message.getSid());
//        }
    }
