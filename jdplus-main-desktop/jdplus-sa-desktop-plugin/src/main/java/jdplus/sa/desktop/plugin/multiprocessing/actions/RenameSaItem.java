/*
 * Copyright 2013 National Bank of Belgium
 *
 * Licensed under the EUPL, Version 1.1 or – as soon they will be approved
 * by the European Commission - subsequent versions of the EUPL (the "Licence");
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 *
 * http://ec.europa.eu/idabc/eupl
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 */
package jdplus.sa.desktop.plugin.multiprocessing.actions;

import jdplus.sa.desktop.plugin.multiprocessing.ui.MultiProcessingManager;
import jdplus.sa.desktop.plugin.multiprocessing.ui.SaBatchUI;
import jdplus.sa.desktop.plugin.multiprocessing.ui.SaNode;
import jdplus.toolkit.desktop.plugin.ui.ActiveViewAction;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "SaProcessing",
        id = "demetra.desktop.sa.multiprocessing.actions.RenameSaItem")
@ActionRegistration(displayName = "#CTL_RenameSaItem", lazy = true)
@ActionReferences({
    @ActionReference(path = MultiProcessingManager.LOCALPATH, position = 1700)
})
@Messages("CTL_RenameSaItem=Rename...")
public final class RenameSaItem extends ActiveViewAction<SaBatchUI> {

    public static final String RENAME_TITLE = "Please enter the new name",
            NAME_MESSAGE = "New name:";

    public RenameSaItem() {
        super(SaBatchUI.class);
    }

    @Override
    protected void process(SaBatchUI cur) {
        SaNode item = cur.getSelection()[0];
        if (item != null) {
            String newName, oldName = item.getName();
            SaItemName nd = new SaItemName(cur, NAME_MESSAGE, RENAME_TITLE, oldName);
            if (DialogDisplayer.getDefault().notify(nd) != NotifyDescriptor.OK_OPTION) {
                return;
            }
            newName = nd.getInputText().trim();
            item.setName(newName);
            cur.getController().getDocument().setDirty();
            cur.getController().changed();
            cur.redrawAll();
        }
    }

    @Override
    protected void refreshAction() {
        SaBatchUI ui = context();
        if (ui == null) {
            enabled = false;
        } else {
            enabled = ui.getSelectionCount() == 1;
        }
    }

}

class SaItemName extends NotifyDescriptor.InputLine {

    SaItemName(final SaBatchUI cur, String title, String text, final String old) {
        super(title, text);
        setInputText(old);
        textField.addKeyListener(new KeyAdapter() {
            // To handle VK_ENTER !!!
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && !textField.getInputVerifier().verify(textField)) {
                    e.consume();
                }
            }
        });
        textField.setInputVerifier(new InputVerifier() {

            @Override
            public boolean verify(JComponent input) {
                JTextField txt = (JTextField) input;
                String name = txt.getText().trim();
                if (name.equals(old)) {
                    return true;
                }
                if (cur.getElement().getCurrent().parallelStream()
                        .anyMatch(item -> item.getName().equalsIgnoreCase(name))) {
                    NotifyDescriptor nd = new NotifyDescriptor.Message(name + " is in use. You should choose another name!");
                    DialogDisplayer.getDefault().notify(nd);
                    return false;
                }
                return true;
            }
        });
    }
}
