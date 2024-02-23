# DynamoDB Local Sample Java Project
This is a sample Java project that demonstrates how to use DynamoDB Local for local development and testing. 
DynamoDB Local is a downloadable version of DynamoDB that enables you to develop and test applications without connecting to the actual DynamoDB service provided by AWS. 
This project showcases multiple approaches to set up and use DynamoDB Local, including downloading JAR files, running it as a Docker image, and using it as a Maven dependency.

## Prerequisites
- Java Development Kit (JDK) 11 or later installed on your system
- Apache Maven installed on your system
- Docker installed (if running DynamoDB Local as a Docker image)

## Setup
1. Clone this repository to your local machine.
2. Ensure that you have the prerequisites mentioned above installed.
3. Open a terminal or command prompt and navigate to the project's root directory.

## Downloading DynamoDB Local JAR Files
If you prefer to download and run DynamoDB Local as JAR files, follow these steps:
1. Download the DynamoDB Local JAR files from the AWS website:
    - [DynamoDB Local](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.DownloadingAndRunning.html)
    - [DynamoDB Local Dependencies](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.RequiredLibraries.html)
2. Place the downloaded JAR files in a directory of your choice.
3. Open a terminal or command prompt and navigate to the directory where you placed the JAR files.
4. Run the following command to start DynamoDB Local:
   ```
   java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb
   ```
   The `-sharedDb` flag indicates that DynamoDB Local will use a single database file for all data and will persist between restarts.


## Running DynamoDB Local as a Docker Image
If you prefer to use DynamoDB Local as a Docker image, follow these steps:
1. Make sure Docker is running on your system.
2. Open a terminal or command prompt.
3. Run the following command to pull the DynamoDB Local Docker image:
   ```
   docker pull amazon/dynamodb-local
   ```
4. Once the image is downloaded, run the following command to start DynamoDB Local as a Docker container:
   ```
   docker run -p 8000:8000 amazon/dynamodb-local
   ```


## Using DynamoDB Local as a Maven Dependency
If you prefer to use DynamoDB Local as a Maven dependency, follow these steps:
1. Open the `pom.xml` file in your project.
2. Add the following dependency to the `<dependencies>` section:
   ```xml
   <dependency>
        <groupId>com.amazonaws</groupId>
        <artifactId>DynamoDBLocal</artifactId>
         <version><LATEST_VERSION></version>
   </dependency>
   ```
You can find the latest version of DynamoDB Local here: https://mvnrepository.com/artifact/com.amazonaws/DynamoDBLocal
3. Build your project using Maven:
   ```
   mvn clean package
   ```

### Optional: Create a a runnable jar: https://www.baeldung.com/executable-jar-with-maven

## Resources
- [DynamoDB Local Documentation](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.html)
- [AWS SDK for Java Documentation](https://docs.aws.amazon.com/sdk-for-java/index.html)
- [DynamoDB Local Docker Hub](https://hub.docker.com/r/amazon/dynamodb-local)

## Security

See [CONTRIBUTING](CONTRIBUTING.md#security-issue-notifications) for more information.

## Contact us
To report a bug or request a feature or to contact the DynamoDB Local team, reach out to us at aws-ddblocal-feedback@amazon.com.

## License

This library is licensed under the MIT-0 License. See the LICENSE file.

