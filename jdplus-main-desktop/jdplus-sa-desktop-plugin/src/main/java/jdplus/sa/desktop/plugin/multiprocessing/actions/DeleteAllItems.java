/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdplus.sa.desktop.plugin.multiprocessing.actions;

import jdplus.sa.desktop.plugin.multiprocessing.ui.MultiProcessingManager;
import jdplus.sa.desktop.plugin.multiprocessing.ui.SaBatchUI;
import jdplus.toolkit.desktop.plugin.ui.ActiveViewAction;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "SaProcessing",
        id = "demetra.desktop.ui.sa.multiprocessing.actions.DeleteAllItems")
@ActionRegistration(displayName = "#CTL_DeleteAllItems", lazy=false)
@ActionReferences({
    @ActionReference(path = MultiProcessingManager.CONTEXTPATH, position = 1600)
})
@Messages("CTL_DeleteAllItems=Clear")
public final class DeleteAllItems extends ActiveViewAction<SaBatchUI> {

    public DeleteAllItems() {
        super(SaBatchUI.class);
        refreshAction();
        putValue(NAME, Bundle.CTL_DeleteAllItems());
    }

    @Override
    protected void refreshAction() {
        SaBatchUI ui = context();
        enabled = ui != null && !ui.getElement().getCurrent().isEmpty();
    }

    @Override
    protected void process(SaBatchUI cur) {
        cur.clear(true);
    }
}
