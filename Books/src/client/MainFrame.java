package client;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

import rmiInterface.MyInterface;
import server.Book;

public class MainFrame extends JFrame{
	private static final long serialVersionUID = -3071391856524045044L;
	MyInterface stub;
	public MainFrame(String title, MyInterface stub) {
		super(title);
		this.stub = stub;

		setLayout(new BorderLayout());
		JTextField clientTextfield = new JTextField(10);
		clientTextfield.setMinimumSize(clientTextfield.getPreferredSize());
		final JTextArea textArea = new JTextArea();
		JLabel nameLabel = new JLabel("Книга:");
		JLabel authorLabel = new JLabel("Автор:");
		JLabel yearLabel = new JLabel("Година:");
		JLabel countLabel = new JLabel("Брой:");
		JTextField nameTextfield = new JTextField(10);
		nameTextfield.setMinimumSize(nameTextfield.getPreferredSize());
		JTextField authorTextfield = new JTextField(10);
		authorTextfield.setMinimumSize(authorTextfield.getPreferredSize());
		JTextField yearTextfield = new JTextField(10);
		yearTextfield.setMinimumSize(yearTextfield.getPreferredSize());
		JTextField countTextfield = new JTextField(10);
		countTextfield.setMinimumSize(countTextfield.getPreferredSize());
		JButton addBtn = new JButton("Дари книга");
		JButton listBtn = new JButton("Общ брой и списък с дарени книги:");

		nameTextfield.setText("книга ");
		authorTextfield.setText("автор ");
		yearTextfield.setText("1900");
		
		JComboBox<String> listBox = new JComboBox<String>();
		Dimension size2= listBox.getPreferredSize();
		size2.width=300;
		listBox.setPreferredSize(size2);
		listBox.setMinimumSize(listBox.getPreferredSize());
		DefaultComboBoxModel<String> dm = new DefaultComboBoxModel<String>();


		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
				String name = nameTextfield.getText();
				String author = authorTextfield.getText();//////
				int year = Integer.parseInt(yearTextfield.getText());
				String text="";
				text += name+", "+author+", "+year+"\n";
				textArea.append(text);
		            Book p=new Book(name,author,year);
		    	      try { 
		    	    	 synchronized(MyInterface.class) {
		    	         stub.addBook(p,title);}
		    	      } catch (Exception e) {
		    	         System.err.println("Client exception: " + e.toString());  
		    	      }
			
				nameTextfield.setText("книга ");
				authorTextfield.setText("автор ");
				yearTextfield.setText("1900");
					}
					
				});
				thread.start();
			}
			
		});
		listBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
						try {   
			    	         int counter = stub.getCount();
			    	         countTextfield.setText(Integer.toString(counter));
		    	         List<Book> list = (List<Book>)stub.getBooks(); 
		    	         dm.removeAllElements();
		    	         for (Book s:list) { 
		    	        	String record = s.getName()+", " + s.getAuthor()+", " + s.getYear();
		    					dm.addElement(record);
		    					listBox.setModel(dm);
		    	         }

		    	      } catch (Exception e) {
		    	         System.err.println("Client exception: " + e.toString()); 
		    	      }		
				    	 		
			}
			
		});
		Timer timer = new Timer(10000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				 try {   
	    	         int counter = stub.getCount();
	    	         countTextfield.setText(Integer.toString(counter));
    	         List<Book> list = (List<Book>)stub.getBooks(); 
    	         dm.removeAllElements();
    	         for (Book s:list) { 
    	        	String record = s.getName()+", " + s.getAuthor()+", " + s.getYear();
    					dm.addElement(record);
    					listBox.setModel(dm);
    	         }

    	      } catch (Exception e) {
    	         System.err.println("Client exception: " + e.toString()); 
    	      }	
			}
		});
		timer.start();
		JPanel detailPanel= new JPanel();
		
		Dimension size = detailPanel.getPreferredSize();
		size.width = 250;
		detailPanel.setPreferredSize(size);
		detailPanel.setBorder(BorderFactory.createTitledBorder("Книга"));
		detailPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx=0.5;
		gc.weighty=0.5;
		//first column
		gc.anchor=GridBagConstraints.LINE_END;
		gc.gridx=0;
		gc.gridy=0;
		detailPanel.add(nameLabel,gc);
		gc.gridx=0;
		gc.gridy=1;
		detailPanel.add(authorLabel,gc);
		gc.gridx=0;
		gc.gridy=2;
		detailPanel.add(yearLabel,gc);
		//second column
		gc.anchor=GridBagConstraints.LINE_START;
		gc.gridx=1;
		gc.gridy=0;
		detailPanel.add(nameTextfield,gc);
		gc.gridx=1;
		gc.gridy=1;
		detailPanel.add(authorTextfield,gc);
		gc.gridx=1;
		gc.gridy=2;
		detailPanel.add(yearTextfield,gc);
		//third row
		gc.gridx=1;
		gc.gridy=4;
		gc.weighty=5;
		detailPanel.add(addBtn,gc);
		
		gc.weighty=7;
		gc.gridx=0;
		gc.gridy=6;
		gc.gridwidth=2;
		gc.fill=GridBagConstraints.HORIZONTAL;
		detailPanel.add(listBtn,gc);
		
		gc.gridwidth=1;
		gc.fill =GridBagConstraints.NONE;
		gc.weightx=0.5;
		gc.gridx=0;
		gc.gridy=7;
		gc.anchor=GridBagConstraints.LINE_END;
		detailPanel.add(countLabel,gc);
		gc.gridx=1;
		gc.gridy=7;
		gc.anchor=GridBagConstraints.LINE_START;
		detailPanel.add(countTextfield,gc);		
		
		gc.weighty=0.5;
		gc.anchor=GridBagConstraints.FIRST_LINE_START;
		gc.gridx=0;
		gc.gridy=8;
		gc.gridwidth=2;
		gc.fill=GridBagConstraints.HORIZONTAL;
		detailPanel.add(listBox,gc);
		
		Container c = getContentPane();
		c.add(detailPanel,BorderLayout.WEST);
		c.add(textArea,BorderLayout.CENTER);
		


	}


}
