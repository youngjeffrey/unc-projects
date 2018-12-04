var rootUrl = "http://comp426.cs.unc.edu:3002/api/";

$(document).ready(() => {

    $('#loginButton').on('click', () => {
       let user = $('#loginUser').val();
       let pass = $('#loginPass').val();

       console.log(user);
       console.log(pass);
       $.ajax(rootUrl + 'login',
        {
            type: 'GET',
            xhrFields: {withCredentials: true},
            data: {
                username: user,
                password: pass
            },
            success: (response) => {
                if (response.status) {
                    build_question_interface();
                    $('h1').css('background-color', 'white')
                }else{
                    $('#mesg_div').html("Login failed. Try again.");
                }
            },
            error: () => {
                alert('error');
            }
        });
    });
});


var build_question_interface = function () {
    let body = $('body');

    body.empty();

    body.append('<div style="background-color: antiquewhite;" class="header-container"><h1 style="background-color: antiquewhite;" class="title">Welcome to the 426 website</h1><h1 style="background-color: antiquewhite;" class="subtitle">Answer and Review questions!</h1></div>');
    body.append('<div style="margin-right: 75%;" class="button-container"><button style="background-color: lightblue; border: none; color: white; padding: 8px 20px; text-align: center; text-decoration: none; display: inline-block; font-size: 16px;" class="review" onclick="reviewQuestions()">Review Answers</button>');

    let qlist = $('<div class = "questions-container"></div>');

    body.append(qlist);

    $.ajax(rootUrl + "questions",
        {
            type: 'GET',
            dataType: 'json',
            xhrFields: {withCredentials: true},
            success: (response) => {
                let qarray = response.data;
                for (let i=0; i<qarray.length; i++) {
                    let qdiv = create_question_div(qarray[i]);
                    qlist.append(qdiv);
                    let qid = qarray[i].id;
                    $.ajax(rootUrl + 'answers/' + qid,
                        {
                            type: 'GET',
                            dataType: 'json',
                            xhrFields: {withCredentials: true},
                            success: (response) => {
                                if (response.data != null) {
                                    
                                    let answer1 = response.data.answer_text;
                                    qdiv.append('<div class = "inputSlot" id = "slot_' + qid + '">Your Response:&nbsp;' + answer1 + '</div>');
                                    qdiv.append('<div class = "inputDiv" id = "inputUpdateDiv' + qid + '"><input type = "text" placeholder = "Update Answer" id="inputUpdateAnswer_' + qid + '"></div>');
                                    qdiv.append('<button style="background-color: lightblue; border: none; color: white; padding: 8px 20px; text-align: center; text-decoration: none; display: inline-block; font-size: 16px;" type = "submit" class="editButton" id = "edit_' + qid + '" onclick = "editAnswer(' + qid + ')"> Edit </button>');
                                    qdiv.append('<button style="background-color: lightblue; border: none; color: white; padding: 8px 20px; text-align: center; text-decoration: none; display: inline-block; font-size: 16px;" type = "submit" class="clearButton" id = "clear_' + qid + '" onclick = "clearAnswer(' + qid + ')"> Clear </button>');
                                    qdiv.append('<br></br>');
                                    
                                } else {
                                    qdiv.append('<div class = "inputSlot" id = "slot_' + qid + '">' + '</div>');
                                    qdiv.append('<div class = "inputDiv" id = "inputDiv' + qid + '"><input type = "text" placeholder = "Please put an answer" id="inputAnswer_' + qid + '"></div>');
                                    qdiv.append('<button style="background-color: lightblue; border: none; color: white; padding: 8px 20px; text-align: center; text-decoration: none; display: inline-block; font-size: 16px;" type = "submit" class="submitbutton" id = "submit_' + qid + '" onclick = "submitAnswer(' + qid + ')"> Submit Answer </button>');
                                    qdiv.append('<div class = "clearSlot" id = "clearslot_' + qid + '">' + '</div>');
                                    qdiv.append('<br></br>');
                                }
                            }
                        });
                }
            }
        });

    let create_question_div = (question) => {
        let qdiv = $('<div class="question" id="qid_' + question.id + '"></div>');
	    qdiv.append('<div class="qtitle">' + question.title + '</div>');
	    qdiv.append('<div class="count">Answer Count:&nbsp;' + question.answerCount + '</div>');
        return qdiv;
    };
};

function reviewQuestions() {
    let body = $('body');
    body.empty();
    body.append('<div style="background-color: antiquewhite;" class="header-container"><h1 style="background-color: antiquewhite;" class="title">Welcome to the review mode</h1></div>');
    body.append('<button style="background-color: lightblue; border: none; color: white; padding: 8px 20px; text-align: center; text-decoration: none; display: inline-block; font-size: 16px;" type = "submit" class="home" onclick = "goHome()"> Go Home </button>')
    let reviewdiv = $('<div class="pair-container"></div>');
    
    
    $.ajax(rootUrl + 'review',
        {
        type: 'GET',
        xhrFields: {withCredentials: true},	
        success: (response) => {
            
            let question1 = response.data;
            if(response == null) {
                body.append('<p>You have not submitted any answers for review yet!</p>');
            } else {
                body.append('<br></br>');
                body.append('<div class="button-container">' 
                            + '<button style="background-color: lightblue; border: none; color: white; padding: 8px 20px; text-align: center; text-decoration: none; display: inline-block; font-size: 16px;" type = "submit" class="choice1" id="choice1">Choice 1</button>'
                            + '<button style="background-color: lightblue; border: none; color: white; padding: 8px 20px; text-align: center; text-decoration: none; display: inline-block; font-size: 16px;" type = "submit" class="choice2" id="choice2">Choice 2</button>'
                            + '<button style="background-color: lightblue; border: none; color: white; padding: 8px 20px; text-align: center; text-decoration: none; display: inline-block; font-size: 16px;" type = "submit" class="equalchoice" id="equalchoice">Equal</button></div>');
                body.append('<br></br>');
                body.append('<p class="Explanation">Review the question below and look at the two answer choices. Select the one you think is better. If both are equal then select "Equal"</p>');
                body.append('<div class="question" id="rq_"><b>Reviewed Question:&nbsp;<b> ' + question1.question.question_title + '</div>');
                body.append('<div class="choice1" id= "rt1_"> Choice 1:&nbsp;' + question1.answer1.text + '</div>');
                body.append('<div class="choice2" id= "rt2_"> Choice 2:&nbsp;' + question1.answer2.text + '</div>');
            }
        
            
            $("#equalchoice").click(function() {
                    $.ajax(rootUrl + 'review?qid=' + question1.question.question_id + '&a1=' + question1.answer1.id + '&a2=' + question1.answer2.id + '&best=0',
                          {
                            type: 'PUT',
                            dataType: 'json',
                            xhrFields: {withCredentials: true},
                            success: () => {
                                $.ajax(rootUrl + 'review', {
                                    type: 'GET',
                                    xhrFields: {withCredentials: true},	
                                    success: (response) => {

                                        twoanswer = response.data;
                                        body.append('<div class="question" id="rq_"><b>Reviewed Question:&nbsp;<b> ' + twoanswer.question.question_title + '</div>');
                                        body.append('<div class="choice1" id= "rt1_"> Choice 1:&nbsp;' + twoanswer.answer1.text + '</div>');
                                        body.append('<div class="choice2" id= "rt2_"> Choice 2:&nbsp;' + twoanswer.answer2.text + '</div>');
                                    }
                                });
                            }

                    });
				});
            
            $("#choice1").click(function() {
                    $.ajax(rootUrl + 'review?qid=' + question1.question.question_id + '&a1=' + question1.answer1.id + '&a2=' + question1.answer2.id + '&best' + question1.answer1.id,
                          {
                            type: 'PUT',
                            dataType: 'json',
                            xhrFields: {withCredentials: true},
                            success: () => {
                                $.ajax(rootUrl + 'review', {
                                    type: 'GET',
                                    xhrFields: {withCredentials: true},	
                                    success: (response) => {

                                        twoanswer = response.data;
                                        body.append('<div class="question" id="rq_"><b>Reviewed Question:&nbsp;<b> ' + twoanswer.question.question_title + '</div>');
                                        body.append('<div class="choice1" id= "rt1_"> Choice 1:&nbsp;' + twoanswer.answer1.text + '</div>');
                                        body.append('<div class="choice2" id= "rt2_"> Choice 2:&nbsp;' + twoanswer.answer2.text + '</div>');
                                    }
                                });
                            }

                    });
				});
            
            $("#choice2").click(function() {
                    $.ajax(rootUrl + 'review?qid=' + question1.question.question_id + '&a1=' + question1.answer1.id + '&a2=' + question1.answer2.id + '&best' + question1.answer2.id,
                          {
                            type: 'PUT',
                            dataType: 'json',
                            xhrFields: {withCredentials: true},
                            success: () => {
                                $.ajax(rootUrl + 'review', {
                                    type: 'GET',
                                    xhrFields: {withCredentials: true},	
                                    success: (response) => {

                                        twoanswer = response.data;
                                        body.append('<div class="question" id="rq_"><b>Reviewed Question:&nbsp;<b> ' + twoanswer.question.question_title + '</div>');
                                        body.append('<div class="choice1" id= "rt1_"> Choice 1:&nbsp;' + twoanswer.answer1.text + '</div>');
                                        body.append('<div class="choice2" id= "rt2_"> Choice 2:&nbsp;' + twoanswer.answer2.text + '</div>');
                                    }
                                });
                            }

                    });
				});
            
            },
            error: () => {
		       alert('error');
		   }
    });
    
}

/*function choiceTwo(responses) {
        $.ajax(rootUrl + 'review?qid=' + responses.question.question_id + '&a1=' + responses.answer1.id + '&a2=' + responses.answer2.id + '&best=' + responses.answer2.id,
          {
            type: 'PUT',
            dataType: 'json',
            xhrFields: {withCredentials: true},
            success: () => {
                $.ajax(rootUrl + 'review', {
                    type: 'GET',
                    xhrFields: {withCredentials: true},	
                    success: (response) => {
                        
                        twoanswer = response.data;
                        $("#rq_").replaceWith('<div class="question" id="rq_"><b>Reviewed Question:&nbsp;<b> ' + twoanswer.question.question_title + '</div>');
                        $("#rt1_").replaceWith('<div class="choice1" id= "rt1_"> Choice 1:&nbsp;' + twoanswer.answer1.text + '</div>');
                        $("$rt2_").replaceWith('<div class="choice2" id= "rt2_"> Choice 2:&nbsp;' + twoanswer.answer2.text + '</div>');
                    }
                });
            }
        
        });
    
        console.log(responses);
}*/


/*function choiceOne(responses) {
    $.ajax(rootUrl + 'review?qid=' + responses.question.question_id + '&a1=' + responses.answer1.id + '&a2=' + responses.answer2.id + '&best=' + responses.answer1.id,
          {
            type: 'PUT',
            dataType: 'json',
            xhrFields: {withCredentials: true},
            success: () => {
                $.ajax(rootUrl + 'review', {
                    type: 'GET',
                    xhrFields: {withCredentials: true},	
                    success: (response) => {
                        
                        twoanswer = response.data;
                        $("#rq_").replaceWith('<div class="question" id="rq_"><b>Reviewed Question:&nbsp;<b> ' + twoanswer.question.question_title + '</div>');
                        $("#rt1_").replaceWith('<div class="choice1" id= "rt1_"> Choice 1:&nbsp;' + twoanswer.answer1.text + '</div>');
                        $("$rt2_").replaceWith('<div class="choice2" id= "rt2_"> Choice 2:&nbsp;' + twoanswer.answer2.text + '</div>');
                    }
                });
            }
        
    });
}*/

/*function equalChoice(responses) {
    $.ajax(rootUrl + 'review?qid=' + responses.question.question_id + '&a1=' + responses.answer1.id + '&a2=' + responses.answer2.id + '&best=0',
          {
            type: 'PUT',
            dataType: 'json',
            xhrFields: {withCredentials: true},
            success: () => {
                $.ajax(rootUrl + 'review', {
                    type: 'GET',
                    xhrFields: {withCredentials: true},	
                    success: (response) => {
                        
                        twoanswer = response.data;
                        $("#rq_").replaceWith('<div class="question" id="rq_"><b>Reviewed Question:&nbsp;<b> ' + twoanswer.question.question_title + '</div>');
                        $("#rt1_").replaceWith('<div class="choice1" id= "rt1_"> Choice 1:&nbsp;' + twoanswer.answer1.text + '</div>');
                        $("$rt2_").replaceWith('<div class="choice2" id= "rt2_"> Choice 2:&nbsp;' + twoanswer.answer2.text + '</div>');
                    }
                });
            }
        
    });
}*/

function goHome() {
    build_question_interface();
}

function clearAnswer(qid) {
    $.ajax(rootUrl + 'answers/' + qid,
        {
        type: 'DELETE',
        xhrFields: {withCredentials: true},	
        success: (response) => {
            //console.log("hello!");
            $("#slot_" + qid).replaceWith('<div class = "inputSlot" id = "slot_' + qid + '">' + '</div>');
            $("#inputUpdateDiv" + qid).replaceWith('<div class = "inputDiv" id = "inputDiv' + qid + '"><input type = "text" placeholder = "Please put an answer" id="inputAnswer_' + qid + '"></div>');
            $("#edit_" + qid).replaceWith('<button style="background-color: lightblue; border: none; color: white; padding: 8px 20px; text-align: center; text-decoration: none; display: inline-block; font-size: 16px;" type = "submit" class="submitbutton" id = "submit_' + qid + '" onclick = "submitAnswer(' + qid + ')"> Submit Answer </button>');
            $("#clear_" + qid).replaceWith('<div class = "clearSlot" id = "clearslot_' + qid + '">' + '</div>');
            $(this).remove();
            },
            error: () => {
				alert('error');
            }
        });
}


function editAnswer(qid) {
    let answer2 = $("#inputUpdateAnswer_" + qid).val();
    //console.log(answer2);
    $.ajax(rootUrl + 'answers/' + qid + '?answer=' + answer2,
          {
            type: 'POST',
            xhrFields: {withCredentials: true},
            success: (response) => {
                $("#slot_" + qid).replaceWith('<div class = "inputSlot" id = "slot_' + qid + '">Your Response:&nbsp;' + answer2 + '</div>');
            },
            error: () => {
				alert('error');
            }
          });
}


function submitAnswer(qid) {
    let answer = $("#inputAnswer_" + qid).val();
    $.ajax(rootUrl + 'answers/' + qid + '?answer=' + answer, 
        {
            type: 'PUT',
            xhrFields: {withCredentials: true},
            success: (response) => {
                $("#slot_" + qid).replaceWith('<div class = "inputSlot" id = "slot_' + qid + '">Your Response:&nbsp;' + answer + '</div>');
                $("#inputAnswer_" + qid).replaceWith('<div class = "inputDiv" id = "inputUpdateDiv' + qid + '"><input type = "text" placeholder = "Update Answer" id="inputUpdateAnswer_' + qid + '"></div>');
                $("#submit_" + qid).replaceWith('<button style="background-color: lightblue; border: none; color: white; padding: 8px 20px; text-align: center; text-decoration: none; display: inline-block; font-size: 16px;" type = "submit" class="editButton" id = "edit_' + qid + '" onclick = "editAnswer(' + qid + ')"> Edit </button>');
                $("#clearslot_" + qid).replaceWith('<button style="background-color: lightblue; border: none; color: white; padding: 8px 20px; text-align: center; text-decoration: none; display: inline-block; font-size: 16px;" type = "submit" class="clearButton" id = "clear_' + qid + '" onclick = "clearAnswer(' + qid + ')"> Clear </button>');
                },
				error: () => {
				    alert('error');
				}
        });
}