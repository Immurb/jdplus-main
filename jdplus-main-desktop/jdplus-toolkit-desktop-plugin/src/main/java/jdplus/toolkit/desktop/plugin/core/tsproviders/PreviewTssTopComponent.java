/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdplus.toolkit.desktop.plugin.core.tsproviders;

import jdplus.toolkit.desktop.plugin.TsManager;
import jdplus.toolkit.desktop.plugin.components.JTsChart;
import jdplus.toolkit.desktop.plugin.components.parts.HasTsCollection.TsUpdateMode;
import jdplus.toolkit.base.api.timeseries.TsCollection;
import jdplus.toolkit.base.api.timeseries.TsInformationType;
import jdplus.toolkit.base.tsp.DataSet;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.*;
import org.openide.windows.TopComponent;

import java.util.Optional;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//ec.nbdemetra.ui//PreviewTss//EN",
        autostore = false)
@TopComponent.Description(preferredID = "PreviewTssTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "explorer", openAtStartup = false)
@ActionID(category = "Window", id = "ec.nbdemetra.core.PreviewTssTopComponent")
@ActionReference(path = "Menu/Window", position = 301)
@TopComponent.OpenActionRegistration(displayName = "#CTL_PreviewTssAction",
        preferredID = "PreviewTssTopComponent")
@NbBundle.Messages({
        "CTL_PreviewTssAction=Preview TimeSeries",
        "CTL_PreviewTssTopComponent=Preview Window",
        "HINT_PreviewTssTopComponent=This is a Preview window"
})
public final class PreviewTssTopComponent extends TopComponent implements LookupListener {

    private Lookup.Result<DataSetNode> lookupResult = null;

    public PreviewTssTopComponent() {
        initComponents();
        setName(Bundle.CTL_PreviewTssTopComponent());
        setToolTipText(Bundle.HINT_PreviewTssTopComponent());

        jTsChart1.setLegendVisible(false);
        jTsChart1.setTsUpdateMode(TsUpdateMode.None);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTsChart1 = new JTsChart();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 680, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, 0)
                                        .addComponent(jTsChart1, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                                        .addGap(0, 0, 0)))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 420, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, 0)
                                        .addComponent(jTsChart1, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                                        .addGap(0, 0, 0)))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JTsChart jTsChart1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        lookupResult = Utilities.actionsGlobalContext().lookupResult(DataSetNode.class);
        lookupResult.addLookupListener(this);
    }

    @Override
    public void componentClosed() {
        lookupResult.removeLookupListener(this);
        lookupResult = null;

    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    public void resultChanged(LookupEvent le) {
        if (le.getSource().equals(lookupResult)) {
            jTsChart1.setTsCollection(
                    lookupResult
                            .allInstances()
                            .stream()
                            .filter(o -> o instanceof SeriesNode)
                            .map(o -> o.getLookup().lookup(DataSet.class))
                            .map(o -> TsManager.get().getTs(o, TsInformationType.None))
                            .filter(Optional::isPresent)
                            .map(Optional::orElseThrow)
                            .collect(TsCollection.toTsCollection())
            );
        }
    }
}
