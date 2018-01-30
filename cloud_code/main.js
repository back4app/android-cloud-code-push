Parse.Cloud.define("pushsample", function (request, response) {
    Parse.Push.send({
            channels: ["News"],
            data: {
                title: "Hello from the Cloud Code",
                alert: "Back4App rocks!",
            }
       }, {
            success: function () {
                // Push was successful
                response.success("push sent");
                console.log("Success: push sent");
            },
            error: function (error) {
                // Push was unsucessful
                response.error("error with push: " + error);
                console.log("Error: " + error);
            },
            useMasterKey: true
       });
});