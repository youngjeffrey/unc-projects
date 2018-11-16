$(document).ready(function() {

    //Toggle hide and show
    $(".question-body").append("<button class='hide'>Toggle Hide/Show</button>");
      
    $(".hide").click(function() {
        if ($(".hide").html() == "Toggle Hide/Show") {
            $(this).closest(".question").find("form").toggle();
        }
    });
    
    // Sorting answered questions
    $(".button").click(function() {
       if($(".button").html() == "Submit") {
           $(this).closest(".question").find("question-header").append("<label>Submitted!</label>");
       }
    });
      
    //Substring search
    $(".questions-section").prepend("<br><div class='string-search'><label>Search for a question by name</label><br><input class='myInput' type='text'></div><br>");
    
    $(".myInput").keyup(function() {
       var string = $(this).val().toLowerCase().trim();
        
        $(".question-header").each(function() {
           if($(this).text().toLowerCase().trim().includes(string)) {
               $(this).parent().show();
           } else {
               $(this).parent().hide();
           }
        });
    });
    
    // Add answer count
    $(".question").each(function() {
        $(this).append("Answer Count: " + $(this).attr("data-answercount"));
    });
    
    
    // Sorting
    $(".questions-section").prepend("<div class='alpha_sort'><button class='alpha'>Sort by Alphabetical Order</button><button class='number'>Sort by Answer Count</button></div>");
    
    // Alphabetical sort
    $(".alpha").click(function() {
       $(".question").sort(function(a, b) {
          var a = $(a).find(".question-header").html().toUpperCase();
          var b = $(b).find(".question-header").html().toUpperCase();
           
        if(a < b) {
            return -1;
        } else {
            return 1;
        }
        
        }).insertAfter(".questions-title");
        
    });
    
    // Answer count sort
    $(".number").click(function() {
        $(".question").sort(function(a,b) {
            var a = parseInt($(a).attr("data-answercount"));
            var b = parseInt($(b).attr("data-answercount"));
            
           if (a < b) {
                return 1;
            } else if (a == b) {
                return 0;
            } else {
                return -1;
            }
        }).insertAfter(".questions-title");
        
    });    
    
});