<?php


    
    $answer1 = $_POST['question-1-answer'];
    $answer2 = $_POST['question-2-answer'];
    $answer3 = $_POST['question-3-answer'];
    $answer4 = $_POST['question-4-answer'];
    $answer5 = $_POST['question-5-answer'];
    $answer6 = $_POST['question-6-answer'];


    $totalCorrect = 0;
    
    if ($answer1 == "1") { $totalCorrect++; }
    if ($answer2 == "2") { $totalCorrect++; }
    if ($answer3 == "3") { $totalCorrect++; }
    if ($answer4 == "4") { $totalCorrect++; }
    if ($answer5 == "5") { $totalCorrect++; }
    if ($answer6 == "6") { $totalCorrect++; }    


?>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>A.I Quiz Results</title>
    <link rel="stylesheet" href="../css/style_results.css">
</head>
<body>
    <br>
    <br>
    <header style="color:white"><span>  These were the results! </span></header>
    <br>
    <br>
    <section class="reasons">
    <br>
    <br>
    <?php    echo "<div id='results'>$totalCorrect / 6 correct</div>";  ?>
        
    </section>
    <br>
    <br>
    <br>
<br>
<br>
    <div class="ending">
    <a href="finalexam.html"><button style="width:20%">Go Home</button></a>
    <a href="test.html"><button style="width:20%">Take It Again!</button></a>
    </div>
<br>
</body>
</html>