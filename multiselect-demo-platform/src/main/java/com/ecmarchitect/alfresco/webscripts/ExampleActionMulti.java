package com.ecmarchitect.alfresco.webscripts;

import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.ActionService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jpotts, Metaversant on 8/3/20.
 */
public class ExampleActionMulti extends DeclarativeWebScript {
    private NodeService nodeService;
    private ActionService actionService;

    @Override
    protected Map<String, Object> executeImpl(WebScriptRequest req, Status status, Cache cache) {
        Map<String, Object> model = new HashMap();

        Object content = req.parseContent();
        if (!(content instanceof JSONArray)) {
            status.setCode(Status.STATUS_BAD_REQUEST);
            status.setRedirect(true);
            return model;
        }

        try {
            JSONArray dataObj = (JSONArray) content;
            for (int i = 0; i < dataObj.length(); i++) {
                JSONObject obj = (JSONObject) dataObj.get(i);
                NodeRef nodeRef = new NodeRef((String) obj.get("nodeRef"));
                Action exampleAction = actionService.createAction("example-action");
                if (nodeService.exists(nodeRef)) {
                    actionService.executeAction(exampleAction, nodeRef);
                }
            }
        } catch (Exception e) {
            status.setCode(Status.STATUS_INTERNAL_SERVER_ERROR);
            status.setException(e);
            status.setMessage("Problem executing example action multi web script");
        }

        return model;
    }

    public void setNodeService(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    public void setActionService(ActionService actionService) {
        this.actionService = actionService;
    }
}
