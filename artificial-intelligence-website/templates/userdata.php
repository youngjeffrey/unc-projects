<html>
<body>
    <?php
    
    $email = $_POST['email_name'];
    $rating = $_POST['rating_source'];
    
    echo $email . "</br>";
    echo $rating . "</br>";
    
    $str = <<<EOD
    Your email is $email
    and your rating is $rating</br>
EOD;
    
    echo $str;
    
    ?>
    
    
    </body>
</html>


