<?php 

    require_once 'conn.php';
    
        //getting values 
        $username = $_POST['username'];
        //$password = $_POST['password'];
        
        
        //$username='201501009';
        
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

    $sql = $con->prepare("SELECT course_id,course_name,lecs FROM (SELECT * FROM (SELECT course_id,finger_id FROM
course_taken AS x WHERE NOT EXISTS(SELECT
course_id,course_name,finger_id, total_attendance,lecs FROM (SELECT
course_id,finger_id,COUNT(attendance) as total_attendance FROM
course_attendance GROUP BY course_id,finger_id) AS a NATURAL JOIN (SELECT
* FROM faculty) AS b WHERE x.course_id=course_id and x.finger_id=finger_id))
AS y NATURAL JOIN (SELECT course_name,course_id,lecs FROM faculty) AS z) AS
k NATURAL JOIN (SELECT name,s_id,finger_id FROM student) AS l where s_id = '$username' ");

//executing the query 
	$sql->execute();
	
	//binding results to the query 
	$sql->bind_result($course_id,$course_name,$lecture);
	
	//traversing through all the result 
	while($sql->fetch()){
		$temp = array();
		$temp['course_id'] = $course_id; 
		$temp['course_name'] = $course_name; 
		$temp['attendance'] = 0; 
		$temp['lecture'] = $lecture;
		array_push($products, $temp);
}

 echo json_encode($products);

 ?>