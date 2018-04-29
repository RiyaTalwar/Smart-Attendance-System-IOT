<?php 

    require_once 'conn.php';
    
        //getting values 
        $username = $_POST['username'];
        //$password = $_POST['password'];
        
        
        //$username='crypto';
        
        //echo $username;
        
        $stmt = $con->prepare("SELECT course_id,course_name, total_attendance,lecs FROM (SELECT course_id,course_name,finger_id, total_attendance,lecs
    FROM (SELECT course_id,finger_id,COUNT(attendance) as total_attendance
    FROM course_attendance GROUP BY course_id,finger_id) AS a NATURAL JOIN
    (SELECT * FROM faculty) AS b) AS k NATURAL JOIN (SELECT name,s_id,finger_id
    FROM student) AS l where s_id = '$username' ");
	
	//executing the query 
	$stmt->execute();
	
	//binding results to the query 
	$stmt->bind_result($course_id,$course_name,$attendance,$lecture);
	
	$products = array(); 
	
	//traversing through all the result 
	while($stmt->fetch()){
		$temp = array();
		$temp['course_id'] = $course_id; 
		$temp['course_name'] = $course_name; 
		$temp['attendance'] = $attendance; 
		$temp['lecture'] = $lecture;
		array_push($products, $temp);
}
 echo json_encode($products);

 ?>