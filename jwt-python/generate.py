"""jwt_generate.

Usage:
  jwt_generate.py [--key=<str>] [--issuer=<str>] [--username=<str>]
  jwt_generate.py (-h | --help)

Options:
  --key=<str>       path to private key
  --issuer=<str>    issuer id from api connsole
  --username=<str>  username if generating user token
  -h --help         Show this screen.
"""

import jwt
import time
from docopt import docopt


def generate(arguments):

    del arguments['--help']

    proceed = True
    for k, v in arguments.items():
        if k == '--username':
            continue

        if v is None:
            print(f"parameter {k} is required")
            proceed = False

    if proceed is False:
        return 'sorry'

    privateKeyPath = arguments['--key']

    with open(privateKeyPath) as f:
        privateKey = f.read()

    currentTime = int(time.time())

    payload = {}
    payload['iat'] = currentTime
    payload['exp'] = currentTime + 1800
    payload['iss'] = arguments['--issuer']

    if '--username' in arguments:
        payload['sub'] = arguments['--username']

    # RS512 requires $ pip install cryptography
    encoded_jwt = jwt.encode(payload, privateKey, algorithm='RS512')
    return encoded_jwt


if __name__ == '__main__':
    arguments = docopt(__doc__)

    print(generate(arguments))
