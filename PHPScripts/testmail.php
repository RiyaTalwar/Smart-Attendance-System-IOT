<?php

require_once 'conn.php';
    
        //$course_id = $_POST['course_id'];

        $course_id='11478';
        $date= date("Y-m-d");
        
        //echo $date;
        
        $stmt = $con->prepare("SELECT s_id,course_id,course_name,emailid FROM (SELECT DISTINCT course_id,finger_id FROM (SELECT course_id,finger_id FROM (SELECT course_id FROM (SELECT finger_id,course_id,attendance,cast(timestamp as date) as date FROM course_attendance) AS a WHERE date='$date') AS b NATURAL JOIN (SELECT * FROM course_taken)AS c ) AS x WHERE NOT EXISTS (SELECT DISTINCT course_id,finger_id FROM (SELECT course_id,finger_id FROM (SELECT finger_id,course_id,attendance,cast(timestamp as date) as date FROM course_attendance) AS d WHERE date='$date') AS e WHERE x.course_id=course_id AND x.finger_id=finger_id)) AS y NATURAL JOIN (SELECT name,s_id,finger_id,emailid FROM student) AS z NATURAL JOIN (SELECT course_id,course_name FROM faculty) AS w where course_id='$course_id'");
	
	//executing the query 
	$stmt->execute();
	
	//binding results to the query 
	$stmt->bind_result($s_id,$course_id,$course_name, $emailid);
	
	//traversing through all the result 
	while($stmt->fetch()){

$msg = "This is to inform you that you have been absent for the $course_id - $course_name lecture on $date.";
$msg = wordwrap($msg,70);

//echo $msg;
//echo $email;

// send email
mail($emailid,"IOT Smart Attendance System Test",$msg);

//echo "$s_id@daiict.ac.in";
}

?> 