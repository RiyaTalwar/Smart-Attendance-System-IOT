<?php
//Creates new record as per request
    //Connect to database

define('HOST_NAME','localhost');

define('USER_NAME','id5262205_riya');

define('PASSWORD','iotproject');

define('DATABASE','id5262205_smartattendance');
    // Create connection
    $conn = new mysqli(HOST_NAME,USER_NAME,PASSWORD,DATABASE);
    // Check connection
    if (!$conn) {
        die("Database Connection failed ");
    }

    //Get current date and time
    //date_default_timezone_set('Asia/Kolkata');
   // $d = date("Y-m-d");
    //echo " Date:".$d."<BR>";
   // $t = date("H:i:s");

    if(!empty($_POST['finger_id']) && !empty($_POST['course_Id']) && !empty($_POST['attendance']))
    {
    	$finger_id = $_POST['finger_id'];
    	$course_Id = $_POST['course_Id'];
    	$attendance = $_POST['attendance'];

	    $sql = "INSERT INTO course_attendance (finger_id, course_Id, attendance)
		
		VALUES ('".$finger_id."', '".$course_Id."', '".$attendance."')";

		if ($conn->query($sql) === TRUE) {
		    echo "OK";
		} else {
		    echo "Error: " . $sql . "<br>" . $conn->error;
		}
	}


	$conn->close();
?>