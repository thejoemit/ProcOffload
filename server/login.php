<?php
$username = "CARLETON1";
$password = "password";
$sent_user = $_GET['user'];
$sent_pass = $_GET['pass'];
$sent_salt = $_GET['rid'];

if (!empty($sent_user) and !empty($sent_pass) and !empty($sent_salt)) {
if (strcmp($username,$sent_user) == '0') {
        $salt1 = substr($sent_salt,0,10);
        $salt2 = substr($sent_salt,10,10);
        $hash_pass = hash('SHA512',$salt1.$password.$salt2);
        if (strcmp($hash_pass,$sent_pass) == '0') {
                echo "$username:1234567";
        } else {
                echo "password error";
        }
} else {
 echo "username error";
}
} else {
 echo "parameter error";
}
?>
