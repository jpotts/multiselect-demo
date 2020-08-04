package com.ecmarchitect.alfresco.actions;

import org.alfresco.repo.action.executer.ActionExecuterAbstractBase;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.ParameterDefinition;
import org.alfresco.service.cmr.repository.NodeRef;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Created by jpotts, Metaversant on 8/3/20.
 */
public class ExampleAction extends ActionExecuterAbstractBase {
    private static Log logger = LogFactory.getLog(ExampleAction.class);

    @Override
    protected void executeImpl(Action action, NodeRef nodeRef) {
        logger.debug("Example action ran against: " + nodeRef.getId());
    }

    @Override
    protected void addParameterDefinitions(List<ParameterDefinition> list) {
        // This particular action has no parameters, so nothing to do here
    }
}
