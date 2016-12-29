package source;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class AutoComboBox extends JComboBox<Object> {

	/**
		 * 
		 */
	private static final long serialVersionUID = 4127002739909805989L;
	private Vector<String> myVector = new Vector<String>();

	public AutoComboBox() {

		this.setModel(new DefaultComboBoxModel(this.myVector));
		this.setSelectedIndex(-1);
		this.setEditable(true);
		JTextField text = (JTextField) this.getEditor().getEditorComponent();
		text.setFocusable(true);
		text.setText("");
		text.addKeyListener(new ComboListener(this, this.myVector));
	}

	/**
	 * set the item list of the AutoComboBox
	 * 
	 * @param keyWord
	 *            an String array
	 */
	public void setKeyWord(ArrayList<String> keyWord) {
		this.myVector.clear();
		int a;
		for (a = 0; a < keyWord.size(); a++) {
			this.myVector.add(keyWord.get(a));
		}
	}

	public class ComboListener extends KeyAdapter {
		private @SuppressWarnings("rawtypes") JComboBox cbListener;
		private @SuppressWarnings("rawtypes") Vector vector;

		@SuppressWarnings("rawtypes")
		public ComboListener(JComboBox cbListenerParam, Vector vectorParam) {
			this.cbListener = cbListenerParam;
			this.vector = vectorParam;
		}

		@Override
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public void keyReleased(KeyEvent key) {
			// TODO Auto-generated method stub
			String text = ((JTextField) key.getSource()).getText();

			this.cbListener.setModel(new DefaultComboBoxModel(this.getFilteredList(text)));
			this.cbListener.setSelectedIndex(-1);
			((JTextField) this.cbListener.getEditor().getEditorComponent()).setText(text);
			this.cbListener.showPopup();
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		public Vector getFilteredList(String text) {
			Vector v = new Vector();
			for (int a = 0; a < this.vector.size(); a++) {
				if (this.vector.get(a).toString().toLowerCase().startsWith(text.toLowerCase())) {
					v.add(this.vector.get(a).toString());
				}
			}
			return v;
		}
	}

}
// bron: http://stackoverflow.com/questions/13681977/jcombobox-autocomplete
// (mits enkele aanp�ssingen)