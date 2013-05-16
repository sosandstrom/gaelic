var ACCESS_TOKEN = "access_token";

function registerFederated(apiUrl, basicUsername, basicPassword, providerId) {
    var token = getParameterByName("access_token");
    var jConnection;

    //call to register
    var tok = basicUsername + ':' + basicPassword;
    var hash = window.btoa(tok);
    $.ajax({
            async: false,
            type: "GET",
            url: apiUrl + "/federated/v11",
            data:{
                    access_token: token,
                    jBasic: hash,
                    providerId: providerId,
//                    providerUserId: element,
                    expires_in: getParameterByName("expires_in")
            },
            dataType: "json",
            error: function(jqXHR, textStatus, errorThrown){
                    console.log("register fail...")
            },
            success: function(data, textStatus, jqXHR){
//                                    $("#response").html("register success" + data);
                    //Redirect to admin login success page
//                                    redirect(token);
                    jConnection = data;
                    console.log("Register success " + data);
            },
            complete: function(jqXHR, textStatus){
                    console.log(textStatus);
            }
    });
    return jConnection;
}
