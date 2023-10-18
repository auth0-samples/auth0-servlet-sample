# NIMBUS: 10000 requests with 10 concurrent:
echo "----- EXECUTING NIMBUS VERIFICATION WITH 10,000 REQUESTS, WITH 10 CONCURRENT CONNECTIONS" >| results.txt 
ab -n 10000 -c 10 -rk "http://localhost:3000/nimbus?jwt=$1" >> results.txt
echo "" >> results.txt 
echo "" >> results.txt 
echo "" >> results.txt 

# JAVA-JWT: 10000 requests with 10 concurrent:
echo "----- EXECUTING JAVA-JWT VERIFICATION WITH 10,000 REQUESTS, WITH 10 CONCURRENT CONNECTIONS" >> results.txt
ab -n 10000 -c 10 -rk "http://localhost:3000/javajwt?jwt=$1" >> results.txt
echo "" >> results.txt
echo "" >> results.txt
echo "" >> results.txt
#
## NIMBUS: 10000 requests with 10 concurrent:
echo "----- EXECUTING NIMBUS VERIFICATION WITH 100,000 REQUESTS, WITH 100 CONCURRENT CONNECTIONS" >> results.txt
ab -n 100000 -c 100 -rk "http://localhost:3000/nimbus?jwt=$1" >> results.txt
echo "" >> results.txt
echo "" >> results.txt
echo "" >> results.txt
#
## JAVA-JWT: 10000 requests with 10 concurrent:
echo "----- EXECUTING JAVA-JWT VERIFICATION WITH 100,000 REQUESTS, WITH 100 CONCURRENT CONNECTIONS" >> results.txt
ab -n 100000 -c 100 -rk "http://localhost:3000/javajwt?jwt=$1" >> results.txt
echo "" >> results.txt
echo "" >> results.txt
echo "" >> results.txt
#
## NIMBUS: 1000000 requests with 10 concurrent:
echo "----- EXECUTING NIMBUS VERIFICATION WITH 1,000,000 REQUESTS, WITH 100 CONCURRENT CONNECTIONS" >> results.txt
ab -n 1000000 -c 100 -rk "http://localhost:3000/nimbus?jwt=$1" >> results.txt
echo "" >> results.txt
echo "" >> results.txt
echo "" >> results.txt
#
## JAVA-JWT: 100000 requests with 10 concurrent:
echo "----- EXECUTING JAVA-JWT VERIFICATION WITH 1,000,000 REQUESTS, WITH 100 CONCURRENT CONNECTIONS" >> results.txt
ab -n 1000000 -c 100 -rk "http://localhost:3000/javajwt?jwt=$1" >> results.txt
echo "" >> results.txt
echo "" >> results.txt
echo "" >> results.txt