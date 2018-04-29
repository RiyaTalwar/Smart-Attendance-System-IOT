<?php 

    require_once 'conn.php';
    
        //getting values 
        $username = $_POST['username'];
        //$password = $_POST['password'];
        
        
        //$username='crypto';
        
        //echo $username;
        
        $stmt = $con->prepare("SELECT course_id,course_name,total_attendance,students,date FROM (SELECT
course_id,username,course_name,name,total_attendance,students,date FROM (SELECT
course_id,cast(timestamp as date) as date,COUNT(attendance) as
total_attendance FROM course_attendance GROUP BY course_id,cast(timestamp
as date)) AS a NATURAL JOIN (SELECT * FROM faculty) AS b) AS k where username = '$username' ");
	
	//executing the query 
	$stmt->execute();
	
	//binding results to the query 
	$stmt->bind_result($course_id,$course_name,$attendance,$students,$date);
	
	$products = array(); 
	
	//traversing through all the result 
	while($stmt->fetch()){
		$temp = array();
		$temp['course_id'] = $course_id; 
		$temp['course_name'] = $course_name; 
		//$temp['name'] = $name; 
		$temp['attendance'] = $attendance; 
		$temp['students'] = $students;
		$temp['date'] = $date;
		array_push($products, $temp);
}
 echo json_encode($products);

 ?>