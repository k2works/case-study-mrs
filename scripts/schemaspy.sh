docker run --network="host" -v "$PWD/public/schemaspy:/output" -v "$PWD/schemaspy.properties:/schemaspy.properties" schemaspy/schemaspy:latest -all
