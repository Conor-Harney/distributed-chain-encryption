# distributed-chain-encryption
A POC demonstrating use of a node cluster to implement a chain of encryptions, distributing partial data to each node. 

# Generating Keys
## Using OpenSSL
```
openssl genrsa -out private.pem 2048
openssl rsa -in private.pem -pubout -out public.pem
```

