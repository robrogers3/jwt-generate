require 'jwt'
require 'optparse'

options = {}
OptionParser.new do |opts|
  opts.banner = "Usage: generate.rb -k path/to/file -i your_issuer_id [-u a_usernamee]"
  opts.on("-h", "--help", "show help") do |h|
    options[:help] = opts.banner
  end

  opts.on("-k KEY", "--key KEY", "path to private key") do |key|
    options[:key] = key
  end

  opts.on("-i","--issuer_id ISSUER_ID","Your Issuer Id") do |issuer_id|
    options[:issuer_id] = issuer_id
  end
  opts.on("-u","--username USER_NAME", "A user name") do |user_name|
    options[:user_name] = user_name
  end
end.parse!

errors = []
[:key, :issuer_id].each do |key|
  unless options.has_key?(key)
    errors << "Option parameter %s is required" % key
  end
end

if errors.length > 0
  puts errors
  exit;
end

begin
  rsa_private_key = OpenSSL::PKey::RSA.new File.read options[:key]
rescue Errno::ENOENT => e
  puts "Caught the exception: #{e}"
  exit -1
end

current_time = Time.now.to_i

payload = {}
payload['iss'] = options[:issuer_id]
payload['iat'] = current_time
payload['exp'] = current_time + 1800

if options.key?(:user_name)
  payload['sub'] = options[:user_name]
end

token = JWT.encode payload, rsa_private_key, 'RS512'

puts token
# puts JWT.decode token, nil, false
