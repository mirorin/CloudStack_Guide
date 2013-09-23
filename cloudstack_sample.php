<?php

putenv('HOME=/root');

require_once('/root/aws-php-sdk/sdk.class.php');
$ec2 = new AmazonEC2();
$ec2->set_region(AmazonEC2::CLOUDSTACK_URL);

$res = $ec2->describe_instances();

if ($res->body->reservationSet->item) {
    foreach ($res->body->reservationSet->item as $item) {
        echo 'Instance ID: ' . $item->instancesSet->item->instanceId . PHP_EOL;
        echo '---> Private IP: ' . $item->instancesSet->item->privateIpAddress . PHP_EOL;
        echo '---> State: ' . $item->instancesSet->item->instanceState->name . PHP_EOL;
        echo PHP_EOL;
    }

} else { echo 'NO DATA'; }

?>
