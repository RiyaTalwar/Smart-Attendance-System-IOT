<?php

    require_once 'conn.php';
 
    $response = array();

    //for login we need the username and password 
    if(isTheseParametersAvailable(array('username', 'password'))){
        //getting values 
        $username = $_POST['username'];
        $password = $_POST['password'];
        
        //echo $username;
        //echo $password;
        
        //$username = "201501419";
        //$password = "abhinkakkad";
                        
        //creating the query 
        $stmt = $con->prepare("SELECT username,password FROM student WHERE username = ? AND password = ?");
        $stmt->bind_param("ss",$username, $password);
                        
        $stmt->execute();
                        
        $stmt->store_result();
                        
        //if the user exist with given credentials 
	    if($stmt->num_rows > 0){
					
            $stmt->bind_result($username, $password);
            $stmt->fetch();
                        
            $user = array(
            'username'=>$username, 
            'password'=>$password,
	        );
						
        $response['error'] = false; 
        $response['message'] = 'Login successfull'; 
        $response['user'] = $user; 
        }

        else{
            //if the user not found 
            $response['error'] = false; 
            $response['message'] = 'Invalid username or password';
        }
    }
 
 echo json_encode($response);
 
 function isTheseParametersAvailable($params){
 
 foreach($params as $param){
 if(!isset($_POST[$param])){
 return false; 
 }
 }
 return true; 
 }
?>