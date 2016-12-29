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

    setModel(new DefaultComboBoxModel(myVector));
    setSelectedIndex(-1);
    setEditable(true);
    JTextField text = (JTextField) this.getEditor().getEditorComponent();
    text.setFocusable(true);
    text.setText("");
    text.addKeyListener(new ComboListener(this, myVector));
}

/**
 * set the item list of the AutoComboBox
 * @param keyWord an String array
 */
public void setKeyWord(ArrayList<String> keyWord) {
	myVector.clear();
    int a;
    for (a = 0; a < keyWord.size(); a++) {
        myVector.add(keyWord.get(a));
    }
}

public class ComboListener extends KeyAdapter
{
private @SuppressWarnings("rawtypes")
JComboBox cbListener;
private @SuppressWarnings("rawtypes")
Vector vector;

@SuppressWarnings("rawtypes")
public ComboListener(JComboBox cbListenerParam, Vector vectorParam)
{
    cbListener = cbListenerParam;
    vector = vectorParam;
}

@SuppressWarnings({ "unchecked", "rawtypes" })
public void keyReleased(KeyEvent key)
{
            // TODO Auto-generated method stub
            String text = ((JTextField)key.getSource()).getText();

            cbListener.setModel(new DefaultComboBoxModel(getFilteredList(text)));
            cbListener.setSelectedIndex(-1);
            ((JTextField)cbListener.getEditor().getEditorComponent()).setText(text);
            cbListener.showPopup();
}


@SuppressWarnings({ "rawtypes", "unchecked" })
public Vector getFilteredList(String text)
{
    Vector v = new Vector();
    for(int a = 0;a<vector.size();a++)
    {
        if(vector.get(a).toString().toLowerCase().startsWith(text.toLowerCase()))
        {
            v.add(vector.get(a).toString());
        }
    }
    return v;
}
}

}
//bron: http://stackoverflow.com/questions/13681977/jcombobox-autocomplete (mits enkele aanp�ssingen)