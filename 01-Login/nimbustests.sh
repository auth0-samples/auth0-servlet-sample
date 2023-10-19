# NIMBUS: 10000 requests with 10 concurrent:
echo "----- EXECUTING NIMBUS VERIFICATION WITH 10,000 REQUESTS, WITH 10 CONCURRENT CONNECTIONS" >| nimbus-results.txt
ab -n 10000 -c 10 -rk "http://localhost:3000/nimbus?jwt=$1" >> nimbus-results.txt
echo "" >> nimbus-results.txt
echo "" >> nimbus-results.txt
echo "" >> nimbus-results.txt

## NIMBUS: 100000 requests with 10 concurrent:
echo "----- EXECUTING NIMBUS VERIFICATION WITH 100,000 REQUESTS, WITH 100 CONCURRENT CONNECTIONS" >> nimbus-results.txt
ab -n 100000 -c 10 -rk "http://localhost:3000/nimbus?jwt=$1" >> nimbus-results.txt
echo "" >> nimbus-results.txt
echo "" >> nimbus-results.txt
echo "" >> nimbus-results.txt

## NIMBUS: 100000 requests with 100 concurrent:
echo "----- EXECUTING NIMBUS VERIFICATION WITH 100,000 REQUESTS, WITH 100 CONCURRENT CONNECTIONS" >> nimbus-results.txt
ab -n 100000 -c 100 -rk "http://localhost:3000/nimbus?jwt=$1" >> nimbus-results.txt
echo "" >> nimbus-results.txt
echo "" >> nimbus-results.txt
echo "" >> nimbus-results.txt

## NIMBUS: 1000000 requests with 100 concurrent:
echo "----- EXECUTING NIMBUS VERIFICATION WITH 1,000,000 REQUESTS, WITH 100 CONCURRENT CONNECTIONS" >> nimbus-results.txt
ab -n 1000000 -c 100 -rk "http://localhost:3000/nimbus?jwt=$1" >> nimbus-results.txt
echo "" >> nimbus-results.txt
echo "" >> nimbus-results.txt
echo "" >> nimbus-results.txt

## NIMBUS: 10000000 requests with 100 concurrent:
echo "----- EXECUTING NIMBUS VERIFICATION WITH 10,000,000 REQUESTS, WITH 100 CONCURRENT CONNECTIONS" >> nimbus-results.txt
ab -n 10000000 -c 100 -rk "http://localhost:3000/nimbus?jwt=$1" >> nimbus-results.txt
echo "" >> nimbus-results.txt
echo "" >> nimbus-results.txt
echo "" >> nimbus-results.txt
