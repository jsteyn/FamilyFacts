package my;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONObject;

import smx.swing.AfPanel;
import smx.swing.layout.AfAnyWhere;
import smx.swing.layout.AfColumnLayout;
import smx.swing.layout.AfMargin;
import smx.swing.layout.AfRowLayout;
import smx.swing.layout.AfXLayout;

public class AddFather extends JDialog {

	JButton addnewpersonButton = new JButton("Add New Person");
	JButton selectexistingpersonButton = new JButton("Select Existing Person");
	JButton cancelButton = new JButton("Cancel");

	private boolean retValue = false;

	static String personid = null;

	public AddFather(JFrame owner) {
		super(owner, "Add Father", true);
		this.setSize(500, 300);

		AfPanel root = new AfPanel();
		this.setContentPane(root);
		root.setLayout(new AfColumnLayout(10));
		root.padding(10);

		AfPanel mainPanel = new AfPanel();
		root.add(mainPanel, "1w");
		mainPanel.setLayout(new AfColumnLayout(10));
		mainPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		mainPanel.padding(10);

		mainPanel.add(new JLabel("Add a father to " + personid), "200px");

		AfPanel buttonPanel = new AfPanel();
		root.add(buttonPanel, "30px");
		buttonPanel.setLayout(new AfRowLayout(10));

		buttonPanel.add(addnewpersonButton, "auto");
		addnewpersonButton.addActionListener((e) -> {
			// Open the window to add a new person
			// 打开添加一个新人的窗口
			onAddIndividual();

		});

		buttonPanel.add(selectexistingpersonButton, "auto");
		selectexistingpersonButton.addActionListener((e) -> {
			// Open the window of select existing person
			// 打开select existing person的窗口
			onSelectExist();

		});
		buttonPanel.add(cancelButton, "auto");

	}

	public boolean exec() {

		// Relative to owner centered display
		// 相对owner居中显示
		Rectangle frmRect = this.getOwner().getBounds();
		int width = this.getWidth();
		int height = this.getHeight();
		int x = frmRect.x + (frmRect.width - width) / 2;
		int y = frmRect.y + (frmRect.height - height) / 2;
		this.setBounds(x, y, width, height);

		// Display window
		// 显示窗口 ( 阻塞 ，直接对话框窗口被关闭)
		this.setVisible(true);

		return retValue;
	}

	// Open the window to add a new person
	// 打开添加一个新人的窗口
	public void onAddIndividual() {
		AddIndividual aii = new AddIndividual(this);
		if (aii.exec()) {
			Person person = aii.getValue();
			String firstname = person.firstname;
			String lastname = person.lastname;
			boolean sex1 = person.sex;
			String sex = String.valueOf(sex1);
			String birth = person.birthdate;
			String death = person.deathdate;
			String address = person.homeaddress;
			aii.testhttpaddfather(personid, firstname, lastname, sex, birth, death, address);

		}
	}

	// Open the window of select existing person
	// 打开select existing person的窗口
	private void onSelectExist() {
		SelectExistPerson sep = new SelectExistPerson(this);
		sep.testhttpselectexistfather(personid);
		System.out.println("personid:" + personid);
		if (sep.exec()) {
		}

	}

	public void testhttpaddfather(String personid, String firstname, String lastname, String sex, String birth,
			String death, String address) {
		// TODO Auto-generated method stub

		TestCallHttpAddFather.httpURLPOSTCase(personid, firstname, lastname, sex, birth, death, address);
	}
}
