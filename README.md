# Run Beta Persei Service
To run the Beta Persei Service use the following gradle command:

```
gradlew bootRun
```
# Create a bloom filter API

use the following curl command:

```
curl -X PUT "http://localhost:8080/bloom?url=https://norvig.com/big.txt"
```
# Query API

use the following curl command:

```
curl "http://localhost:8080/bloom?url=https://norvig.com/big.txt&contains=guns"
```