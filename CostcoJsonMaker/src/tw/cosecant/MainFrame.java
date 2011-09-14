package tw.cosecant;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class MainFrame extends JFrame{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		MainFrame mf = new MainFrame();
//		go();
	}

	private static void go2() {
		for (long i = 20110001 ; i <=  20110044 ; i++){
			File file = new File(i+".json");

			try {
				FileInputStream fin = new FileInputStream(file);
				InputStreamReader isr = new InputStreamReader(fin,
			      "utf-8");
				JSONTokener jtk = new JSONTokener(isr);
				JSONObject ret = new JSONObject(jtk);
				fin.close();
				
				FileOutputStream fout = new FileOutputStream(file);
				fout.write(ret.toString().getBytes("utf-8"));
				fout.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private static void go() {
		JSONObject ret;
		JSONArray arr = new JSONArray();
		for (long i = 20110104 ; i <=  20110135 ; i++){
			arr.put(i);
		}	
		try {
			File file = new File("verlog.json");
			FileInputStream fin = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fin,
		      "utf-8");
			JSONTokener jtk = new JSONTokener(isr);
			 ret = new JSONObject(jtk);
			fin.close();
			
			ret.put("maxVer", 8);

			ret.put("chan0008", arr);
			

			FileOutputStream fout = new FileOutputStream(file);
			fout.write(ret.toString(2).getBytes("utf-8"));
			fout.close();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
	}

	JPanel pan = new JPanel();
	JLabel[] labs = new JLabel[12];
	JTextField[] txts = new JTextField[12];
	String[] keys= {"sdate",
	 "edate",
	  "campname",
	  "avail",
	  "img",
	  "branname",
	  "prodname",
	  "oprice",
	  "dprice",
	  "sn",
	  "quan1",
	  "quan2"};
	
	JButton butSave = new JButton("save");
	JButton butClear = new JButton("clear");
	JTextField fname = new JTextField("20110001");
	
	public MainFrame(){
		super("JSON MAKER");
		pan.setLayout(new GridLayout(14,2));
		pan.add(new JLabel("File Name"));
		pan.add(fname);
		for (int i = 0 ; i < 12 ; i++){
			labs[i] = new JLabel(keys[i]);
			txts[i] = new JTextField();
			pan.add(labs[i]);
			pan.add(txts[i]);
		}
		butSave.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JSONObject ret = new JSONObject();
				for (int i = 0 ; i < 12 ; i++){
					try {
						if (txts[i].getText().length() > 0)
						ret.put(keys[i], txts[i].getText());
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				
				File file = new File(fname.getText()+".json");
				try {
					FileOutputStream fout = new FileOutputStream(file);
					fout.write(ret.toString(2).getBytes("utf-8"));
					fout.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String fn = fname.getText();
				fname.setText((Long.parseLong(fn)+1)+"");
				txts[4].setText((Long.parseLong(txts[4].getText().split("[.]")[0])+1)+".png");
				for (int i = 5 ; i < 12 ; i++){
					txts[i].setText("");
				}	
			}			
		});
		
		butClear.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0 ; i < 12 ; i++){
					txts[i].setText("");
				}			
			}			
		});
		pan.add(butSave);
		pan.add(butClear);
		pan.setSize(800, 600);
		
		this.add(pan);
		
		this.pack();
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
//		System.exit(0);
	}
}
