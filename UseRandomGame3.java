import javax.swing.border.*;
import java.awt.*;
import java.util.Random;
import java.awt.event.*;
import javax.swing.*;
class UseRandomGame3 extends JFrame implements ActionListener
{
	JButton bicon;
	Border border;
	Timer t;
	JButton jb[] = new JButton[5];
	JPanel jp; 
	JLabel l1,l2,l3;
	String str[]={"0.png","1.png","2.png","3.png","4.png"};
	int n=5,m=5,index=0,j=0,k=0;
	int rnum[] = new int[m];
	int a[] = new int[5];
	Font f;
	JPanel disPan,pan2;
	UseRandomGame3()
	{
		setTitle("Image Game");
		setSize(500,500);
		setVisible(true);
		setDefaultCloseOperation(3);
		setLayout(new FlowLayout());
		
		f = new Font("Broadway",Font.BOLD,20);
		l1 = new JLabel("REMEMBER THE ORDER OF IMAGES");	
		l1.setFont(f);
		jp = new JPanel();
		
		disPan=new JPanel();

		t=new Timer(1000,this);		
		bicon=new JButton();
		add(l1);
		add(bicon);
		t.start();
		validate();
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(n>=1)
		{
			int num=generateNum();

			if(index==0)
				rnum[index]=num;
			else
			{
				for(int i=0;i<index;i++)
				{
					while(rnum[i]==num)
					{
						num=generateNum();
						i = 0;
					}
				}
				rnum[index]=num;
			}
			System.out.println(rnum[index]);
			index++;
			bicon.setIcon(myIcon(str[num],150));
			this.add(bicon);
		}
		else
		{
			t.stop();
			bicon.setIcon(null);
			remove(l1);
			addPanel();
			add(disPan);
			//remove(l3);
			l3=new JLabel("YOUR ORDER");
			l3.setFont(f);
			add(l3);	
		}
		n--;
		validate();	
	}
	public Icon myIcon(String str,int x)
	{
		ImageIcon icon=new ImageIcon(str);
		Image img=icon.getImage();
		Image newImage=img.getScaledInstance(x,x,Image.SCALE_SMOOTH);
		Icon icon2=new ImageIcon(newImage);
		return(icon2);	
	}
	public void addPanel()
	{
		l2= new JLabel("CHOOSE THE ORDER OF IMAGES");
		l2.setFont(f);
		add(l2);
		for(int i = 0;i<5;i++)
			{
				jb[i]=new JButton(""+i);
				jb[i].setIcon(myIcon(str[i],50));
				jb[i].addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ae)
					{
						if(ae.getSource() instanceof JButton) 
						{
							a[k]= Integer.parseInt(((JButton)ae.getSource()).getText());
							System.out.println(a[k]);
							k++;
								
							JButton disbutton=new JButton(((JButton)ae.getSource()).getIcon());
							disPan = new JPanel();
							displayPanel(disbutton);
							add(disPan);
							
							if(k==5)
							{
								l3 = new JLabel(result());
								Font f1 = new Font("Vivaldi",Font.BOLD,70); 		
								l3.setFont(f1);
								//for(int i=0;i<5;i++) remove(disPan);
								remove(jp);
								remove(l2);
								remove(l3);
								
								add(l3);
							}
							validate();
		
						}
					}
				});
				jp.add(jb[i]);
			}
		add(jp);
	}
	public void displayPanel(JButton btn)
	{
		//System.out.println("hello");
		border = new LineBorder(Color.RED);
		disPan.setBorder(border);
		disPan.add(btn);
	}
	int generateNum()
	{
		Random r=new Random();
		int x=r.nextInt(5);
		return x;
	}
	public String result()
	{
		boolean b = true;
		for(int i=0;i<5;i++)
		{
		if(rnum[i] == a[i]) b = true;
		else b=false;
		}
		if(b) return("YOU WON");
		else return("YOU LOSE BETTER LUCK NEXT TIME");
	}	
	public static void main(String ar[])
	{
		new UseRandomGame3();
	}
}