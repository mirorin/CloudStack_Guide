import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeAvailabilityZonesResult;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;

public class cloudstack_sample {

    static AmazonEC2      ec2;

Class loc = this.getClass();
    private static void init() throws Exception {
        AWSCredentials credentials = new PropertiesCredentials(
                cloudstack_sample.class.getResourceAsStream("AwsCredentials.properties"));

        ec2 = new AmazonEC2Client(credentials);
	ec2.setEndpoint("http://192.168.100.10:7080/awsapi/");
    }
    public static void main(String[] args) throws Exception {

        System.out.println("===========================================");
        System.out.println("Lets See if AWS Java SDK works on CloudStack");
        System.out.println("===========================================");
        init();

        try {
            DescribeAvailabilityZonesResult availabilityZonesResult = ec2.describeAvailabilityZones();
            System.out.println("You have access to " + availabilityZonesResult.getAvailabilityZones().size() +
                    " Availability Zones.");

            DescribeInstancesResult describeInstancesRequest = ec2.describeInstances();
            List<Reservation> reservations = describeInstancesRequest.getReservations();
            Set<Instance> instances = new HashSet<Instance>();

            for (Reservation reservation : reservations) {
                instances.addAll(reservation.getInstances());
                System.out.println(reservation.getInstances());
            }

           System.out.println("You have " + instances.size() + " Amazon EC2 instance(s) running.");
        } catch (AmazonServiceException ase) {
                System.out.println("Caught Exception: " + ase.getMessage());
                System.out.println("Reponse Status Code: " + ase.getStatusCode());
                System.out.println("Error Code: " + ase.getErrorCode());
                System.out.println("Request ID: " + ase.getRequestId());
        }

    }
}
