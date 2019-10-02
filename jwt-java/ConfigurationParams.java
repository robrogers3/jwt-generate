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

public class ConfigurationParams {
	// Your values for these 2 variables are fixed, after you set them
	// The values here are merely indicative of the values you need to find and set.
	// Get your iss value from the dashboard page of your Yodlee developer account
	// iss is the "Issuer ID" (your Yodlee-assigned unique developer ID).
	
	public static final String iss = "3d2d9393-b3fa-3568-bb50-abadcafebeef";

	
	// Get your private RSA key by clicking "view private key" on the dashboard
	// copy/paste the private key into a local file on your PC, and keep it
	// confidential. The key is given to you in PEM format, so put it in a
	// file with a PEM extension, and put the pathname to your file in this variable.
	// Once you move out of sandbox environment you will generate your own RSA keys.
	public static final String privateKeyFile = "/Users/plinden/token/priv.pem";
	
}
