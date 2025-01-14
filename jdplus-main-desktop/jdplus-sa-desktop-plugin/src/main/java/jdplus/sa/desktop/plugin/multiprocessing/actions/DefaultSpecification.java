/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdplus.sa.desktop.plugin.multiprocessing.actions;

import jdplus.sa.desktop.plugin.multiprocessing.ui.MultiProcessingManager;
import jdplus.sa.desktop.plugin.multiprocessing.ui.SaBatchUI;
import jdplus.toolkit.desktop.plugin.ui.ActiveViewAction;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "SaProcessing",
        id = "demetra.sa.multiprocessing.actions.DefaultSpecification")
@ActionRegistration(displayName = "#CTL_DefaultSpecification", lazy = false)
@ActionReferences({
    @ActionReference(path = MultiProcessingManager.CONTEXTPATH, position = 1000, separatorAfter = 1005),
    @ActionReference(path = "Shortcuts", name = "D")
})
@Messages("CTL_DefaultSpecification=Default specification...")
public final class DefaultSpecification extends ActiveViewAction<SaBatchUI> {

    public DefaultSpecification() {
        super(SaBatchUI.class);
        refreshAction();
        putValue(NAME, Bundle.CTL_DefaultSpecification());
    }

    @Override
    protected void refreshAction() {
        SaBatchUI ui = context();
        enabled = ui != null;
    }

    @Override
    protected void process(SaBatchUI cur) {
        cur.editDefaultSpecification();
    }

}
