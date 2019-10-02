Copyright 2019 Envestnet | Yodlee

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject
to the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.


THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR
ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
---------

This sample application shows how to mint (create) JSON Web Tokens (JWTs).
JWTS are an industry standard, and the basic authentication approach used
by Yodlee APIs.  JWTs are a convenient alternative to SAML authentication
used by large financial institutions.

1. Where to find more info on JWTs

The website jwt.io has much useful information.  It has a debugger,
allowing you to paste in a token and your key.  It will decode it, or issue
an error, allowing you to confirm that your code works.  The site also has
many open source libraries for token processing.  This sample app uses the
fusionauth library from https://github.com/fusionauth/fusionauth-jwt
Another widely used JWT library is Nimbus JOSE-JWT.
Another popular library is java-jwt3.4.0, which is used by us to read tokens.

JWTs come in two varieties: app tokens, and user tokens.  App tokens have
the "issuer id" value from your developer dashboard.  This identifies you,
the app developer (essentially, this is an application key).  

The User token has all the same info as an app token, and also one extra 
field, the subject.  The subject represents the specific end user you are 
working with.  In the developer portal, subjects are identified by 
20-character unique strings that you (the developer) assign to keep track of 
your users. You can see a list of 5 predefined test users in your developer account
dashboard.  They have this general appearance: "sbMem5c758c82bb1d12".  Outside the
developer portal, your app can use any scheme you find convenient to create 
and assign unique ids to your end users.  They can be from 3 to 150 characters
in length.

2. Configuring the sample app with your data

To generate a JWT, you need your issuer id and private key from your 
developer account dashboard.  Copy/paste your private key into a file and
input the private key file pathname as a string in the ConfigurationParams.java 
file. The private key is given to you in PEM format, which uses a limited
set of ASCII characters, allowing easy copy/paste.  Your issuer id can go 
directly in the ConfigurationParams file as a String.  Keep your private 
key secret and do not share with others.  Never put your private key into an
app or on a mobile device. 

To create a user token, you will also need to add the unique id of a user 
to the ConfigurationParams.java file.  In production, you will register
your users using the /user API.  In the sandbox, for testing,
use the id of one of the predefined test users, e.g. "sbMem5c758c82bb1d12".   
By convention, this is the predefined test user that you link your own
accounts to (when testing).

3. How to compile the app 

a. get the fusionauth crypto library from
   https://github.com/fusionauth/fusionauth-jwt and bring the library into
   your IDE as a maven project.  Make sure it shows no errors.  In Eclipse IDE,
   right clicking on the project name, then selecting "Maven", "Update project"
   clears any errors on importing.

b. bring the sample app's two java files into your IDE as a Java project 
   The TokenManagement.java file contains the functions to mint the token. It
   refers to ConfigurationParams.java to read your issuer id, and private key 
   file path.  

   Fill in those 2 parameters with your own data from the developer dashboard in 
   your Yodlee developer account.  Be sure to put the pathname to the file 
   containing your private key, not the private key itself, in the 
   ConfigurationParams.java file.

c. make sure the java project can see the crypto library (typically done by 
   putting the library in the build path of the java project).  

d. compile the app, and correct any errors the IDE shows.

4. How to run the app

Run TokenManagement as a Java application. The app generates both 
types of JWTs - an app token and a user token.  These are printed to stdout, 
ready to be copy pasted for further testing.

5. How to run the code with Postman

You can use the Postman app to test your newly generated JWTs by making
Yodlee's API calls, and substituting in the token that the Java code created.
Use a user token if you are making a call that involves a specific user, and use
an app token if you are making a call without a specific user.
Postman enables you to make API calls before writing any code.  This sample app
enables you to generate the JWTs needed to make those Postman calls.

In the Authorization tab of the API endpoint that you
are testing, select the "Bearer Token" type and paste in your generated JWT.
If the API calls you make with your JWTs are made successfully, you know
that your JWTs are valid and work.

6. Other encryption resources

https://developer.yodlee.com/apidocs/utility/encrypt.html - this page lets
you encrypt data, using your public key.  You may use this when you are
developing code that uses PII (Publicly Identifiable Information).  Yodlee
requires that PII data is encrypted before sending it to Yodlee servers.
More information on generating JWTs can be found on page
https://developer.yodlee.com/docs/api/1.1/using_json_web_tokens 

