/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdplus.sa.desktop.plugin.multiprocessing.actions;

import jdplus.sa.desktop.plugin.multiprocessing.ui.MultiProcessingManager;
import jdplus.sa.desktop.plugin.multiprocessing.ui.SaBatchUI;
import jdplus.toolkit.desktop.plugin.ui.ActiveViewAction;
import jdplus.sa.base.api.EstimationPolicyType;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle;

@ActionID(category = "SaProcessing",
        id = "demetra.desktop.sa.multiprocessing.actions.LocalRefreshMovingAverageParameters")
@ActionRegistration(displayName = "#CTL_LocalRefreshMovingAverageParameters", lazy = false)
@ActionReferences({
    @ActionReference(path = MultiProcessingManager.LOCALPATH + LocalRefreshPartial.PATH, position = 1230)
})
@NbBundle.Messages("CTL_LocalRefreshMovingAverageParameters=+ Moving average parameters")
public final class LocalRefreshMovingAverageParameters extends ActiveViewAction<SaBatchUI> {

    public LocalRefreshMovingAverageParameters() {
        super(SaBatchUI.class);
        refreshAction();
        putValue(NAME, Bundle.CTL_LocalRefreshMovingAverageParameters());
    }

    @Override
    protected void process(SaBatchUI ui) {
        ui.refresh(EstimationPolicyType.FixedAutoRegressiveParameters, true, false);
    }

    @Override
    protected void refreshAction() {
        SaBatchUI ui = context();
        enabled = ui != null && ui.getElement().isRefreshable() && ui.getSelectionCount() > 0;
    }
}
