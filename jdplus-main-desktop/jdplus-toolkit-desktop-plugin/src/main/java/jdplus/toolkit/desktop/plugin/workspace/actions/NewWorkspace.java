package jdplus.toolkit.desktop.plugin.workspace.actions;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import jdplus.toolkit.desktop.plugin.workspace.WorkspaceFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "File",
id = "demetra.desktop.workspace.actions.NewWorkspace")
@ActionRegistration(displayName = "#CTL_NewWorkspace")
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 100)
})
@Messages("CTL_NewWorkspace=New Workspace")
public final class NewWorkspace implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!WorkspaceFactory.getInstance().closeWorkspace(true))
            return;
        WorkspaceFactory.getInstance().newWorkspace();
    }
}
