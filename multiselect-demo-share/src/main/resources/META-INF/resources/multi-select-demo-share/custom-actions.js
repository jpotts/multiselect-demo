(function() {

        YAHOO.Bubbling.fire("registerAction",
            {
                actionName: "onExampleAction",
                fn: function custom_DLTB_onExampleAction(assets) {
                    // Post the list of selected items to a repo tier web script
                    Alfresco.util.Ajax.jsonPost(
                        {
                            url: Alfresco.constants.PROXY_URI + "ecmarchitect/example-action-multi",
                            responseContentType: "application/json",
                            dataObj: assets,
                            successMessage: this.msg("message.example-action.success"),
                            failureMessage: this.msg("message.example-action.failure"),
                            successCallback:
                                {
                                    fn: function exampleSuccess() {
                                        console.log("Success");
                                    },
                                    scope: this
                                },
                            failureCallback:
                                {
                                    fn: function exampleFailure() {
                                        console.log("Failure");
                                    },
                                    scope: this
                                }
                        });
                }
            });

})();