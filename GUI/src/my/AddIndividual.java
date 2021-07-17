package my;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import smx.swing.AfPanel;
import smx.swing.layout.AfColumnLayout;
import smx.swing.layout.AfRowLayout;
import smx.swing.layout.AfYLayout;

public class AddIndividual extends JDialog {

	String ssss = null;//Get user input-sex
	static String personid = null;

	public JTextField personidField = new JTextField(20);
	public JTextField firstnameField = new JTextField(20);
	public JTextField lastnameField = new JTextField(20);
	public JComboBox<String> sexField = new JComboBox<>();
	public JTextField birthdateField = new JTextField(20);
	public JTextField deathdateField = new JTextField(20);
	public JTextField homeaddressField = new JTextField(20);

	JButton okButton = new JButton("OK");

	DefaultTableModel tableModel = new DefaultTableModel();

	private boolean retValue = false;

	public AddIndividual(JFrame owner) {
		super(owner, "Add Person", true);
		this.setSize(500, 400);

		AfPanel root = new AfPanel();
		this.setContentPane(root);
		root.setLayout(new AfColumnLayout(10));
		root.padding(10);

		AfPanel main = new AfPanel();
		root.add(main, "1w");
		main.setLayout(new AfColumnLayout(10));
		main.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		main.padding(10);

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("First name"), "70px");
			row.add(firstnameField, "1w");

		}
		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Last name"), "70px");
			row.add(lastnameField, "1w");
		}

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Sex"), "70px");
			row.add(sexField, "1w");

			sexField.addItem("Female");
			sexField.addItem("Male");

			sexField.addItemListener(new ItemListener() {

				@Override
				// In order to test the sex drop-down list
				// 为了测试sex下拉列表
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					if (e.getStateChange() == ItemEvent.SELECTED) {
						System.out
								.println("select: " + sexField.getSelectedIndex() + " = " + sexField.getSelectedItem());
						ssss = (String) sexField.getSelectedItem();
						System.out.println(ssss);

					}
				}

			});

		}

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Birth"), "70px");
			row.add(birthdateField, "1w");
		}

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Death"), "70px");
			row.add(deathdateField, "1w");

		}
		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Home Address"), "70px");
			row.add(homeaddressField, "1w");

		}
		// bottom
		// 底下
		AfPanel bottom = new AfPanel();
		root.add(bottom, "30px");
		bottom.setLayout(new AfRowLayout(10));
		bottom.add(new JLabel(), "1w");
		bottom.add(okButton, "auto");

		// Lambda expression
		// Lambda 表达式
		okButton.addActionListener((e) -> {

			if (checkValid()) {
				retValue = true;
				setVisible(false);
			}

		});

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

	// Set initial value
	// 设置初始值
	public void setValue(Person v) {
		personidField.setText(v.personid);
		firstnameField.setText(v.firstname);
		lastnameField.setText(v.lastname);
		sexField.setSelectedIndex(v.sex ? 1 : 0);
		birthdateField.setText(v.birthdate);
		deathdateField.setText(v.deathdate);
		homeaddressField.setText(v.homeaddress);

	}

	// Check input validity
	// 检查输入有效性
	public boolean checkValid() {
		Person v = getValue();
		if (v.firstname.isEmpty()) {
			JOptionPane.showMessageDialog(this, "firstname must not be empty!");
			return false;
		}
		if (v.lastname.isEmpty()) {
			JOptionPane.showMessageDialog(this, "lastname must not be empty!");
			return false;
		}
		return true;
	}

	// Get user input
	// 获取用户的输入
	public Person getValue() {
		Person v = new Person();
		v.firstname = firstnameField.getText().trim();
		v.lastname = lastnameField.getText().trim();
		if (ssss.equals("Male")) {
			v.sex = false;
		} else {
			v.sex = true;
		}

		v.birthdate = birthdateField.getText().trim();
		v.deathdate = deathdateField.getText().trim();
		v.homeaddress = homeaddressField.getText().trim();

		return v;
	}

	// Connection interface - AddPerson：TestCallHttpAddPerson class
	// 连接接口 TestCallHttpAddPerson 类
	public void testhttpaddperson(String firstname, String lastname, String sex, String birth, String death,
			String address) {

		TestCallHttpAddPerson.httpURLPOSTCase(firstname, lastname, sex, birth, death, address);
	}

	// Connection interface - AddSpouse：TestCallHttpAddSpouse class
	// 连接接口 TestCallHttpAddSpouse 类
	public void testhttpaddspouse(String personid, String firstname, String lastname, String sex, String birth,
			String death, String address) {

		TestCallHttpAddSpouse.httpURLPOSTCase(personid, firstname, lastname, sex, birth, death, address);
	}

	// Connection interface - AddFather：TestCallHttpAddFather class
	// 连接接口 TestCallHttpAddFather 类
	public void testhttpaddfather(String personid, String firstname, String lastname, String sex, String birth,
			String death, String address) {

		TestCallHttpAddFather.httpURLPOSTCase(personid, firstname, lastname, sex, birth, death, address);
	}

	// Connection interface - AddMother：TestCallHttpAddMother class
	// 连接接口 TestCallHttpAddMother 类
	public void testhttpaddmother(String personid, String firstname, String lastname, String sex, String birth,
			String death, String address) {

		TestCallHttpAddMother.httpURLPOSTCase(personid, firstname, lastname, sex, birth, death, address);
	}

	// Connection interface - PersonEdit：TestCallHttpPersonEdit class
	// 连接接口 TestCallHttpPersonEdit 类
	public void testhttpeditperson(String personid, String firstname, String lastname, String sex, String birth,
			String death, String address) {

		TestCallHttpPersonEdit.httpURLPOSTCase(personid, firstname, lastname, sex, birth, death, address);
	}

	public void testhttp() {

		TestCallHttpList tchpl = new TestCallHttpList();

		// Get server response result
		// 获取服务器响应结果
		JSONArray array = tchpl.httpURLGETCase("http://3.9.172.108:8090/api/person/list");

		// Display the returned person list information
		// 显示返回的person列表信息
		if (!array.isEmpty()) {
			for (int i = 0; i < array.size(); i++) {
				JSONObject jsonObject = array.getJSONObject(i);
				Person person = new Person();
				person.personid = Integer.toString(jsonObject.getInt("personId"));
				person.firstname = jsonObject.getString("firstName");
				person.lastname = jsonObject.getString("lastName");
				String str = jsonObject.getString("sex");
				person.sex = "male".equals(str) ? true : false;
				person.birthdate = Integer.toString(jsonObject.getInt("birth"));
				person.deathdate = Integer.toString(jsonObject.getInt("death"));
				person.homeaddress = jsonObject.getString("address");
				// Add person to the table
				// 在表格中添加person
				addTableRow(person);

			}
		}
	}

	private void addTableRow(Person item) {

		Vector<Object> rowData = new Vector<>();
		rowData.add(item.personid);
		rowData.add(item.firstname);
		rowData.add(item.lastname);
		rowData.add(item.sex);
		rowData.add(item.birthdate);
		rowData.add(item.deathdate);
		rowData.add(item.homeaddress);

		// Add a row
		// 添加一行
		tableModel.addRow(rowData);
	}

	// Add a spouse (add a new person)
	// 添加一个配偶（添加一个新人）
	public AddIndividual(AddSpouse addSpouse) {
		super(addSpouse, "Add Person", true);
		this.setSize(500, 400);

		AfPanel root = new AfPanel();
		this.setContentPane(root);
		root.setLayout(new AfColumnLayout(10));
		root.padding(10);

		AfPanel main = new AfPanel();
		root.add(main, "1w");
		main.setLayout(new AfColumnLayout(10));
		main.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		main.padding(10);

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("First name"), "70px");
			row.add(firstnameField, "1w");

		}
		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Last name"), "70px");
			row.add(lastnameField, "1w");
		}

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Sex"), "70px");
			row.add(sexField, "1w");

			sexField.addItem("Female");
			sexField.addItem("Male");

			sexField.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {

					if (e.getStateChange() == ItemEvent.SELECTED) {
						System.out
								.println("select: " + sexField.getSelectedIndex() + " = " + sexField.getSelectedItem());
						ssss = (String) sexField.getSelectedItem();
						System.out.println(ssss);

					}
				}

			});
		}

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Birth"), "70px");
			row.add(birthdateField, "1w");
		}

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Death"), "70px");
			row.add(deathdateField, "1w");

		}
		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Home Address"), "70px");
			row.add(homeaddressField, "1w");

		}

		AfPanel bottom = new AfPanel();
		root.add(bottom, "30px");
		bottom.setLayout(new AfRowLayout(10));
		bottom.add(new JLabel(), "1w");
		bottom.add(okButton, "auto");

		okButton.addActionListener((e) -> {

			if (checkValid()) {
				retValue = true;
				setVisible(false);
			}

		});

	}

	// Add a Father (add a new person)
	// 添加一个Father（添加一个新人）
	public AddIndividual(AddFather addFather) {
		super(addFather, "Add Person", true);
		this.setSize(500, 400);

		AfPanel root = new AfPanel();
		this.setContentPane(root);
		root.setLayout(new AfColumnLayout(10));
		root.padding(10);

		AfPanel main = new AfPanel();
		root.add(main, "1w");
		main.setLayout(new AfColumnLayout(10));
		main.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		main.padding(10);

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("First name"), "70px");
			row.add(firstnameField, "1w");

		}
		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Last name"), "70px");
			row.add(lastnameField, "1w");
		}

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Sex"), "70px");
			row.add(sexField, "1w");

			sexField.addItem("Female");
			sexField.addItem("Male");

			sexField.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {

					if (e.getStateChange() == ItemEvent.SELECTED) {
						System.out
								.println("select: " + sexField.getSelectedIndex() + " = " + sexField.getSelectedItem());
						ssss = (String) sexField.getSelectedItem();
						System.out.println(ssss);

					}
				}

			});
		}

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Birth"), "70px");
			row.add(birthdateField, "1w");
		}

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Death"), "70px");
			row.add(deathdateField, "1w");

		}
		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Home Address"), "70px");
			row.add(homeaddressField, "1w");

		}

		AfPanel bottom = new AfPanel();
		root.add(bottom, "30px");
		bottom.setLayout(new AfRowLayout(10));
		bottom.add(new JLabel(), "1w");
		bottom.add(okButton, "auto");

		okButton.addActionListener((e) -> {

			if (checkValid()) {
				retValue = true;
				setVisible(false);
			}

		});

	}

	// Add a Mother (add a new person)
	// 添加一个Mother（添加一个新人）
	public AddIndividual(AddMother addMother) {
		super(addMother, "Add Person", true);
		this.setSize(500, 400);

		AfPanel root = new AfPanel();
		this.setContentPane(root);
		root.setLayout(new AfColumnLayout(10));
		root.padding(10);

		AfPanel main = new AfPanel();
		root.add(main, "1w");
		main.setLayout(new AfColumnLayout(10));
		main.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		main.padding(10);

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("First name"), "70px");
			row.add(firstnameField, "1w");

		}
		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Last name"), "70px");
			row.add(lastnameField, "1w");
		}

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Sex"), "70px");
			row.add(sexField, "1w");

			sexField.addItem("Female");
			sexField.addItem("Male");

			sexField.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {

					if (e.getStateChange() == ItemEvent.SELECTED) {
						System.out
								.println("select: " + sexField.getSelectedIndex() + " = " + sexField.getSelectedItem());
						ssss = (String) sexField.getSelectedItem();
						System.out.println(ssss);

					}
				}

			});
		}

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Birth"), "70px");
			row.add(birthdateField, "1w");
		}

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Death"), "70px");
			row.add(deathdateField, "1w");

		}
		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Home Address"), "70px");
			row.add(homeaddressField, "1w");

		}

		AfPanel bottom = new AfPanel();
		root.add(bottom, "30px");
		bottom.setLayout(new AfRowLayout(10));
		bottom.add(new JLabel(), "1w");
		bottom.add(okButton, "auto");

		okButton.addActionListener((e) -> {

			if (checkValid()) {
				retValue = true;
				setVisible(false);
			}

		});

	}

}
