$( document ).ready(function() {

    // GET REQUEST
    $("#customerForm").submit(function(event){
        event.preventDefault();
        ajaxGet();
    });

    // DO GET
    function ajaxGet(){
        $.ajax({
            type : "GET",
            url : "http://localhost:8080/test",
            success: function(result){
                alert(result.status);
                if(result.status == "Success"){
                    console.log("Success: ", result);
                }else{
                    console.log("Fail: ", result);
                }
            },
            error : function(e) {
                console.log("ERROR: ", e);
            }
        });
    }
})