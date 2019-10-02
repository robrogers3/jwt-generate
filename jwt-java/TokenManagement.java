// Copyright 2019 Envestnet | Yodlee
//
// Permission is hereby granted, free of charge, to any person obtaining
// a copy of this software and associated documentation files (the
// "Software"), to deal in the Software without restriction, including
// without limitation the rights to use, copy, modify, merge, publish,
// distribute, sublicense, and/or sell copies of the Software, and to
// permit persons to whom the Software is furnished to do so, subject
// to the following conditions:
// 
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
// IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR
// ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
// CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
// WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.rsa.RSASigner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class TokenManagement {
    // Variable sub ("subject") represents an end user, and you change the value 
    // to change the specific user you are working with.
    public static String sub = "sbMem5c758c82bb1d12"; 
    // 20 char ID representing one of your end users.  The value is hardcoded here
    // for demonstration purposes.  
    // Get the IDs of the 5 test users from the dashboard page of your Yodlee
    // developer account.  Login and click "view test users",
    // then use "test user name" for any of the predefined test users.  The value used   
    // here is the second dummy test user which, by convention, you (the developer) 
    // use to link your own real world accounts.  Most API calls require you
    // to give the ID of the user you are working with (the user whose accounts or
    // transactions you want).  That user ID goes into the JWT we are about to make.
    // In the sandbox, use the 5 predefined users for testing;  
    // you cannot register additional test users.
    // In production, you will generate, issue, and maintain the IDs for 
    // each of your real world end users.  

	static private String getKey(String file) {
		String privKey;
		try {
			privKey = new String(Files.readAllBytes((Paths.get(file))));
		} catch (IOException ex) {
			privKey = null;
		}
		return privKey;
	}

	static public String generateJWT(String subject) {

		String privateKey = getKey(ConfigurationParams.privateKeyFile);

		Signer signer = RSASigner.newSHA512Signer(privateKey);

		JWT jwt = new JWT().setIssuer(ConfigurationParams.iss)
				.setIssuedAt(ZonedDateTime.now(ZoneOffset.UTC))
				.setSubject(subject)
				.setExpiration(ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(30));

		String encodedJWT = JWT.getEncoder().encode(jwt, signer);

		return encodedJWT;

	}

	static public String generateAdminJWT() {

		String privateKey = getKey(ConfigurationParams.privateKeyFile);

		Signer signer = RSASigner.newSHA512Signer(privateKey);

		JWT jwt = new JWT().setIssuer(ConfigurationParams.iss)
				.setIssuedAt(ZonedDateTime.now(ZoneOffset.UTC))
				.setExpiration(ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(30));

		String encodedJWT = JWT.getEncoder().encode(jwt, signer);
		return encodedJWT;
	}

	public static void main(String[] args) {
		String token = generateAdminJWT(); 
		System.out.println("The app token is: " + token + "\n");
        
		String user = sub;
		token = generateJWT(user); 
		System.out.println("The user token is: " + token);
	}
}
