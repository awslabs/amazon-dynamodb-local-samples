package aws.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;
import software.amazon.dynamodb.services.local.embedded.DynamoDBEmbedded;
import software.amazon.dynamodb.services.local.shared.access.AmazonDynamoDBLocal;

public class DynamoDBEmbeddedTest {

    private AmazonDynamoDBLocal dynamoDBLocal;

    private DynamoDbTable<Customer> customerTable;

    @BeforeEach
    void setUp() {
        this.dynamoDBLocal = DynamoDBEmbedded.create();
        var dynamoDbClient = dynamoDBLocal.dynamoDbClient();
        var dynamoDbEnhancedClient = DynamoDbEnhancedClient.builder().dynamoDbClient(dynamoDbClient)
                .build();
        this.customerTable = Customer.table(dynamoDbEnhancedClient);
        this.customerTable.createTable();
    }

    @AfterEach
    void tearDown() {
        Optional.ofNullable(this.dynamoDBLocal).ifPresent(AmazonDynamoDBLocal::shutdown);
    }

    @Test
    void putItem() {
        Customer newItem = new Customer();
        newItem.setId("1");
        newItem.setCustName("Customer Name");
        newItem.setEmail("info@example.com");

        this.customerTable.putItem(newItem);

        Customer key = new Customer();
        key.setId("1");
        key.setEmail("info@example.com");
        Customer storedItem = this.customerTable.getItem(key);
        
        assertEquals(newItem.getId(), storedItem.getId());
        assertEquals(newItem.getCustName(), storedItem.getCustName());
        assertEquals(newItem.getEmail(), storedItem.getEmail());
    }

    /**
     * This class is defined in the following guide.
     * https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/ddb-en-client-gs-tableschema.html#ddb-en-client-gs-tableschema-anno-bean-cust
     */
    @DynamoDbBean
    public static class Customer {

        private String id;
        private String name;
        private String email;
        private Instant regDate;

        @DynamoDbPartitionKey
        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCustName() {
            return this.name;
        }

        public void setCustName(String name) {
            this.name = name;
        }

        @DynamoDbSortKey
        public String getEmail() {
            return this.email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Instant getRegistrationDate() {
            return this.regDate;
        }

        public void setRegistrationDate(Instant registrationDate) {
            this.regDate = registrationDate;
        }

        @Override
        public String toString() {
            return "Customer [id=" + id + ", name=" + name + ", email=" + email
                    + ", regDate=" + regDate + "]";
        }

        static DynamoDbTable<Customer> table(DynamoDbEnhancedClient cleint) {
            return cleint.table("Customer", TableSchema.fromBean(Customer.class));
        }

    }

}
