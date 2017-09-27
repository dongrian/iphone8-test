package com.gaeasoft.preorder.remote;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Base64Utils;

public class SmsApiProvider {
	private static final Logger logger = LoggerFactory.getLogger(SmsApiProvider.class);

	private static final String USER_ID = "diocean"; // SMS 아이디
	private static final String SECURE = "b4db2936fd03a28b26d8c4c5e5a744db"; // 인증키
	private static final String SPHONE1 = "1544";
	private static final String SPHONE2 = "0010";
	private static final String SPHONE3 = "";

	private static final String API_URL = "https://sslsms.cafe24.com/sms_sender.php";
	private static final String USER_AGENT = "Mozilla/5.0";
	private static final String CHARSET = "UTF-8";

    @Value("${g6.sms.test}")
    private static boolean isTest;
//	private static final boolean isTest = false;
	
    public static void main(String[] args) {
        sendSms("test sms", "01062228476");
//        sendSms2("test sms", "01062228476");
    }

	public static void sendSms(String msg, String phoneNumber) {
		try {
			URL obj = new URL(API_URL);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setRequestProperty("Accept-Charset", CHARSET);
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", USER_AGENT);
			
			logger.debug("{}: {}", msg, phoneNumber);

			String postParams = "user_id=" + base64Encode(USER_ID)
					+ "&secure=" + base64Encode(SECURE)
					+ "&msg=" + base64Encode(msg)
					+ "&rphone=" + base64Encode(phoneNumber)
					+ "&sphone1=" + base64Encode(SPHONE1) 
					+ "&sphone2=" + base64Encode(SPHONE2) 
					+ "&sphone3=" + base64Encode(SPHONE3)
					+ "&mode=" + base64Encode("1") 
					+ "&smsType=" + base64Encode("S"); // SMS/LMS

			// test 모드일 경우, 실제로 SMS발송은 되지 않고 성공적인 호출 확인 여부만 확인 할 수 있도록함
			if (isTest) {
				postParams += "&testflag=" + base64Encode("Y");
			}
			
			logger.debug("### postParams: {}", postParams);

			// For POST only - START
			con.setDoOutput(true);
			OutputStream os = con.getOutputStream();
			os.write(postParams.getBytes());
			os.flush();
			os.close();
			// For POST only - END

			int responseCode = con.getResponseCode();
			logger.debug("POST Response Code :: " + responseCode);

			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer buf = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					buf.append(inputLine);
				}
				in.close();

				logger.debug("SMS Content : " + buf.toString());
			} else {
				logger.error("POST request not worked");
			}
		} catch (IOException ex) {
			logger.error("SMS IOException : " + ex.getMessage());
		}
	}
	
	public static void sendSms2(String message, String phoneNumber) {
		try {
			String charsetType = CHARSET;
	        String sms_url = "";
	        sms_url = API_URL; // SMS 전송요청 URL
	        String user_id = base64Encode(USER_ID); // SMS아이디
	        String secure = base64Encode(SECURE);//인증키
	        String msg = base64Encode(message);
	        String rphone = base64Encode(phoneNumber);
	        String sphone1 = base64Encode(SPHONE1);
	        String sphone2 = base64Encode(SPHONE2);
	        String sphone3 = base64Encode(SPHONE3);
	        String rdate = base64Encode("");
	        String rtime = base64Encode("");
	        String mode = base64Encode("1");
	        String subject = base64Encode("");
//	        if(nullcheck(request.getParameter("smsType"), "").equals("L")) {
//	            subject = base64Encode(nullcheck(request.getParameter("subject"), ""));
//	        }
	        String testflag = base64Encode("Y");
	        String destination = base64Encode("");
	        String repeatFlag = base64Encode("");
	        String repeatNum = base64Encode("");
	        String repeatTime = base64Encode("");
	        String returnurl = base64Encode("");
	        String nointeractive = base64Encode("");
	        String smsType = base64Encode("S");

	        String[] host_info = sms_url.split("/");
	        String host = host_info[2];
	        String path = "/" + host_info[3];
	        int port = 80;

	        // 데이터 맵핑 변수 정의
	        String arrKey[]
	            = new String[] {"user_id","secure","msg", "rphone","sphone1","sphone2","sphone3","rdate","rtime"
	                        ,"mode","testflag","destination","repeatFlag","repeatNum", "repeatTime", "smsType", "subject"};
	        String valKey[]= new String[arrKey.length] ;
	        valKey[0] = user_id;
	        valKey[1] = secure;
	        valKey[2] = msg;
	        valKey[3] = rphone;
	        valKey[4] = sphone1;
	        valKey[5] = sphone2;
	        valKey[6] = sphone3;
	        valKey[7] = rdate;
	        valKey[8] = rtime;
	        valKey[9] = mode;
	        valKey[10] = testflag;
	        valKey[11] = destination;
	        valKey[12] = repeatFlag;
	        valKey[13] = repeatNum;
	        valKey[14] = repeatTime;
	        valKey[15] = smsType;
	        valKey[16] = subject;

	        String boundary = "";
	        Random rnd = new Random();
	        String rndKey = Integer.toString(rnd.nextInt(32000));
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        byte[] bytData = rndKey.getBytes();
	        md.update(bytData);
	        byte[] digest = md.digest();
	        for(int i =0;i<digest.length;i++)
	        {
	            boundary = boundary + Integer.toHexString(digest[i] & 0xFF);
	        }
	        boundary = "---------------------"+boundary.substring(0,11);

	        // 본문 생성
	        String data = "";
	        String index = "";
	        String value = "";
	        for (int i=0;i<arrKey.length; i++)
	        {
	            index =  arrKey[i];
	            value = valKey[i];
	            data +="--"+boundary+"\r\n";
	            data += "Content-Disposition: form-data; name=\""+index+"\"\r\n";
	            data += "\r\n"+value+"\r\n";
	            data +="--"+boundary+"\r\n";
	        }

	        //out.println(data);

	        InetAddress addr = InetAddress.getByName(host);
	        Socket socket = new Socket(host, port);
	        // 헤더 전송
	        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), charsetType));
	        wr.write("POST "+path+" HTTP/1.0\r\n");
	        wr.write("Content-Length: "+data.length()+"\r\n");
	        wr.write("Content-type: multipart/form-data, boundary="+boundary+"\r\n");
	        wr.write("\r\n");

	        // 데이터 전송
	        wr.write(data);
	        wr.flush();

	        // 결과값 얻기
	        BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream(),charsetType));
	        String line;
	        String alert = "";
	        ArrayList tmpArr = new ArrayList();
	        while ((line = rd.readLine()) != null) {
	            tmpArr.add(line);
	        }
	        wr.close();
	        rd.close();

	        String tmpMsg = (String)tmpArr.get(tmpArr.size()-1);
	        String[] rMsg = tmpMsg.split(",");
	        String Result= rMsg[0]; //발송결과
	        String Count= ""; //잔여건수
	        if(rMsg.length>1) {Count= rMsg[1]; }

	                        //발송결과 알림
	        if(Result.equals("success")) {
	            alert = "성공적으로 발송하였습니다.";
	            alert += " 잔여건수는 "+ Count+"건 입니다.";
	        }
	        else if(Result.equals("reserved")) {
	            alert = "성공적으로 예약되었습니다";
	            alert += " 잔여건수는 "+ Count+"건 입니다.";
	        }
	        else if(Result.equals("3205")) {
	            alert = "잘못된 번호형식입니다.";
	        }
	        else {
	            alert = "[Error]"+Result;
	            alert += " 잔여건수는 "+ Count+"건 입니다.";
	        }
	        
	        logger.info(alert);

//	        out.println(nointeractive);
//
//	        if(nointeractive.equals("1") && !(Result.equals("Test Success!")) && !(Result.equals("success")) && !(Result.equals("reserved")) ) {
//	            out.println("<script>alert('" + alert + "')</script>");
//	        }
//	        else if(!(nointeractive.equals("1"))) {
//	            out.println("<script>alert('" + alert + "')</script>");
//	        }
//
//
//	        out.println("<script>location.href='"+returnurl+"';</script>");

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
    public static String base64Encode(String str)  throws java.io.IOException {
        sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        byte[] strByte = str.getBytes();
        String result = encoder.encode(strByte);
        return result ;
    }

}
