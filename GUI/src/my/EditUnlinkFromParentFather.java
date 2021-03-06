package my;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
import net.sf.json.JSONObject;
import smx.swing.AfPanel;
import smx.swing.layout.AfColumnLayout;
import smx.swing.layout.AfRowLayout;

public class EditUnlinkFromParentFather extends JDialog {

	AfPanel root = new AfPanel();
	JPanel jpanel1 = new JPanel();
	JPanel jpanel2 = new JPanel();
	AfPanel jpanel3 = new AfPanel();
	MigLayout layout = new MigLayout();

	JTable informationtableFather = new JTable();
	DefaultTableModel informationmodelFather = new DefaultTableModel();
	String[] informationcolumnNamesFather = { "Facts", "Informations" };

	String fatherfirstname = null;
	String fatherlastname = null;
	String fatherbirth = null;
	String fatherdeath = null;
	String fatheraddress = null;

	String fatherid = null;

	static String personid = null;

	JButton UnlinkButton = new JButton("Unlink from Parents");
	JButton cancelButton = new JButton("Cancel");

	private boolean retValue = false;

	public EditUnlinkFromParentFather(JFrame owner) {
		super(owner, "Edit Unlink From Father", true);
		this.setSize(500, 400);

		this.setContentPane(root);
		root.setLayout(new AfColumnLayout(10));

		jpanel1.setPreferredSize(new Dimension(500, 100));
		jpanel2.setPreferredSize(new Dimension(500, 200));
		jpanel3.setPreferredSize(new Dimension(500, 50));

		root.add(jpanel1);
		root.add(jpanel2);

		JLabel label = new JLabel("Unlink from Parents?");
		JLabel label1 = new JLabel("Do you want to Unlink the hightlighted person as a child in this family?");
		jpanel1.add(label);
		jpanel1.add(label1);

		JScrollPane scrollPane = new JScrollPane(informationtableFather);
		scrollPane.setPreferredSize(new Dimension(480, 180));
		jpanel2.add(scrollPane, BorderLayout.CENTER);

		Object[][] informationdataFather = { { "First name", "" }, { "Last name", "" }, { "Birth", "" },
				{ "death", "" }, { "Address", "" } };
		informationmodelFather.setDataVector(informationdataFather, informationcolumnNamesFather);
		informationtableFather.setModel(informationmodelFather);
		informationtableFather.setFillsViewportHeight(true);
		informationtableFather.setRowSelectionAllowed(true);
		informationtableFather.setRowHeight(30);

		root.add(jpanel3, "30px");
		jpanel3.setLayout(new AfRowLayout(10));
		jpanel3.add(new JLabel(), "1w");
		jpanel3.add(UnlinkButton, "auto");
		UnlinkButton.addActionListener((e) -> {
			onunlinkfather();

		});

		jpanel3.add(cancelButton, "auto");

		onshowfather();
	}

	private void onunlinkfather() {
		TestCallHttpUnlinkFather.httpURLPOSTCase(personid, fatherid);

	}

	private void onshowfather() {

		JSONObject fatherObject = null;

		JSONObject informationfather = TestCallHttpAllRelevantPeople.httpURLGETCase(personid);

		System.out.println("personid:" + personid);
		System.out.println("--———————a中的father(选中的person's father information)(字符串)++++++------");
		String father = informationfather.getString("father");
		System.out.println("father:" + father);

		fatherObject = JSONObject.fromObject(father);

		String id = fatherObject.getString("personId");
		System.out.println("father personId：" + id);
		fatherid = id;
		String firstname = fatherObject.getString("firstName");
		System.out.println("father firstname：" + firstname);
		String lastname = fatherObject.getString("lastName");
		System.out.println("father lastname：" + lastname);
		String birth = fatherObject.getString("birth");
		System.out.println("father birth：" + birth);
		String death = fatherObject.getString("death");
		System.out.println("father death：" + death);
		String address = fatherObject.getString("address");
		System.out.println("father address：" + address);

		fatherfirstname = firstname;
		fatherlastname = lastname;
		fatherbirth = birth;
		fatherdeath = death;
		fatheraddress = address;

		Test t1 = new Test();
		t1.ttest(fatherfirstname, fatherlastname, fatherbirth, fatherdeath, fatheraddress);
		Object[][] informationdataFather = { { "First name", fatherfirstname }, { "Last name", fatherlastname },
				{ "Birth", fatherbirth }, { "death", fatherdeath }, { "Address", fatheraddress } };
		informationmodelFather.setDataVector(informationdataFather, informationcolumnNamesFather);
		informationtableFather.setModel(informationmodelFather);
		informationtableFather.setFillsViewportHeight(true);
		informationtableFather.setRowSelectionAllowed(true);
		informationtableFather.setRowHeight(30);

	}

	public boolean exec() {

		Rectangle frmRect = this.getOwner().getBounds();
		int width = this.getWidth();
		int height = this.getHeight();
		int x = frmRect.x + (frmRect.width - width) / 2;
		int y = frmRect.y + (frmRect.height - height) / 2;
		this.setBounds(x, y, width, height);

		this.setVisible(true);

		return retValue;
	}

}
