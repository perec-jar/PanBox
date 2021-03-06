/*
 * 
 *               Panbox - encryption for cloud storage 
 *      Copyright (C) 2014-2015 by Fraunhofer SIT and Sirrix AG 
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Additonally, third party code may be provided with notices and open source
 * licenses from communities and third parties that govern the use of those
 * portions, and any licenses granted hereunder do not alter any rights and
 * obligations you may have under such open source licenses, however, the
 * disclaimer of warranty and limitation of liability provisions of the GPLv3 
 * will apply to all the product.
 * 
 */
package org.panbox.desktop.common.gui;

import org.panbox.Settings;
import org.panbox.desktop.common.gui.devices.DeviceListCellRenderer;
import org.panbox.desktop.common.gui.devices.DeviceListModel;
import org.panbox.desktop.common.gui.devices.PanboxDevice;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AddDeviceToShareDialog extends javax.swing.JDialog {

	private static final long serialVersionUID = -7114881024525097652L;
	private static final ResourceBundle bundle = ResourceBundle.getBundle(
			"org.panbox.desktop.common.gui.Messages", Settings.getInstance()
					.getLocale());
	private final DeviceListModel model = new DeviceListModel();

	private List<PanboxDevice> devices = new ArrayList<PanboxDevice>();
	private boolean aborted = false;

	public AddDeviceToShareDialog(java.awt.Frame parent, DeviceListModel model,
			List<PanboxDevice> list) {
		super(parent, true);

		for (int i = 0, ctr = 0; i < model.getSize(); i++) {
			if (!list.contains(model.get(i))) {
				this.model.add(ctr++, model.get(i));
			}
		}
		initComponents();

		setLocationRelativeTo(parent);
		
		if(this.model.getSize() == 0) {
			addDevicesButton.setEnabled(false);
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		addDevicesButton = new javax.swing.JButton();
		abortButton = new javax.swing.JButton();
		deviceListScrollPane = new javax.swing.JScrollPane();
		deviceList = new javax.swing.JList<PanboxDevice>();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle(bundle.getString("AddDeviceToShareDialog.title")); // NOI18N
		setResizable(false);

		addDevicesButton.setText(bundle
				.getString("AddDeviceToShareDialog.addDevicesButton.text")); // NOI18N
		addDevicesButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addDevicesButtonActionPerformed(evt);
			}
		});

		abortButton.setText(bundle
				.getString("AddDeviceToShareDialog.abortButton.text")); // NOI18N
		abortButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				abortButtonActionPerformed(evt);
			}
		});

		deviceList.setModel(model);
		deviceList.setCellRenderer(new DeviceListCellRenderer());
		deviceListScrollPane.setViewportView(deviceList);
		deviceList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(deviceList.getSelectedValuesList().isEmpty()) {
					addDevicesButton.setEnabled(false);
				} else {
					addDevicesButton.setEnabled(true);
				}
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														deviceListScrollPane,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														332, Short.MAX_VALUE)
												.addGroup(
														javax.swing.GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup()
																.addGap(0,
																		0,
																		Short.MAX_VALUE)
																.addComponent(
																		abortButton)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		addDevicesButton)))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(deviceListScrollPane,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										397, Short.MAX_VALUE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(addDevicesButton)
												.addComponent(abortButton))
								.addContainerGap()));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void abortButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_abortButtonActionPerformed
		aborted = true;
		this.dispose();
	}// GEN-LAST:event_abortButtonActionPerformed

	private void addDevicesButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_addDevicesButtonActionPerformed
		devices = deviceList.getSelectedValuesList();
		this.dispose();
	}// GEN-LAST:event_addDevicesButtonActionPerformed

	// @SuppressWarnings("unchecked")
	public List<PanboxDevice> getResult() throws OperationAbortedException {
		if (aborted) {
			throw new OperationAbortedException("Operation was aborted!");
		} else if (devices == null) {
			throw new OperationAbortedException("No contacts selected!!");
		} else {
			return devices;
		}
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton abortButton;
	private javax.swing.JButton addDevicesButton;
	private javax.swing.JList<PanboxDevice> deviceList;
	private javax.swing.JScrollPane deviceListScrollPane;
	// End of variables declaration//GEN-END:variables
}
