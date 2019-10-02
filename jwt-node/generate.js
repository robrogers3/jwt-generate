/**
 * Usage:
 * $ node generate.js --key="private.key" --issuer_id="YOUR_ISSUER_ID"
 * $ node generate.js --key="private.key" --issuer_id="YOUR_ISSUER_ID" --username="a_username"
 */

const fs = require('fs')
const jwt = require('jsonwebtoken')
const argv = require('yargs').argv

function generate() {
    const keyFile = argv.key || 'devSandbox.key'

    //Location of the file with your private key
    const privateKey = fs.readFileSync(keyFile,"utf8");

    const currentTime =  Math.floor(Date.now() / 1000);

    const signOptions = {
	algorithm: "RS512"  //Yodlee requires RS512 algorithm when encoding JWT
    };

    let payload = {};

    // The issuer id from the API Dashboard
    payload.iss = argv.issuer || 'no issuer id set'
    payload.iat = currentTime;  //Epoch time when token is issued in seconds
    payload.exp = currentTime + 1800;  //Epoch time when token is set to expire. Must be 1800 seconds

    //generate user token if present in argv
    if (typeof argv.username != 'undefined') {
        payload.sub = argv.username
    }

    return token = jwt.sign(payload, privateKey, signOptions);

}
module.exports = generate

if (require.main === module) {
    console.log('token:', generate())
}
