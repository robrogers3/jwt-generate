# JSON Web Token Generation Scripts

This repositoriy includes sample programs showing how to mint (create) JSON Web Tokens (JWTs) in:

- [Java](jwt-java)
- [Node](jwt-node)
- [Python](jwt-python)
- [Ruby](jwt-ruby)
- [PHP](jwt-php)

## About the Scripts

After you clone this repository, select the sub directory that references your language of choice.

Each sub directory has language specific installation and usage instructions in the related README file.

## About JSON Web Tokens

JWTS are an industry standard, and the basic authentication approach used by Yodlee APIs.  JWTs are a convenient alternative to SAML authentication used by large financial institutions.

## Where to find more info on JWTs

The website [jwt.io](http://jwt.io) has much useful information.  It has a debugger, allowing you to paste in a token and your key.  It will decode it, or issue an error, allowing you to confirm that your code works.  The site also has many open source libraries for token processing.

JWTs come in two varieties: app tokens, and user tokens.  App tokens have the "issuer id" value from your developer dashboard.  This identifies you, the app developer (essentially, this is an application key).

The User token has all the same info as an app token, and also one extra field, the subject.  The subject represents the specific end user you are working with.  In the developer portal, subjects are identified by 20-character unique strings that you (the developer) assign to keep track of your users. You can see a list of 5 predefined test users in your developer account dashboard.  They have this general appearance: "sbMem5c758c82bb1d12".  Outside the developer portal, your app can use any scheme you find convenient to create and assign unique ids to your end users.  They can be from 3 to 150 characters in length.
